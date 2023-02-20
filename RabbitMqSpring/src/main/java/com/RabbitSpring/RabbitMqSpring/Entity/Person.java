package com.RabbitSpring.RabbitMqSpring.Entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person implements Serializable {
    private Long id;

    private String name;
}
