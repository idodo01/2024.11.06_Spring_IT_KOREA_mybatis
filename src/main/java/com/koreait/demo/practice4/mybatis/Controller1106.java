package com.koreait.demo.practice4.mybatis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Controller1106 {
    @Autowired
    private MyMapper myMapper;

    @GetMapping("/one")
    public void get_one(@RequestParam("bookName") String bookName){
        int result = myMapper.selectOne();
        System.out.println(result);

        String name = myMapper.selectA1Name();
        System.out.println(name);

        bookName = myMapper.selectBookName(bookName);
        System.out.println(bookName);
    }





}
