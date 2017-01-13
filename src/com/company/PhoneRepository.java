package com.company;

import java.util.TreeMap;

/**
 * Created by asus on 12.01.2017.
 */
public class PhoneRepository {
    private TreeMap<String, String> phonesMap = new TreeMap<>();

    public void add(String name, String number) {
        phonesMap.put(number, name);
    }

    public TreeMap getAll() {
        return phonesMap;
    }

    public String searchByPhone(String data) {
        return phonesMap.get(data);
    }

    public String searchByName(String data) {
        for (String key : phonesMap.keySet()) {
            String value = phonesMap.get(key);
            if (value.equals(data))
                return key;
        }
        return null;
    }

    public boolean hasPhone(String phone) {
        return phonesMap.containsKey(phone);
    }

    public void printAll() {
        for (String key : phonesMap.keySet()) {
            System.out.println(String.format("%s:%s", key, phonesMap.get(key)));
        }
    }
}
