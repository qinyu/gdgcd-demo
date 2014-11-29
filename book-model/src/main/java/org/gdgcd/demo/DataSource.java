package org.gdgcd.demo;

import org.gdgcd.demo.domain.Book;
import org.gdgcd.demo.service.BookMeta;
import org.gdgcd.demo.service.BookService;
import org.gdgcd.demo.service.ReviewService;

import javax.inject.Inject;

import rx.Observable;

public class DataSource {

    @Inject
    ReviewService reviewService;
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
                .map(bookMeta -> buildBook(reviewService, bookMeta));
    }

    Book buildBook(ReviewService reviewService, BookMeta bookMeta) {
        Book book = new Book();
        book.title = bookMeta.getTitle();
        book.imageUrl = bookMeta.getImage();
        book.description = bookMeta.getDescription();
        book.rating = reviewService.getReview(bookMeta.getIsbn()).flatMap(envelope -> Observable.from(envelope.getBookReviews()))
                .map(reviewMeta -> reviewMeta.getAverageRating());
        return book;
    }
}
