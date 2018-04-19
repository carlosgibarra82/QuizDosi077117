package com.athena.quizdosi077117.Url;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by carlos on 12/04/18.
 */

public class HttpManager {

    public static String getData(String url) throws IOException {

        // Clase para manejar archivos
        BufferedReader bufferedReader;

        // Clase para manejar las urls de internet
        URL urlData = new URL(url);

        // Clase para abrir la conexión a internet
        HttpURLConnection httpURLConnection = (HttpURLConnection) urlData.openConnection();

        // Clase paa manejar los tipos de archivos
        StringBuilder stringBUilder = new StringBuilder();

        // Leer datos de internet
        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

        String line;
        while((line = bufferedReader.readLine())!=null) {
            stringBUilder.append(line + "\n");
        }

        return stringBUilder.toString();
    }
}

