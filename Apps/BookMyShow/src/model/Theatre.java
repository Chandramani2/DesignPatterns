package model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Theatre {
    private final String id;
    private final String name;
    private final Map<String, Screen> screens = new HashMap<>();

    public Theatre(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void addScreen(Screen screen) {
        screens.put(id, screen);
    }

    public Screen getScreen(String id) {
        return screens.get(id);
    }
}
