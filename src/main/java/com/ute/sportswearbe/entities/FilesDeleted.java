package com.ute.sportswearbe.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FilesDeleted {
    @Id
    private String id;

    private String title;

    private String uId;

    private List<String> links = new ArrayList<>();
}
