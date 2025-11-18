import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Consumer;

public class Server {
    public Consumer<Socket> getConsumer(){
       return (clientSocket)-> {
           try {
               PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true);
               toClient.println("Hello from sever");
               toClient.close();
               clientSocket.close();
           }catch(IOException ex){
               return ;
           }
       };
    }

    public static void main(String[] args){
        int port = 8010;
        Server server = new Server();
        try {
            ServerSocket socket = new ServerSocket(port);
            socket.setSoTimeout(10000);
            while (true) {
                System.out.println("Server Running on port : "+port);
                Socket acceptedSocket = socket.accept();
                System.out.println("Server Address : "+acceptedSocket.getLocalSocketAddress());
                Thread thread = new Thread(()->server.getConsumer().accept(acceptedSocket));
                thread.start();
                acceptedSocket.close();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }

    }
}
