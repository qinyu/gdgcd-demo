package org.gdgcd.demo;

import org.gdgcd.demo.domain.Book;
import org.gdgcd.demo.service.BookMeta;
import org.gdgcd.demo.service.BookService;

import javax.inject.Inject;

import rx.Observable;

public class DataSource {

    @Inject
    BookService bookService;

    public Observable<Book> search(String query) {
        return bookService.getBooks(query).
                flatMap(response ->
                {
                    Observable<BookMeta> observable;
                    if (Integer.parseInt(response.getError()) != 0) {
                        observable = Observable.error(new Exception(response.getError()));
                    } else {
                        observable = Observable.from(response.getBooks());
                    }
                    return observable;
                })
                .map(this::buildBook);
    }

    Book buildBook(BookMeta bookMeta) {
        Book book = new Book();
        book.title = bookMeta.getTitle();
        book.imageUrl = bookMeta.getImage();
        book.description = bookMeta.getDescription();
        return book;
    }
}
