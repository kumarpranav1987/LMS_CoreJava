package com.livetechstudy.library.dao;

import java.util.List;

import com.livetechstudy.library.model.Book;


public interface BookDao {
	public int insertBook(Book book);
	public int deleteBook(String bookID);
	public List<Book>getAllBooks();
	public Book findBookByID(String bookID);
	public List<Book> findBooksByTitle(String title);
}
