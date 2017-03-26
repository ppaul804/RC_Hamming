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

    public static char[] bitRed(char[] msgOri) {
        int m = msgOri.length;// Quantidade de bits da mensagem
        int r = 0;// Quantidade de bits de redundância necessários
        boolean bitsSuf = false;// Tem bits sufucientes?
        
        while (bitsSuf == false) {
            r++;
            bitsSuf = (Math.pow(2, r) >= m + r + 1);
        }// fim while
        
        char[] msgRed = new char [m + r];
        
        int iAux = 0;
        int exp = 1;
        for (int i = 0; i < m + r; i++) {
            if (i == Math.pow(2, exp)) {
                msgRed[i] = ' ';// Põe um expaço onde deve ficar o bit de redundância
                exp++;
            } else {
                System.out.println("i\t"+i+"\tiAux\t"+iAux);
                msgRed[i] = msgOri[iAux];
                iAux++;
            }// fim else
        }// fim for
        
        //Mostra o vetor
        System.out.println("msgRed\tíndice");
        for (int i = 0; i < msgRed.length; i++) {
            System.out.println(msgRed[i]+"\t"+i);
        }
        
        return msgRed;
    }// fim método bitRed

}// fim classe Hamming
