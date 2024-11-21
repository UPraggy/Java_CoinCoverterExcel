package coin_coverter_excel;

import coin_coverter_excel.ExcelManager;


import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.URI;
import java.time.Duration;

import java.io.IOException;
import java.net.URISyntaxException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonElement;

import java.util.Map.Entry;

public class ConversionCoin{

    private ExcelManager excelWorkManager;

    public ConversionCoin(ExcelManager excelWorkManager){
        this.excelWorkManager = excelWorkManager;
    }


    public void getExchangeRate(Object origemCoin, Object valueOrigemCoin, Object destinoCoin){
        try {
            this.excelWorkManager.verifyExistOrCreateFile();
            
            HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://economia.awesomeapi.com.br/json/last/"+origemCoin+"-"+destinoCoin))
                .timeout(Duration.ofSeconds(3))
                .build();
            
            HttpClient clientHttp = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();

            HttpResponse<String> response = clientHttp.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();
            System.out.println(response.body());
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);

            // Pega a primeira chave dinamicamente
            Entry<String, JsonElement> entry = jsonObject.entrySet().iterator().next(); 

            // int index = 0;
            // while (iterator.hasNext()) {
            //     Entry<String, JsonElement> entry = iterator.next();
            //     if (index == 2) { // Pega a terceira chave (0 = primeira, 1 = segunda, 2 = terceira)
            //         thirdKey = entry.getKey();
            //         break;
            //     }
            //     index++;
            // }

            String dynamicKey = entry.getKey();
            JsonObject dynamicObject = jsonObject.getAsJsonObject(dynamicKey);

            String name = dynamicObject.get("name").getAsString();
            double ask = dynamicObject.get("ask").getAsDouble();

            
            double valueOrigemCoinDouble = Double.parseDouble(valueOrigemCoin.toString());
            double totalExchange = Math.round((valueOrigemCoinDouble * ask) * 100.0) / 100.0;
            
            System.out.println("\nResultado Final da Conversão de "+valueOrigemCoinDouble);
            System.out.print(name + " -> ");
            System.out.println(totalExchange);
            this.excelWorkManager.fillLastLineData(name, valueOrigemCoinDouble, totalExchange);
            

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void showDataExcel(){
        this.excelWorkManager.readExcelFile();
    }


}