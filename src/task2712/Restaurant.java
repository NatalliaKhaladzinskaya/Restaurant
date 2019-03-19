package task2712;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Замена чисел
*/

public class Restaurant {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             BufferedReader reader2 = new BufferedReader(new FileReader(reader.readLine()))) {
            reader2.lines().forEach(new Consumer<String>() {
                @Override
                public void accept(String line) {
                    Pattern pattern = Pattern.compile(("\\b\\d+\\b"));
                    Matcher matcher = pattern.matcher(line);
                    String newString = line;
                    while (matcher.find()) {
                        String gr = matcher.group();
                        int number = Integer.parseInt(gr);
                        if (map.containsKey(number)) {
                            Pattern pattern2 = Pattern.compile((gr + "\\b"));
                            Matcher matcher2 = pattern2.matcher(line);
                            line = matcher2.replaceAll(map.get(number));
                        }
                    }
                    System.out.println(line);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
