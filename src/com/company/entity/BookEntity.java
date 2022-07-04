package com.company.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class BookEntity {
	private Long id;
	private String title;
	private String author;
	private String isbn;
	private Integer pages;
	private BigDecimal price;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(final String isbn) {
		this.isbn = isbn;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(final Integer pages) {
		this.pages = pages;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(final BigDecimal price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		return Objects.hash(author, id, isbn, pages, price, title);
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		BookEntity other = (BookEntity) obj;
		return Objects.equals(author, other.author) && Objects.equals(id, other.id) && Objects.equals(isbn, other.isbn)
				&& Objects.equals(pages, other.pages) && Objects.equals(price, other.price)
				&& Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", isbn=" + isbn + ", pages=" + pages
				+ ", price=" + price + "]";
	}
}