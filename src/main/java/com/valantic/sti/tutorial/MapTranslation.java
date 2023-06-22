package com.valantic.sti.tutorial;

import java.util.Map;

public class MapTranslation implements Translation {
    
    private final Map<String, String> map;

    MapTranslation(final Map<String, String> data) {
        map = data;
    }

    @Override
    public String translate(final String key) {
        return map.getOrDefault(key, "NO_TRANSLATION");
    }
}
