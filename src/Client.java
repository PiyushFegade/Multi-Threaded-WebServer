import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public void run() throws UnknownHostException,IOException {
        int port = 8010;
        Socket socket = new Socket(InetAddress.getByName("localhost"),port);

        PrintWriter toServer = new PrintWriter(socket.getOutputStream(),true);
        BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        toServer.println("Hello from Client");
        String lines = fromServer.readLine();
        System.out.println("Response from Server : "+ lines);
        toServer.close();
        fromServer.close();
        socket.close();
    }

    public static void main(String[] args){
        try{
            Client client = new Client();
            client.run();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }
}
