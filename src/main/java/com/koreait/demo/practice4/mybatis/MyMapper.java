package com.koreait.demo.practice4.mybatis;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

public interface MyMapper {
    int selectOne();
    String selectA1Name();
    String selectBookName(String bookName);
    Book selectBookByISBN(String isbn);
    List<Book> selectAllBooks();



}
