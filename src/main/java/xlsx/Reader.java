package xlsx;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class Reader {
    private Workbook workbook;
    private static final Logger LOGGER = LoggerFactory.getLogger(Reader.class);
    public Reader(String fileName)  {
        try {
            File file = new File(fileName);
            this.workbook = new XSSFWorkbook(file);
        } catch (IOException | InvalidFormatException e) {
            LOGGER.error("Invalid File Path");
        }
    }


    public Workbook getWorkbook() {
        return workbook;
    }
}
