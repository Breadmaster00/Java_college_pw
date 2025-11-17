public class Order<T> {
    private T id;
    private String date;

    public Order(T id, String date) {
        this.id = id;
        this.date = date;
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "id - " + id + ", date - " + date;
    }
}
