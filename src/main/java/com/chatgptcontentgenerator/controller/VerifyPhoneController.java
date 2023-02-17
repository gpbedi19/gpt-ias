package com.chatgptcontentgenerator.controller;


import com.chatgptcontentgenerator.model.request.IasGptRequest;
import com.chatgptcontentgenerator.model.request.OtpRequest;
import com.chatgptcontentgenerator.model.request.User;
import com.chatgptcontentgenerator.model.response.ChatGptResponse;
import com.chatgptcontentgenerator.service.IasGptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;

@RestController
@RequiredArgsConstructor
public class VerifyPhoneController {

    public static final String ACCOUNT_SID = "ACd8b2224861cea78c99d4019844f95821";
    public static final String AUTH_TOKEN = "54308737d7a87e08f020c04023affc75";
    public static final String VERIFY_SID = "VA0095da0e8338ae40ba980065b599b52d";

    @PostMapping("/sendOTP")
    public ModelAndView sendOTp(@ModelAttribute("user") User user) {
        System.out.println("in send OTP:"+user);
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Verification verification = Verification.creator(VERIFY_SID, user.getPhoneNumber(), "sms").create();

        ModelAndView mav = new ModelAndView("login-otp");
        mav.addObject("otpRequest",new OtpRequest());
        return mav;
    }

    @PostMapping("/verifyOTP")
    public void verifyOTp(@ModelAttribute("user") User user) {

        System.out.println("in verify OTP:"+user);
        VerificationCheck verificationCheck = VerificationCheck.creator(VERIFY_SID).setTo(user.getPhoneNumber()).setCode(user.getOtp()).create();

        System.out.println("status:"+verificationCheck.getStatus());
    }


}