package com.example.sqlite_maytinh;

public class computer {
    private int Idcomputer;
    private String Tencomuter;

    public computer(int idcomputer, String tencomuter) {
        Idcomputer = idcomputer;
        Tencomuter = tencomuter;
    }

    public int getIdcomputer() {
        return Idcomputer;
    }

    public void setIdcomputer(int idcomputer) {
        Idcomputer = idcomputer;
    }

    public String getTencomuter() {
        return Tencomuter;
    }

    public void setTencomuter(String tencomuter) {
        Tencomuter = tencomuter;
    }
}
