package com.antoine.gestioncrous.model;

import java.util.HashSet;
import java.util.Set;

public class Nature {
    private long id;
    private String code;
    private double pourcentage;
    private Set biens = new HashSet<Bien>();

    public Nature(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public Set getBiens() {
        return biens;
    }

    public void setBiens(Set biens) {
        this.biens = biens;
    }
}
