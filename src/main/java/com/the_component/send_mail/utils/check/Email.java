package com.the_component.send_mail.utils.check;

public class Email {
    private Email() {}

    public static boolean isEmail(String input) {
        if (!input.contains("@") || !input.contains(".")) {
            return false;
        }

        String name = input.split("@")[0];
        if (name.isEmpty()) {
            return false;
        }
        for (int i = 0; i < name.length(); i++) {
            char ele = name.charAt(i);
            if (!Character.isLetterOrDigit(ele) && ele != '.') {
                return false;
            }
        }

        String[] domain = input.split("@")[1].split("\\.");
        for (String s : domain) {
            if (s.isEmpty()) {
                return false;
            }
            for (int i = 0; i < s.length(); i++) {
                char ele = s.charAt(i);
                if (!Character.isLetter(ele)) {
                    return false;
                }
            }
        }

        if (input.split("@").length > 2) {
            return false;
        }

        return true;
    }
}
