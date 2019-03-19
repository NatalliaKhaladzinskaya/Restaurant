package task2712.kitchen;

import com.javarush.task.task27.task2712.Tablet;

import java.util.ArrayList;

public class TestOrder extends Order {
    public TestOrder(Tablet tablet) {
        super(tablet);
    }

    protected void initDishes()  {
        //Определим случайное количество блюд от 1 до общего количества в меню :)
        int countRandomDish = (int)(1 + (Math.random() * Dish.values().length));
        dishes = new ArrayList<>();
        while (countRandomDish > 0)
        {
            //Выбираем случайное блюдо
            int indexRandom = (int)(Math.random() * Dish.values().length);
            //Помещаем его в список
            dishes.add(Dish.values()[indexRandom]);
            countRandomDish--;
        }
    }
}
