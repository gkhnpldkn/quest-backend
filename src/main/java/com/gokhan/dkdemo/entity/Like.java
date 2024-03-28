package com.gokhan.dkdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "p_like")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id",nullable = false)// hangi kolona bağladığımızı yazıypruz
    @OnDelete(action = OnDeleteAction.CASCADE)       // bir user'i sldiğimizde buna ait olan yorumlar da silinsin
    @JsonIgnore
    Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)// hangi kolona bağladığımızı yazıypruz
    @OnDelete(action = OnDeleteAction.CASCADE)       // bir useri sldiğimizde buna ait olan yorumlar da silinsin
    @JsonIgnore
    User user;
}
