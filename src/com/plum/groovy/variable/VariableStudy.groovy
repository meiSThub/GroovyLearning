package com.plum.groovy.variable

// 介绍groovy的数据类型

double y =3.14

println y.class

//////////////////字符串的定义方式////////////////////////

// 1.单引号定义字符串，不会保留格式
def name ='Tom'
println name.class
println name

// 2.三个单引号定义的字符串，会保留字符串的格式,如，会分三行输入，保留了格式
def thupleName='''line one
line two
line three
'''
println thupleName.class
println thupleName

// 3.双引号定义字符串,可以加入表达式，此时字符串的类型是GStringImpl，即GString的实现类
def doubleName="My name is ${name}"
println doubleName.class
println doubleName

name="plum"
String myName="my name is ${name}"
println myName.class
println myName

println '-------------\n'
//////////////////字符串的方法////////////////////////

// 1.字符串扩充
def str="groovy"
println str.center(5,'a')
println str.padLeft(8,'a')
println str.padRight(8,'a')

// 2.字符串比较
def str2="Hello"
println str > str2

// 3. 字符串字符截取
println str2[0]
println str2[0..1]

// 4. 字符串排除
def str3 ="Hello groovy"
println str3.minus(str2)// str3 减去 str2
println str3 - str2

// 5. 字符传其它判断方法
println str.capitalize() // 首字母大写

def num="12345"
println num.isNumber() // 判断字符串是否为数字