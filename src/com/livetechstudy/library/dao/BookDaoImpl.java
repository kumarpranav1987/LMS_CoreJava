package com.livetechstudy.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.livetechstudy.library.model.Book;



public class BookDaoImpl implements BookDao {
	private static final String INSERT_QUERY = "insert into books values(?,?,?);";
	private static final String DELETE_QUERY_BY_ID = "delete from books where BookID=?";
	private static final String SELECT_ALL_QUERY = "select * from books";
	private static final String SELECT_QUERY_BY_ID = "select * from books where BookID=?";
	private static final String SELECT_QUERY_BY_TITLE = "select * from books where BookTitle=?";

	@Override
	public int insertBook(Book book) {
		Connection conn = ConnectFactory.getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(INSERT_QUERY);
			ps.setString(1, book.getBookId());
			ps.setString(2, book.getBookTitle());
			ps.setString(3, book.getAuthor());
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@Override
	public int deleteBook(String bookID) {
		Connection conn = ConnectFactory.getConnection();
		PreparedStatement ps = null;
		int result = 0;
		try {
			ps = conn.prepareStatement(DELETE_QUERY_BY_ID);
			ps.setString(1, bookID);
			result = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<Book> getAllBooks() {
		List<Book> books = new ArrayList<>();
		Connection conn = ConnectFactory.getConnection();
		Statement s = null;
		ResultSet rs = null;
		try {
			s = conn.createStatement();
			rs = s.executeQuery(SELECT_ALL_QUERY);
			while (rs.next()) {
				String bookID = rs.getString("BookID");
				String title = rs.getString("BookTitle");
				String author = rs.getString("Author");
				Book book = new Book(bookID, title, author);
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				s.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		return books;
	}

	@Override
	public Book findBookByID(String bookID) {
		Book result = null;
		Connection conn = ConnectFactory.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_QUERY_BY_ID);
			ps.setString(1, bookID);
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("BookID");
				String title = rs.getString("BookTitle");
				String author = rs.getString("Author");
				result = new Book(id, title, author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	@Override
	public List<Book> findBooksByTitle(String title) {
		List<Book> books = new ArrayList<>();
		Connection conn = ConnectFactory.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(SELECT_QUERY_BY_TITLE);
			ps.setString(1, title);
			rs = ps.executeQuery();
			while (rs.next()) {
				String id = rs.getString("BookID");
				String t = rs.getString("BookTitle");
				String author = rs.getString("Author");
				books.add(new Book(id, t, author));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

}
