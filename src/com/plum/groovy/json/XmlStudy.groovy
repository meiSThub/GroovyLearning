package com.plum.groovy.json

import groovy.xml.MarkupBuilder
import groovy.xml.XmlSlurper

// groovy 解析xml数据


def xmlStr = '''
<response version-api="2.0">
    <value>
        <books id="1" classification="android">
            <book available="20" id="1">
                <title>疯狂Android讲义</title>
                <author id="1">李刚</author>
            </book>
             <book available="14" id="2">
                <title>第一行代码</title>
                <author id="2">郭凌</author>
            </book> 
            <book available="13" id="3">
                <title>Android开发艺术探索</title>
                <author id="3">人鱼缸</author>
            </book> 
            <book available="5" id="4">
                <title>Android源码设计模式</title>
                <author id="3">和红会</author>
            </book>
        </books>
        <books id="2" classification="web">
            <book available="10" id="1">
                <title>Vue从入门到精通</title>
                <author id="4">李刚</author>
            </book>
        </books>
    </value>
</response>
'''
// 1.groovy 解析 xml格式数据
def xmlSlurper = new XmlSlurper()
def response = xmlSlurper.parseText(xmlStr)
println response.value.books[1].book[0].title // 访问节点
println response.value.books[1].@classification // 访问属性，通过@字符，加上属性名

println('循环遍历：')
// 对xml进行过滤遍历
def list = []// 定义一个list
response.value.books.each { books ->
    def array = books.book.findAll { book ->
        book.author.text().equals('李刚')
    }
    list.addAll(array)
}

println list.toListString()

println('深度优先遍历：')
// 通过groovy提供对深度优先遍历api找到 所有作者为李刚对书
// 深度遍历xml数据
println response.depthFirst().findAll { book ->
    book.author.text().equals('李刚')
}

// 深度遍历除了使用depthFirst()方法外，groovy还提供了更简单对操作方式，即：'**'
// 平时使用对时候，建议使用：depthFirst()方法，比较清晰
println response.'**'.findAll { book ->
    book.author.text().equals('李刚')
}

println('广度优先遍历：')
// 除了可以使用深度优先遍历的方法遍历xml数据外，还可以使用广度优先遍历xml数据
// 广度优先遍历
println response.breadthFirst().findAll { book ->
    book.author.text().equals('李刚')
}


// 2.groovy 创建xml 格式数据

// 使用MarkupBuilder类，创建xml格式但数据
// 创建xmlStr格式的xml数据

def sw = new StringWriter();
def xmlBuilder = new MarkupBuilder(sw);// 会把xml数据都写入到StringWriter中

// 节点名称是什么，闭包的名称就是什么，这样MarkupBuilder就会帮我们生成对应的节点
// xmlBuilder.response(version_api: '2.0') {
//     value() {
//         // <books id="1" classification="android"> 节点上的属性，直接以key：value的形式指定
//         // 如果不指定key，则定义的是该节点的value
//         books(id: '2', classification: 'web') {
//             book(available: '10', id: '1') {
//                 title('Vue从入门到精通') // 如果不指定属性名，则直接就是该节点的值，如： <title>Vue从入门到精通</title>
//                 author(id: '4', '李刚')
//             }
//         }
//
//         books(id: '1', classification: 'android') {
//             book(id: '1', available: '20') {
//                 title('疯狂Android讲义')
//                 author(id: '1', '李刚')
//             }
//             book(id: 2, available: '14') {
//                 title('第一行代码')
//                 author(id: '2', '郭凌')
//             }
//             book(id: 3, available: '13') {
//                 title('Android开发艺术探索')
//                 author(id: '3', '人鱼缸')
//             }
//             book(id: 4, available: '5') {
//                 title('Android源码设计模式')
//                 author(id: '3', '和红会')
//             }
//         }
//     }
// }
//
// println sw

class Langs {
    String type
    int count
    String mainsteam
    def languages = [new Language(flavor: 'static', version: '2.0', value: 'java'),
                     new Language(flavor: 'dynamic', version: '2.0', value: 'javaScript'),
                     new Language(flavor: 'dynamic', version: '2.0', value: 'c++'),]
}

class Language {
    String flavor
    String version
    String value
}

def langs = new Langs(type: '语言', count: 3, mainsteam: 'true')
xmlBuilder.langs(type: langs.type, count: langs.count, mainStream: langs.mainsteam) {
    langs.languages.each { lang ->
        language(flavor: lang.flavor, version: lang.version, lang.value)
    }
}
println sw
/*
<langs type='语言' count='3' mainStream='true'>
  <language flavor='static' version='2.0'>java</language>
  <language flavor='dynamic' version='2.0'>javaScript</language>
  <language flavor='dynamic' version='2.0'>c++</language>
</langs>
* */



