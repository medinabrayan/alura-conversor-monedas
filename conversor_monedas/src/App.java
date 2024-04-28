import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.*; 

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        boolean repeat = true;
        int opcion = 0;
        Scanner sc = new Scanner(System.in);
        moneda dolar =new moneda("USD","Dolar");
        moneda pesoArgentino =new moneda("ARS","Peso Argentino");
        moneda realBrasileno =new moneda("BRL","Real Brasileno");
        moneda pesoColombiano =new moneda("COP","Peso Colombiano");
        
        dolar.getConversion_rate_list();
        pesoArgentino.getConversion_rate_list();
        realBrasileno.getConversion_rate_list();
        pesoColombiano.getConversion_rate_list();
        dolar.addConvert(pesoArgentino.getCode());
        dolar.addConvert(realBrasileno.getCode());
        dolar.addConvert(pesoColombiano.getCode());
        pesoArgentino.addConvert(dolar.getCode());
        realBrasileno.addConvert(dolar.getCode());
        pesoColombiano.addConvert(dolar.getCode());



        while (repeat) {
            System.out.println("*****************************************************************");
            System.out.println("Bienvenido al conversor de monedas ingresa el tipo de conversion.");
            System.out.println("1. Dolar a Peso Argentino");
            System.out.println("2. Peso Argentino a Dolar");
            System.out.println("3. Dolar a Real Brasileno");
            System.out.println("4. Real Brasileno a Dolar");
            System.out.println("5. Dolar a Peso Colombiano");
            System.out.println("6. Peso Colombiano a Dolar");
            System.out.println("7. Salir");
            System.out.println("*****************************************************************");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    dolar.convertidor(pesoArgentino,sc);
                break;
                case 2:
                    pesoArgentino.convertidor(dolar, sc);
                break;
                case 3:
                    dolar.convertidor(realBrasileno,sc);
                break;
                case 4:
                    realBrasileno.convertidor(dolar, sc);
                break;
                case 5:
                    dolar.convertidor(pesoColombiano,sc);
                break;
                case 6:
                    pesoColombiano.convertidor(dolar, sc);
                break;
                case 7:
                    repeat = false;
                break;
            
                default:
                    System.out.println("Ingresa una de las opciones...");
                break;
            }
        }
        

        System.out.println();
        
    }
    
}
