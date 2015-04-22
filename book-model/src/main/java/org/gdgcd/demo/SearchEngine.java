package org.gdgcd.demo;

import org.gdgcd.demo.domain.Book;
import org.gdgcd.demo.service.BookMeta;
import org.gdgcd.demo.service.BookMetaEnvelope;
import org.gdgcd.demo.service.BookService;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class SearchEngine {

    @Inject
    BookService bookService;

    public Observable<Book> search(String query)


    public Observable<BookMetaEnvelope> search1(String query) {
        return bookService.getBooks(query);
    }

    public static Book buildBook(BookMeta bookMeta) {
        Book book = new Book();
        book.title = bookMeta.getTitle();
        book.imageUrl = bookMeta.getImage();
        book.description = bookMeta.getDescription();
        return book;
    }
}
