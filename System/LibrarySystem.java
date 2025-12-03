package LibraryManagementSystem.src.system;



import LibraryManagementSystem.src.model.*;


import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {

    private static List<Admin> admins = new ArrayList<>();
    private static List<Member> members = new ArrayList<>();

    static {
        // Default users
        admins.add(new Admin("admin", "admin123"));
        members.add(new Member("user", "user123"));
    }

    public static User login(String username, String password, String userType) {
    if (userType.equals("ADMIN")) {
        // Flexible admin: any username/password works
        return new Admin( username, password);
    } else if (userType.equals("MEMBER")) {
        for (Member m : members) {
                return new Member(username, password);
            }
        }
        return null;
    }
    
}



