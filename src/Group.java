import java.util.List;
import java.util.ArrayList;
import java.lang.*;

public class Group implements IGroup {
    ArrayList<IUser> Users = new ArrayList<IUser>();
    String id;

    public Group(){

    }

    @Override
    public List<IUser> getUsers() {

        return Users;
    }

    @Override
    public void addUser(IUser user) {

        Users.add(user);
    }

    @Override
    public void removeUser(IUser user) {

        Users.remove(user);
    }

    @Override
    public int getUserCount() {

        return Users.size();
    }

    @Override
    public boolean equals(Object o) {

        boolean result = false;
        if(o instanceof Group && this.id.equals(((Group) o).getID())){
            result = true;
        }
        return result;
    }

    @Override
    public String getID() {

        return this.id;
    }

    @Override
    public void setID(String id) {

        this.id = id;
    }

    @Override
    public int compareTo(Identifiable i) {

        return this.id.compareTo(i.getID());
    }

    public String toString(){

        return this.id;
    }
}
