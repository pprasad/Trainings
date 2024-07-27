package org.zensar.serializer;
import org.apache.kafka.common.serialization.Serializer;
import org.zensar.dto.BookDto;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class BookSerializer implements Serializer<BookDto> {

    @Override
    public byte[] serialize(String Topic,BookDto bookDto) {
        System.out.println("****Serializer*******");
        if (bookDto ==null) {
            return null;
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream stream = new ObjectOutputStream(byteArrayOutputStream);
            stream.writeObject(bookDto);
            return byteArrayOutputStream.toByteArray();
        }catch(Exception ex) {
            System.err.println("Exception :"+ ex.getMessage());
            return null;
        }
    }
}
