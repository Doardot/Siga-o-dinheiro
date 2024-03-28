public class Matriz {
    private char[][] matriz;
    private Leitor leitor;

    public Matriz(String filePath) {
        leitor = new Leitor();
        String conteudo = leitor.leitorArquivo(filePath);
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

    public enum Direction {
        UP, DOWN, LEFT, RIGHT
        // UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT;
    }

    public int percorrerCaminho(int xInicial) {
        int soma = 0;
        int x = xInicial;
        int y = 0;
        Direction direction = Direction.RIGHT;
        boolean isEnd = false;

        while (!isEnd) {
            char atual = matriz[x][y]; // Caractere atual
            System.out.println("Lendo posição: (" + x + ", " + y + ") -> Caractere: " + atual);

            if (Character.isDigit(atual)) {
                soma += Character.getNumericValue(atual);
            }

            // TODO: Implementar a lógica para o caractere "|"
            if (atual == '|') {
                if (x + 1 < matriz.length && matriz[x + 1][y] == '/') { // Verifique se há um "/" logo abaixo
                    x--; // Se sim, move para cima
                } else if (x + 1 < matriz.length && matriz[x + 1][y] == '\\') { // Verifique se há um "\" logo abaixo
                    x++; // Se sim, mova para baixo
                }
                // Se não, continue na mesma direção
            }

            if (direction == Direction.RIGHT) {
                y++;
            } else if (direction == Direction.LEFT) {
                y--;
            } else if (direction == Direction.UP) {
                x--;
            } else if (direction == Direction.DOWN) {
                x++;
            }

            // TODO: Implementar a lógica para os caracteres "/" e "\"
            // O uso de x e y é uma possível solução para o problema de caminhamento
            if (atual == '/') {
                if (direction == Direction.RIGHT) {
                    direction = Direction.UP;
                    y--;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.DOWN;
                    // y++;
                } else if (direction == Direction.UP) {
                    direction = Direction.RIGHT;
                    // x++;
                } else if (direction == Direction.DOWN) {
                    direction = Direction.LEFT;
                    // x--;
                }
            }

            if (atual == '\\') {
                if (direction == Direction.RIGHT) {
                    direction = Direction.DOWN;
                    // y++;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.UP;
                    // y--;
                } else if (direction == Direction.UP) {
                    direction = Direction.LEFT;
                    // x--;
                } else if (direction == Direction.DOWN) {
                    direction = Direction.RIGHT;
                    // x++;
                }
            }

            if (atual == '#') {
                isEnd = true;
            }
        }
        return soma;
    }

    public void exibirMatriz() {
        for (char[] x : matriz) {
            for (char elemento : x) {
                System.out.print(elemento + " ");
            }
            System.out.println();
        }
    }
}