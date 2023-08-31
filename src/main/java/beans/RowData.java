package beans;

import org.apache.poi.ss.usermodel.CellValue;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class RowData implements Serializable {

    private final List<CellData> cellDataList;

    public RowData() {
        cellDataList = new LinkedList<>();
    }

    public void addCellValue(CellValue cellValue) {
        cellDataList.add(new CellData(cellValue));
    }

    public String printRow() {
        return String.join(SheetData.COMMA, this.cellDataList.stream().map(CellData::printCellData).toList());
    }
}
