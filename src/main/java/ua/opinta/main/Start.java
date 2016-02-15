package ua.opinta.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.opinta.fileHandler.FileAnalytics;

import java.util.Map;

public class Start {
    public static void main(String[] args) {
        String folderPath = "c://Users/1/Downloads/";
        Map<String, Integer> filesTypeCount=null;

        /*
        // 1-st variant
        FileAnalytics fileAnalytics = new FileAnalytics();
        Map<String, Integer> filesTypeCount = fileAnalytics.getFilesTypeCount(folderPath);
        */

        // 2-nd variant
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"context.xml"});
        FileAnalytics fileAnalytics = (FileAnalytics)context.getBean("Analytics");
        fileAnalytics.getFilesTypeCount(folderPath);
        fileAnalytics.getListOfFiles(folderPath);

        //filesTypeCount = fileAnalytics.getFilesTypeCount(folderPath);

        //// result output
        //System.out.println(filesTypeCount);
        //System.out.println(fileAnalytics.getListOfFiles(folderPath));
    }
}
