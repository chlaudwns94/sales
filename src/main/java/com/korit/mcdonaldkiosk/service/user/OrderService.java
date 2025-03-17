package com.korit.mcdonaldkiosk.service.user;

import com.korit.mcdonaldkiosk.dto.request.ReqUserOrderDto;
import com.korit.mcdonaldkiosk.entity.Order;
import com.korit.mcdonaldkiosk.entity.OrderDetail;  // OrderDetail 추가
import com.korit.mcdonaldkiosk.repository.user.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public void orderMenu(List<ReqUserOrderDto> reqUserOrderDtos) {
        // 1. 주문을 생성하고 orderId를 받음
        Order order = new Order();
        orderRepository.addOrder(order);

        if (order.getOrderId() == null) {
            throw new RuntimeException("주문 ID 생성 실패");
        }

        // 2. 생성된 orderId를 주문 상세 정보에 추가
        List<OrderDetail> orderDetails = reqUserOrderDtos.stream()
                .map(dto -> new OrderDetail(0, order.getOrderId(), dto.getMenuId(), dto.getMenuCount(), null))
                .collect(Collectors.toList());

        // 3. 주문 상세 정보 저장
        orderRepository.addOrderDetails(orderDetails);
    }
}

