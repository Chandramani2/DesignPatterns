package mediator;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator{
    private List<User> users;

    public ChatRoom(){
        this.users = new ArrayList<>();
    }
    @Override
    public void sendMessage(String msg, User user) {
        for (User u : users) {
            // Message should not be received by the user who sent it
            if (u != user) {
                u.receive(msg);
            }
        }
    }

    @Override
    public void addUser(User user) {
        this.users.add(user);
    }
}
