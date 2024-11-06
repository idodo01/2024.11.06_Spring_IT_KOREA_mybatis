package com.koreait.demo.practice4.mybatis;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Book {
    private String isbn;
    private String name;
    private Integer price; // int도 가능
}
