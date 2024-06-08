package org.example.serializer;

import java.io.IOException;

/**
 * 提供 Java对象序列化和反序列化 接口  方法
 */
public interface Serializer {
    /**
     *  序列化
     * @param object
     * @return
     * @param <T>
     * @throws IOException
     */
    <T> byte[] serializer(T object) throws IOException;

    /**
     * 反序列化
     * @param bytes
     * @param type
     * @return
     * @param <T>
     * @throws IOException
     */
    <T> T deSerializer(byte[] bytes,Class<T> type) throws IOException, ClassNotFoundException;
}
