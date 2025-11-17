public class Publication {
    private String title;
    private String author;
    private int pages;
    private String publisher;

    public Publication(String title, String author, int pages, String publisher) {
        this.title = title;
        this.author = author;
        this.pages = pages;
        this.publisher = publisher;
    }

    public String getTitle() {return title;}
    public String getAuthor() {return author;}
    public int getPages() {return pages;}
    public String getPublisher() {return publisher;}
    @Override
    public String toString() {
        return "Название: " + title + ", автор " + author + ", кол-во страниц " + pages + ", издатель: " + publisher;
    }
}