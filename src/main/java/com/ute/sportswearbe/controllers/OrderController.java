package com.ute.sportswearbe.controllers;

import com.ute.sportswearbe.dtos.OrderDto;
import com.ute.sportswearbe.entities.Order;
import com.ute.sportswearbe.services.order.OrderService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/rest/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ApiOperation(value = "test")
    @GetMapping("/test")
    public ResponseEntity<String> testOrder(){
        return new ResponseEntity<>("test order", HttpStatus.OK);
    }

    @ApiOperation(value = "Get tất cả Order không phân trang cho admin", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<Order>> getAllOrder(){
        return new ResponseEntity<>(orderService.getAllOrder(), HttpStatus.OK);
    }

    @ApiOperation(value = "Get list Order có phân trang cho admin", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/paging")
    public ResponseEntity<Page<Order>> getOrdersPaging(
            @RequestParam(name = "search", required = false, defaultValue = "") String search,
            @RequestParam(name = "page", required = false, defaultValue = "${paging.default.page}") int page,
            @RequestParam(name = "size", required = false, defaultValue = "${paging.default.size}") int size,
            @RequestParam(name = "sort", required = false, defaultValue = "asc") String sort,
            @RequestParam(name = "column", required = false, defaultValue = "orderDate") String column){
        return new ResponseEntity<>(
                orderService.getOrdersPaging(search,page,size,sort,column), HttpStatus.OK);
    }

    @ApiOperation(value = "Get list order by userId")
    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
    @GetMapping("/{id}")
    public ResponseEntity<List<Order>> getOrderByUserID(
            @PathVariable(value = "id") String id){
        return new ResponseEntity<>(orderService.getOrderByUserId(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Get list order success by userId")
    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
    @GetMapping("/success/{id}")
    public ResponseEntity<List<Order>> getOrderSuccessByUserID(
            @PathVariable(value = "id") String id){
        return new ResponseEntity<>(orderService.getOrderSuccessByUserId(id), HttpStatus.OK);
    }

//    @ApiOperation(value = "Get list order by userId ")
//    @PreAuthorize("hasAnyRole('ADMIN','MEMBER')")
//    @GetMapping
//    public ResponseEntity<List<Order>> getAllOrderByUser(
//            @RequestParam(value = "userId", required = true) String id,
//            @RequestParam(value = "statusId", required = true) int status,
//            Principal principal){
//        return new ResponseEntity<>(orderService.getListOrderByUserIdWithIf(id, status, principal), HttpStatus.OK);
//    }

    @ApiOperation(value = "User tạo đơn đặt hàng", notes = "Member")
    @PreAuthorize("hasRole('MEMBER')")
    @PostMapping
    public ResponseEntity<Order> createNewOrder(Principal principal, @RequestBody OrderDto dto){
        return new ResponseEntity<>(orderService.createNewOrder(principal, dto), HttpStatus.OK);
    }

    @ApiOperation(value = "User hủy đơn hàng(nếu đơn hàng chưa được xử lý)", notes = "Admin")
    @PreAuthorize("hasRole('MEMBER')")
    @DeleteMapping("/{userID}/{orderID}")
    public ResponseEntity<Order> callOffOrder(
            @PathVariable(value = "userID") String userID,
            @PathVariable(value = "orderID") String orderID,
            Principal principal){
        return new ResponseEntity<>(orderService.callOffOrder(userID, orderID, principal), HttpStatus.OK);
    }

    @ApiOperation(value = "Admin thay đổi trang thái đơn hàng", notes = "Admin")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Order> changeStatusOrder(@PathVariable(value = "id") String id){
        return new ResponseEntity<>(orderService.changeStatusOrder(id), HttpStatus.OK);
    }

//    @ApiOperation(value = "Admin thay đổi trang thái thanh toán", notes = "Admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/status/{id}")
//    public ResponseEntity<Order> changeStatusPayOfOrder(@PathVariable String id){
//        return new ResponseEntity<>(orderService.changeStatusPayOfOrder(id), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Admin thay đổi trang thái vẫn chuyển hàng(shipping)", notes = "Admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/shipping/{id}")
//    public ResponseEntity<Order> changeShippingOrder(@PathVariable String id){
//        return new ResponseEntity<>(orderService.changeShippingOrder(id), HttpStatus.OK);
//    }
//
//    @ApiOperation(value = "Admin thay đổi trang thái đã giao hàng(delivered)", notes = "Admin")
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/delivered/{id}")
//    public ResponseEntity<Order> changeDeliveredOrder(@PathVariable String id){
//        return new ResponseEntity<>(orderService.changeDeliveredOrder(id), HttpStatus.OK);
//    }
}
