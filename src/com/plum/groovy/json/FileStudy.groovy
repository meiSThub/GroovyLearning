package com.plum.groovy.json

def file = new File('../../../../../GroovyLearning.iml')
// 按行读文件
// file.eachLine { line ->
//     println line // 输出每一行数据
// }

// 直接获取文件内容
def content = file.getText()
// println content

// 读取文件的内容，返回文件的所有行，是一个list集合
def lines = file.readLines()

// 读取文件部分内容
def reader = file.withReader { reader ->
    char[] buffer = new char[100]
    reader.read(buffer)
    return buffer
}
println reader

/**
 * 实现一个文件拷贝功能
 * @param sourcePath
 * @param desPath
 */
def copy(String sourcePath, String desPath) {
    try {
        def desFile = new File(desPath)
        if (desFile.exists()) {
            desFile.createNewFile()
        }

        // 读出源文件的内容
        new File(sourcePath).withReader { reader ->
            def lines = reader.readLines()
            // 把读出的内容写入到目标文件中
            desFile.withWriter { writer ->
                lines.each { line ->
                    writer.append(line) // 写如每一行数据
                            .append('\r\n')// 换行
                }
            }
        }
        return true
    } catch (Exception e) {
        e.printStackTrace()
    }
}

// def result = copy('../../../../../GroovyLearning.iml', '../../../../../GroovyLearning2.iml')
// println result


/**********对象保存**********/
/**
 * 保存对象
 * @param path
 * @param obj
 * @return
 */
def saveObject(String path, def obj) {
    try {
        def file = new File(path)
        if (!file.exists()) {
            file.createNewFile()
        }
        file.withObjectOutputStream { outputStream ->
            outputStream.writeObject(obj)
        }
    } catch (Exception e) {
        e.printStackTrace()
    }
}

/**
 * 从文件中读取对象
 * @param path
 * @return
 */
def readObject(String path) {
    def obj = null
    try {
        def file = new File(path)
        if (!file.exists()) {
            return null
        }
        file.withObjectInputStream { inputStream ->
            obj = inputStream.readObject()
        }
    } catch (Exception e) {
        e.printStackTrace()
    }
    return obj
}

class Friend implements Serializable{
    String name
    int age
}

def friend = new Friend(name: "Tom", age: 23)
def savePath = '../../../../../friend.bin';
// saveObject(savePath, friend)
def readObj = readObject(savePath)
println "save obj:name is ${readObj.name} and age is ${readObj.age}"