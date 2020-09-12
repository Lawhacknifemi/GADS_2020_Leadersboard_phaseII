package com.example.leadersboard.model;

public class SkillIQ {
    private String name;
    private String score;
    private String country;
    private String badgeUrl;

    public SkillIQ(String name, String score, String country, String badgeUrl) {
        this.name = name;
        this.score = score;
        this.country = country;
        this.badgeUrl = badgeUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

    public void setBadgeUrl(String badgeUrl) {
        this.badgeUrl = badgeUrl;
    }

    @Override
    public String toString() {
        return "SkillData{" +
                "name='" + name + '\'' +
                ", score='" + score + '\'' +
                ", country='" + country + '\'' +
                ", badgeURL='" + badgeUrl + '\'' +
                '}';
    }
}
