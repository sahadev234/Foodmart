package com.appsoft.foodmart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appsoft.foodmart.service.CartService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/esewa")
public class PaymentController {
	
	@Autowired
	CartService cartService;

    @GetMapping("/pay")
    public String esewaPay(@RequestParam("userId") int userId,Model model) {
        // These values should come from your database or cart logic
    	
    	
    	
        double amt =cartService.calculateTotalPrice(userId);
        double txAmt =0;
        double psc = 0;
        double pdc =100;
        double tAmt = amt + txAmt + psc + pdc;

        String pid = UUID.randomUUID().toString(); // unique transaction ID

        model.addAttribute("tAmt", tAmt);
        model.addAttribute("amt", amt);
        model.addAttribute("txAmt", txAmt);
        model.addAttribute("psc", psc);
        model.addAttribute("pdc", pdc);
        model.addAttribute("scd", "EPAYTEST"); // replace with your merchant code in production
        model.addAttribute("pid", pid);
        model.addAttribute("su", "http://localhost:8080/esewa/success?pid=" + pid);
        model.addAttribute("fu", "http://localhost:8080/esewa/failure");

        return "PaymentInfo"; // Name of your HTML template
    }
    
    @GetMapping("/success")
    public String verifyEsewa(@RequestParam("refId") String refId,
                              @RequestParam("pid") String pid) throws IOException {

        String verificationUrl = "https://rc-epay.esewa.com.np/api/epay/verify";
        String data = "amt=100&scd=EPAYTEST&pid=" + pid + "&rid=" + refId;

        URL url = new URL(verificationUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(data.getBytes());
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String response = in.lines().collect(Collectors.joining());
        in.close();

        if (response.contains("<response_code>Success</response_code>")) {
            return "LoginForm";
        } else {
            return "SignupForm";
        }
    }

    @GetMapping("/failure")
    public String handleFailure() {
        return "SignupForm";
    }
}

