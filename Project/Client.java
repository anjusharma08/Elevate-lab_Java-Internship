package JavaChatApp;
import java.io.*;
import java.net.*;
import java.util.Scanner;
public class Client {
    public static void main(String[] args) throws Exception {
        try (Socket socket = new Socket("localhost", 5000)) {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            try (Scanner sc = new Scanner(System.in)) {
                System.out.print("Enter your name: ");
                String name = sc.nextLine();

                // Receive Messages
                new Thread(() -> {
                    String msg;
                    try {
                        while ((msg = in.readLine()) != null) {
                            System.out.println(msg);
                        }
                    } catch (Exception ignored) {}
                }).start();

                // Send Messages
                while (true) {
                    String message = sc.nextLine();
                    out.println(name + ": " + message);
                }
            }
        }
    }
}
