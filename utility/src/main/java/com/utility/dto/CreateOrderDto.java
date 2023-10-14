package com.utility.dto;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class CreateOrderDto {

    private Long userId;
    private Date createdAt;
    private List<Long> productIds;
}
