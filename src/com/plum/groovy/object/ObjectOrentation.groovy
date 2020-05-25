package com.plum.groovy.object

def person = new Person(name: "Tom", age: 22)
//println "name is $person.name " +// person.name 操作的不是person的name属性，最终点用的还是getName方法，groovy默认创建getName方法
//        "age:$person.age"

println person.cry()

// 为类动态添加一个属性
Person.metaClass.sex = 'male'
def per = new Person()
println per.sex

// 为类动态添加一个方法
Person.metaClass.setUpperCase = {
    sex.toUpperCase()
}
def person2 = new Person();
println person2.setUpperCase()

// 为类动态添加静态方法
Person.metaClass.static.createPerson = {
    name, age ->
        return new Person(name: name, age: age)
}
def person3 = Person.createPerson("Tom", 22)
println "name is $person3.name,age is $person3.age"