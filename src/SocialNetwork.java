import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SocialNetwork {
	private List<Identifiable> users;
	private List<Identifiable> groups;
	
	public SocialNetwork() {
		users = new ArrayList<Identifiable>();
		groups = new ArrayList<Identifiable>();
		
	}
	
	public IGroup getGroup(String groupID) {
		return (IGroup)groups.get(findIndex(groupID,groups));
	}
	
	public int getUserCount() {
		return users.size();
	}
	
	public int getGroupCount() {
		return groups.size();
	}
	
	public IUser getUser(String userID) {
		return (IUser)users.get(findIndex(userID,users));
	}
	
	public IUser getUser(int i) {
		return (IUser)users.get(i);
	}
	
	public IGroup getGroup(int i) {
		return (IGroup)groups.get(i);
	}
	
	public void addGroups(List<IGroup> gs) {
		for (int i = 0; i < gs.size(); ++i) {
			this.addGroup(gs.get(i));
		}
	}
	
	public int addGroup(IGroup group) {
		if (groups.isEmpty()) {
			groups.add(group);
			return 0;
		} else {
			int i = findIndex(group.getID(),groups);
			groups.add(i,group);
			return i;
		}
	}
	
	public void addUsers(List<IUser> us) {
		for (int i = 0; i < us.size(); ++i) {
			this.addUser(us.get(i));
		}
	}
	
	public int addUser(IUser user) {
		if (users.isEmpty()) {
			users.add(user);
			return 0;
		} else {
			int i = findIndex(user.getID(),users);
			users.add(i,user);
			return i;
		}
	}
	
	public List<IUser> recommendUsersToFollow(IUser user, double minCoefficient) {

		List<IUser> potentialUsersToRecommend = new ArrayList<IUser>();
		List<IUser> recommendedUsers = new ArrayList<IUser>();
		List<IUser> f = user.getFollowedUsers();
		List<IUser> fof = new ArrayList<IUser>();
		if(f.size() < 1){
			return potentialUsersToRecommend;
		}

		for (int i = 0; i < f.size(); i++) {
			List<IUser> temp = f.get(i).getFollowedUsers();
			for (int j = 0; j < temp.size(); j++) {
				fof.add(temp.get(j));
			}
		}

		fof.remove(f);
		fof.remove(user);

		HashMap<IUser, Integer> count_filtered_fof = new HashMap<IUser, Integer>();

		for(int y = 0; y < fof.size(); y++)
		{
			IUser key = fof.get(y);

			if(count_filtered_fof.containsKey(key))
			{
				count_filtered_fof.put(key, count_filtered_fof.get(key) + 1);
			}
			else {
				count_filtered_fof.put(key, 1);
			}
		}


		for(Map.Entry<IUser,Integer> entry :count_filtered_fof.entrySet()){
			IUser key = entry.getKey();
			Integer value = entry.getValue();
			double F = value/(double)f.size();

			if(F > minCoefficient){
				recommendedUsers.add(key);

			}
		}
			return recommendedUsers;
	}
	
	private static int findIndex(String id, List<Identifiable> list) {
		//binary search to add users in order
		int startIndex = 0;
		int endIndex = list.size();
		int midIndex;
		while (startIndex < endIndex) {
			midIndex = (startIndex + endIndex) / 2;
			Identifiable other = list.get(midIndex);
			//If someone accidently added  null to the list, it should go to the end.
			int compValue = other != null ? id.compareTo(other.getID()) : -1;
			if (compValue == 0) {
				//The user already exists in users list
				return midIndex;
			} else if (compValue > 0) {
				startIndex = midIndex + 1;
			} else {
				endIndex = midIndex;
			}
		}
		return endIndex;
	}
	
	public static void main(String[] args) {
		try {
			//set up our network
			SocialNetwork sn = new SocialNetwork();
			DataReader dr = new DataReader("network.dat");
			dr.readDataSet();
			sn.addUsers(dr.getUserList());
			sn.addGroups(dr.getGroupList());
			
			//process the twitter data
			TweetReader tr = new TweetReader("tweets.dat");
			while (tr.advance()) {
				sn.getUser(tr.getTweeterID()).tweeted(tr.getTweet());
			}

			
			//gather personal info
			IUser lt = sn.getUser("lorenterveen");
			List<IUser> friends = lt.getFollowedUsers();
			List<IGroup> groups = lt.getGroups();
			
			// print out user data
			System.out.format("The social network has %d users, and %d groups.\n", sn.getUserCount(), sn.getGroupCount());
			System.out.format("%s has %d friends and is in %d groups\n", lt, friends.size(), groups.size());
			
			System.out.println();
			
			for (IUser friend : friends) {
				System.out.format("%s => %s\n", friend, friend.getLastTweet());
			}
			
			System.out.println();
		} catch (Exception e) {
			// panic - and tell us about it
			System.out.println(e);
		}
	}
}
