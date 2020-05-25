package com.plum.groovy.object

/**
 * trait类型相当于java中的抽象类
 */
trait DefaultAction {

    abstract void eat()

    void play() {
        println('i can play')
    }
}