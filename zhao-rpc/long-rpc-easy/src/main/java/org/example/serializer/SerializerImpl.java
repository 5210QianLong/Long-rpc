package org.example.serializer;

import java.io.*;

/**
 * 基于jdk 序列化器
 */
public class SerializerImpl implements Serializer {
    @Override
    public <T> byte[] serializer(T object) throws IOException {
        // ByteArrayOutputStream 对byte类型数据进行写入的类 相当于一个中间缓冲层,将类写入到文件等其他outputStream
        //Java 输出流类,它可以将输出写入到内存中的   字节数组
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // Java 中用于将对象序列化到字节数组中的常用代码片段
        //ObjectOutputStream 是 Java 中用于将对象序列化到   输出流   的类。
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public <T> T deSerializer(byte[] bytes, Class<T> type) throws IOException{
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        try {
            return (T) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }finally {
            objectInputStream.close();
        }

    }
}
