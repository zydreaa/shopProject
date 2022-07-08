package users;

import java.util.ArrayList;

public class UserService {

    private User activeUser;
    private final ArrayList<User> users = new ArrayList<>();

    public User getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(User activeUser, String pin) throws Exception {
        if (!activeUser.verifyPin(pin)) {
            throw new InvalidUserException("User pin incorrect!");
        }

        this.activeUser = activeUser;

    }

    public User findUserByUsername(String username) throws Exception {
        for (User currentUser: this.users){
            if(currentUser.getUsername().equals(username)) return currentUser;
        }
        throw new InvalidUserException("User "+ username +" not found!");
    }

    public String createUser(User user) throws Exception {
        if (this.verifyUserData(user)){
            this.users.add(user);
            return "User added successfully";
        }

        return "Problems adding user";
    }
    private boolean verifyUserData(User user) throws  InvalidUserException{
        if (user.getUsername().isEmpty() || user.getUsername().length() < 2) {
            throw new InvalidUserException("User not valid");
        }
        if (user.getBalance().isNaN()){
            throw new InvalidUserException("Balance is not valid");
        }
        return true;
    }

    public void updateUserBalance(User user) throws InvalidUserException {
        for (User currentUser: this.users){
            if(user.getId().equals(currentUser.getId())){
                currentUser.setBalance(user.getBalance());
                return;
            }
        }
        throw new InvalidUserException("Update balance: User not found!");
    }

    public void syncActiveUser() throws Exception{
        this.activeUser = this.findUserByUsername(this.activeUser.getUsername());
    }
}
