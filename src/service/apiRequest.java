package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Classe responsável por realizar a requisição HTTP à API de taxas de câmbio
 * e retornar os dados em formato JsonObject.
 */
public class apiRequest {

    /**
     * Faz a requisição para a API de câmbio e retorna os dados como JsonObject.
     *
     * @param base A moeda base (ex: "BRL")
     * @return Um JsonObject contendo as taxas de câmbio
     */
    public JsonObject buscarTaxasDeCambio(String base) {
        String url = "https://api.exchangerate.host/latest?base=" + base;

        // Criando o cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Criando a requisição
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        try {
            // Enviando a requisição e recebendo a resposta como String
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Convertendo a String JSON para JsonObject usando Gson
            Gson gson = new Gson();
            return gson.fromJson(response.body(), JsonObject.class);

        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao acessar a API: " + e.getMessage());
            return null;
        }
    }
}
