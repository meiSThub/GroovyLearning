package com.plum.groovy.variable


def num =1.34
def result
switch (num){
    case 'foo':
        result="found foo"
        break
    case [1.34,5,6,"inlist"]:// 列表
        result="list"
        break
    case 13..30:// 范围，这也是groovy中的一种数据类型
        result="range"
        break
    case Integer:
        result='integer'
        break
    case BigDecimal:
        result='big integer'
        break
    default:result='result'
        break
}

println(result)

// 对范围对循环
def  sum=0;
for (i in 1..9){
    sum+=i;
}
println sum

sum=0;
// 对列表List的遍历
for (i in [1,2,3,4,5,6,7,8,9]){
    sum+=i;
}
println sum

sum=0;
// 对map的遍历，不用通过迭代器遍历了
for (i in ["lili":1,"tom":3,"marry":8]){ // i代表的就是map中的节点对象
    sum+=i.value
}
println sum


// 计算阶乘方法
int fab(int  number){
    int result=1;
    1.upto(number,{num-> result=result*num});
    return result;
}

int x= fab(5)

println x

int  cal(int  number){
    int result=0;
    number.times {
        num->result+=num
    }
    return result
}
//
//println cal(100)

//def closer = {
//    print("hello ${it}")
//}
//closer();

def closer={String name ,int age->// 只有一个参数，则参数name会覆盖隐藏参数it
    print("hello ${name}, age is ${age}")// 默认的隐含参数it
}
//closer();// 闭包的调用 ，输出：hello null
closer("groovy",10) // 输出：hello groovy

closer.call("groovy",10)

print("\n")
// 闭包与字符串的结合使用
def str = "the 2 and 3 is 5"

// each闭包，遍历字符串的每个字符
println str.each {
    print(it)
} // 输出：the 2 and 3 is 5the 2 and 3 is 5

// find闭包，查找符合条件的第一个
println str.find{s->
    s.isNumber() // 输出：2
}

// findAll闭包，查找所有符合条件的
def list= str.findAll{
    s-> s.isNumber()
}
println list.toListString() // 输出：[2, 3, 5]

// any闭包，判断是否包含符合条件的
println str.any{s-> // 输出：true
    s.isNumber()
}

// every闭包，判断是否都符合条件
println str.every { s-> // 输出：false
        s.isNumber()
}

// collect闭包，把字符串中的每个字符都按给定的操作处理
def list2=str.collect{s->
    s.toUpperCase()
}
println list2.toListString()  // 输出：[T, H, E,  , 2,  , A, N, D,  , 3,  , I, S,  , 5]

println("-------------------")

/**
 * 闭包的三个重要变量：this，owner，delegate
 */

def scriptCloser={
    println "scriptCloser this:"+this // 代表闭包定义处的类，
    println "scriptCloser owner:"+owner // 代表闭包定义处的类或者对象
    println "scriptCloser delegate:"+delegate// 代表任意对象，默认与owner一致
}
scriptCloser.call()
/** 输出：
 * scriptCloser this:com.plum.groovy.variable.StructStudy@4975dda1
 * scriptCloser owner:com.plum.groovy.variable.StructStudy@4975dda1
 * scriptCloser delegate:com.plum.groovy.variable.StructStudy@4975dda1
 */
println("---------------------\n")

class Person{
    def static classCloser={
        println "classCloser this:"+this // 代表闭包定义处的类，
        println "classCloser owner:"+owner // 代表闭包定义处的类或者对象
        println "classCloser delegate:"+delegate// 代表任意对象，默认与owner一致
    }

    def static say(){
        def classCloser={
            println "methodClassCloser this:"+this // 代表闭包定义处的类，
            println "methodClassCloser owner:"+owner // 代表闭包定义处的类或者对象
            println "methodClassCloser delegate:"+delegate// 代表任意对象，默认与owner一致
        }
        classCloser.call()
    }
}

Person.classCloser.call()
Person.say()
/**输出：代表的是类
 * classCloser this:class com.plum.groovy.variable.Person
 * classCloser owner:class com.plum.groovy.variable.Person
 * classCloser delegate:class com.plum.groovy.variable.Person
 * methodClassCloser this:class com.plum.groovy.variable.Person
 * methodClassCloser owner:class com.plum.groovy.variable.Person
 * methodClassCloser delegate:class com.plum.groovy.variable.Person
 */

println("----\n")

// 闭包中定义闭包
def nestCloser={
    def innerCloser={
        println "innerClassCloser this:"+this // 代表的是StructStudy对象
        println "innerClassCloser owner:"+owner // 代表是nestCloser闭包对象
        println "innerClassCloser delegate:"+delegate// 代表是nestCloser闭包对象
    }
    innerCloser.delegate =new Person()
    innerCloser.call()
}

nestCloser.call()
/** 输出：
 * innerClassCloser this:com.plum.groovy.variable.StructStudy@2e362407
 * innerClassCloser owner:com.plum.groovy.variable.StructStudy$_run_closure9@2a5abd3c
 * innerClassCloser delegate:com.plum.groovy.variable.StructStudy$_run_closure9@2a5abd3c
 */
/**
 * 总结：
 * 1. 在大多数情况下，this，owner，delegate所指向的类或对象一致
 * 2. 我们在闭包中，定义一个闭包的时候，this指向的是当前类或者对象，owner和delegate所指向的是外层闭包
 * 3. 如果我们修改类delegate的值的话，那么delegate则与owner和this的值不一样
 * 4. 即this，owner值不可修改，delegate值可以修改
 */

println("-------\n")
/**
 * 闭包的委托策略
 * */

class Student{
    String name
    def pretty={"my name is ${name}"}

    @Override
    String toString() {
        pretty.call()
    }
}

class Teacher{
    String name
    String name1
}

def stu=new Student(name: "Search")
def tea=new Teacher(name: "Mr Wang")
println stu.toString() // 输出：my name is Search
// 修改代理对象
stu.pretty.delegate = tea
//println stu.toString() // 输出：my name is Search
// 修改代理策略
stu.pretty.resolveStrategy = Closure.DELEGATE_FIRST // 委托优先，即闭包先去委托中找，如果找到了name，则选用，没有则又取本身的
// 委托优先,即先从委托：delegate中去寻找变量和方法，如果没有找到，则去owner中寻找
println stu.toString() // 输出：my name is Mr Wang
// 说明在修改闭包的delegate对象
stu.pretty.resolveStrategy = Closure.DELEGATE_ONLY // 只在委托对象中找，找不到，则报错
println stu.toString()
/**
* 委托策略总结：
 * 1. Closure.DELEGATE_FIRST，委托优先：即先从委托：delegate中去寻找变量和方法，如果没有找到，则去owner中寻找
 * 2. Closure.DELEGATE_ONLY,只在委托对象中找，即只在委托对象delegate中寻找变量和方法，如果没有找到，则报错
 * 3. OWNER_ONLY,只在owner对象中找
 * 4. OWNER_FIRST,owner优先，
* */