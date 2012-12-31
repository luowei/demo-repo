package com.vvvv.test;

import com.vvvv.db.DBManager;

public class TestDBManager {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println(DBManager.getConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
