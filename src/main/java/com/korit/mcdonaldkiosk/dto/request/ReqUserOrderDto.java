package com.korit.mcdonaldkiosk.dto.request;

import com.korit.mcdonaldkiosk.entity.Order;
import com.korit.mcdonaldkiosk.entity.OrderDetail;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReqUserOrderDto {
    private int menuId;
    private int menuCount;

    public OrderDetail toEntity(int orderId) {
        return OrderDetail.builder()
                .orderId(orderId)
                .menuId(menuId)
                .menuCount(menuCount)
                .build();
    }
}
