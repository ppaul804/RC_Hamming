
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

class Client {

    public static void main(String args[]) {
        try {
            System.out.println("Iniciando cliente.\nIniciando conexão com o servidor.");
            // Objeto Socket para estabelecer a conexão com o servidor 
            Socket skt = new Socket("127.0.0.1", 1234); //new Socket("localhost", 1234);

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

            Scanner scanner = new Scanner(System.in);
            System.out.println("Entre somente com números binarios ou digite \"FIM\" para encerrar a conexão.");
            while (true) {
                System.out.print("Digite: ");
                String msg = scanner.next("[01]+||[FIM]{3}");// Lê mensagem do teclado seguindo o padrão estabelecido
                char[] msgOri = msg.toCharArray();// Transforma a mensagem em um vetor de caracteres
                
                //Mostra o vetor
//                System.out.println("msgVet\tíndice");
//                for (int i = 0; i < msgOri.length; i++) {
//                    System.out.println(msgOri[i]+"\t"+i);
//                }
                
                // 1º Identificar posição dos bits de redundância
                ArrayList<Character> msgRedLis = Hamming.bitRed(msgOri);
                // 2º Calcular a paridade do bit de redundância
                ArrayList<Character> msgRedDef = Hamming.calPar(msgRedLis);
                // 3º Determinar o bloco formado de bits de dados e os bits de paridade
                
                // No receptor repetir os passos anteriores para verificar os bits
                
                out.println(msg);// Envia a mensagem ao servidor

                if ("FIM".equals(msg)) {
                    break;
                } else {
                    msg = in.readLine(); // aguarda a resposta do servidor
                    System.out.println("Mensagem do servidor: " + msg);
                }
            }//fim while
            
            System.out.println("Encerrando conexão.");
            in.close();
            out.close();
            skt.close();
        } catch (Exception e) {
            System.out.print("Ops! Não deu certo!\n");
            System.out.println(e);
        }
    }
}
