package com.plum.groovy.object

/**
 * 接口定义,接口中不允许定义非public类型的方法
 */
interface IAction {

    void eat()

    void play()

}

/**
 * 接口的实现
 */
class PersonAction implements IAction {

    @Override
    void eat() {

    }

    @Override
    void play() {

    }
}