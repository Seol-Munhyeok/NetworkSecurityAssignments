import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    // 128 비트 암호화 키
    public static String encryptionKey =
            "DAKSJFKDJFDFJDKSJFDSWRJRETOWPTDFJLDSJFGORJOWEJTJDSSDASQMSAAARWET" +
                    "JWELTEDSLFDJTETOZMQLJVQQKRZMLEJWHRTPEWRJKEJRDPQQEWQJZDRTQAZTRSTS";
    // 암호화할 문자열 = 평문 (10 bytes)
    public static String plaintext = "PKNUISGOOD";

    public static void main(String[] args) {
        try {
            String ciphertext = AesEncryption.encrypt(plaintext);
            System.out.println("암호문: " + ciphertext);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }
}