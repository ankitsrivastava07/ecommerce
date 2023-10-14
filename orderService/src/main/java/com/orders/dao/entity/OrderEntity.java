
package com.orders.dao.entity;
import lombok.Data;

import java.util.Date;

@Data
public class OrderEntity {
    private Long id;
    private Long userId;

    private Long productId;
    private Date createdAt;
    private Date updatedAt;
    private double price;
}

