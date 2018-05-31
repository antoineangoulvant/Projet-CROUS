package com.antoine.gestioncrous.dao;

public class DAO {
    private static DAO instance = null;

    private DAO(){}

    private static synchronized DAO getInstance(){
        if(instance == null){
            instance = new DAO();
        }
        return instance;
    }
}
