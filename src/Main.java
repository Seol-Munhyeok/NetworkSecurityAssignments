import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Main {
    // 암호화할 문자열 = 평문
    public static final String plaintext =
            "Oops, I got 99 problems singing bye, bye, bye";

    public static void main(String[] args) {
        try {
            String AesCiphertext = AesEncryption.encrypt(plaintext);
            String AesDecryptedText = AesEncryption.decrypt(AesCiphertext);
            System.out.println("AES로 암호화합니다.");
            System.out.println("평문: " + plaintext);
            System.out.println("암호문: " + AesCiphertext);
            System.out.println("복호화 결과: " + AesDecryptedText);
            System.out.println();

            String DesCiphertext = DesEncryption.encrypt(plaintext);
            String DesDecryptedText = DesEncryption.decrypt(DesCiphertext);
            System.out.println("DES로 암호화합니다.");
            System.out.println("평문: " + plaintext);
            System.out.println("암호문: " + DesCiphertext);
            System.out.println("복호화 결과: " + DesDecryptedText);
            System.out.println();

            String TripleDesCiphertext = TripleDesEncryption.encrypt(plaintext);
            String TripleDesDecryptedText = TripleDesEncryption.decrypt(TripleDesCiphertext);
            System.out.println("삼중 DES로 암호화합니다.");
            System.out.println("평문: " + plaintext);
            System.out.println("암호문: " + TripleDesCiphertext);
            System.out.println("복호화 결과: " + TripleDesDecryptedText);
            System.out.println();

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException |
                 IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}