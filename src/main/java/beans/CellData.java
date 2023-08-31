package beans;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;

import java.io.Serializable;

public class CellData implements Serializable {
    private CellValue value;

    public CellData(String stringValue) {
        this.value = new CellValue(stringValue);
    }

    public CellData(CellValue cellValue) {
        this.value = cellValue;
    }

    public CellType getType() {
        return this.value.getCellType();
    }


    public CellValue getValue() {
        return value;
    }

    public void setValue(CellValue value) {
        this.value = value;
    }

    public String getStringValue() {
        return this.value.getStringValue();
    }

    public Double getNumericValue() {
        return this.value.getNumberValue();
    }

    public String printCellData() {
        if (this.value.getCellType() == CellType.STRING) {
            StringBuilder cellStringValue = new StringBuilder();
            cellStringValue.append("'").append(this.value.getStringValue()).append("'");
            return cellStringValue.toString();
        }

        if(this.value.getCellType() == CellType.NUMERIC){
            return String.valueOf((int) this.value.getNumberValue());
        }


        return this.value.getStringValue();
    }
}
