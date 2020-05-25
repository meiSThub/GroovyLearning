package com.plum.groovy.object.global

import com.plum.groovy.object.Person

class PersonFactory {

    /**
     * 创建对象的工厂方法
     * @param name
     * @param age
     * @return
     */
    static Person createPerson(String name, int age) {
        return Person.createPerson(name, age)
    }
}
