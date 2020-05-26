package com.plum.groovy.json

import com.plum.groovy.object.Person
import groovy.json.JsonOutput
import groovy.json.JsonSlurper

// groovy json详解

// 1.把对象转换成json字符串
def list = [new Person(name: 'Tom', age: 23),
            new Person(name: 'John', age: 33)]
// 直接调用api转json
def json = JsonOutput.toJson(list)
println json // 普通方式打印 // [{"age":23,"name":"Tom"},{"age":33,"name":"John"}]
println JsonOutput.prettyPrint(json) // 把json格式化之后，再打印
/*
[
    {
        "age": 23,
        "name": "Tom"
    },
    {
        "age": 33,
        "name": "John"
    }
]
* */

// 2.把json字符串转成对象
def jsonStr = '[{"age":23,"name":"Tom"},{"age":33,"name":"John"}]'
def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText(jsonStr)
println object.class
def person = object[0]
// groovy中，json转化整实体对象的时候，不需要指明转换的类型，转换后的数据可以直接引用，这是非常方便的一点
// 不用我们去定义各种实体类了
println "name is ${person.name} age is ${person.age}"


/**模拟从网络请求数据，并把json数据转换成实体类*/

def getNetworkData(String url) {
    // 发送http请求
    def connection = new URL(url).openConnection()
    connection.setRequestMethod('GET')
    connection.connect()
    def response = connection.content.text
    println response
    // 将json转换成实体对象
    def slurper = new JsonSlurper();
    slurper.parseText(response)
}

def response = getNetworkData('https://www.youdao.com/w/eng/json/#keyfrom=dict2.index')

