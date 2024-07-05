package org.filkart.serializers;
import org.apache.kafka.common.serialization.Deserializer;
import org.filkart.dto.BookDto;
import org.filkart.dto.CarDto;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class CarDeserializer implements Deserializer<CarDto> {
    @Override
    public CarDto deserialize(String topic, byte[] bytes) {
        System.out.println("*** Deserialize***");
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            CarDto obj = (CarDto) objectInputStream.readObject();
            return obj;
        }catch(Exception ex) {
            return null;
        }
    }
}
