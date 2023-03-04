package cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import processors.Processor;
import xlsx.Reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;


public class Main {
    static Reader reader;
    static Map<Integer,String> fileExtensions = new LinkedHashMap<>();
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
      BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

      LOGGER.info("Choose your file extension");
      LOGGER.info("1. xlsx \n2. xls");
      int option =Integer.parseInt(bufferedReader.readLine());
      LOGGER.info("Enter File Name");


      //Get File Name
      String fileName = bufferedReader.readLine();
      reader = new Reader(fileName+fileExtensions.get(option));
      if(LOGGER.isInfoEnabled()){
          LOGGER.info(String.format("Number of sheets : %s",reader.getWorkbook().getNumberOfSheets()));
      }

      //Process
        Processor processor = new Processor();
        processor.init(reader.getWorkbook());
        processor.process();

    }

    static {
        fileExtensions.put(1,".xlsx");
        fileExtensions.put(2,".xls");
    }
}
