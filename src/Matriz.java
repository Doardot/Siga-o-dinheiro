public class Matriz {
    private final char[][] matriz;

    public Matriz(String filePath) {
        Leitor leitor = new Leitor();
        String conteudo = leitor.leitorArquivo(filePath);
        this.matriz = transformarEmMatriz(conteudo);
    }

    public char[][] transformarEmMatriz(String conteudo) {
        String[] linhas = conteudo.split("\n"); // Divide o conteúdo em linhas
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
    }

    public int percorrerCaminho(int xInicial) {
        int soma = 0;
        int x = xInicial;
        int y = 0;
        int novoNum = 0;
        Direction direction = Direction.RIGHT;
        boolean isEnd = false;
        StringBuilder numStr = new StringBuilder();

        while (!isEnd) {
            char atual = matriz[x][y]; // Caractere atual

            if (Character.isDigit(atual)) {
                numStr.append(atual);
                novoNum = Integer.parseInt(numStr.toString()); // Transforma a string em número
            } else {
                soma += novoNum;
                novoNum = 0;
                numStr.setLength(0); // Limpa a string
            }

            if (atual == '/') {
                if (direction == Direction.RIGHT) {
                    direction = Direction.UP;
                    x--;
                } else if (direction == Direction.LEFT) {
                    direction = Direction.DOWN;
                    x++;
                } else if (direction == Direction.UP) {
                    direction = Direction.RIGHT;
                } else {
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
                } else {
                    direction = Direction.RIGHT;
                }
            }

            // Atualização das coordenadas conforme a direção
            if (direction == Direction.RIGHT) {
                y++; // Segue a direita se não for "/" ou "\"
                if (y >= matriz[0].length) {
                    break; // Atingiu o limite da direita
                }
            } else if (direction == Direction.LEFT) {
                y--; // Segue a esquerda se não for "/" ou "\"
                if (y < 0) {
                    break; // Atingiu o limite da esquerda
                }
            } else if ((atual != '\\' && atual != '/') && direction == Direction.UP) {
                x--;
                if (x < 0) {
                    break;
                }
            } else if (atual != '\\' && atual != '/') {
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
}
