package com.sqx.service;

import com.sqx.pojo.Book;
import com.sqx.pojo.Page;

import java.util.List;

/**
 * @author SQX
 * @date 2020/9/4 - 23:37
 */
public interface BookService {

    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize);

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
