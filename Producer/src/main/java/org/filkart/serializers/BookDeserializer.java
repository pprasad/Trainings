package org.filkart.serializers;
import org.apache.kafka.common.serialization.Deserializer;
import org.filkart.dto.BookDto;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class BookDeserializer implements Deserializer<BookDto> {
    @Override
    public BookDto deserialize(String topic, byte[] bytes) {
        System.out.println("*** Deserialize***");
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            BookDto bookDto = (BookDto) objectInputStream.readObject();
            return bookDto;
        }catch(Exception ex) {
            return null;
        }
    }
}
