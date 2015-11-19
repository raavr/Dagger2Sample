package pl.rr.dagger2sample.model;

/**
 * Created by Rafal on 2015-06-17.
 */
public class Repository {

    private int id;
    private String name;

    public Repository(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
