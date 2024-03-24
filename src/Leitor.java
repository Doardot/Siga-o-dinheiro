import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Leitor {
    public static String leitorArquivo(String filePath) {
        StringBuilder conteudo = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                conteudo.append(linha).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(conteudo.toString());
        return conteudo.toString();
    }
}