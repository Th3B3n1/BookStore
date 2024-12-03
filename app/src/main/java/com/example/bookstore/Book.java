package com.example.bookstore;

public class Book {
    private final String title;
    private final String author;
    private final int pageAmount;

    public Book(String title, String author, int pageAmount) {
        this.title = title;
        this.author = author;
        this.pageAmount = pageAmount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageAmount() {
        return pageAmount;
    }
}
