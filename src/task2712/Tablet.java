package task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.ad.NoVideoAvailableException;
import com.javarush.task.task27.task2712.kitchen.Order;
import com.javarush.task.task27.task2712.kitchen.TestOrder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.logging.Level.SEVERE;

public class Tablet  {
    final int number;
    static Logger logger = Logger.getLogger(Tablet.class.getName());
    private LinkedBlockingQueue<Order> queue;

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }


    public Tablet(int number) {
        this.number = number;
    }

    public Order createOrder() {
        try {
            Order order = new Order(this);
            return getOrder(order);
        }
        catch (Exception e) {
            logger.log(SEVERE, "Console is unavailable.");
            return null;
        }
    }

    private Order getOrder(Order order) {
        if (!order.isEmpty()) {
            ConsoleHelper.writeMessage(order.toString());
            try {
                (new AdvertisementManager(order.getTotalCookingTime()*60)).processVideos();
            } catch (NoVideoAvailableException e) {
                logger.log(Level.INFO, "No video is available for the order " + order);
                // StatisticManager.getInstance().register(new NoAvailableVideoEventDataRow(order.getTotalCookingTime()));
            }
            queue.add(order);
            return order;
        }
        else
            return null;
    }


    public void createTestOrder(){
        try {

            getOrder(new TestOrder(this));

        }
        catch (Exception e) {
            logger.log(SEVERE, "Console is unavailable.");
        }

    }


    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }

}
