package com.chatgptcontentgenerator.controller;


import com.chatgptcontentgenerator.model.request.IasGptRequest;
import com.chatgptcontentgenerator.model.request.User;
import com.chatgptcontentgenerator.model.response.ChatGptResponse;
import com.chatgptcontentgenerator.service.IasGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequiredArgsConstructor
public class IasGptController {

    @Autowired
    private IasGptService botService;

    final String content_related = "(Answer the question in context of UPSC IAS Exam)";

    @PostMapping("/send")
    public ChatGptResponse sendMessage(@RequestBody IasGptRequest iasGptRequest) {
        return botService.askQuestion(iasGptRequest);
    }

    @PostMapping("/chat")
    public ResponseEntity<ChatGptResponse> submitForm(@RequestBody IasGptRequest iasGptRequest) {
        System.out.println("Request");
        System.out.println("iasGptRequest:" + iasGptRequest);

        iasGptRequest.setMessage(iasGptRequest.getMessage() + " " + content_related);

        System.out.println("iasGptRequest after appending:" + iasGptRequest);

        ChatGptResponse response = sendMessage(iasGptRequest);

        System.out.println("Response");
        System.out.println("ChatGptResponse:" + response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/chat")
    public ModelAndView showForm(Model model) {
        ModelAndView mav = new ModelAndView("ias-chat-gpt");
        IasGptRequest iasGptRequest = new IasGptRequest();
        mav.addObject("iasGptRequest", iasGptRequest);
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView showLoginForm(Model model) {
        ModelAndView mav = new ModelAndView("login-form");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }

    @GetMapping("/otp")
    public ModelAndView showLoginOtp(Model model) {
        ModelAndView mav = new ModelAndView("login-otp");
        User user = new User();
        mav.addObject("user", user);
        return mav;
    }
}