public record Content(String title, String imageUrl) {

//    private final String title;
//    private final String urlImage;

    public Content(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public String getTitle(){
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
