package com.milind.dockersecret;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Security;
import java.util.Base64;
/**
 * Created by tavant on 19/06/17.
 */
public abstract class EncryptUtil {

    /**
     * Encryption strategy is to reverse the string which is not recommended for production
     * @param stringToEncrypt
     * @return
     */
    public static String encrypt(String stringToEncrypt){

        return new StringBuilder(stringToEncrypt).reverse().toString();
    }
}
