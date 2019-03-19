package task2712;

import com.javarush.task.task27.task2712.ad.StatisticAdvertisementManager;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Locale;
import java.util.Map;

public class DirectorTablet {
    StatisticManager manager = StatisticManager.getInstance();
    StatisticAdvertisementManager advertisementManager = StatisticAdvertisementManager.getInstance();
    public void printAdvertisementProfit()   //какую сумму заработали на рекламе, сгруппировать по дням;
    { double total = 0.0;
        Map<String, Double> profit = manager.reklama();
        for (Map.Entry<String, Double>entry:profit.entrySet()){
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", entry.getKey(), entry.getValue()));
            total+=entry.getValue();
        }
        if (total>0)
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", total));
    }
    public void printCookWorkloading()   //загрузка (рабочее время) повара, сгруппировать по дням;
    {
        Map<String, Map<String, Integer>> loading = manager.povar();
        for (Map.Entry<String, Map<String, Integer>> load : loading.entrySet()) {
            ConsoleHelper.writeMessage(load.getKey());
            for (Map.Entry<String, Integer> inner : load.getValue().entrySet()) {
                //    int workTime = (int) Math.ceil(inner.getValue() / 60.0);
                ConsoleHelper.writeMessage(String.format("%s - %d min", inner.getKey(), inner.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet( )   //список активных роликов и оставшееся количество показов по каждому
    {
        Map<String, Integer> map = advertisementManager.activVideo();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            ConsoleHelper.writeMessage(entry.getKey()+" - "+entry.getValue());
        }
    }
    public void printArchivedVideoSet()  //список неактивных роликов (с оставшемся количеством показов равным нулю)
    {
        Map<String, Integer> map = advertisementManager.arhivVideo();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            ConsoleHelper.writeMessage(entry.getKey());
        }
    }


}
