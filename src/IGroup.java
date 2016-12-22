import java.util.List;


public interface IGroup extends Identifiable {
    /**
     * Retrieve the list of users in the current group. These
     * should be the users added by the addUser() method.
     *
     * @return The list of users in the current group. If there
     * are no users in the group, an empty list should be returned.
     */
	public List<IUser> getUsers();

    /**
     * Add the user, user, to the current list of users
     * in this group.
     *
     * @param user The user to add
     */
	public void addUser(IUser user);

    /**
     * Remove the user, user, from the current list of
     * users.
     *
     * @param user The user to remove
     */
	public void removeUser(IUser user);

    /**
     * Get the total number of users in this group.
     *
     * @return The total number of users in this group
     */
	public int getUserCount();

    public boolean equals(Object o);

    public String toString();
}
