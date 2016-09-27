package com.linux_girl.booklist;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by Lani on 7/31/2016.
 */
public class BooksAdapter extends ArrayAdapter<Books>  {

    /**
     * Create a new {@link BooksAdapter} object.
     */
    public BooksAdapter(Context context, ArrayList<Books> book) {

        super(context, 0, book);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listBooksView = convertView;
        if (listBooksView == null) {
            listBooksView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_item, parent, false);
        }

        // Get the {@link Books} object located at this position in the list
        Books currentBook = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID title.
        TextView titleView = (TextView) listBooksView.findViewById(R.id.title);
        // Get the current book title
        titleView.setText(currentBook.getTitle());

        // Find the TextView in the list_item.xml layout with the ID author.
        TextView authorView = (TextView) listBooksView.findViewById(R.id.author);
        // Get the current book author(s)
        authorView.setText(currentBook.getAuthor());

        // Return the whole book item layout (containing 2 TextViews) so that it can be shown in
        // the ListView.
        return listBooksView;
    }
}