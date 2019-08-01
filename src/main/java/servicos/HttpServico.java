package servicos;

import com.google.gson.Gson;
import dto.GetTransacoesDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpServico {

    private static final String API_URL = "https://my-json-server.typicode.com/cairano/backend-test/db";

    private Gson gson;

    public GetTransacoesDTO getTransacoesAPI() throws Exception {


        URL obj = new URL(API_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return gson.fromJson(response.toString(), GetTransacoesDTO.class);

    }

    public HttpServico() {
        gson = new Gson();
    }
}
