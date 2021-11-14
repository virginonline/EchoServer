
//telnet 127.0.0.1 8080
public class main {

    public static void main(String[] args) {
        Server server = new Server(8080);
        server.startServer();
    }
}