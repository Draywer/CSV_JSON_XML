package ru.idcore.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class JSONFile<T> {

    public String getJSONStringFromList(List<T> tList) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.toJson(tList);
    }

    public boolean writeJSONFile(String fileName, List<T> tList) throws IOException {
        boolean result = false;
        String jsonString = getJSONStringFromList(tList);

        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonString);
            fileWriter.flush();
            result = true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public String getJSONStringFromFile(String jsonFile) {
        String result = null;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        JsonParser parser = new JsonParser();
        try {
            result = gson.toJson(parser.parse(new FileReader(jsonFile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<T> getObjFromJSONFile(String jsonFile, Type userListType) {
        String jsonStringFromFile = getJSONStringFromFile(jsonFile);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        return gson.fromJson(jsonStringFromFile, userListType);
    }
}
