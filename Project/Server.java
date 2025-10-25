package JavaChatApp;
import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static Set<PrintWriter> clientWriters = new HashSet<>();

    public static void main(String[] args) throws Exception {
        System.out.println("Server started...");
        try (ServerSocket server = new ServerSocket(5000)) {
            while (true) {
                Socket client = server.accept();
                System.out.println("New client connected!");
                new ClientHandler(client).start();
            }
        }
    }

    static class ClientHandler extends Thread {
        Socket socket;
        BufferedReader in;
        PrintWriter out;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                clientWriters.add(out);

                String message;
                while ((message = in.readLine()) != null) {
                    for (PrintWriter writer : clientWriters) {
                        writer.println(message);
                    }
                }
            } catch (Exception e) {
                System.out.println("Client disconnected.");
            }
        }
    }
}
