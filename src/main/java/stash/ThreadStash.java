package stash;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public final class ThreadStash {
    private Map<String, Object> stash = null;


    public void put(String key, Object value) {
        stash.put(key, value);
    }

    public Object get(String key) {
        return getOrDefault(key, null);
    }

    public void clear() {
        if (stash instanceof HashMap) stash.clear();
        else stash = new HashMap<>();
        setPredefinedValues();
    }

    public Object getOrDefault(String key, Object defaultValue) {
        return stash.getOrDefault(key, defaultValue);
    }

    public ThreadStash() {
        setPredefinedValues();
    }

    private void setPredefinedValues() {
        if (!(stash instanceof HashMap)) stash = new HashMap<>();
        final String FORMAT_RU = "dd.MM.yyyy";
        LocalDate today = LocalDate.now();
        LocalDate yesterday = today.minus(Period.ofDays(1));
        stash.put("вчерашняя дата (ISO)", today.minus(Period.ofDays(1)));
        stash.put("вчерашняя дата", yesterday.format(DateTimeFormatter.ofPattern(FORMAT_RU)));
        stash.put("текущая дата (ISO)", today);
        stash.put("текущая дата", today.format(DateTimeFormatter.ofPattern(FORMAT_RU)));
    }
}
