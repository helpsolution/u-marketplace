package com.reljicd.service.impl;

import com.reljicd.exception.NotEnoughProductsInStockException;
import com.reljicd.model.*;
import com.reljicd.repository.*;
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
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderOfSystemRepository orderOfSystemRepository;

    @Autowired
    private ProductInBasketRepository productInBasketRepository;

    private HashMap<Product, Integer> products = new HashMap();

    /**
     * If product is in the map just increment quantity by 1.
     * If product is not in the map with, add it with quantity 1
     *
     * @param product
     */
    @Override
    public void addProduct(Product product) {

        Basket basket = basketRepository.findOneActiveBasket(SecurityContextHolder.getContext().getAuthentication().getName());
        if (basket == null) {
            basket = new Basket();
            basket.setActive(true);
            basket.setCustomer(customerRepository.findCustomerByName(SecurityContextHolder.getContext().getAuthentication().getName()));
            basket = basketRepository.save(basket);
        }

        Collection<ProductInBasket> productInBasketCollection = productInBasketRepository.findProductInBasketByBasketId(basket.getId());

        if (productInBasketCollection.size() != 0) {
            Optional<ProductInBasket> productExists = productInBasketCollection.stream().filter(productInBasket -> productInBasket.getProductId().equals(product.getId())).findFirst();
            if (productExists.isPresent()) {
                productExists.get().setProductCount(productExists.get().getProductCount() + 1);
            } else {
                ProductInBasket productInBasket = new ProductInBasket();
                productInBasket.setProductId(product.getId());
                productInBasket.setBasketId(basket.getId());
                productInBasket.setPriceAtNow(product.getPrice());
                productInBasket.setProductCount(1L);
                productInBasketCollection.add(productInBasket);
                productInBasketRepository.save(productInBasket);
            }
        } else {
            ProductInBasket productInBasket = new ProductInBasket();
            ProductInBasketPK productInBasketPK = new ProductInBasketPK();
            productInBasketPK.setBasketId(basket.getId());
            productInBasketPK.setProductId(product.getId());
            productInBasket.setPriceAtNow(product.getPrice());
            productInBasket.setProductCount(1L);
            productInBasket.setBasketId(basket.getId());
            productInBasket.setProductId(product.getId());
            productInBasketCollection.add(productInBasket);
            productInBasketRepository.save(productInBasket);
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
        Basket basket = basketRepository.findOneActiveBasket(SecurityContextHolder.getContext().getAuthentication().getName());
        if (basket == null) {
            return Collections.EMPTY_MAP;
        }
        Collection<ProductInBasket> productInBaskets = productInBasketRepository.findProductInBasketByBasketId(basket.getId());
        Map<Product, Integer> result = new HashMap<>();
        for (ProductInBasket productInBasket: productInBaskets) {
            result.put(productRepository.findById(productInBasket.getProductId()).get(), productInBasket.getProductCount().intValue());
        }
        return result;
    }

    /**
     * Checkout will rollback if there is not enough of some product in stock
     */
    @Override
    public void checkout(String customerName, String address, String paymentType, Date dateFrom, Date dateTo) {

        //size must be equal 1
        Basket basket = basketRepository.findOneActiveBasket(customerName);

        if (basket == null) {
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
