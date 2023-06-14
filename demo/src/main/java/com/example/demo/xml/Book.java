package com.example.demo.xml;

public class Book {
    private String category;
    private String title;
    private String author;
    private int year;
    private double price;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [category=" + category + ", title=" + title + ", author=" + author + ", year=" + year + ", price="
                + price + "]";
    }
}
