package com.tulingxueyuan.stock.test;

import java.util.HashMap;
import java.util.Map;

public class MapDemo {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>(8);

        map.put("1", "1");
        map.put("1", "2");
        map.put("1", "3");
        map.put("1", "4");
        map.put("1", "5");
        map.put("1", "6");
        map.put("1", "7");
        map.put("1", "8");
        map.put("1", "9");
    }
}
