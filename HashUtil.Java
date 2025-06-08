
// To generate a (encryption-like) version of a password

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashUtil {

    public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");    // Tells Java to use SHA-256 algorithm
            byte[] hashBytes = md.digest(input.getBytes());             // Converting the words to a hashed set of bytes.

            StringBuilder hexString = new StringBuilder();              // transforming the byte in hexadecimal text
            for (byte b : hashBytes) {hexString.append(String.format("%02x", b));}
            
            return hexString.toString();
        } 
        
        catch (NoSuchAlgorithmException e) {
            System.out.println("Erro ao aplicar hash: " + e.getMessage());      //exception (forced by jv)
            return null;
        }
    }
}
