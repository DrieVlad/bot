package boostbrain;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GameContentFromFile {
    
    public Map<String, ArrayList<String>> dictContentForGames = new HashMap<String, ArrayList<String>>();
    
    GameContentFromFile(String folderName)
    {
        String path = System.getProperty("user.dir") + "\\content\\" + folderName + "\\";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
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
