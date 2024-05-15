import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class App {
	public static void main(String[] args) {
        String apiUrl = "https://viacep.com.br/ws/30130010/json/";

        try {
            // Criando a conexão HTTP
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Lendo a resposta da API
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Convertendo a resposta JSON para um objeto JsonNode usando a biblioteca Jackson
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonResponse = mapper.readTree(response.toString());

            // Extraindo as informações desejadas
            String cep = jsonResponse.get("cep").asText();
            String logradouro = jsonResponse.get("logradouro").asText();
            String complemento = jsonResponse.get("complemento").asText();
            String bairro = jsonResponse.get("bairro").asText();
            String localidade = jsonResponse.get("localidade").asText();
            String uf = jsonResponse.get("uf").asText();
            String ibge = jsonResponse.get("ibge").asText();
            String gia = jsonResponse.get("gia").asText();
            String ddd = jsonResponse.get("ddd").asText();
            String siafi = jsonResponse.get("siafi").asText();

            // Imprimindo os dados
            System.out.println("CEP: " + cep);
            System.out.println("Logradouro: " + logradouro);
            System.out.println("Complemento: " + complemento);
            System.out.println("Bairro: " + bairro);
            System.out.println("Localidade: " + localidade);
            System.out.println("UF: " + uf);
            System.out.println("IBGE: " + ibge);
            System.out.println("GIA: " + gia);
            System.out.println("DDD: " + ddd);
            System.out.println("SIAFI: " + siafi);

            // Fechando a conexão
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
}
}
