package com.company;

import java.util.*;

public class PhoneRepository {
    private TreeMap<String, String> phonesMap = new TreeMap<>();

    public void add(String name, String number) {
        phonesMap.put(number, name);
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
}
