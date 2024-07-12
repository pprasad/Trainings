package org.zensar.kafka.kafka.dto;

public class MessageDto {
     private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "message='" + message + '\'' +
                '}';
    }
}
