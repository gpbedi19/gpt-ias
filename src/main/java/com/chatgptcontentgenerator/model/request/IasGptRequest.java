package com.chatgptcontentgenerator.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class IasGptRequest implements Serializable {
    private String message;
}



