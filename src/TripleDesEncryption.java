import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class TripleDesEncryption {
    public static String algorithm = "DESede";
    public static String mode = "CBC";
    public static String padding = "PKCS5Padding";
    public static final String keyString = "happybirthday321happybir";  // 3DES의 키는 24 바이트(192 비트) 키
    public static String ivString = keyString.substring(0, 8);  // 3DES의 iv는 8 바이트

    public static String encrypt(String plaintext)
            throws NoSuchAlgorithmException,
            InvalidKeySpecException,
            NoSuchPaddingException,
            InvalidKeyException,
            IllegalBlockSizeException,
            BadPaddingException,
            InvalidAlgorithmParameterException {

        // Cipher 객체 생성, AES 암호화, CBC 모드
        Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
        // 비밀키 객체 생성
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), algorithm);
        // 초기화 벡터 객체 생성
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivString.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] plaintextBytes = plaintext.getBytes(StandardCharsets.UTF_8);
        byte[] encryptedBytes = cipher.doFinal(plaintextBytes);
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String ciphertext)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            InvalidAlgorithmParameterException,
            IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {

        Cipher cipher = Cipher.getInstance(algorithm + "/" + mode + "/" + padding);
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyString.getBytes(StandardCharsets.UTF_8), algorithm);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(ivString.getBytes(StandardCharsets.UTF_8));
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(ciphertext);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

}
