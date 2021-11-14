import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    int  port;
    int count = 0;
    List<String> historyMessage = new ArrayList<>();
    public Server(int port) {
        this.port = port;
    }

    public synchronized void startServer(){
        try (ServerSocket server = new ServerSocket(port)){
            Socket socket = server.accept();
            try (BufferedWriter writer =
                         new BufferedWriter(
                                 new OutputStreamWriter(
                                         socket.getOutputStream())))
            {
                write("Server Started!\n",writer);

                try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))){
                    while(true){
                        String str = reader.readLine();
                        if(str.equalsIgnoreCase("exit")){
                            writer.close();
                            break;
                        }
                        if(str.equalsIgnoreCase("all")){
                            for(String message : historyMessage){
                                write(message,writer);
                            }
                        } else {


                            count++;
                            historyMessage.add(str);
                            str = String.format("%d. %s \n", count, str);
                            write(str, writer);
                        }
                    }
                }
            }
            socket.close();
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    private void write(String message, BufferedWriter bufferWriter){
        try{
            bufferWriter.write(message);
            bufferWriter.flush();
            bufferWriter.newLine();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
