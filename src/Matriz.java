public class Matriz {
    private char[][] matriz;
    char RIGHT = '/';
    char LEFT = '\\';
    char PATH = '-';
    char linha = '|';

    public Matriz(String filePath) {
        String conteudo = Leitor.leitorArquivo(filePath);
        this.matriz = transformarEmMatriz(conteudo);
    }

    public char[][] transformarEmMatriz(String conteudo) {
        String[] linhas = conteudo.trim().split("\n"); // Divide o conteúdo em linhas
        String[] dimensoes = linhas[0].split("\\s+");
        int largura = Integer.parseInt(dimensoes[0]);
        int altura = Integer.parseInt(dimensoes[1]);

        char[][] matriz = new char[altura][largura];
        // System.out.println("Largura: " + largura);
        // System.out.println("Altura: " + altura);

        for (int i = 0; i < altura; i++) {
            String linha = linhas[i + 1]; // Começa da segunda linha após as dimensões
            for (int j = 0; j < largura; j++) {
                matriz[i][j] = j < linha.length() ? linha.charAt(j) : ' ';
                // Se a linha acabar, preenche com espaço em branco
            }
        }
        return matriz;
    }

    // Método "comecaAqui"
    public int encontrarHifenEsquerda() {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] == '-') {
                return i; // Retorna a linha onde o primeiro hífen foi encontrado
            }
        }
        return -1; // Se nenhum hífen for encontrado na primeira coluna
    }

    // TODO: Implementar a lógica de percorrer o caminho
    public int percorrerCaminho(int linhaInicial) {
        int soma = 0;
        int linha = linhaInicial;
        int coluna = 0;

        while (linha >= 0 && linha < matriz.length && coluna >= 0 && coluna < matriz[0].length) {
            char atual = matriz[linha][coluna];
            System.out.println("Lendo posição: (" + linha + ", " + coluna + ") -> Caractere: " + atual);

            if (Character.isDigit(atual)) {
                soma += Character.getNumericValue(atual);
            }
            /*
             * if (atual == '-') {
             * coluna++; // Mover para a direita
             * } else
             */
            if (atual == '/') {
                linha++; // Mover para baixo
                coluna--; // Mover para a esquerda
            } else if (atual == '\\') {
                linha++; // Mover para baixo
                coluna++; // Mover para a direita
            } else if (atual == '|') {
                if (coluna + 1 < matriz[0].length && matriz[linha][coluna + 1] == '-') {
                    coluna++; // Avance para ignorar o "|"
                } else {
                    linha++; // Mover para baixo
                }
            } else if (atual == '#') {
                break; // Fim do caminho
            }
            // Avançar para o próximo caractere de acordo com as regras do caminho
            coluna++;
        }
        return soma;
    }

    public void exibirMatriz() {
        for (char[] linha : matriz) {
            for (char elemento : linha) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }
}