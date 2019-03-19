package task2712.statistic;

import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;


public class StatisticManager {
    private static StatisticManager ourInstance = new StatisticManager();
    private StatisticStorage statisticStorage = new StatisticStorage();

    public static StatisticManager getInstance() {
        return ourInstance;
    }

    private StatisticManager() {
    }

    public void register(EventDataRow data) {
        if (data != null) statisticStorage.put(data);
    }



    public Map<String, Double> reklama() {
        Map<String, Double> result = new TreeMap<>(Collections.reverseOrder());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (EventDataRow video : statisticStorage.getStorage(EventType.SELECTED_VIDEOS)) {
            VideoSelectedEventDataRow videoRow = (VideoSelectedEventDataRow) video;
            double allMoney = videoRow.getAmount();
            String data = sdf.format(videoRow.getDate());
            if (result.containsKey(data)) {
                result.put(data, result.get(data) + allMoney / 100.00);
            }
            else {
                result.put(data, allMoney / 100.00);
            }
        }
        return result;
    }

    public Map<String, Map<String, Integer>> povar() {
        Map<String, Map<String, Integer>> result = new TreeMap<>(Collections.reverseOrder());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        for (EventDataRow event : statisticStorage.getStorage(EventType.COOKED_ORDER)) {
            CookedOrderEventDataRow cookRow = (CookedOrderEventDataRow) event;
            String data = sdf.format(cookRow.getDate());
            String name = cookRow.getCookName();
            Integer time = (int) Math.ceil(cookRow.getTime() / 60.00);
            if (time > 0) {
                if (!result.containsKey(data)) {
                    Map<String, Integer> inner = new TreeMap<>();
                    inner.put(name, time);
                    result.put(data, inner);
                } else {
                    Map<String, Integer> inner = result.get(data);
                    if (!inner.containsKey(name)) {
                        inner.put(name, time);
                    } else {
                        inner.put(name, inner.get(name) + time);
                    }
                }
            }
        }
        return result;
    }

    private class StatisticStorage {
        Map<EventType, List<EventDataRow>> storage = new HashMap<>();

        public StatisticStorage() {

            for (EventType eventType : EventType.values()) {
                storage.put(eventType, new ArrayList<>());
            }
        }

        private void put(EventDataRow data) {
            storage.get(data.getType()).add(data);
        }

        public List<EventDataRow> getStorage(EventType eventType) {
            return storage.get(eventType);
        }
    }
}
