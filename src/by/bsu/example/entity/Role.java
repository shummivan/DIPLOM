package by.bsu.example.entity;

public class Role extends Entity {
    private String name;
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null) return false;
        if (this.getClass()!=o.getClass()) return false;
        if (!super.equals(o)) return false;

        Role role = (Role) o;

        return getName() != null ? getName().equals(role.getName()) : role.getName() == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                '}';
    }
}
