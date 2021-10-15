//TRABALHO MEGASENA - SIMULAR SORTEIO MEGASENA
// ALUNOS: ADELTON CAETANO, ROGERIO KRYCHAK - 5o PERÍODO
//ANÁLISE E DESENVOLVIMENTO DE SISTEMAS -UNIFAESP

import java.util.Scanner;

public class MegaSena {

    static int qtd_dezenas = 6;

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        int[] sorteio = sorteaSena();//variável para o sorteio
        int[] aposta = new int[qtd_dezenas];//variável para aposta

        //trecho processa a aposta
        System.out.println("Faça sua aposta: ");
        for (int i = 0; i < qtd_dezenas; i++) {
            int nroAposta;
            boolean nrorepetido = false;

            do {
                System.out.print("Informe "+(i+1)+"a dezena: ");
                nroAposta = teclado.nextInt();
                if (nroAposta <= 0) {
                    System.out.println("Número inválido, aposta cancelada!");
                    return;
                }
                nrorepetido = existeNumero(aposta, nroAposta);
                if (nrorepetido) {
                    System.out.println("Atenção, número repetido!");
                }
            } while(nrorepetido); // evita repetição de número

            aposta[i] = nroAposta;
        }

        System.out.println("\n\nConfira sua aposta:");
        for (int i = 0; i < aposta.length; i++) {
            System.out.print( aposta[i] + " ");
        }

        System.out.println("\n\nResultado do sorteio:");
        for (int i = 0; i < sorteio.length; i++) {
            System.out.print( sorteio[i] + " ");
        }

        int nroAcertos = contaAcertos(sorteio, aposta);
        System.out.println("\n\nNúmero de acertos: "+nroAcertos);

        switch (nroAcertos) {
            case 4: System.out.println("\nVocê acertou a quadra!"); break;
            case 5: System.out.println("\nVocê acertou a quina!"); break;
            case 6: System.out.println("\nParabéns. Você é campeão da MegaSena!"); break;
            default: System.out.println("\nTente novamente!"); break;
        }
    }

    /*
     * Função retorna uma array com 6 números gerados randomicamente (API do Java),
     * sem duplicidade, representando o sorteio da megasena.
     */
    static int[] sorteaSena() {
        int[] resultado = new int[qtd_dezenas];

        for (int i = 0; i < qtd_dezenas; i++) {
            int sorteado;
            boolean repetido = false;

            do {
                sorteado = (int) (Math.random()*60)+1; //Math.random, gera o número aleatório de 1 à 60
                repetido = existeNumero(resultado, sorteado);
            } while(repetido); // evita repetição de número

            resultado[i] = sorteado;
        }
        return resultado;
    }

    /*
     * Função comparada cada número apostado, com os números sorteados.
     * Por fim retorna a quantidade de acertos.
     */
    static int contaAcertos(int[] sorteio, int[] aposta) {
        int acertos = 0;
        for (int i = 0; i < qtd_dezenas; i++) {
            int nroAposta = aposta[i];

            //compara cada nro apostado com os sorteados
            if (existeNumero(sorteio, nroAposta)) {
                acertos++;
            }
        }
        return acertos;
    }

    /*
     * Função indica se o n existe no array numeros.
     * Útil para validar duplicadade de dados.
     */
    static boolean existeNumero(int[] numeros, int n) {
        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i] == n) {
                return true;
            }
        }
        return false;
    }

}

