package com.ute.sportswearbe.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.crypto.Data;
import java.util.List;

/**
 * Created by: IntelliJ IDE
 * User: NVD-NVD
 * Date: 9/29/2022
 * Time: 11:18 PM
 * Filename: EmbeddedReviews
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmbeddedReviews {
    private List<Like> likes;

    private List<Comments> comments;

    private long likeTotal;

    private long commentTotal;
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Like{
    private String userID;
    private String username;
    private String linkAvatar;
    private boolean like = true;

}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class Comments{
    private String userID;
    private String username;
    private String linkAvatar;
    private List<EmbeddedComment> comments;
    private long commentTotal;
}
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
class EmbeddedComment{
    private String comment;

    private Data time;
}

