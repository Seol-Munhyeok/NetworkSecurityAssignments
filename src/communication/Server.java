package communication;

import encryption.AesEncryption;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream in = new DataInputStream(socket.getInputStream());

            // 키 수신
            String key = in.readUTF();
            System.out.println("수신한 키: " + key);
            // 평문 메시지 수신
            String plaintextMessage = in.readUTF();
            System.out.println("수신한 평문 메시지: " + plaintextMessage);
            // 암호문 메시지 수신
            String encryptedMessage = in.readUTF();
            System.out.println("수신한 암호화된 메시지: " + encryptedMessage);
            
            // 서버 측에서 복호화
            String decryptedMessage = AesEncryption.decrypt(encryptedMessage, key);
            System.out.println("메시지 복호화 됨: " + decryptedMessage);

            socket.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(54321);
        server.start();
    }
}
