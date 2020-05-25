package com.plum.groovy.object

/**
 * 1.Groovy 类默认都是public的
 */
class Person {
    /**
     * Groovy会为类创建构成函数，
     * 如：def person = new Person(name: "Tom", age: 22)
     */

    /**
     * 熟悉默认也是public的
     * groovy默认创建getName方法，而且通过点访问属性的时候，调用的还是getName方法
     */
    String name
    Integer age

    /**
     * 方法定义
     * 方法默认也是public的
     * @param years
     * @return
     */
    def increaseAge(int years) {
        this.name += years
    }

    def invokeMethod(String name, Object args) {
        return "this method is $name,the paramter is $args"
    }
}
