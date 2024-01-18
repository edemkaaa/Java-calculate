import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            try {
                String result = calc(input);
                System.out.println(result);

            } catch (Exception e) {
                System.out.println("throws Exception");
            }
        }
    }

    /**
     * Калькулятор, выполняющий операции сложения, вычитания, умножения и деления с двумя числами.
     *
     * @param input строка с арифметическим выражением
     * @return строка с результатом выполнения выражения
     * @throws Exception в случае ошибок проверки условий
     */
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
            throw new Exception("Invalid input");
        }

        String operation = parts[1];
        if (!operation.equals("+") && !operation.equals("-") && !operation.equals("*") && !operation.equals("/")) {
            throw new Exception("Invalid operation");
        }

        int num1, num2;
        if (isNumeric(parts[0]) && isNumeric(parts[2])) {
            num1 = Integer.parseInt(parts[0]);
            num2 = Integer.parseInt(parts[2]);
        } else if (romanNumerals.containsKey(parts[0]) && romanNumerals.containsKey(parts[2])) {
            num1 = romanNumerals.get(parts[0]);
            num2 = romanNumerals.get(parts[2]);
        } else {
            throw new Exception("Input contains both Roman and Arabic numerals");
        }

        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new Exception("Numbers must be between 1 and 10");
        }

        int result;
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
                throw new Exception("Invalid operation");
        }

        if (isNumeric(parts[0]) && isNumeric(parts[2])) {
            return String.valueOf(result);
        } else {
            return toRoman(result);
        }
    }

    /**
     * Проверяет, является ли строка числом.
     *
     * @param str проверяемая строка
     * @return true, если строка является числом, иначе - false
     */
    private static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Преобразует число в римскую систему счисления.
     *
     * @param number число
     * @return строка с числом в римской системе счисления
     */
    private static String toRoman(int number) {
        if (number < 1) {
            throw new IllegalArgumentException("Roman numerals can't represent zero or negative numbers");
        }
        if (number > 10) {
            throw new IllegalArgumentException("Roman numerals can only represent numbers between 1 and 10");
        }
        String[] romanOnes = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        return romanOnes[number];
    }
}