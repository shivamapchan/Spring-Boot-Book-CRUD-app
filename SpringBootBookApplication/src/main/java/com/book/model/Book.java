package com.book.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="book")
public class Book {
	
	@Id
	@Column(name="bkid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int bkid;
	@Column(nullable=false,length=45)
	private String book_name;
	@Column(nullable=false,length=15)
	private double book_price;
	@Column(nullable=false,length=45)
	private String author;
	@Column(nullable=false,length=45)
	private String publisher;
	
		
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	// For BookRepositoryTest purpose
	public Book(int bkid, String book_name, double book_price, String author, String publisher) {
		super();
		this.bkid = bkid;
		this.book_name = book_name;
		this.book_price = book_price;
		this.author = author;
		this.publisher = publisher;
	}
	public int getBkid() {
		return bkid;
	}
	public void setBkid(int bkid) {
		this.bkid = bkid;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public double getBook_price() {
		return book_price;
	}
	public void setBook_price(double book_price) {
		this.book_price = book_price;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getPublisher() {
		return publisher;
	}
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	@Override
	public String toString() {
		return "Book [bkid=" + bkid + ", book_name=" + book_name + ", book_price=" + book_price + ", author=" + author
				+ ", publisher=" + publisher + "]";
	}
	
	
	
}
