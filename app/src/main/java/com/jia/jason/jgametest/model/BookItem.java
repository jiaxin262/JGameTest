package com.jia.jason.jgametest.model;

/**
 * Created by jiaxin on 16/10/22.
 */

public class BookItem {

    private int index;
    private String name;
    private String author;
    private double price;
    private int pages;

    public BookItem() {
    }

    public BookItem(int index, String name, String author, double price, int pages) {
        this.index = index;
        this.name = name;
        this.author = author;
        this.price = price;
        this.pages = pages;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
