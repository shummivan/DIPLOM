package by.bsu.example.entity;

public class Command extends Entity {

    private String name;
    private boolean menuItem;

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (menuItem ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Command{" +
                "name='" + name + '\'' +
                ", menuItem=" + menuItem +
                '}';
    }

    public boolean isMenuItem() {
        return menuItem;
    }

    public void setMenuItem(boolean menuitem) {
        this.menuItem = menuitem;
    }

    public Command(){}

    public Command(int id, String name){
        super(id);
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o==null) return false;
        if (this.getClass()!=o.getClass()) return false;
        if (!super.equals(o)) return false;

        Command command = (Command) o;

        return getName() != null ? getName().equals(command.getName()) : command.getName() == null;

    }


}

