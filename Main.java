import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Map<String, Integer> ROMAN_NUMERALS = new HashMap<>() {{
        put("I", 1);
        put("II", 2);
        put("III", 3);
        put("IV", 4);
        put("V", 5);
        put("VI", 6);
        put("VII", 7);
        put("VIII", 8);
        put("IX", 9);
        put("X", 10);
    }};

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
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("Неверный ввод");
        }

        boolean isNum1Numeric = isNumeric(parts[0]);
        boolean isNum2Numeric = isNumeric(parts[2]);
        boolean isNum1Roman = isRoman(parts[0]);
        boolean isNum2Roman = isRoman(parts[2]);

        if ((isNum1Numeric && isNum2Roman) || (isNum1Roman && isNum2Numeric)) {
            throw new Exception("Калькулятор умеет работать только с однородными числами (только арабскими или только римскими)");
        }

        int num1, num2;
        if (isNum1Numeric) {
            num1 = Integer.parseInt(parts[0]);
        } else {
            num1 = ROMAN_NUMERALS.get(parts[0]);
        }

        if (isNum2Numeric) {
            num2 = Integer.parseInt(parts[2]);
        } else {
            num2 = ROMAN_NUMERALS.get(parts[2]);
        }

        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new Exception("Введите, пожалуйста, числа от 1 до 10 включительно");
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

        if (isNum1Numeric) {
            return String.valueOf(result);
        } else {
            if (result <= 0) {
                throw new Exception("Результат работы с римскими числами не может быть отрицательным или нулевым");
            }
            return toRoman(result);
        }
    }

    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+");
    }

    private static boolean isRoman(String str) {
        return ROMAN_NUMERALS.containsKey(str);
    }

    private static String toRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Римские цифры только от 1 до 3999");
        }
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : ROMAN_NUMERALS.entrySet()) {
            while (number >= entry.getValue()) {
                result.append(entry.getKey());
                number -= entry.getValue();
            }
        }
        return result.toString();
    }
}