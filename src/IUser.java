import java.util.List;
import java.util.ArrayList;


public interface IUser extends Identifiable {
    /**
     * Adds the user, user, to the list of followed users.
     *
     * @param user The user to follow
     */
    public void follow(IUser user);

    /**
     * Remove the user, user, from the list of followed users.
     *
     * @param user The user to stop following
     */
    public void unfollow(IUser user);

    /**
     * Adds the current user to the specified group, group. This requires
     * not only adding group to the list of groups that the current user
     * belongs to, but also add the current user to the members
     * of the group, group.
     *
     * @param group The group to add the user to
     */
    public void addToGroup(IGroup group);

    /**
     * Removes the current user from the specified group, group. This requires
     * not only removing the group from the list of groups that the current
     * user belongs to, but also removing the the current user from the members
     * of the group, group.
     *
     * @param group The group to remove the user from
     */
    public void removeFromGroup(IGroup group);

    /**
     * Retrieve a list of users followed by this user. These should be
     * the users added by the follow() method.
     *
     * @return The list of current users followed
     */
    public List<IUser> getFollowedUsers();

    /**
     * Retrieve a list of groups from this user. These should be
     * the groups added by the addToGroup() method.
     *
     * @return The list of current groups the user is in. If the user
     * is not in any groups, an empty list should still be returned.
     */
    public List<IGroup> getGroups();

    /**
     * Retrieve a list of followed users that are in the specified group
     *
     * @param group The group for which to return followed from.
     *
     * @return The list of followed users in the specifed group. If this user doesn't follow
     * anyone in that group, return an empty list.
     */
    public List<IUser> getFollowedUsersInGroup(IGroup group);

    /**
     * Store the string, tweet, at the end of the user's tweet history (tweet list).
     *
     * @param tweet The most recent tweet this user made
     */
    public void tweeted(String tweet);

    /**
     * Get the last tweet this user made (the tweet at the end of the tweet list).
     *
     * @return The last tweet this user made
     */
    public String getLastTweet();

    /**
     * Get the user's tweet history (tweet list)
     *
     * @return The user's tweet history
     */
    public List<String> getTweetHistory();

    public boolean equals(Object o);

    public String toString();
}
