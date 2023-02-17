package com.chatgptcontentgenerator.service;


import com.chatgptcontentgenerator.model.request.IasGptRequest;
import com.chatgptcontentgenerator.model.response.ChatGptResponse;

public interface IasGptService {

    ChatGptResponse askQuestion(IasGptRequest iasGptRequest);
}
