package task2712.ad;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class StatisticAdvertisementManager {
    AdvertisementStorage advertisementStorage = AdvertisementStorage.getInstance();
    private static StatisticAdvertisementManager ourInstance = new StatisticAdvertisementManager();

    public static StatisticAdvertisementManager getInstance() {
        return ourInstance;
    }

    private StatisticAdvertisementManager() {
    }

    public Map<String, Integer> activVideo(){
        Map<String,Integer>map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        List<Advertisement> list = advertisementStorage.list();

        for (Advertisement a:list){
            if (a.getHits()>0) map.put(a.getName(), a.getHits());
        }
        return map;
    }

    public Map<String, Integer> arhivVideo(){
        List<Advertisement> list = advertisementStorage.list();
        Map<String,Integer>map = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (Advertisement a:list){
            if (a.getHits()==0) map.put(a.getName(), a.getHits());
        }
        return map;
    }
}
