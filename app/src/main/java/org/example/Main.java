package coin_coverter_excel;

import coin_coverter_excel.InterfaceConverter;
import coin_coverter_excel.ConversionCoin;
import coin_coverter_excel.ExcelManager;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int option = InterfaceConverter.showWelcomeMessage(scanner);
        Map<String, Object> coinsMap;

        ExcelManager excelWorkManager = new ExcelManager("E:/projects/CoinCoverterExcel/","excelManager.xlsx");
        ConversionCoin conversionCoin = new ConversionCoin(excelWorkManager);

        switch(option){
            case 1:
                InterfaceConverter.showAvailableCoins();
                coinsMap = InterfaceConverter.askSelectTwoCoinsAndConverter(scanner);
                conversionCoin.getExchangeRate(coinsMap.get("coin1"),coinsMap.get("coin1Value"),coinsMap.get("coin2"));
                //coinsMap.clear();
                //coinsMap.get("coin1")
                break;
            case 2:
                conversionCoin.showDataExcel();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }
}
