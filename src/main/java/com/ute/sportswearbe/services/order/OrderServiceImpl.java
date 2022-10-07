package com.ute.sportswearbe.services.order;

import com.ute.sportswearbe.dtos.OrderDto;
import com.ute.sportswearbe.entities.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    @Override
    public List<Order> getAllOrder() {
        return null;
    }

    @Override
    public Page<Order> getOrderPaging(String search, int page, int size, String sort, String column) {
        return null;
    }

    @Override
    public Order getOrderById(String id) {
        return null;
    }

    @Override
    public List<Order> getOrderByUserId(String id) {
        return null;
    }

    @Override
    public Order createNewOrder(String userID, Principal principal, OrderDto dto) {
        return null;
    }

    @Override
    public Order disableOrder(String userID, Principal principal) {
        return null;
    }

    @Override
    public Order callOffOrder(String userID, String orderID, Principal principal) {
        return null;
    }

    @Override
    public Order changeStatusOrder(String id) {
        return null;
    }

    @Override
    public Order changeStatusPayOfOrder(String id) {
        return null;
    }

    @Override
    public Order changeShippingOrder(String id) {
        return null;
    }

    @Override
    public Order changeDeliveredOrder(String id) {
        return null;
    }

    @Override
    public List<Order> getListOrderByUserIdWithIf(String id, int status, Principal principal) {
        return null;
    }

    @Override
    public List<Order> getOrderSuccessByUserId(String id) {
        return null;
    }

    @Override
    public Order getOrderByIdForPayment(String id) {
        return null;
    }

    @Override
    public Order save(Order order) {
        return null;
    }
}
