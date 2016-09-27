package com.linux_girl.booklist;

/**
 * {@Books} represents a book on the list. It holds the details
 * of that book to include the book's title, author, rating and a thumbnail
 * image.
 */
public class Books {

    /** Title of the Book  */
    public final String title;

    /** Author of the BOok */
    public final String author;

    /**
     * Constructs a new {@link Books}.
     *
     * @param bookTitle is the title of the book
     * @param bookAuthor is the author of the book
     */
    public Books(String bookTitle, String bookAuthor) {
        title = bookTitle;
        author = bookAuthor;
    }

    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
}