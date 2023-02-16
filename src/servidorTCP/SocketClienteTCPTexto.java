package servidorTCP;

import com.sun.source.tree.Scope;

import java.io.*;
import java.net.Socket;

public class SocketClienteTCPTexto {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private InputStreamReader isr;
    private BufferedReader br;
    private PrintWriter pw;
    public SocketClienteTCPTexto(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }
    public void start() throws IOException {
        System.out.println("(CLIENTE) Estableciendo CONEXION...");
        socket = new Socket(serverIP,serverPort);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(CLIENTE) Conexion establecida...");
    }
    public void stop() throws IOException {
        System.out.println("(Cliente) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(Cliente) Conexiones cerradas.");
    }

    public void abrirCanalesDetexto() {
        System.out.println("(Cliente) Abriendo canales de texto...");
        //canales de lectura
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        //canales de escritura
        pw = new PrintWriter(os);
        System.out.println("(Cliente) Canales de texto abiertos...");
    }


    public void cerrarCanalesDeTexto() throws IOException {
        System.out.println("(Cliente) Cerrando canales de texto...");
        //canales de lectura
        isr.close();
        br.close();
        //canales de escritura
        pw.close();
        System.out.println("(Cliente) Canales de texto cerrados...");
    }

    public String leerMensajeDeTexto() throws IOException {
        String mnsj = br.readLine();
        return mnsj;
    }

    public void escribirMensajeDeTexto(String mensaje) {
        System.out.println("(Cliente) Enviando mensaje...");
        pw.println(mensaje);
        pw.flush();
        System.out.println("(Cliente) Mensaje enviado...");
    }

    public static void main(String[] args) {
        String serverIP = "localhost";
        int serverPort = 1027;
        try {
            SocketClienteTCPTexto servidor = new SocketClienteTCPTexto(serverIP, serverPort);
            servidor.start();
            servidor.abrirCanalesDetexto();
            servidor.escribirMensajeDeTexto("Mensaje enviado por el CLIENTE");
            System.out.println("Mensaje del servidor: " + servidor.leerMensajeDeTexto());
            servidor.cerrarCanalesDeTexto();
            servidor.stop();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
