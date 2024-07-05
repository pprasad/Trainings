package org.filkart.serializers;

import org.apache.kafka.common.serialization.Serializer;
import org.filkart.dto.CarDto;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class CarSerializer implements Serializer<CarDto> {

    @Override
    public byte[] serialize(String Topic,CarDto obj) {
        System.out.println("****Serializer*******");
        if (obj ==null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(byteArrayOutputStream);
            stream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        }catch(Exception ex) {
            System.err.println("Exception :"+ ex.getMessage());
            return null;
        }
    }
}
