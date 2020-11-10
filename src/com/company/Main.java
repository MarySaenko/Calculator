package com.company;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

    public class Main {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите арабские или римские цифры от 1 до 10 и операцию (Пример: 2 + 2): ");

            String num1 = scanner.next();
            char operation = scanner.next().charAt(0);
            String num2 = scanner.next();

            String text = "1,2,3,4,5,6,7,8,9,10";
            int i = text.indexOf(num1);
            int j = text.indexOf(num2);

            if (( i > 0 & i < text.length()) && (j > 0 & j < text.length())) {
                int x = Integer.parseInt(num1);
                int y = Integer.parseInt(num2);
                int answer;

                switch (operation) {
                    case '+':
                        answer = x + y;
                        break;
                    case '-':
                        answer = x - y;
                        break;
                    case '*':
                        answer = x * y;
                        break;
                    case '/':
                        answer = x / y;
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + operation);
                    }
                    System.out.println("Ответ: " + answer);

            } else {
                int a = RomanArabicConverter.romanToArabic(num1);
                int b = RomanArabicConverter.romanToArabic(num2);
                int answer;

                switch (operation) {
                        case '+':
                            answer = a + b;
                            break;
                        case '-':
                            answer = a - b;
                            break;
                        case '*':
                            answer = a * b;
                            break;
                        case '/':
                            answer = a / b;
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + operation);
                }
                System.out.println("Ответ: " + RomanArabicConverter.arabicToRoman(answer));
            }
        }
        public static class RomanArabicConverter {
            public static int romanToArabic(String input) {
                String romanNumeral = input.toUpperCase();
                int result = 0;

                List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

                int i = 0;

                while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
                    RomanNumeral symbol = romanNumerals.get(i);
                    if (romanNumeral.startsWith(symbol.name())) {
                        result += symbol.getValue();
                        romanNumeral = romanNumeral.substring(symbol.name().length());
                    } else {
                        i++;
                    }
                }

                if (romanNumeral.length() > 0) {
                    throw new IllegalArgumentException(input + " cannot be converted to a Roman Numeral");
                }

                return result;
            }
                public static String arabicToRoman(int number) {
                    if ((number <= 0) || (number > 4000)) {
                        throw new IllegalArgumentException(number + " is not in range (0,4000]");
                    }

                    List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

                    int i = 0;
                    StringBuilder sb = new StringBuilder();

                    while ((number > 0) && (i < romanNumerals.size())) {
                        RomanNumeral currentSymbol = romanNumerals.get(i);
                        if (currentSymbol.getValue() <= number) {
                            sb.append(currentSymbol.name());
                            number -= currentSymbol.getValue();
                        } else {
                            i++;
                        }
                    }

                    return sb.toString();
                }
                enum RomanNumeral {
                    I(1), IV(4), V(5), IX(9), X(10),
                    XL(40), L(50), XC(90), C(100),
                    CD(400), D(500), CM(900), M(1000);

                    private int value;

                    RomanNumeral(int value) {
                        this.value = value;
                    }

                    public int getValue() {
                        return value;
                    }

                    public static List<RomanNumeral> getReverseSortedValues() {
                        return Arrays.stream(values())
                                .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                                .collect(Collectors.toList());
                    }

                }
            }
        }


