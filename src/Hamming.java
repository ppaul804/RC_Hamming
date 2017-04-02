
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
    private static int r;// Qantidade de bits necessários
    //LEMBRAR DE SEPARAR EM MÉTODOS PEQUENOS!
    /**
     * Método que elege as posicões de cada um dos bits de redundância colocando o caracter "r" neles.
     * 
     * @param msgOri A mensagem original
     * @return Um ArrayList com os "r" dentro
     */
    public static ArrayList<Character> bitRed(char[] msgOri) {
        int m = msgOri.length;// Quantidade de bits da mensagem original
        r = 0;// Quantidade de bits de redundância necessários
        boolean bitsSuf = false;// Tem bits sufucientes?
        
        while (bitsSuf == false) {
            r++;
            bitsSuf = ((int) Math.pow(2, r) >= m + r + 1);
        }// fim while
                
        
        ArrayList<Character> msgRedLis = new ArrayList<>(); 
        int iAux = msgOri.length - 1;
        int exp = 0;
        for (int i = 0; i < m + r; i++) {
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
        for (Character item : itens) 
            System.out.printf(" %c\n", item);
    }//fim método mostraArrayList

    public static ArrayList<Character> calPar(ArrayList<Character> msgRedLis) {
        
        //percorre a mensagem com os "r"
        for (int i = 0; i < r; i++) {// percorre o arraylist dependendo da quantidade de bits de redundância
            int ctPar = 0;// quantos numeros 1 tem?
            boolean considera = true;// considero a "casa"?
            System.out.printf("\n");
            for (int j = (int) Math.pow(2, i); j < msgRedLis.size(); j++) {//Percorre o arraylist apartir do bit de paridade
                for (int ctJ = 1; ctJ < Math.pow(2, i); ctJ++) {//vai de zero até 2 elevado a i
                    
                    System.out.printf("(int) Math.pow(2, %d) %d\n",i-1 , (int) Math.pow(2, i-1));
                    System.out.printf("%d == %d\n", ctJ, (int) Math.pow(2, i-1));
//                    System.out.printf("ctJ == i %b\n", ctJ == i);
                    System.out.printf("considera %b pos %d\n", considera,j);
                    if (ctJ+1 <= (int) Math.pow(2, i-1)) {//se o contador for menor ou igual
                        if (considera) {//se considera a posição
                            if (msgRedLis.get(j) == '1') {
                                ctPar++;//adiciona um pro contador
                            }
                        }
                        considera = !considera;//muda a consideração
                    }
                    if (considera) {//se considera a posição
                        if (msgRedLis.get(j) == '1') {
                            ctPar++;
                        }
                    }//ctj++;
                }//fim for interno
            }//fim for
            if (ctPar%2 == 0) {
                msgRedLis.set(i, 'p');// coloca 1
            } else {
                msgRedLis.set(i, 'n');// coloca 0
            }
        }//fim for
        
        mostraArrayList(msgRedLis, "msgRedLis Modificado  = \n");
        
        return msgRedLis;
        
    }//fim do método calPar

}// fim classe Hamming
