package processors;

import beans.RowData;
import beans.SheetData;
import enums.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.Writer;

import java.io.Serializable;
import java.util.List;

public abstract class SheetProcessor implements Serializable {
    private final List<SheetData> sheetData;
    protected Writer writer;

    protected Operation selectedOperation;
    private static final Logger LOGGER = LoggerFactory.getLogger(SheetProcessor.class.getName());

    public SheetProcessor(List<SheetData> sheetData) {
        this.sheetData = sheetData;
        this.writer = new Writer();
    }

    public void process() {
        this.sheetData.parallelStream().forEach(sheet -> {
            LOGGER.info(sheet.printColumns());
            LOGGER.info(sheet.printRows());
            //Check for Insert Script
        });
    }


    /**
     * Generator Row based on script type
     *
     * @param columns     - List of columns extracted from file
     * @param rowDataList - List of rowData for a particular sheet
     * @return row string
     */
    protected abstract String generateRow(List<String> columns, List<RowData> rowDataList);

    /**
     * Generates Multiple rows based on file type internally calls generateRow method
     *
     * @param sheetData - contains data of respective sheet
     * @return multiple rows
     */
    protected abstract String generateRows(SheetData sheetData);

    public void setSelectedOperation(Operation operation){
        this.selectedOperation = operation;
    }
}
