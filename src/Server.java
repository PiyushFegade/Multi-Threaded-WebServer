import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public void run(){
        int port = 8010;
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
            while (true) {
                System.out.println("Server Running on port : "+port);
                Socket acceptedSocket = socket.accept();
                System.out.println("Server Address : "+acceptedSocket.getLocalSocketAddress());
                PrintWriter toClient = new PrintWriter(acceptedSocket.getOutputStream(),true);
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedSocket.getInputStream()));
                toClient.println("Hello from sever");
                System.out.println(fromClient.readLine());
                toClient.close();
                fromClient.close();
                acceptedSocket.close();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
    public static void main(String[] args){
        Server server  = new Server();
            server.run();

    }
}
