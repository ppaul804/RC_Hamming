
import java.io.*;
import java.net.*;
import java.util.ArrayList;

class Server {
    public static ArrayList<Character> bitRedLis = new ArrayList<>();
    public static int quaRed;

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
                if (!bitRedLis.isEmpty()) {
                    bitRedLis.clear();
                }
                String msg = in.readLine();// Lê a mensagem enviada pelo cliente
                System.out.println("Mensagem do cliente [" + skt.getInetAddress().getHostName() + "]: " + msg);// Imprime a mensagem
                
                // 1º Identificar posição dos bits de redundância; (a mensagem já vem com os bits de redundância)
                // 2º Calcular a paridade do bit de redundância e 3º Determinar o bloco formado de bits de dados e os bits de paridade
                ArrayList<Character> msgLis = StringToArrayList(msg);// Transforma String em ArrayList de Characteres
                quantBits(msgLis);
                verPar(msgLis);//Faz o cálculo da paridade
                
                 
                if ("FI0M00".equals(msg)) {
                    break;// encerra o laço
                } else {
                    out.println(bitRedLis);//senão ecoa a mensagem para o cliente
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
    public static ArrayList<Character> StringToArrayList (String str){
        ArrayList<Character> list = new ArrayList<>();
                for (int i = 0; i < str.length(); i++) {
                    list.add(str.charAt(i));
                }
        return list;
    }//fim método StringToArrayList
    
    public static void quantBits(ArrayList<Character> msgList){
        int m = msgList.size();// Quantidade de bits da mensagem original
        quaRed = 0;// Quantidade de bits de redundância necessários
        boolean bitsSuf = false;// Tem bits sufucientes?
        
        while (bitsSuf == false) {
            quaRed++;
            bitsSuf = ((int) Math.pow(2, quaRed) >= m + quaRed + 1);
        }// fim while
    }
    
    public static void verPar(ArrayList<Character> msgLis) {
        //percorre a mensagem com os "r"
        for (int r = 0; r < quaRed; r++) {// percorre o arrayList dependendo da quantidade de bits de redundância
            
            int ctPar = 0;// quantos números 1 tem?
            boolean considera = false;// considero a posição?
            System.out.printf("\n");
            int potR = (int) Math.pow(2, r);//atalho para 2 elevado a r
            
            for (int i = potR-1; i < msgLis.size(); i++) {//Percorre o arraylist apartir do bit de paridade
                
                //Algoritmo de consideração
                int ctI = i - potR + 2;//faz o indice sempre contar apartir de um
                if (potR == 1) {//se 2 elevado a 0 for igual a um
                    considera = !considera;//alterna o valor de considera
                } else {
                    if (ctI % potR == 1)//se o resto do indice (alterado) e 2 elevado a r for 1  
                        considera = !considera;//altera o valor de considera
                }//fim if else
                
                if (considera) {//se considera a posição
                    if (msgLis.get(i) == '1')//se achar um "1"
                        ctPar++;//conta ele
                }//fim if
            }//fim for que percorre o arraylist apartir do bit de paridade
            if (ctPar % 2 == 0) {//se o resto da divisão entre a quantidade de uns e 2 for 0
                bitRedLis.add('0');
            } else {
                bitRedLis.add('1');
            }
        }//fim for q
    }
}//fim classe Server
