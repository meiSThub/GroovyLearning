package com.plum.groovy.object.global

class Entry {
    static void main(String[] agrs) {
        println('应用程序正在启动。。。')
        ApplicationManager.init()
        println('应用程序初始化完成。')
        def person = PersonFactory.createPerson("Tom", 33)
        println "name is ${person.name} age is ${person.age}"

        def name="abcd"
        println String.reverse2(name)
    }
}
