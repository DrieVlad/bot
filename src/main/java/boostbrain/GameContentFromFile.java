package boostbrain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class GameContentFromFile {
    
    public Map<String, ArrayList<String>> dictContentForGames = new HashMap<String, ArrayList<String>>();
    
    GameContentFromFile(String folderName)
    {
        String path = System.getProperty("user.dir") + "\\content\\" + folderName + "\\";
        System.out.println(System.getProperty("user.dir"));
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            System.out.println(1);
            if (file.isFile()) {
                String fileName = file.getName();
                String letter = String.valueOf(fileName.substring(0, fileName.indexOf('.')));
                try (FileInputStream fstream = new FileInputStream(path + fileName)) {

                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    String strLine;
                    ArrayList<String> towns = new ArrayList<String>();
                    while ((strLine = br.readLine()) != null) {
                        towns.add(strLine);
                    }
                    dictContentForGames.put(letter, towns);
                    br.close();
                } catch (IOException e) {
                    System.out.println("Ошибка");
                }
            }
        }
    }
}
