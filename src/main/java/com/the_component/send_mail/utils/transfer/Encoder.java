package com.the_component.send_mail.utils.transfer;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public class Encoder {
    public static String encodeWithEmailAndTime(String email, LocalDateTime time) {
        String content = email + "///" + time;
        return Base64.getEncoder().encodeToString(content.getBytes());
    }

    public static HashMap<String, String> decodeWithEmailAndTime(String code) {
        String content = new String(Base64.getDecoder().decode(code));
        HashMap<String, String> res = new HashMap<>();
        String[] parts = content.split("///");

        if (parts.length == 2) {
            res.put("email", parts[0]);
            res.put("time", parts[1]);
        } else {
            res.put("error", "Invalid encoded format");
        }

        return res;
    }

    public static String encodeWithEmailAndContent(String email, String content) {
        return null;
    }
}
