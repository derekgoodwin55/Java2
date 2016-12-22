import java.util.List;
import java.util.ArrayList;
import java.lang.*;

public class User implements IUser{
    ArrayList<IUser> FollowedUsers = new ArrayList<IUser>();
    ArrayList<IGroup> GroupList = new ArrayList<IGroup>();
    ArrayList<String> TweetList = new ArrayList<String>();
    String id;

    public User(){

    }

    @Override
    public void follow(IUser user) {

        FollowedUsers.add(user);
    }

    @Override
    public void unfollow(IUser user) {

        FollowedUsers.remove(user);
    }

    @Override
    public void addToGroup(IGroup group) {

        group.addUser(this);
        GroupList.add(group);
    }

    @Override
    public void removeFromGroup(IGroup group) {

        group.removeUser(this);
        GroupList.remove(group);
    }

    @Override
    public List<IUser> getFollowedUsers() {

        ArrayList<IUser> temp = new ArrayList<IUser>();
        for(int i = 0; i < FollowedUsers.size(); i++){
            if(this.FollowedUsers.contains(FollowedUsers.get(i))){
                temp.add(FollowedUsers.get(i));
            }
        }
        return temp;
    }

    @Override
    public List<IGroup> getGroups() {

        ArrayList<IGroup> temp1 = new ArrayList<IGroup>();
        for(int i = 0; i < GroupList.size(); i++){
            if(this.GroupList.contains(GroupList.get(i))){
                temp1.add(GroupList.get(i));
            }
        }
        return temp1;
    }

    @Override
    public List<IUser> getFollowedUsersInGroup(IGroup group) {

        ArrayList<IUser> temp2 = new ArrayList<IUser>();
        for(int i = 0; i < FollowedUsers.size(); i++){
            if(group.getUsers().contains(FollowedUsers.get(i))){
                temp2.add(FollowedUsers.get(i));
            }
        }
        return temp2;
    }

    @Override
    public void tweeted(String tweet) {

        TweetList.add(tweet);
    }

    @Override
    public String getLastTweet() {

        if(TweetList.size() < 1){
            return "";
        }

        return TweetList.get(TweetList.size()-1);
    }

    @Override
    public List<String> getTweetHistory() {

        return TweetList;
    }

    @Override
    public boolean equals(Object o) {

        boolean result = false;
        if(o instanceof User && this.id.equals(((User) o).getID())){
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
