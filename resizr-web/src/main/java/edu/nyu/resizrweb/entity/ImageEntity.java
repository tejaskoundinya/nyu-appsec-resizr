package edu.nyu.resizrweb.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User user;

    private String uploadUrl;

    private String resizedUrl;
}
