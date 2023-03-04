package sql;

import beans.SheetData;

import java.io.*;

public class Writer {

    public Writer(){
        //init
    }

    public void write(SheetData sheetData) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(sheetData.getSheetName()+".sql"));
        bufferedWriter.write(sheetData.printColumns());
        bufferedWriter.close();
    }
}
