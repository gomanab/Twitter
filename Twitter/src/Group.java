import java.util.ArrayList;

public class Group extends Profile implements Subject, Observer {
	private ArrayList<Observer> observers = new ArrayList<Observer>();
	private ArrayList newsFeed = new ArrayList();
	private ArrayList comments = new ArrayList();
	
	Group (int newId, String newName, boolean isG) {
		super(newId, newName, isG);
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
		for (Observer observer : observers) {
			for (int i = 0; i < comments.size(); i++) {
				observer.update(comments.get(i).toString());
			}
		}
	}
}
