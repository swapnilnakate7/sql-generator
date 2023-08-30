package processors;

import beans.RowData;
import beans.SheetData;
import enums.Operation;
import factories.SheetProcessorFactory;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Processor {

    List<SheetData> sheetDataList = new LinkedList<>();

    private Operation selectedOperation;
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class);

    public void process(){

        SheetProcessor sheetProcessor = SheetProcessorFactory.getProcessor(selectedOperation,sheetDataList);

        sheetProcessor.process();
    }

    public void init(Workbook workbook){
        Iterator<Sheet> sheetIterator = workbook.sheetIterator();
        while(sheetIterator.hasNext()){

            Sheet currentSheet = sheetIterator.next();


            if(null != currentSheet) {
                SheetData sheetData = new SheetData(currentSheet.getSheetName());
                if(LOGGER.isInfoEnabled()){
                    LOGGER.info(String.format("Sheet Name: %s", currentSheet.getSheetName()));
                }

                extractColumns(currentSheet.getRow(0), sheetData);
                extractRows(currentSheet.rowIterator(), sheetData);
                sheetDataList.add(sheetData);
            }

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

    public void setSelectedOperation(Operation operation){
        this.selectedOperation = operation;
    }

}
