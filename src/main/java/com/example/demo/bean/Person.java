package com.example.demo.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by lind on 2020/8/17.
 */
@Getter
@Setter
@ToString
public class Person {
    private String name ;
    private Integer age ;
    private String bz;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }


}
