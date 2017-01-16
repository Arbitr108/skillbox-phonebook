package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static PhoneRepository phoneRepository = new PhoneRepository();

    public static void main(String[] args) {
        for (; ; ) {
            try {
                System.out.println("Введите строку для поиска:");
                String input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    if (input.equals("quit"))
                        break;
                    if (input.equals("LIST")) {
                        phoneRepository.printAll();
                    } else if (Validator.isNumber(input)) {
                        String result = phoneRepository.searchByPhone(input);
                        if (result != null)
                            System.out.println(result);
                        else
                            saveName(input);
                    } else if (Validator.isName(input)) {
                        TreeMap<String, String> result = phoneRepository.searchByName(input);
                        if (result != null) {
                            for (String key : result.keySet())
                                System.out.println(String.format("%s : %s", result.get(key), key));
                        }
                        else
                            savePhone(input);
                    } else {
                        System.out.println("Непонятный ввод, попробуйте еще раз");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveName(String input) throws IOException {
        while (true) {
            System.out.println("Введите имя данного абонента");
            String name = reader.readLine().trim();
            if (name.equals("quit"))
                break;
            if (!Validator.isName(name)) {
                System.out.println("Введено некорректное имя");
            } else {
                phoneRepository.add(name, input);
                System.out.println("Имя успешно сохранено");
                break;
            }
        }
    }

    private static void savePhone(String input) throws IOException {
        while (true) {
            System.out.println("Введите номер для данного абонента");
            String number = reader.readLine().trim();
            if (number.equals("quit"))
                break;
            if (!Validator.isNumber(number)) {
                System.out.println("Введен некорректный номер");
            } else if (phoneRepository.hasPhone(number)) {
                System.out.println("Номер уже существует. Заменить(y/n)?");
                String command = reader.readLine().trim();
                if (command.equals("y")) {
                    String name = phoneRepository.searchByPhone(number);
                    phoneRepository.add(name, number);
                    System.out.println("Данные успешно изменены");
                    break;
                }

            } else {
                phoneRepository.add(input, number);
                System.out.println("Номер успешно сохранен");
                break;
            }
        }

    }

}
