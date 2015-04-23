package org.gdgcd.demo;

import org.gdgcd.demo.domain.Book;
import org.gdgcd.demo.service.BookMeta;
import org.gdgcd.demo.service.BookMetaEnvelope;
import org.gdgcd.demo.service.BookService;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Func1;

public class SearchEngine {

    @Inject
    BookService bookService;

    public Observable<Book> search(String query) {
        return bookService.getBooks(query).flatMap(new Func1<BookMetaEnvelope, Observable<BookMeta>>() {
            @Override
            public Observable<BookMeta> call(BookMetaEnvelope bookMetaEnvelope) {
                final List<BookMeta> books = bookMetaEnvelope.getBooks();
                return Observable.from(books);

            }
        }).map(new Func1<BookMeta, Book>() {
            @Override
            public Book call(BookMeta bookMeta) {
                return buildBook(bookMeta);
            }
        });
    }


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
