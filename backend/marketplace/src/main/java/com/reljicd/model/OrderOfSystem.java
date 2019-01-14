package com.reljicd.model;

import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author aguminskaya
 * @since 2019-01-14
 */
@Entity(name = "order_of_system")
@Data
public class OrderOfSystem {

    public enum PayingType {
        CASH("CASH"),
        BANK_CARD("BANK_CARD"),
        PAY_PAL("PAY_PAL");
        protected String value;

        PayingType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "basket_id")
    private Basket basket;

    @Column(name = "delivery_address")
    private String deliveryAddress;

    @Column(name = "delivery_time_from")
    private Date deliveryTimeFrom;

    @Column(name = "delivery_time_to")
    private Date deliveryTimeTo;

    @Column(name = "paying_time")
    private Date payingTime;

    @Column(name = "paying_type", columnDefinition = "payingtype")
    @Type(type="com.reljicd.model.EnumType")
    private PayingType payingType;
}