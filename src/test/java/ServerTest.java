import org.junit.jupiter.api.Test;

class ServerTest {

    @Test
    void startServer() {
        Server server = new Server(8080);
        server.startServer();
    }
}