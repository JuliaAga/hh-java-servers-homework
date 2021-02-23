public class JerseyApplication {

    public static void main(String[] args) throws Exception {

        JettyServer server = new JettyServer(8081);
        server.start();
        server.join();
    }
}
