package org.filkart.serializers;
import org.apache.kafka.common.serialization.Deserializer;
import org.filkart.dto.CarDto;
import org.filkart.dto.MobileDto;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class MobileDeserializer implements Deserializer<MobileDto> {
    @Override
    public MobileDto deserialize(String topic, byte[] bytes) {
        System.out.println("*** Deserialize***");
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            MobileDto obj = (MobileDto) objectInputStream.readObject();
            return obj;
        }catch(Exception ex) {
            return null;
        }
    }
}
