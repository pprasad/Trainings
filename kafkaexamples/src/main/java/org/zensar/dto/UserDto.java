package org.zensar.dto;

import java.io.Serializable;

public class UserDto {
    private int id;
    private String name;
    private long mobile;
    public UserDto() {}
    public UserDto(int id, String name, long mobile) {
        super();
        this.id = id;
        this.name= name;
        this.mobile = mobile;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public long getMobile() {
        return mobile;
    }
}
