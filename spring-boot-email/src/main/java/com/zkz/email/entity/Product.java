package com.zkz.email.entity;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Jason Zhuang on 17/11/21.
 */

@Data
@Entity
@Table(name = "product")
@RequiredArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private Float price;

    @NonNull
    private int count;

}