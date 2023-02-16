package servidorTCP;

import java.io.*;
import java.net.Socket;

public class SocketServidorTCPTexto {
    private String serverIP;
    private int serverPort;
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private InputStreamReader isr;
    private BufferedReader br;
    private PrintWriter pw;
    public SocketServidorTCPTexto(int serverPort) {
        this.serverPort = serverPort;
    }
    public void start() throws IOException {
        System.out.println("(SERVIDOR) Estableciendo CONEXION...");
        socket = new Socket(serverIP,serverPort);
        is = socket.getInputStream();
        os = socket.getOutputStream();
        System.out.println("(SERVIDOR) Conexion establecida...");
    }
    public void stop() throws IOException {
        System.out.println("(SERVIDOR) Cerrando conexiones...");
        is.close();
        os.close();
        socket.close();
        System.out.println("(SERVIDOR) Conexiones cerradas.");
    }

    public void abrirCanalesDetexto() {
        System.out.println("(SERVIDOR) Abriendo canales de texto...");
        //canales de lectura
        isr = new InputStreamReader(is);
        br = new BufferedReader(isr);
        //canales de escritura
        pw = new PrintWriter(os);
        System.out.println("(SERVIDOR) Canales de texto abiertos...");
    }


    public void cerrarCanalesDeTexto() throws IOException {
        System.out.println("(SERVIDOR) Cerrando canales de texto...");
        //canales de lectura
        isr.close();
        br.close();
        //canales de escritura
        pw.close();
        System.out.println("(SERVIDOR) Canales de texto cerrados...");
    }

    public String leerMensajeDeTexto() throws IOException {
        String mnsj = br.readLine();
        return mnsj;
    }

    public void escribirMensajeDeTexto(String mensaje) {
        System.out.println("(SERVIDOR) Enviando mensaje...");
        pw.println(mensaje);
        pw.flush();
        System.out.println("(SERVIDOR) Mensaje enviado...");
    }

    public static void main(String[] args) {
        String serverIP = "localhost";
        int serverPort = 1027;
        try {
            SocketServidorTCPTexto servidor = new SocketServidorTCPTexto(serverPort);
            servidor.start();
            servidor.abrirCanalesDetexto();
            servidor.escribirMensajeDeTexto("Hola mundo");
            System.out.println("Mensaje del servidor: " + servidor.leerMensajeDeTexto());

            servidor.stop();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
