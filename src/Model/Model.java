/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import controller.Conroller;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BOSHA
 */
public class Model extends UnicastRemoteObject implements ServerInterface {

    Conroller controller;
    public static DB.DbQueries db = DB.DbQueries.getInstance();
    
    HashMap<Integer, ClientInterface> clients =new HashMap<>();
    
    HashMap<String, Group> groups =new HashMap<>();

    public Model(Conroller controller) throws RemoteException {
        this.controller = controller;
        System.out.println("model");
    }

    @Override
    public boolean isExist(String email) throws RemoteException {
        return db.isExist(email);
    }

    @Override
    public User getUserData(String email) throws RemoteException {
        return db.getUserData(email);
    }

    @Override
    public int insertUser(User bean) throws RemoteException {
        return db.insertUser(bean);
    }

    @Override
    public int updateUser(User bean) throws RemoteException {
        return db.updateUser(bean);
    }

    @Override
    public int updateStatus(int id, String status) throws RemoteException {

        return db.updateStatus(id, status);
    }

    @Override
    public int updateOnline(int id, String onlineStatus) throws RemoteException {
        return db.updateOnline(id, onlineStatus);
    }

    @Override
    public ArrayList<User> getFriends(int id) throws RemoteException {
        return db.getFriends(id);
    }

    @Override
    public void registerUser(int id, ClientInterface client)throws RemoteException {
        clients.put(id, client);
    }

    @Override
    public void unRegisterUser(int id, ClientInterface client)throws RemoteException {
        if(clients.containsKey(id)){
            clients.remove(id);
        }
    }

    @Override
    public void sendToUser(Massage msg, int fromId, int toId)throws RemoteException {
        clients.get(toId).recieve(msg, db.getUserData(fromId));
    }

    @Override
    public void notifyChangeStatus(User user, String status)throws RemoteException {
        ArrayList<User> friends = db.getFriends(user.getId());
        friends.forEach((f) -> {
            if(clients.containsKey(f.getId())){
                try {
                    clients.get(f.getId()).notifyChangeStatus(user, status);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    @Override
    public void registerGroup(Group group)throws RemoteException {
        groups.put(group.name, group);
    }

    @Override
    public void unRegisterGroup(User user, Group group)throws RemoteException {
        if(groups.containsKey(group.name)){
            Group g = groups.get(group.name);
            g.members.remove(user);
            if(g.members.isEmpty()){
                groups.remove(group.name);
            }
            
        }
    }

    @Override
    public void sendToGroup(Massage msg, Group group)throws RemoteException {
        if(groups.containsKey(group.name)){
            Group g = groups.get(group.name);
            g.members.forEach((t) -> {
                if(clients.containsKey(t.getId())){
                    try {
                        clients.get(t.getId()).recieveFromGroup(msg, group);
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
        }
    }

}
