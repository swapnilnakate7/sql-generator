package processors;

import beans.CellData;
import beans.RowData;
import beans.SheetData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class UpdateScriptProcessor extends SheetProcessor{

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateScriptProcessor.class);


    public UpdateScriptProcessor(List<SheetData> sheetData) {
        super(sheetData);
    }

    @Override
    public void process() {
        this.sheetData.parallelStream().forEach(sheet -> {
            LOGGER.info(sheet.printColumns());
            LOGGER.info(sheet.printRows());
            //Check for Insert Script
            //INSERT INTO {TABLE_NAME} (COLUMNS) VALUES ()

            String rows = generateRows(sheet);
            this.writer.write(sheet.getSheetName(), rows);

            LOGGER.info(rows);
        });
    }

    /**
     * Generate row
     *
     * @param tableName -
     * @param columns   -
     * @param rowData   -
     * @return printable row string
     */
    @Override
    protected String generateRow(String tableName, String columns, RowData rowData) {
        StringBuilder script = new StringBuilder(16);


        script.append("UPDATE ").append(tableName);
        List<String> columnList = Arrays.asList(columns.split(","));
        List<CellData> cellDataList = rowData.getCellDataList();
        int idx=1;
        Iterator<String> columnIterator = columnList.subList(1,columnList.size()).listIterator();
        while (columnIterator.hasNext()){
           String column= columnIterator.next();
           if(idx==1){
               script.append(" SET ").append(column);
           }
            script.append(" = ").append(cellDataList.get(idx).printCellData());

            if(columnIterator.hasNext()){
                script.append(" , ");
            }
            idx++;
        }

                script.append(" WHERE ").append(columnList.get(0))
                .append(" = ").append(cellDataList.get(0).printCellData());
        return script.toString();    }

    /**
     * Generates Multiple rows based on file type internally calls generateRow method
     *
     * @param sheetData - contains data of respective sheet
     * @return multiple rows
     */
    @Override
    protected String generateRows(SheetData sheetData) {
        List<String> rows = new LinkedList<>();
        sheetData.getRows().forEach(rowData -> {
            rows.add(generateRow(sheetData.getSheetName(), sheetData.printColumns(), rowData));
        });

        return String.join("\n", rows);    }
}
