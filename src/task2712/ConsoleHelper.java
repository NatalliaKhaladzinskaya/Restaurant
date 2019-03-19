package task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {

        String line= reader.readLine();return line;
    }

    public static List<Dish> getAllDishesForOrder() {
        writeMessage(Dish.allDishesToString());
        List<Dish> list = new ArrayList<>();
        writeMessage("Пожалуйста, выберите блюдо");
        try{String dish = readString();
            while (!dish.equals("exit")) {
                try {
                    list.add(Dish.valueOf(dish));
                    writeMessage("Добавлено, чего еще желаете?");
                } catch (IllegalArgumentException e) {
                    writeMessage("Такого блюда нет, сделайте выбор");
                }
                dish = readString();
            }}
        catch (IOException e){}
        return list;
    }

}
