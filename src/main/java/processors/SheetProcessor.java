package processors;

import beans.SheetData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sql.Writer;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class SheetProcessor implements Serializable {
    private final List<SheetData> sheetData;
    private static final Logger LOGGER = LoggerFactory.getLogger(SheetProcessor.class);
    public SheetProcessor(List<SheetData> sheetData){
        this.sheetData = sheetData;
    }

    public void process(){
        this.sheetData.parallelStream().forEach(sheet->{
            LOGGER.info(sheet.printColumns());
            LOGGER.info(sheet.printRows());
            //Check for Insert Script
        });
    }
}
