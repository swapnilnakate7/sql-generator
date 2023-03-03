package processors;

import beans.RowData;
import beans.SheetData;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Processor {

    Map<String,String> extractedData = new LinkedHashMap<>();
    List<SheetData> sheetDataList = new LinkedList<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    public void process(){
        SheetProcessor sheetProcessor = new SheetProcessor(sheetDataList);
        sheetProcessor.process();
    }

    public void init(Workbook workbook){
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while(sheetIterator.hasNext()){

            Sheet currentSheet = sheetIterator.next();
            SheetData sheetData = new SheetData();

            if(null != currentSheet) {
                if(LOGGER.isDebugEnabled()){
                    LOGGER.debug(String.format("Sheet Name: %s", currentSheet.getSheetName()));
                }

                extractColumns(currentSheet.getRow(0), sheetData);
                extractRows(currentSheet.rowIterator(), sheetData);
            }
            sheetDataList.add(sheetData);

        }
    }

    private void extractRows(Iterator<Row> rowIterator, SheetData sheetData){
        while (rowIterator.hasNext()){
           Row row= rowIterator.next();
           if(row.getRowNum() != 0){
               sheetData.addRow(getRowData(row.cellIterator()));
           }
        }
    }

    private RowData getRowData(Iterator<Cell> cellIterator){
        RowData rowData= new RowData();
        while (cellIterator.hasNext()){
            rowData.addCellValue(getCellValue(cellIterator.next()));
        }
        return rowData;
    }

    private String getCellValue(Cell cell){
        if(cell.getCellType() == CellType.NUMERIC){
            return Integer.toString((int)cell.getNumericCellValue());
        }
        return cell.getStringCellValue();
    }

    private void extractColumns(Row row, SheetData sheetData){
       Iterator<Cell> cellIterator = row.cellIterator();
       while(cellIterator.hasNext()){
           Cell currentCell = cellIterator.next();
           sheetData.addColumn(currentCell.getStringCellValue());
       }
    }

}
