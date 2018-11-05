package com.damon.shared.common.test;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MiscTester {

    public static void main(String[] args) {
        String template = "insert into promotion_invite_code(code) value ('{ww}');";

        Set<String> codes = new HashSet<>();
        for (int i = 0; i < 10000; i++) {
            UUID uuid = UUID.randomUUID();
            String str1 = uuid.toString().replaceAll("0", "").toUpperCase();
            String str2 = str1.replaceAll("O", "").toUpperCase();
            String str3 = str2.replaceAll("1", "").toUpperCase();
            String str4 = str3.replaceAll("-", "").substring(0, 6);
            codes.add(str4);
            if (codes.size() >= 4030) break;
        }

        System.out.println(codes.size());
        for (String code : codes) {
//            System.out.println(code.toUpperCase());
            System.out.println(template.replace("{ww}", code.toUpperCase()));
        }
    }
}
