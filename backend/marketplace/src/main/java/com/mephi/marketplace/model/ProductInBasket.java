package com.mephi.marketplace.model;

public class ProductInBasket {
    private Product product;
    private Basket basket;
    private Long productCount;

    public ProductInBasket(Product product, Basket basket, Long productCount) {
        this.product = product;
        this.basket = basket;
        this.productCount = productCount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Long getProductCount() {
        return productCount;
    }

    public void setProductCount(Long productCount) {
        this.productCount = productCount;
    }
}
