package task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.util.List;

public class Order {


    private final Tablet tablet;

    public List<Dish> getDishes() {
        return dishes;
    }

    protected List<Dish> dishes;
    protected void initDishes(){
        dishes = ConsoleHelper.getAllDishesForOrder();
    }


    public Order(Tablet tablet) {
        this.tablet = tablet;
       initDishes();
    }
    public Tablet getTablet() {
        return tablet;
    }

    @Override
    public String toString() {
        if (dishes.isEmpty()) return null;
        else return String.format("Your order: %s of %s", dishes, tablet);
    }

    public int getTotalCookingTime() {
        int timeCooking = 0;
        for (Dish dish : dishes) {
            timeCooking += dish.getDuration();
        }
        return timeCooking;
    }

    public boolean isEmpty() {
        return dishes.isEmpty();
    }


}
