package com.reljicd.service.impl;

import com.reljicd.exception.NotEnoughProductsInStockException;
import com.reljicd.model.Basket;
import com.reljicd.model.OrderOfSystem;
import com.reljicd.model.Product;

import com.reljicd.model.ProductInBasket;
import com.reljicd.repository.BasketRepository;
import com.reljicd.repository.OrderOfSystemRepository;
import com.reljicd.repository.ProductInBasketRepository;
import com.reljicd.repository.ProductRepository;
import com.reljicd.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;

/**
 * Shopping Cart is implemented with a Map, and as a session bean
 *
 * @author Dusan
 */
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;
    private final BasketRepository basketRepository;
    private final ProductInBasketRepository productInBasketRepository;
    private final OrderOfSystemRepository orderOfSystemRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartServiceImpl(ProductRepository productRepository,
                                   ProductInBasketRepository productInBasketRepository,
                                   OrderOfSystemRepository orderOfSystemRepository,
                                   BasketRepository basketRepository) {
        this.productRepository = productRepository;
        this.basketRepository = basketRepository;
        this.productInBasketRepository = productInBasketRepository;
        this.orderOfSystemRepository = orderOfSystemRepository;
    }

//    @Autowired
//    public ShoppingCartServiceImpl(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) {
//        Basket basket = basketRepository.findByCustomerID(product.getSeller().getId());
//        if (basket== null){
//            basket = new Basket(product.);
//        }

        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }

    /**
     * If product is in the map with quantity > 1, just decrement quantity by 1.
     * If product is in the map with quantity 1, remove it from map
     *
     * @param product
     */
    @Override
    public void removeProduct(Product product) {
        if (products.containsKey(product)) {
            if (products.get(product) > 1)
                products.replace(product, products.get(product) - 1);
            else if (products.get(product) == 1) {
                products.remove(product);
            }
        }
    }

    /**
     * @return unmodifiable copy of the map
     */
    @Override
    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     */
    @Override
    public void checkout(String customerName, String address, String paymentType, Date dateFrom, Date dateTo) {

        //size must be equal 1
        Collection<Basket> allActiveBaskets = basketRepository.findAllActiveBaskets(customerName);

        if (allActiveBaskets.size() == 0) {
            throw new RuntimeException("Basket collection can't be empty while creating the order");
        }

        Collection<ProductInBasket> productInBasketCollection = productInBasketRepository.findProductInBasketByBasketId(1L);

        int orderPrice = 0;

        int newCount;
        for (ProductInBasket productInBasket : productInBasketCollection) {
            orderPrice += productInBasket.getPriceAtNow().intValue() * productInBasket.getProductCount();
            Product product = productRepository.findOne(productInBasket.getProductId());
            newCount = product.getQuantity() - productInBasket.getProductCount().intValue();
            if (newCount < 0) {
                throw new RuntimeException("Not enough amount of the product " + product.getName());
            }
            product.setQuantity(newCount);
            productRepository.save(product);
        }

        Basket basket = allActiveBaskets.iterator().next();
        //@anna look
        basket.setActive(false);

        OrderOfSystem orderOfSystem = new OrderOfSystem();
        orderOfSystem.setPrice(new BigDecimal(orderPrice));
        orderOfSystem.setBasket(basket);
        orderOfSystem.setDeliveryAddress(address);
        OrderOfSystem.PayingType payingType = getPayingType(paymentType);
        orderOfSystem.setPayingType(getPayingType(paymentType));
        orderOfSystem.setDeliveryTimeFrom(dateFrom);
        orderOfSystem.setDeliveryTimeTo(dateTo);

        if (payingType != OrderOfSystem.PayingType.CASH) {
            orderOfSystem.setPayingTime(new Date());
        }


        orderOfSystemRepository.save(orderOfSystem);

//        Product product;
//        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
//            // Refresh quantity for every product before checking
//            product = productRepository.findOne(entry.getKey().getId());
//            if (product.getQuantity() < entry.getValue())
//                throw new NotEnoughProductsInStockException(product);
//            entry.getKey().setQuantity(product.getQuantity() - entry.getValue());
//        }
//        productRepository.save(products.keySet());
//        productRepository.flush();
//        products.clear();

    }

    private OrderOfSystem.PayingType getPayingType(String rawPayingType) {
        switch (rawPayingType) {
            case "BANK_CARD":
                return OrderOfSystem.PayingType.BANK_CARD;
            case "CASH":
                return OrderOfSystem.PayingType.CASH;
            case "PAY_PAL":
                return OrderOfSystem.PayingType.PAY_PAL;
            default:
                return OrderOfSystem.PayingType.CASH;
        }

    }

    @Override
    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(entry -> entry.getKey().getPrice().multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }
}
