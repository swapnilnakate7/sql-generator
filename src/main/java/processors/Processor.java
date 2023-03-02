package processors;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Processor {

    Map<String,String> extractedData = new LinkedHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    public void process(Workbook workbook){
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while(sheetIterator.hasNext()){
            Sheet currentSheet = sheetIterator.next();
            if(null != currentSheet) {
                if(LOGGER.isDebugEnabled()){
                    LOGGER.debug(String.format("Sheet Name: %s", currentSheet.getSheetName()));
                }
                Iterator<Row> rowIterator = currentSheet.rowIterator();
                extractColumns(currentSheet.getRow(0));
                readRows(rowIterator);
            }

        }
    }

    private void readRows(Iterator<Row> rowIterator){
        while (rowIterator.hasNext()){
           Row row= rowIterator.next();
           if(row.getRowNum() != 0){
               //TODO read row data
           }
        }
    }

    private void extractColumns(Row row){
       Iterator<Cell> cellIterator = row.cellIterator();
       while(cellIterator.hasNext()){
           Cell currentCell = cellIterator.next();
           extractedData.put(currentCell.getStringCellValue(),null);
       }
    }

}
