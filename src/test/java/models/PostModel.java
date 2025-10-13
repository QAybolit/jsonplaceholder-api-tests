package models;

import lombok.Data;

@Data
public class PostModel {

    private int id;
    private String title;
    private String body;
    private int userId;
}
