public class Main {
    public static void main(String[] args) {
        String filePath = "src\\Casos\\casoG50.txt"; // Caminho do arquivo
        Matriz matriz = new Matriz(filePath);

        int linhaHifen = matriz.encontrarHifenEsquerda();
        int soma = matriz.percorrerCaminho(linhaHifen);
        System.out.println("Soma: " + soma);
    }
}