package coin_coverter_excel;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class InterfaceConverter{


    public static int showWelcomeMessage(Scanner scanner){
        System.out.println("Bem vindo ao Conversor de Moedas!\n"+
        "Escolha uma opção abaixo para prosseguir:");
        InterfaceConverter.showMenuOptions();
        int option = InterfaceConverter.getMenuOption(scanner);
        return option;
    }

    public static int showOptions(Scanner scanner){
        InterfaceConverter.showMenuOptions();
        int option = InterfaceConverter.getMenuOption(scanner);
        return option;
    }

    public static void showMenuOptions(){
        System.out.println("1 - Realizar uma nova conversão de moeda.\n"+
            "2 - Exibir o histórico de conversões.\n"+
            "3 - Sair do programa.\n");
        System.out.print("> ");
    }

    public static void showAvailableCoins(){
        System.out.println(
            "Aqui está uma lista das Moedas Disponiveis:\n"+
        "BRL - Real Brasileiro\n"+
        "USD - Dólar Americano\n"+
        "SEK - Coroa Sueca\n"+
        "RUB - Rublo Russo\n"+
        "IQD - Dinar Iraquiano");
    }

    public static Map<String, Object> askSelectTwoCoinsAndConverter(Scanner scanner){

        Map<String, Object> coinData = new HashMap<>();

        System.out.print("Favor, insira a moeda de origem:\n> ");
        String coin1 = InterfaceConverter.getString(scanner);
        coinData.put("coin1", coin1);

        System.out.print("Valor da moeda (Ex.: 0,00):\n> ");
        float coin1Value = InterfaceConverter.getFloat(scanner);
        coinData.put("coin1Value", coin1Value);

        System.out.print("Favor, insira a moeda de destino:\n> ");
        String coin2 = InterfaceConverter.getString(scanner);
        coinData.put("coin2", coin2);

        return coinData;

    }

    public static int getMenuOption(Scanner scanner){
        while (true) {
            try {
                int optionSelected = scanner.nextInt();
                scanner.nextLine(); // Limpa o buffer
                return optionSelected;
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Limpa o buffer caso ocorra erro
            }
        }
    }

    public static String getString(Scanner scanner){
        while (true) {
            try {
                String optionSelected = scanner.nextLine();
                return optionSelected;
            } catch (Exception e) {
                System.out.println("Entrada inválida.");
                scanner.nextLine(); // Limpa o buffer caso ocorra erro
            }
        }
    }

    public static float getFloat(Scanner scanner) {
    while (true) {
        try {
            float optionSelected = scanner.nextFloat();
            scanner.nextLine(); // Limpa o buffer
            return optionSelected;
        } catch (Exception e) {
            System.out.println("Entrada inválida. Por favor, insira um número decimal.");
            scanner.nextLine(); // Limpa o buffer caso ocorra erro
        }
    }
    }
}