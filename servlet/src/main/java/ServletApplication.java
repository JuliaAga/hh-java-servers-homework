
public class ServletApplication {

    public static void main(String[] args) throws Exception {
        int port = 8081;
        Server server = new Server(port);
        server.start();
        server.join();
    }
}
