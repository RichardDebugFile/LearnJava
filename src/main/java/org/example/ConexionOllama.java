package org.example;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ConexionOllama {

    // Metodo para realizar la conexión y devolver la respuesta
    public String obtenerRespuesta(String modelName, String promptText) throws IOException {
        URL url = new URL("http://localhost:11434/api/generate");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; utf-8");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);

        StringBuilder response = new StringBuilder();

        try {
            // Crear el cuerpo JSON de la solicitud
            String jsonInputString = String.format(
                    "{ \"model\": \"%s\", \"prompt\": \"%s\", \"stream\": false }",
                    modelName, promptText
            );

            // Escribir el cuerpo JSON en la solicitud
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Obtener el código de respuesta
            int code = conn.getResponseCode();
            System.out.println("Response Code: " + code);

            // Leer el cuerpo de la respuesta
            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
            )) {
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
            }

            // Analizar la respuesta JSON y extraer el campo "response"
            JSONObject jsonResponse = new JSONObject(response.toString());
            String responseText = jsonResponse.getString("response"); // Cambia "response" si es necesario

            // Devolver la respuesta
            return responseText;

        } catch (IOException e) {
            e.printStackTrace();
            return null; // En caso de error, devolver null
        } finally {
            // Cerrar la conexión
            conn.disconnect();
        }
    }
}
