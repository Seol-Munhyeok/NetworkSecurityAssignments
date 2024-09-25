import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;

public class AesEncryption {
    public static String encrypt(String plaintext)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("AES");
        SecretKey secretKey = keyFactory.generateSecret(new PBEKeySpec(plaintext.toCharArray()));
        return Arrays.toString(secretKey.getEncoded());
    }
}
