package coin_coverter_excel;

//FILE MANAGER
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

//EXCEL MANAGER
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.CellStyle;


public class ExcelManager{

    private String filePath;
    private String fileName;
    private XSSFWorkbook excelWorkBook;
    private Sheet sheet;

    public ExcelManager(String filePath, String fileName){
        this.filePath = filePath;
        this.fileName = fileName;
    }


    public void verifyExistOrCreateFile(){
        File arquivoLocal = new File(this.filePath+this.fileName);
        System.out.println("\n----------------\nVerify File Exists");
        
        if(arquivoLocal.exists()){
            try{
                System.out.println("File Exists on: " + this.filePath+this.fileName);
                FileInputStream fileInputStream = new FileInputStream(arquivoLocal);
                this.excelWorkBook = new XSSFWorkbook(fileInputStream);
                this.sheet = this.excelWorkBook.getSheetAt(0);
                fileInputStream.close();
                System.out.println("File loaded successfully!");
            }catch(IOException e){
                e.printStackTrace();
            }

        }else{
            System.out.println("Verify File Not Exists\nCreating...");
            this.excelWorkBook = new XSSFWorkbook();
            this.sheet = this.excelWorkBook.createSheet("Plan 1");
            this.setupInitialConfigFile();
            System.out.println("File Sucessfuly create and configured!\n\n");
        }
    }


    public void setupInitialConfigFile(){
        Font font = this.excelWorkBook.createFont();
        font.setFontName("Arial");
        font.setBold(true);
        CellStyle style = this.excelWorkBook.createCellStyle();
        style.setFont(font);

        Row row = this.sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("Converted Coin");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        cell.setCellValue("Original Value");
        cell.setCellStyle(style);

        cell = row.createCell(2);
        cell.setCellValue("Converted Result");
        cell.setCellStyle(style);

        try (FileOutputStream fileOut = new FileOutputStream(this.filePath+this.fileName)){
            this.excelWorkBook.write(fileOut);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void fillLastLineData(String name, double valueOrigemCoinDouble, double totalExchange){
        int newRowIndex = sheet.getLastRowNum() +1;

        Row row = this.sheet.createRow(newRowIndex);
        Cell cell = row.createCell(0);
        cell.setCellValue(name);

        cell = row.createCell(1);
        cell.setCellValue(valueOrigemCoinDouble);

        cell = row.createCell(2);
        cell.setCellValue(totalExchange);

        try(FileOutputStream fileOut = new FileOutputStream(this.filePath+this.fileName)){
            this.excelWorkBook.write(fileOut);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void readExcelFile(){

        if(this.excelWorkBook == null){
            this.verifyExistOrCreateFile();
        }

        int lastRowNum = this.sheet.getLastRowNum();
        System.out.println("\n\n|         Converted Coin         | Original Value | Converted Result |");
        System.out.println("|################################|################|##################|");
        for (int i = 1; i <= lastRowNum; i++) {
            Row row = this.sheet.getRow(i);
            if(row != null){

                System.out.println("|"+centerAlign(row.getCell(0).getStringCellValue(),32) +
                "|"+centerAlign(String.valueOf(row.getCell(1).getNumericCellValue()),16)+
                "|"+centerAlign(String.valueOf(row.getCell(2).getNumericCellValue()),18)+"|");
            }else{
                break;
            }
        }
        
    }



     public static String centerAlign(String input, int totalLength) {
        if (input.length() >= totalLength) {
            return input; // Retorna a string original se já for maior ou igual ao tamanho desejado
        }

        int totalSpaces = totalLength - input.length();
        int spacesLeft = totalSpaces / 2; // Espaços à esquerda
        int spacesRight = totalSpaces - spacesLeft; // Espaços à direita

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < spacesLeft; i++) {
            builder.append(" "); // Adiciona espaços à esquerda
        }
        builder.append(input); // Adiciona a string original
        for (int i = 0; i < spacesRight; i++) {
            builder.append(" "); // Adiciona espaços à direita
        }

        return builder.toString();
    }
}