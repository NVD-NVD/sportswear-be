package com.ute.sportswearbe.entities;

import com.ute.sportswearbe.entities.embedded.EmbeddedAddress;
import com.ute.sportswearbe.entities.embedded.EmbeddedPayment;
import com.ute.sportswearbe.entities.embedded.EmbeddedPrice;
import com.ute.sportswearbe.entities.embedded.EmbeddedProductsInOrder;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "orders")
public class Order {
    @Id
    private String id;

    @DBRef
    private User user;

    private List<EmbeddedProductsInOrder> productsInOrder = new ArrayList<>();

    @NonNull
    private EmbeddedPrice total;

    private EmbeddedAddress address;

    private String phone;

    private String note;

    private String processing;

//    private boolean status = true; // trạng thái của order
//
//    private boolean pay = false; // trạng thái thanh toán
//
//    private boolean shipping = false; // trạng thái vận chuyển hàng (chưa giao hàng(false) or đang giao hàng(true))
//
//    private boolean delivered = false; // trạng thái hoàn tất việc giao hàng

    private EmbeddedPayment payment;

    private Date createdOn;

    private Date updateOn;

    private boolean enable = true;
}
