package com.ute.sportswearbe.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/3/2022
 * Time: 9:33 PM
 * Filename: Transaction
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "transactions")
public class Transaction {
    @Id
    private String id;

    private Date createdOn;

    private Date updateOn;

    private boolean enable = true;
}
