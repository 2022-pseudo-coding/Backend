package WEB3D.common;

import WEB3D.controller.request.RegisterRequest;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Utils {
    public static boolean registerIsInvalid(RegisterRequest registerRequest){
        return isUserInvalid(registerRequest.getUsername(), registerRequest.getPassword());
    }

    public static boolean isUserInvalid(String username, String password) {
        return username == null || password == null || !password.matches("^[\\w-]{6,}$");
    }

    public static boolean isAdminInvalid(String username, String password) {
        return username == null || password == null || !password.matches("^[\\w-]{6,}$");
    }

    public static boolean isStrNotNull(String str){
        return (str != null) && (!"".equals(str));
    }
    //后续可加入更多的静态方法
}
