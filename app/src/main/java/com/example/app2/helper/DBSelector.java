package com.example.app2.helper;


public class DBSelector {

    private static DBSelector dbSelector;
    private String dbName;

    public DBSelector() {
    }

    public synchronized static DBSelector getInstance(){

        if (dbSelector == null){
            dbSelector = new DBSelector();
        }
        return dbSelector;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
