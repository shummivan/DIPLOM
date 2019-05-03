package by.bsu.example.entity;

import java.io.Serializable;
public abstract class Entity implements Serializable, Cloneable {

    protected Integer id=null;
    public Entity() {
    }

    public Entity(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null) return false;
        if (this.getClass()!=o.getClass()) return false;

        Entity entity = (Entity) o;

        return getId() != null ? getId().equals(entity.getId()) : entity.getId() == null;

    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "id=" + id +
                '}';
    }
}
