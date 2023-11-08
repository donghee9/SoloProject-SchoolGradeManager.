package com.example.schoolman.Model;

public enum Department {
    // 이과
    COMPUTER_SCIENCE("Science"),
    MEDICINE("Science"),
    NURSING("Science"),
    PHARMACY("Science"),
    BIOLOGY("Science"),
    CHEMISTRY("Science"),
    MATHEMATICS("Science"),


    // 상경계열
    BUSINESS("Commerce"),
    ACCOUNTING("Commerce"),
    MARKETING("Commerce"),
    FINANCE("Commerce"),

    // 예체능
    ART("Arts"),
    MUSIC("Arts");


    String category;

    Department(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
