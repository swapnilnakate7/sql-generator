package sql;

import beans.SheetData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Writer.class);

    public Writer(){
        //init
    }

    public void write(SheetData sheetData)  {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sheetData.getSheetName() + ".sql"))) {
            bufferedWriter.write(sheetData.printColumns());
        }catch (IOException ioException){
            LOGGER.error(ioException.getMessage());
        }
    }
    public void write(String tableName,String sheetData)  {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tableName + ".sql"))) {
            bufferedWriter.write(sheetData);
        }catch (IOException ioException){
            LOGGER.error(ioException.getMessage());
        }
    }
}
