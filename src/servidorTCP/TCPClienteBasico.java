package servidorTCP;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClienteBasico {
    public static void main(String[] args) throws IOException {
        String Host = "localhost";
        int puerto = 1025;

        Socket servidor = new Socket(Host, puerto);
        InetAddress i = servidor.getInetAddress();
        System.out.println("Puerto local: " + servidor.getLocalPort());
        System.out.println("Puerto remoto: " + servidor.getPort());
        System.out.println("NombreHost/IP: " + servidor.getInetAddress());
        System.out.println("Host remoto: " + i.getHostName());
        System.out.println("IP Host remoto: " + i.getHostAddress());

    }
}
