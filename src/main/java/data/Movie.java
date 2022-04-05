package data;

public class Movie {
    private String title;
    private Integer rating;
    private String poster;
    private Integer year;
    private String genre;
    private String director;
    private String plot;
    private String actors;
    private int id;

    public Movie(){
    }

    public Movie(String title, Integer rating, String poster, Integer year, String genre, String director, String plot, String actors, int id) {
        this.title = title;
        this.rating = rating;
        this.poster = poster;
        this.year = year;
        this.genre = genre;
        this.director = director;
        this.plot = plot;
        this.actors = actors;
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Integer getRating() {
        return rating;
    }

    public String getPoster() {
        return poster;
    }

    public Integer getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getPlot() {
        return plot;
    }

    public String getActors() {
        return actors;
    }

    public int getId() {
        return id;
    }

}
