package com.fd.eurekaserver.config;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import lombok.extern.slf4j.Slf4j;
import org.objenesis.strategy.StdInstantiatorStrategy;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.io.ByteArrayOutputStream;

@Slf4j
public class KyroRedisSerializer implements RedisSerializer {

    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private static final ThreadLocal<Kryo> kryos = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            Kryo kryo = new Kryo();
            //引用，对A对象序列化时，默认情况下kryo会在每个成员对象第一次序列化时写入一个数字，
            // 该数字逻辑上就代表了对该成员对象的引用，如果后续有引用指向该成员对象，
            // 则直接序列化之前存入的数字即可，而不需要再次序列化对象本身。
            // 这种默认策略对于成员存在互相引用的情况较有利，否则就会造成空间浪费
            // （因为没序列化一个成员对象，都多序列化一个数字），
            // 通常情况下可以将该策略关闭，kryo.setReferences(false);
            kryo.setReferences(false);
            //设置是否注册全限定名，
            kryo.setRegistrationRequired(false);
            //设置初始化策略，如果没有默认无参构造器，那么就需要设置此项,使用此策略构造一个无参构造器
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            return kryo;
        }
    };


    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (t == null) {
            return EMPTY_BYTE_ARRAY;
        }

        Kryo kryo = kryos.get();
        kryo.register(t.getClass());

        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             Output output = new Output(baos)) {
            kryo.writeClassAndObject(output, t);
            output.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return EMPTY_BYTE_ARRAY;
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }

        Kryo kryo = kryos.get();

        try (Input input = new Input(bytes)) {
            return kryo.readClassAndObject(input);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;

    }
}
