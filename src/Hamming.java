
import java.util.ArrayList;

/**
 * Classe com métodos para a implementeção do código de Hamming onde o algoritmo
 * consiste em:
 * 1º Identificar posição dos bits de redundância; 
 * 2º Calcular a paridade do bit de redundância; 
 * 3º Determinar o bloco formado de bits de dados e os bits de paridade.
 * 
 * @author Pedro Paul
 */
class Hamming {
    //LEMBRAR DE SEPARAR EM MÉTODOS PEQUENOS!
    /**
     * Método que elege as posicões de cada um dos bits de redundância colocando o caracter "r" neles.
     * 
     * @param msgOri A mensagem original
     * @return Um ArrayList com os "r" dentro
     */
    public static ArrayList<Character> bitRed(char[] msgOri) {
        int m = msgOri.length;// Quantidade de bits da mensagem original
        int r = 0;// Quantidade de bits de redundância necessários
        boolean bitsSuf = false;// Tem bits sufucientes?
        
        while (bitsSuf == false) {
            r++;
            bitsSuf = (Math.pow(2, r) >= m + r + 1);
        }// fim while
                
        
        ArrayList<Character> msgRedLis = new ArrayList<>(); 
        int iAux = msgOri.length - 1;
        int exp = 0;
        for (int i = 0; i < m + r; i++) {
            if (i == Math.pow(2, exp)) {
                msgRedLis.add(i - 1, 'r');
                exp++;
            } else {
                msgRedLis.add(msgOri[iAux]);
                iAux--;
            }
        }// fim for
        
        mostraArrayList(msgRedLis, "msgRedLis = \n");
        
        return msgRedLis;
    }// fim método bitRed

    /**
     * Exibe um ArrayList de caracteres na tela.
     * 
     * @param itens O ArrayList de caracteres de entrada.
     * @param cabecalho Uma String para descrição.
     */
    private static void mostraArrayList(ArrayList<Character> itens, String cabecalho) {
        System.out.print(cabecalho);// exibe o cabeçalho
        // exibe cada elemento nos itens
        for (Character item : itens) 
            System.out.printf(" %c\n", item);
    }//fim método mostraArrayList

    public static ArrayList<Character> calPar(ArrayList<Character> msgRedLis) {
        
        //percorre a mensagem com os "r"
        System.out.println("msgRedLis.size() - 1 -> "+(msgRedLis.size() - 1));
        for (int i = 0; i < msgRedLis.size() - 1; i++) {
            System.out.println("i = "+i);
            //se achar um "r"
            System.out.println("msgRedLis.get(i) == 'r' -> "+(msgRedLis.get(i) == 'r'));
            if (msgRedLis.get(i) == 'r') {
                //percorre a mensagem pulando o resultado da potência de 2 do índice posições começando pelo próprio índice
                System.out.println("Math.pow(2, i) = "+(Math.pow(2, i)));
                for (int j = i; i < msgRedLis.size() - 1; j += Math.pow(2, i)) {
                    System.out.println("j = "+j);
                    int ct = 0;
                    System.out.println("ct = "+ct);
                    //se a posição tiver 1 adiciona ao contador
                    System.out.println("msgRedLis.get(j) == '1' -> "+(msgRedLis.get(j) == '1'));
                    if (msgRedLis.get(j) == '1') {
                        ct++;
                        System.out.println("ct = "+ct);
                    }//fim if interno
                    //se o contador for par
                    System.out.println("ct % 2 == 0 -> "+(ct % 2 == 0));
                    if (ct % 2 == 0) {
                        //no lugar do "r" coloca 1
                        msgRedLis.set(i, '1');
                        System.out.println("msgRedLis.set(i, '1') -> "+(msgRedLis.set(i, '1')));
                    }//fim if da paridade
                }//fim for interno
            }//fim if
        }//fim for
        
        mostraArrayList(msgRedLis, "msgRedLis Modificado  = \n");
        
        return msgRedLis;
        
    }//fim do método calPar

}// fim classe Hamming
