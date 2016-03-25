package org.gdgcd.demo;

import android.content.Intent;
import android.text.TextUtils;

import org.gdgcd.demo.domain.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

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
        then(spyAdapter).should().notifyInsert(0);

        spyAdapter.append(new Book());
        then(spyAdapter).should().notifyInsert(1);
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

        final BookViewHolder mockHolder = mock(BookViewHolder.class);

        spyAdapter.onBindViewHolder(mockHolder, 0);
        then(mockHolder).should().updateView(book0);

        spyAdapter.onBindViewHolder(mockHolder, 1);
        then(mockHolder).should().updateView(book1);
    }

    @Test
    public void should_get_text_utils_work(){
        assertThat(TextUtils.isEmpty(""), is(true));
        assertThat(TextUtils.isEmpty("not empty"), is(false));
    }

    @Test
    public void should_get_intent_work(){
        assertThat(new Intent(), is(notNullValue()));
        assertThat(new Intent().putExtra("test", false).getBooleanExtra("test", true), is(false));
    }


}