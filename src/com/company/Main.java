package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static PhoneRepository phoneRepository = new PhoneRepository();

    public static void main(String[] args) {
        for (; ; ) {
            try {
                System.out.println("Введите строку для поиска:");
                String input = reader.readLine().trim();
                if (!input.isEmpty()) {
                    if (input.equals("LIST")) {
                        phoneRepository.printAll();
                    } else if (Validator.isName(input) && Validator.isNumber(input)) {
                        System.out.println("Данная строка может привести к колизии данных, поэтому она не может быть использована");
                    } else if (Validator.isName(input)) {
                        String result = phoneRepository.searchByName(input);
                        if (result != null)
                            System.out.println(result);
                        else
                            savePhone(input);
                    } else if (Validator.isNumber(input)) {
                        String result = phoneRepository.searchByPhone(input);
                        if (result != null)
                            System.out.println(result);
                        else
                            saveName(input);
                    } else
                        System.out.println("Введена непонятная строка");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void saveName(String input) throws IOException {
        boolean in_progress = true;
        while (in_progress) {
            System.out.println("Введите имя данного абонента");
            String number = reader.readLine().trim();
            if (number.equals("quit"))
                in_progress = false;
            if (!Validator.isName(number)) {
                System.out.println("Введено некоректное имя");
            } else {
                phoneRepository.add(input, number);
                System.out.println("Имя успешно сохранено");
                in_progress = false;
            }
        }
    }

    private static void savePhone(String input) throws IOException {
        boolean in_progress = true;
        while (in_progress) {
            System.out.println("Введите номер для данного абонента");
            String number = reader.readLine().trim();
            if (number.equals("quit"))
                in_progress = false;
            if (!Validator.isNumber(number) || phoneRepository.hasPhone(number)) {
                System.out.println("Введен некоректный номер");
            } else {
                phoneRepository.add(input, number);
                System.out.println("Номер успешно сохранен");
                in_progress = false;
            }
        }

    }

}
