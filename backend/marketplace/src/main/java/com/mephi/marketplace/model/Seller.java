package com.mephi.marketplace.model;

import java.time.LocalDateTime;

public class Seller extends UserOfSystem{
    private String companyName;
    private String inn;
    private String ogrn;
    private  String legalAddress;
    private String actualAddress;

    public Seller() {
    }

    public Seller(Long id, LocalDateTime createdAt, String username, String password, String phone, String email, String companyName, String inn, String ogrn, String legalAddress, String actualAddress) {
        super(id, createdAt, username, password, phone, email);
        this.companyName = companyName;
        this.inn = inn;
        this.ogrn = ogrn;
        this.legalAddress = legalAddress;
        this.actualAddress = actualAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    public String getActualAddress() {
        return actualAddress;
    }

    public void setActualAddress(String actualAddress) {
        this.actualAddress = actualAddress;
    }
}
