package com.sqx.test;

import com.sqx.pojo.Book;
import com.sqx.pojo.Page;
import com.sqx.service.BookService;
import com.sqx.service.impl.BookServiceImpl;
import org.junit.Test;

import javax.lang.model.element.VariableElement;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * @author SQX
 * @date 2020/9/4 - 23:45
 */
public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "sqx成长之路", "sqx", new BigDecimal(9999), 100000, 0, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "sqx成长之路1", "sqx", new BigDecimal(9999), 100000, 0, null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(1, Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1, Page.PAGE_SIZE, 10, 50));
    }
}