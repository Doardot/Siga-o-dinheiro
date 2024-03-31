public class Main {
    public static void main(String[] args) {
        String filePath = "src\\Casos\\casoG50.txt"; // Caminho do arquivo
        Matriz matriz = new Matriz(filePath);
        matriz.exibirMatriz();

        int linhaHifen = matriz.encontrarHifenEsquerda();
        System.out.println("Linha do hífen: " + linhaHifen);

        int soma = matriz.percorrerCaminho(linhaHifen);
        System.out.println("Soma: " + soma);
    }
}