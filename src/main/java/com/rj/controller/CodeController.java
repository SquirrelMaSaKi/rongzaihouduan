package com.rj.controller;

import com.rj.utils.Captcha2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class CodeController {
    @RequestMapping("/captcha2")
    public void captcha2(HttpServletResponse res, HttpServletRequest req) throws IOException {
        Captcha2.generateCaptcha(req, res);
    }
}
