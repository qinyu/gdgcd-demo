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

    public Observable<Book> search(String query) {
        return bookService.getBooks(query)
                .flatMap(new Func1<BookMetaEnvelope, Observable<BookMeta>>() {
                    @Override
                    public Observable<BookMeta> call(BookMetaEnvelope bookMetaEnvelope) {
                        return Observable.from(bookMetaEnvelope.getBookList());
                    }
                })
                .map(new Func1<BookMeta, Book>() {
                    @Override
                    public Book call(BookMeta bookMeta) {
                        return buildBook(bookMeta);
                    }
                });
    }

    static Book buildBook(BookMeta bookMeta) {
        Book book = new Book();
        book.title = bookMeta.getTitle().trim();
        book.imageUrl = bookMeta.getImageUrl();
        book.description = bookMeta.getDescription();
        return book;
    }

}
