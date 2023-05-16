package Patterns.Proxy;

import javafx.scene.Parent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        double a = 0, b = 0;

        while (true) {
            String inputLine;
            if ((inputLine = in.readLine()) != null) {
                //out.println("read " + inputLine);
                String[] arrS = inputLine.split(" ");
                if (arrS[0] != null) a = Double.parseDouble(arrS[0]);
                if (arrS[1] != null) b = Double.parseDouble(arrS[1]);
            }
            out.println("result " + String.format("%.3f", a * b));
        }
        //stop();
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start(5000);
    }
}
