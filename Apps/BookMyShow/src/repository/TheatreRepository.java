package repository;

import model.Theatre;

import java.util.HashMap;
import java.util.Map;

public class TheatreRepository {
    private final Map<String, Theatre> map = new HashMap<>();

    public void save(Theatre theatre){
        map.put(theatre.getId(), theatre);
    }

    public Theatre get(String id){
        return map.get(id);
    }
}
