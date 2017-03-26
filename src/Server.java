
import java.io.*;
import java.net.*;

class Server {

    public static void main(String args[]) {

        try {
            System.out.println("Iniciando servidor.");
            // O ServerSocket associa o serviço a uma porta lógica. Ele assumirá o IP da interface de rede padrão
            ServerSocket srvr = new ServerSocket(1234);
            
            System.out.println("Aguardando conexão.");
            // O método accept() do objeto ServerSocket faz que o servidor receba conexões dos clientes
            // Ele retorna um objeto Socket conectado ao cliente
            Socket skt = srvr.accept();
            
            System.out.print("Conexão estabelecida!\n");
            
            //<editor-fold defaultstate="collapsed" desc="Leitura do Socket">
            // E/S de dados associados a conexão
            InputStream input = skt.getInputStream();
            OutputStream output = skt.getOutputStream();
            
            // InputStream oferece uma interface para leitura de Bytes
            // BufferedReader oferece uma interface para leitura de Strings
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            // OutputStream oferece uma interface para Gravação de Bytes
            // PrintStream oferece uma interface para Gravação de Strings
            PrintStream out = new PrintStream(output);
            //</editor-fold>
            
            while (true) {                
                String msg = in.readLine();// Lê a mensagem enviada pelo cliente
                System.out.println("Mensagem do cliente ["+skt.getInetAddress().getHostName()+"]: "+ msg);// Imprime a mensagem
                if ("FIM".equals(msg)) {
                    break;// encerra o laço
                } else {
                    out.println(msg);//senão ecoa a mensagem para o cliente
                }//fim if else
            }//fim while
            
            System.out.println("Encerrando conexão.");
            in.close();
            out.close();
            skt.close();
            System.out.println("Encerrando servidor.");
            srvr.close();
        } catch (Exception e) {
            System.out.print("Ops! Não deu certo!\n");
            System.err.println(e);
        }
    }
}
