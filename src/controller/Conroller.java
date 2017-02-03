/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DB.DbQueries;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import Model.Model;
import Model.ServerInterface;
import view.ServerProject;

/**
 *
 * @author BOSHA
 */
public class Conroller {

    ServerProject serverProject;
    static Model model;
    DbQueries db;
    static Registry regisrty;

    public Conroller(ServerProject aThis) {
        try {
            serverProject = aThis;
            model = new Model(this);
            db = DbQueries.getInstance();
            regisrty = LocateRegistry.getRegistry();
            regisrty.rebind("chatserver", model);
            System.out.println("control");

        } catch (RemoteException ex) {
            Logger.getLogger(Conroller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void start() {
        try {
            regisrty.rebind("chatserver", model);
            System.out.println("start");
        } catch (RemoteException ex) {
            Logger.getLogger(Conroller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void stop() {
        try {
            regisrty.unbind("chatserver");
            System.out.println("stop");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Conroller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        Application.launch(ServerProject.class, args);
    }

}
