package readWrite;
import service.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadWrite<T> {
    public ReadWrite() {}


    public List<T[]> readFromCsvFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<T[]> list = new ArrayList<>();
            String line = "";
            while ((line = reader.readLine()) != null) {
                T[] array = (T[]) line.split(",");
                list.add(array);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void writeToCsvFile(List<String[]> write, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            for (String[] strings : write) {
                for (int i = 0; i < strings.length; i++) {
                    writer.append(strings[i]);
                    if (i < (strings.length - 1))
                        writer.append(",");
                }
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName2="";
        for(int i=0;i<fileName.length();i++){
            if(fileName.charAt(i)=='.'){
                fileName2+='2';
                fileName2+=fileName.charAt(i);

            }
            else
                fileName2+=fileName.charAt(i);
        }
        try (FileWriter writer = new FileWriter(fileName2)) {
            for (String[] strings : write) {
                for (int i = 0; i < strings.length; i++) {
                    writer.append(strings[i]);
                    if (i < (strings.length - 1))
                        writer.append(",");
                }
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeToAudit(List<String> aWrite){
        try (FileWriter writer = new FileWriter("audit.csv")) {
            String line = "";
            for(String write: aWrite) {
                writer.append(write.split(",")[0]);
                writer.append(",");
                writer.append(write.split(",")[1]);
                writer.append(System.lineSeparator());
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
