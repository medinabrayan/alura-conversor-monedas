import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class moneda {
    private String code;
    private String nombre;
    private ArrayList <conversion> converts=new ArrayList<conversion>();
    private JsonObject actualCurrencies = new JsonObject();
    
    
    public moneda(String code, String nombre) {
        this.code = code;
        this.nombre = nombre;
    } 
    public String getCode() {
        return code;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void addConvert(String code){
        double rate = getConversion_rate_value(code);
        conversion Conversion = new conversion(code,rate);
        converts.add(Conversion);
    }
    public double getConvert(String code){
       for (conversion conversion : converts) {
            if(conversion.getconvertCode().equals(code)){
                return conversion.getRate();
            }
       }
        return 0;
    }


    public void getConversion_rate_list(){
        String url_str = "https://v6.exchangerate-api.com/v6/faf9a020bff34dca41826a6a/latest/"+code;
        // Making Request
        try {
            URL url = new URL(url_str);
            HttpURLConnection request;
            try {
                request = (HttpURLConnection) url.openConnection();
                try {
                    request.connect();
                    JsonParser jp = new JsonParser();
                    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
                    actualCurrencies = root.getAsJsonObject();
                    
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            

            // Convert to JSON
            
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
    public double getConversion_rate_value( String currency){
        String value = actualCurrencies.get("conversion_rates").getAsJsonObject().get(currency).toString();
        return Double.valueOf(value);
    }

    public void convertidor (moneda monedaFinal, Scanner sc){
        double cantidad = 0;
        double resultado = 0;
        System.out.println("Ingrese la cantidad de "+nombre+" a convertir en "+monedaFinal.getNombre());
        cantidad = sc.nextDouble();
        resultado = getConvert(monedaFinal.getCode())*cantidad;
        System.out.println("El resultado de la conversion es "+resultado);
    }

}
