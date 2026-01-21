package repository;

import model.Show;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowRepository {
    private final Map<String, Show> map = new HashMap<>();

    public void save(Show show){
        map.put(show.getId(), show);
    }

    public Show get(String id){
        return map.get(id);
    }

    public List<Show> getAll(){
        return new ArrayList<>(map.values());
    }
}
