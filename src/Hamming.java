
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
        int m = msgOri.length;// Quantidade de bits da mensagem
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
        throw new UnsupportedOperationException("Calculo dos bits de paridade não desenvolvido ainda...");
    }

}// fim classe Hamming
