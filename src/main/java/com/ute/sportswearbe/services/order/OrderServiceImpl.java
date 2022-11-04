package com.ute.sportswearbe.services.order;

import com.ute.sportswearbe.dtos.OrderDto;
import com.ute.sportswearbe.entities.Order;
import com.ute.sportswearbe.entities.Product;
import com.ute.sportswearbe.entities.User;
import com.ute.sportswearbe.entities.embedded.EmbeddedPrice;
import com.ute.sportswearbe.entities.embedded.EmbeddedProductsInOrder;
import com.ute.sportswearbe.exceptions.InvalidException;
import com.ute.sportswearbe.exceptions.NotFoundException;
import com.ute.sportswearbe.repositories.OrderRepository;
import com.ute.sportswearbe.services.product.ProductService;
import com.ute.sportswearbe.services.user.UserService;
import com.ute.sportswearbe.utils.PageUtils;
import com.ute.sportswearbe.utils.enums.EnumProcessing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public Page<Order> getOrdersPaging(String search, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return orderRepository.getOrdersPaging(search, pageable);
    }

    @Override
    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Order có id %s không tồn tại", id)));
    }

    @Override
    public List<Order> getOrderByUserId(String userId) {
        return orderRepository.getOrderByUserId(userId);
    }

    @Override
    public Page<Order> getAllOrderByUserIdPaging(String userId, int page, int size, String sort, String column) {
        Pageable pageable = PageUtils.createPageable(page, size, sort, column);
        return orderRepository.getAllOrderByUserIdPaging(userId, pageable);
    }

    @Override
    public Order createNewOrder(Principal principal, OrderDto dto) {
        User user = userService.getUserByPrincipal(principal);
        if (user == null){
            throw new NotFoundException("Không tìm thấy user");
        }
        Order order = new Order();
        order.setUser(user);

        List<EmbeddedProductsInOrder> productsInOrder = new ArrayList<>();
        dto.getProductsInOrder().forEach(
                e -> {
                    Product product = productService.getProductById(e.getProductID());
                    EmbeddedProductsInOrder embeddedProductsInOrder = new EmbeddedProductsInOrder();
                    embeddedProductsInOrder.setProduct(product);
                    embeddedProductsInOrder.setQuantity(e.getQuantity());
                    float price = product.getPrice().getPrice();
                    float total = (price - price*(product.getDiscount()/100)) * e.getQuantity();
                    embeddedProductsInOrder.setTotal(total);
                    productsInOrder.add(embeddedProductsInOrder);
                });

        order.setProductsInOrder(productsInOrder);
        float total = 0.0F;
        for (EmbeddedProductsInOrder embeddedBookInOrder : productsInOrder) {
            total += embeddedBookInOrder.getTotal();
        }
        EmbeddedPrice price = new EmbeddedPrice();
        price.setPrice(total);
        order.setTotal(price);
        if (dto.getAddressForShipping() != null)
            order.setAddress(dto.getAddressForShipping());
        order.setPhone(dto.getPhone());
        order.setNote(dto.getNote());

        order.setCreatedOn(new Date());
        order.setUpdateOn(new Date());
        order.setPayment(null);
        order.setProcessing(EnumProcessing.Chua_Xu_Ly.name());


        order.setEnable(true);

        return orderRepository.save(order);
    }

    @Override
    public Order disableOrder(Principal principal, String orderID) {
        User user = userService.getUserByPrincipal(principal);
        if (user == null){
            throw new NotFoundException("Không tìm thấy user");
        }
        Order order = getOrderById(orderID);
        order.setEnable(false);
        return save(order);
    }

    @Override
    public Order callOffOrder(String userID, String orderID, Principal principal) {
        return null;
    }

    @Override
    public Order changeStatusOrder(String id) {
        Order order = getOrderById(id);
        order.setEnable(!order.isEnable());
        return save(order);
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
        return orderRepository.save(order);
    }

//    private Order convertOrderDto(OrderDto dto){
//        Order order = new Order();
//        order.setBooksInOrder(order.getBooksInOrder());
//        if (dto.getAddressForShipping() != null)
//            order.setAddress(dto.getAddressForShipping());
//        order.setNote(dto.getNote());
//        return order;
//    }
}
