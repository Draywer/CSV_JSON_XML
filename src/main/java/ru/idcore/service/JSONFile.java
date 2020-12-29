package ru.idcore.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JSONFile<T> {
    public String getJSONString (List<T> tList) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.toJson(tList);
    }

    public boolean writeJSONFile (String jsonString, String fileName) throws IOException {
        boolean result = false;
        try(FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonString);
            fileWriter.flush();
            result = true;
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
