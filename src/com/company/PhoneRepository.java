package com.company;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.*;

public class PhoneRepository {
    private TreeMap<String, String> phonesMap = new TreeMap<>();

    public void add(String name, String number) {
        phonesMap.put(number, name);
    }

    public String update(String name, String number) {
        return phonesMap.replace(number, name);
    }

    public String searchByPhone(String data) {
        return phonesMap.get(data);
    }

    public TreeMap<String, String> searchByName(String data) {
        TreeMap<String, String> resultMap = new TreeMap<>();
        for (String key : phonesMap.keySet()) {
            if (phonesMap.get(key).equals(data))
                resultMap.put(key, data);
        }
        return resultMap;
    }

    public boolean hasPhone(String phone) {
        return phonesMap.containsKey(phone);
    }

    public void printAll() {
        HashMap<String, String> resultMap = new HashMap<>();
        for (String key : phonesMap.keySet()) {
            resultMap.put(key, phonesMap.get(key));
        }
        resultMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(e -> System.out.println(String.format("%s :%s", e.getValue(), e.getKey())));
    }

    public void export(File exportFile) throws IOException {
        if (exportFile.getParentFile() != null) {
            if (!exportFile.getParentFile().exists())
                if (!exportFile.getParentFile().mkdirs()) {
                    System.out.println("Could not create parent directory");
                }
        }
        JSONArray array = new JSONArray();
        for (String key : phonesMap.keySet()) {
            JSONObject object = new JSONObject();
            object.put("name", phonesMap.get(key));
            object.put("phone", key);
            array.add(object.toJSONString());
        }

        try (BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(exportFile), "UTF-8"))) {
            array.writeJSONString(fileWriter);
        }
    }

    public int size() {
        return phonesMap.size();
    }
}
