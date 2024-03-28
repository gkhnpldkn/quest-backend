package com.gokhan.dkdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "post")
@Data
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne(fetch = FetchType.EAGER)
     @JoinColumn(name = "user_id",nullable = false)// hangi kolona bağladığımızı yazıypruz
     @OnDelete(action = OnDeleteAction.CASCADE)       // bir useri sldiğimizde buna ait olan yorumlar da silinsin

     User user;

//    @Lob
//    @Column(columnDefinition = "text")
    String text;

    String title;
}
