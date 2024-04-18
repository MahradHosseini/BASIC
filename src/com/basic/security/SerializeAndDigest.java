package com.basic.security;

import com.basic.user.User;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.util.ArrayList;

public class SerializeAndDigest {

    public static void serializeUsers(ArrayList<User> users) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("User.txt"))) {
            out.writeObject(users);
        }
    }

    public static void generateMD5() throws Exception {
        try (FileOutputStream out = new FileOutputStream("MD5.txt")) {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(Paths.get("User.txt")));
            byte[] digest = md.digest();

            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    out.write('0');
                }
                out.write(hex.getBytes());
            }
        }
    }

    public static boolean verifyMD5() {
        StringBuilder newMD5 = new StringBuilder();
        String originalMD5 = null;
        try {
            originalMD5 = new String(Files.readAllBytes(Paths.get("MD5.txt")));
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(Files.readAllBytes(Paths.get("User.txt")));
            byte[] digest = md.digest();
            for (byte b : digest) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    newMD5.append("0");
                }
                newMD5.append(hex);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return newMD5.toString().equals(originalMD5);
    }
}
