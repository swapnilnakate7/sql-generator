package cli;

import org.apache.logging.log4j.LogManager;
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

      System.out.println("Choose your file extension");
      System.out.println("1. xlsx \n2. xls");
      int option =Integer.valueOf(bufferedReader.readLine());
      System.out.println("Enter File Name");


      //Get File Name
      String fileName = bufferedReader.readLine();
      reader = new Reader(fileName+fileExtensions.get(option));
      System.out.println("Number of sheets : "+reader.getWorkbook().getNumberOfSheets());
      //Process
        Processor processor = new Processor();
        processor.process(reader.getWorkbook());


    }

    static {
        fileExtensions.put(1,".xlsx");
        fileExtensions.put(2,".xls");
    }
}
