package org.gdgcd.demo;

import org.gdgcd.demo.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class BooksAdapterTest {

    private BooksAdapter spyAdapter;

    @Before
    public void setUp() throws Exception {
        spyAdapter = spy(new BooksAdapter());
        doNothing().when(spyAdapter).notifyInsert(anyInt());
    }

    @Test
    public void should_notify_inserting_at_end_of_list_if_appending_book() {

        spyAdapter.append(new Book());
        verify(spyAdapter).notifyInsert(0);

        spyAdapter.append(new Book());
        verify(spyAdapter).notifyInsert(1);
    }


    @Test
    public void should_size_of_books_as_count() {


        spyAdapter.append(new Book());
        assertThat(spyAdapter.getItemCount(), is(1));

        spyAdapter.append(new Book());
        assertThat(spyAdapter.getItemCount(), is(2));
    }

    @Test
    public void should_update_view_to_corresponding_book() {
        final Book book0 = new Book();
        final Book book1 = new Book();
        spyAdapter.append(book0);
        spyAdapter.append(book1);

        final BooksAdapter.ViewHolder mockHolder = mock(BooksAdapter.ViewHolder.class);

        spyAdapter.onBindViewHolder(mockHolder, 0);
        verify(mockHolder).updateView(book0);

        spyAdapter.onBindViewHolder(mockHolder, 1);
        verify(mockHolder).updateView(book1);
    }


}