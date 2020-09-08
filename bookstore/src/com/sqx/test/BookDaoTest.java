package com.sqx.test;

import com.sqx.dao.BookDao;
import com.sqx.dao.impl.BookDaoImpl;
import com.sqx.pojo.Book;
import com.sqx.pojo.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/9/4 - 23:27
 */
public class BookDaoTest {
    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "sqx最帅", "sqx", new BigDecimal(9999), 100000, 0, null));
    }

    @Test
    public void deleteBook() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "大家最帅", "sqx", new BigDecimal(9999), 100000, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookDao.queryBookById(21));
    }

    @Test
    public void queryBooks() {
        for(Book queryBook:bookDao.queryBooks()){
            System.out.println(queryBook);
        }
        System.out.println(bookDao.queryBooks());
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(8, Page.PAGE_SIZE);
        System.out.println(books);
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10, 50));
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(0, Page.PAGE_SIZE, 10 , 50);
        System.out.println(books);
    }
}