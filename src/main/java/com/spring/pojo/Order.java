package com.spring.pojo;

import java.io.Serializable;

public class Order implements Serializable {
    private static final long serialVersionID = -1502291609049620042L;

    private Integer id;
    private String name;
    private String messageId;

    public Order(Integer id, String name, String messageId) {
        this.id = id;
        this.name = name;
        this.messageId = messageId;
    }
    public Order() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }
}
