import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            try {
                String result = calc(input);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("Произошла ошибка: " + e.getMessage());
            }
        }
    }

    public static String calc(String input) throws Exception {
        Map<String, Integer> romanNumerals = new HashMap<>();
        romanNumerals.put("I", 1);
        romanNumerals.put("II", 2);
        romanNumerals.put("III", 3);
        romanNumerals.put("IV", 4);
        romanNumerals.put("V", 5);
        romanNumerals.put("VI", 6);
        romanNumerals.put("VII", 7);
        romanNumerals.put("VIII", 8);
        romanNumerals.put("IX", 9);
        romanNumerals.put("X", 10);

        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("Неверный ввод");
        }

        int num1, num2;
        if (isNumeric(parts[0])) {
            num1 = Integer.parseInt(parts[0]);
        } else if (romanNumerals.containsKey(parts[0])) {
            num1 = romanNumerals.get(parts[0]);
        } else {
            throw new Exception("Неверное значение: " + parts[0]);
        }

        if (isNumeric(parts[2])) {
            num2 = Integer.parseInt(parts[2]);
        } else if (romanNumerals.containsKey(parts[2])) {
            num2 = romanNumerals.get(parts[2]);
        } else {
            throw new Exception("Неверное значение: " + parts[2]);
        }

        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new Exception("Вводите, пожалуйста, числа от 1 до 10 включительно");
        }

        int result;
        String operation = parts[1];
        switch (operation) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                throw new Exception("Неверная операция");
        }

        if (isNumeric(parts[0]) && isNumeric(parts[2])) {
            return String.valueOf(result);
        } else {
            return toRoman(result);
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private static String toRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Римские цифры только от 1 до 13");
        }
        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] romanValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
        StringBuilder result = new StringBuilder();
        int i = romanSymbols.length - 1;
        while (number > 0) {
            if (number - romanValues[i] >= 0) {
                result.append(romanSymbols[i]);
                number -= romanValues[i];
            } else {
                i--;
            }
        }
        return result.toString();
    }
}