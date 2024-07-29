package org.zensar.serializer;

import org.apache.kafka.common.serialization.Serializer;
import org.zensar.dto.UserDto;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class UserSerializer implements Serializer<UserDto> {

    @Override
    public byte[] serialize(String topic, UserDto userDto) {
        if( userDto == null) {
            return  null;
        }
        byte [] username = null;
        try {
            username = userDto.getName().getBytes("UTF-8");
            ByteBuffer buffer = ByteBuffer.allocate(4+4+username.length+8);
            buffer.putInt(userDto.getId());
            buffer.putInt(username.length);
            buffer.put(username);
            buffer.putLong(userDto.getMobile());
            return buffer.array();
        } catch (UnsupportedEncodingException e) {
            return null;
        }

    }
}
