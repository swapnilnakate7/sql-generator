package processors;

import beans.RowData;
import beans.SheetData;

import java.util.List;

public class DeleteScriptProcessor extends SheetProcessor{

    public DeleteScriptProcessor(List<SheetData> sheetData) {
        super(sheetData);
    }

    @Override
    public void process() {
        //TODO Read columns from Each sheetData and prepare the .sql file generation mechanism
        /*
         Use Column data to identify the row
         */
    }

    /**
     * Generator Row based on script type
     *
     * @param columns     - List of columns extracted from file
     * @param rowDataList - List of rowData for a particular sheet
     * @return row string
     */
    @Override
    protected String generateRow(List<String> columns, List<RowData> rowDataList) {
        return null;
    }

    /**
     * Generates Multiple rows based on file type internally calls generateRow method
     *
     * @param sheetData - contains data of respective sheet
     * @return multiple rows
     */
    @Override
    protected String generateRows(SheetData sheetData) {
        return null;
    }
}
