package processors;

import beans.RowData;
import beans.SheetData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.Writer;

import java.util.LinkedList;
import java.util.List;

public class InsertScriptProcessor extends SheetProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(InsertScriptProcessor.class);

    public InsertScriptProcessor(List<SheetData> sheetData) {
        super(sheetData);
    }

    public void process() {
        //TODO Read columns from Each sheetData and prepare the .sql file generation mechanism
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

        script.append("INSERT INTO ").append(tableName)
                .append(" ( ").append(columns).append(" ) ")
                .append(" VALUES ").append(" ( ").append(rowData.printRow()).append(" );");
        return script.toString();
    }

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

        return String.join("\n", rows);
    }

}
