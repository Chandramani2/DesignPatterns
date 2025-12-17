package mediator;

import model.User;

public interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
}