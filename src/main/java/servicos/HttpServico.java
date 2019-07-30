package servicos;

import com.google.gson.Gson;
import dto.GetTransacoesDTO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class HttpServico {

    public static GetTransacoesDTO getTransacoesAPI() throws Exception {

        String url = "https://my-json-server.typicode.com/cairano/backend-test/db";

        URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream(), StandardCharsets.UTF_8));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return new Gson().fromJson(response.toString(), GetTransacoesDTO.class);

    }

}
