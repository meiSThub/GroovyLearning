package com.plum.groovy.object.global

import com.plum.groovy.object.Person

class ApplicationManager {
    static void init() {
        // 使我们动态为类添加的属性和方法在全局都生效
        ExpandoMetaClass.enableGlobally()

        // 模拟为第三方库动态注入方法
        Person.metaClass.static.createPerson = { name, age ->
            new Person(name: name, age: age);
        }

        String.metaClass.static.reverse2 = {str  ->
            return str.reverse()
        }
    }
}
