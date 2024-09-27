import communication.Client;
import encryption.AesEncryption;
import encryption.DesEncryption;
import encryption.TripleDesEncryption;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Scanner;

public class Main {
    // 암호화할 문자열 = 평문
    public static final String plaintext =
            "Oops, I got 99 problems singing bye, bye, bye";

    public static void main(String[] args) throws Exception {
        // 1. 문자열을 암호화하고 복호화 하는 프로그램
        try {
            String AesCiphertext = AesEncryption.encrypt(plaintext, AesEncryption.keyString);
            String AesDecryptedText = AesEncryption.decrypt(AesCiphertext, AesEncryption.keyString);
            System.out.println("AES로 암호화합니다.");
            System.out.println("평문: " + plaintext);
            System.out.println("암호문: " + AesCiphertext);
            System.out.println("복호화 결과: " + AesDecryptedText);
            System.out.println();

            String DesCiphertext = DesEncryption.encrypt(plaintext, DesEncryption.keyString);
            String DesDecryptedText = DesEncryption.decrypt(DesCiphertext, DesEncryption.keyString);
            System.out.println("DES로 암호화합니다.");
            System.out.println("평문: " + plaintext);
            System.out.println("암호문: " + DesCiphertext);
            System.out.println("복호화 결과: " + DesDecryptedText);
            System.out.println();

            String TripleDesCiphertext = TripleDesEncryption.encrypt(plaintext, TripleDesEncryption.keyString);
            String TripleDesDecryptedText = TripleDesEncryption.decrypt(TripleDesCiphertext, TripleDesEncryption.keyString);
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
        // 2. 문자열을 전송하는 client-server 프로그램
        // 여기서는 전송할 문자열과 key를 직접 입력 받음
        Client client = new Client("127.0.0.1", 54321);
        Scanner scanner = new Scanner(System.in);
        System.out.print("전송할 메시지를 입력하세요: ");
        String message = scanner.nextLine();
        client.sendMessages(message);  // 평문과 암호화된 메시지를 전송
    }
}