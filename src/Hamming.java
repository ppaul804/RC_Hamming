
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
    private static int quaRed;// Qantidade de bits necessários
    //LEMBRAR DE SEPARAR EM MÉTODOS PEQUENOS!
    /**
     * Método que elege as posicões de cada um dos bits de redundância colocando o caracter "r" neles.
     * 
     * @param msgOri A mensagem original
     * @return Um ArrayList com os "r" dentro
     */
    public static ArrayList<Character> bitRed(char[] msgOri) {
        int m = msgOri.length;// Quantidade de bits da mensagem original
        quaRed = 0;// Quantidade de bits de redundância necessários
        boolean bitsSuf = false;// Tem bits sufucientes?
        
        while (bitsSuf == false) {
            quaRed++;
            bitsSuf = ((int) Math.pow(2, quaRed) >= m + quaRed + 1);
        }// fim while
                
        
        ArrayList<Character> msgRedLis = new ArrayList<>(); 
        int iAux = msgOri.length - 1;
        int exp = 0;
        for (int i = 0; i < m + quaRed; i++) {
            if (i == (int) Math.pow(2, exp)) {
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
        System.out.println("");
        System.out.print(cabecalho);// exibe o cabeçalho
        // exibe cada elemento nos itens
        for (int i = 0; i < itens.size(); i++) {
            System.out.printf(" %c i: %d\n", itens.get(i), i);
        }
    }//fim método mostraArrayList

    public static ArrayList<Character> calPar(ArrayList<Character> msgRedLis) {
        
        //percorre a mensagem com os "r"
        for (int r = 0; r < quaRed; r++) {// percorre o arraylist dependendo da quantidade de bits de redundância
            
            int ctPar = 0;// quantos numeros 1 tem?
            boolean considera = false;// considero a "casa"?
            System.out.printf("\n");
            int potR = (int) Math.pow(2, r);
            
            for (int i = potR-1; i < msgRedLis.size(); i++) {//Percorre o arraylist apartir do bit de paridade
                
                //Algoritmo de consideração
                int ctI = i - potR + 2;
                if (potR == 1) {
                    considera = !considera;
                } else{
                    if (ctI % potR == 1)
                        considera = !considera;
                }
                
                if (considera) {//se considera a posição
                    if (msgRedLis.get(i) == '1')
                        ctPar++;
                
                    System.out.printf(" %c i: %d ctI: %d considera: %b  \ttem um: %b ctPar: %d\n", 
                            msgRedLis.get(i), 
                            i, 
                            ctI, 
                            considera, 
                            msgRedLis.get(i) == '1',
                            ctPar);
                }
            }//fim for
            if (ctPar % 2 == 0) {
                msgRedLis.set(potR-1, '0');// coloca 0
            } else {
                msgRedLis.set(potR-1, '1');// coloca 1
            }
        }//fim for
        
        mostraArrayList(msgRedLis, "msgRedLis Modificado  = \n");
        
        return msgRedLis;
        
    }//fim do método calPar

}// fim classe Hamming
