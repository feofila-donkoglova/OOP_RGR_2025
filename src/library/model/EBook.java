package library.model;

public class EBook extends Book {
    private String downloadLink;
    private String format;

    public EBook(EBookBuilder builder) {
        super(builder);
        this.downloadLink = builder.downloadLink;
        this.format = builder.format;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public String getFormat() {
        return format;
    }

    @Override
    public boolean isAvailableForBorrow() {
        return true;
    }

        @Override
    public void displayInfo() {
        System.out.println("=== Електронна книга ===");
        System.out.println("Назва: " + getTitle());
        System.out.println("Автор: " + getAuthor());
        System.out.println("Жанр: " + getGenre());
        System.out.println("Сторінки: " + getPageCount());
        System.out.println("Рік: " + getYear());
        System.out.println("Доступність: " + (isAvailable() ? "Так" : "Ні"));
        System.out.println("Стан: " + getState().getStateName());
        System.out.println("Формат: " + format);
        System.out.println("Посилання: " + downloadLink);
        System.out.println("=========================");
    }
    public static class EBookBuilder extends Book.BookBuilder<EBookBuilder> {
        private String downloadLink;
        private String format;

        public EBookBuilder setDownloadLink(String downloadLink) {
            this.downloadLink = downloadLink;
            return this;
        }

        public EBookBuilder setFormat(String format) {
            this.format = format;
            return this;
        }

        @Override
        public EBook build() {
            return new EBook(this);
        }
    }
}
