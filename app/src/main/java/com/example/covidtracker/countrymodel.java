package com.example.covidtracker;

public class countrymodel {
    private String flag,cases,todaycases,deaths,todaydeaths,recovered,critical,active,country;

    public countrymodel() {
    }

    public countrymodel(String flag,String country,String cases, String todaycases, String deaths, String todaydeaths, String recovered, String critical, String active) {
        this.flag = flag;
        this.country = country;
        this.cases = cases;
        this.todaycases = todaycases;
        this.deaths = deaths;
        this.todaydeaths = todaydeaths;
        this.recovered = recovered;
        this.critical = critical;
        this.active = active;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodaycases() {
        return todaycases;
    }

    public void setTodaycases(String todaycases) {
        this.todaycases = todaycases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodaydeaths() {
        return todaydeaths;
    }

    public void setTodaydeaths(String todaydeaths) {
        this.todaydeaths = todaydeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }


}
