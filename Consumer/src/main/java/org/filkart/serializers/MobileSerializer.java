package org.filkart.serializers;

import org.apache.kafka.common.serialization.Serializer;
import org.filkart.dto.MobileDto;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;

public class MobileSerializer implements Serializer<MobileDto> {

    @Override
    public byte[] serialize(String Topic,MobileDto obj) {
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
