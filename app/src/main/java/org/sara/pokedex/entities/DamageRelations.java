package org.sara.pokedex.entities;

import java.util.List;

public class DamageRelations {

    private List<String> doubleDamageFrom;
    private List<String> doubleDamageTo;
    private List<String> halfDamageFrom;
    private List<String> halfDamageTo;
    private List<String> noDamageFrom;
    private List<String> noDamageTo;
    private String name;
    private String url;

    public DamageRelations(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public List<String> getDoubleDamageFrom() {
        return doubleDamageFrom;
    }

    public void setDoubleDamageFrom(List<String> doubleDamageFrom) {
        this.doubleDamageFrom = doubleDamageFrom;
    }

    public List<String> getDoubleDamageTo() {
        return doubleDamageTo;
    }

    public void setDoubleDamageTo(List<String> doubleDamageTo) {
        this.doubleDamageTo = doubleDamageTo;
    }

    public List<String> getHalfDamageFrom() {
        return halfDamageFrom;
    }

    public void setHalfDamageFrom(List<String> halfDamageFrom) {
        this.halfDamageFrom = halfDamageFrom;
    }

    public List<String> getHalfDamageTo() {
        return halfDamageTo;
    }

    public void setHalfDamageTo(List<String> halfDamageTo) {
        this.halfDamageTo = halfDamageTo;
    }

    public List<String> getNoDamageFrom() {
        return noDamageFrom;
    }

    public void setNoDamageFrom(List<String> noDamageFrom) {
        this.noDamageFrom = noDamageFrom;
    }

    public List<String> getNoDamageTo() {
        return noDamageTo;
    }

    public void setNoDamageTo(List<String> noDamageTo) {
        this.noDamageTo = noDamageTo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
