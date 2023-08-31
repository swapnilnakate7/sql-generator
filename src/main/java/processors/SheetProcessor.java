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
    protected final List<SheetData> sheetData;
    protected Writer writer;

    protected Operation selectedOperation;
    private static final Logger LOGGER = LoggerFactory.getLogger(SheetProcessor.class);

    public SheetProcessor(List<SheetData> sheetData) {
        this.sheetData = sheetData;
        this.writer = new Writer();
    }

    public abstract void process();


    /**
     * Generate row
     * @param tableName -
     * @param columns -
     * @param rowData -
     * @return printable row string
     */
    protected abstract String generateRow(String tableName,String columns, RowData rowData);

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
