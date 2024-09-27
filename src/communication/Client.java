package communication;

import encryption.AesEncryption;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private final String ServerIP;
    private final int ServerPort;

    public Client(String ServerIP, int ServerPort) {
        this.ServerIP = ServerIP;
        this.ServerPort = ServerPort;
    }

    public void sendMessages(String message) throws Exception {
        Socket socket = new Socket(ServerIP, ServerPort);
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());


        System.out.print("암호화에 사용할 key를 입력하세요. 16 바이트이어야 합니다: ");
        Scanner scanner = new Scanner(System.in);
        String myKey = scanner.nextLine();

        out.writeUTF(myKey);  // 키 전송
        out.writeUTF(message);  // 평문 전송
        String encryptedMessage = AesEncryption.encrypt(message, myKey);
        out.writeUTF(encryptedMessage);  // 암호문 전송
        out.flush();
        socket.close();
    }

}
