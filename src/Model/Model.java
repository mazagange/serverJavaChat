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

/**
 *
 * @author BOSHA
 */
public class Model extends UnicastRemoteObject implements ServerInterface {

    Conroller controller;
    public static DB.DbQueries db = DB.DbQueries.getInstance();
    
    HashMap<Integer, User> clients =new HashMap<>();
    
    HashMap<String, Group> Groups =new HashMap<>();

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
    public void registerUser(int id, ClientInterface client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unRegisterUser(int id, ClientInterface client) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendToUser(Massage msg, int fromId, int toId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void notifyChangeStatus(User user, String status) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void registerGroup(Group group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void unRegisterGroup(User user, Group group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendToGroup(Massage msg, Group group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
