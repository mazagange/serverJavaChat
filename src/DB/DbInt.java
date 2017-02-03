/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.util.ArrayList;
import Model.User;

/**
 *
 * @author ahmed mohsen
 */
public interface DbInt {

    public int insertUser(User bean);

    public User getUserData(String email);

    public boolean isExist(String email);

    public int updateStatus(int id, String status);

    public int updateUser(User bean);

    public int updateOnline(int id, String status);

    public ArrayList<User> getFriends(int id);

    public void addFriend(int id, int friendId, String category);

    public void acceptFriend(int id, int friendId);

    public void blockFriend(int id, int friendId);
}
