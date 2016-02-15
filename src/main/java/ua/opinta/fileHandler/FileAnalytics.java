package ua.opinta.fileHandler;

import org.springframework.stereotype.Component;
import ua.opinta.annotation.ShowResult;
import ua.opinta.annotation.ShowTime;

import java.io.File;
import java.util.*;

@Component(value = "Analytics")
public class FileAnalytics {

    @ShowTime
    public List<File> getListOfFiles(String pathFolder) {
        List<File> listOfFiles;

        File folder = new File(pathFolder);

        listOfFiles = Arrays.asList(folder.listFiles());

        return listOfFiles;
    }

    private List<String> getListOfExtensions(List<File> listOfFiles) {
        List<String> listOfExt = new ArrayList<>();

        for (File file : listOfFiles) {
            String nameOfFile = file.getName();
            int indexOfExt = nameOfFile.lastIndexOf('.');
            if (indexOfExt>=0) {
                listOfExt.add(nameOfFile.substring(indexOfExt + 1));
            }
        }

        return listOfExt;
    }

    @ShowTime
    @ShowResult
    public Map<String, Integer> getFilesTypeCount(String pathFolder) {
        Map<String, Integer> filesTypeCount = new HashMap<>();

        List<File> listOfFiles = getListOfFiles(pathFolder);
        List<String> listOfExt = getListOfExtensions(listOfFiles);
        for (String ext : listOfExt) {
            Object currentCount = filesTypeCount.get(ext);
            if (currentCount==null) {
                currentCount = 0;
            }
            filesTypeCount.put(ext, (Integer) currentCount+1);
        }

        return filesTypeCount;
    }
}
