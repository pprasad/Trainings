package org.zensar.serializer;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.utils.Bytes;
import org.zensar.dto.UserDto;
import java.nio.ByteBuffer;

public class DeSerializer implements Deserializer<UserDto> {

    @Override
    public UserDto deserialize(String key, byte[] bytes) {
        try {
            ByteBuffer buffer = ByteBuffer.wrap(bytes);
            int id = buffer.getInt();
            int nameBytes = buffer.getInt();
            byte [] byteName = new byte[nameBytes];
            buffer.get(byteName);
            String name =new String(byteName);
            long mobile = buffer.getLong();
            return new UserDto(id, name, mobile);
        } catch(Exception ex) {
            return  null;
        }
    }
}
