import java.util.ArrayList;

public class Student extends Profile implements Subject, Observer {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private ArrayList<String> newsFeed = new ArrayList<String>();
	private ArrayList<Observer> following = new ArrayList<Observer>(); 
	
	Student (int newId, String newName, boolean isG) {
		super(newId, newName, isG);
	}
	
	public void addComment(String c) {
		newsFeed.add(name + ": " + c);
	}
	
	public int getMessagesTotal () {
		return newsFeed.size();
	}
	
	public String getFollowerList() {
		String list = "List View (Current Following) Total: " + following.size() + "\n";
		
		for (Observer follow : following) {
			list += "   - " + ((Student) follow).toString() + "\n";
		}
		
		list += "\n(Followers) Total: " + observers.size() + "\n";
		
		for (Observer observer : observers) {
			list += "   - " + ((Student) observer).toString() + "\n";
		}
		
		return list;
	}
	
	public String getNewsFeed() {
		String feed = "List View (NewsFeed)\n";
		System.out.println(newsFeed.size());
		
		for (String comment : newsFeed) {
			feed += "   - " + comment + "\n";
		}
		
		return feed;
	}
	
	public void addFollowing (Observer newFollowing) {
		following.add(newFollowing);
	}
	
	@Override
	public void update(String comment) {
		newsFeed.add(comment);
	}

	@Override
	public void register(Observer newObserver) {
		observers.add(newObserver);
	}

	@Override
	public void unregister(Observer deleteObserver) {
		observers.remove(deleteObserver);
	}

	@Override
	public void notifyObserver() {
		for (Observer observer : observers)
			observer.update(newsFeed.get(newsFeed.size() - 1));
	}
}
