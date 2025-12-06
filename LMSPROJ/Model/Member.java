package LibraryManagementSystem.model;


import java.util.*;


public class Member extends User {
private List<String> history = new ArrayList<>();


public Member(String username, String password) {
super(username, password);
}


public void addHistory(String record) {
history.add(record);
}


public List<String> getHistory() {
return history;
}
}