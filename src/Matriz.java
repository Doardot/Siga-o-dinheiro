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

            // Implementação da lógica para o caractere "|"
            /*
             * if (atual == '|') {
             * if (x + 1 < matriz.length && matriz[x + 1][y] == '/') { // Verifica se há um
             * "/" logo abaixo
             * x--; // Se sim, move para cima
             * } else if (x + 1 < matriz.length && matriz[x + 1][y] == '\\') { // Verifica
             * se há um "\" logo abaixo
             * x++; // Se sim, move para baixo
             * }
             * // Se não, continue na mesma direção
             * }
             */

            // ta funcionando sem essa validação acima, acreidto que não precise tb pelo
            // visto, apenas manipulei nos ifs. ta meio feio, mas ta funcionando pra ir pra
            // cima
            // acredito que so falte alguma manipulação certa ali no if tb a partir das
            // linha 99 de quando for pra baixo
            // oq ta acontecendo agora é, de fato, quando le o primeiro caractere que vai
            // pra baixo, no caso, (14, 6), depois le o (15, 6) como esperado, mas fica
            // voltando para
            // o 14, como se tivesse ficando direction.UP e direction.DOWN realmente

            if (atual == '/') {
                if (direction == Direction.RIGHT) {
                    direction = Direction.UP;
                    x--;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.DOWN;
                    x++;
                } else if (direction == Direction.UP) {
                    direction = Direction.RIGHT; // nao foi preciso atualizar o y nesses casos, pelo oq vi, dps tava
                                                 // atualizando duas vezes a partir da linha 99, nas validações
                } else if (direction == Direction.DOWN) {
                    direction = Direction.LEFT;
                }
            }

            if (atual == '\\') {
                if (direction == Direction.RIGHT) {
                    direction = Direction.DOWN;
                    x++;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.UP;
                    x--;
                } else if (direction == Direction.UP) {
                    direction = Direction.LEFT;
                } else if (direction == Direction.DOWN) {
                    direction = Direction.RIGHT;
                }
            }

            // Atualização das coordenadas de acordo com a direção
            if ((atual != '/' || atual != '\\') && direction == Direction.RIGHT) {
                y++; // Segue a direita se não for "/" ou "\"
                if (y >= matriz[0].length) {
                    break; // Atingiu o limite da direita
                }
            } else if ((atual != '/' || atual != '\\') && direction == Direction.LEFT) {
                y--; // Segue a esquerda se não for "/" ou "\"
                if (y < 0) {
                    break; // Atingiu o limite da esquerda
                }
            } else if ((atual == '|' || atual != '\\' && atual != '/') && direction == Direction.UP) {
                x--;
                if (x < 0) {
                    break;
                }
            } else if ((atual == '|' || atual != '\\' && atual != '/') && direction == Direction.DOWN) {
                x++;
                if (x >= matriz.length) {
                    break;
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