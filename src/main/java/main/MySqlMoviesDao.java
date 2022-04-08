package main;

import com.mysql.cj.jdbc.Driver;
import config.Config;
import data.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlMoviesDao extends Movie {

    private Connection connection = null;

    public MySqlMoviesDao() {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://" + Config.DB_HOST + ":3306/micah?allowPublicKeyRetrieval=true&useSSL=false",
                    Config.DB_USER,
                    Config.DB_PW
            );
        }catch (SQLException e){
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    public List<Movie> all() throws SQLException {
        // TODO: Get ALL the movies
        // make an empty array list in which to put our movies and return
        ArrayList<Movie> movies = new ArrayList<>();
        // make a statement (don't need a prepared statement since not using any variables from end user
        Statement stmt = connection.createStatement();
        // get a resultset from the statement
        ResultSet movieList = stmt.executeQuery("select * from micah.movie");
        // iterate over the result set
        while (movieList.next()){
            // and make a movie object for each row
            movies.add(new Movie(
                    movieList.getString("title"),
                    movieList.getInt("rating"),
                    movieList.getString("poster"),
                    movieList.getInt("year"),
                    movieList.getString("genre"),
                    movieList.getString("director"),
                    movieList.getString("plot"),
                    movieList.getString("actors"),
                    movieList.getInt("id")
            ));
            // and add that object to an arraylist of movies
        }
        // return the arraylist of movies
        return movies;
    }


    public Movie findOne(int id) {
        // TODO: Get one movie by id
        return null;
    }


    public void insert(Movie movie) throws SQLException {
        // TODO: Insert one movie
        //make a preparedstatement
        PreparedStatement ps = connection.prepareStatement("insert into micah.movie " + " (title, year, genre, director, actors, rating, poster, plot) " + " values (?, ?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
        //set all of the user info in the preparedstatement
        ps.setString(1, movie.getTitle());
        ps.setInt(2, movie.getYear());
        ps.setString(3, movie.getGenre());
        ps.setString(4, movie.getDirector());
        ps.setString(5, movie.getActors());
        ps.setInt(6, movie.getRating());
        ps.setString(7, movie.getPoster());
        ps.setString(8, movie.getPlot());
        //execute the query
        ps.executeUpdate();

        ps.close();
    }

    public void insertAll(Movie[] movies) throws SQLException {
        // TODO: Insert all the movies!
        // iterate over the given movies
        for (Movie movie: movies){
            //call insert() for each of the movies
            insert(movie);
        }
    }


    public void update(Movie movie) throws SQLException {
        //TODO: Update a movie here!
        //assumption: movie only has the fields that we need to update
        //"Update movies set title = 'new title' where id = xxx"
        //"Update movies set title = 'new title', rating = 5 where id = xxx"
        String query = "Updated movies set ";
        if (movie.getTitle() != null){
            query += " title = ?,";
        }
        if (movie.getYear() != null){
            query += " year = ?,";
        }
        if (movie.getGenre() != null){
            query += " genre = ?,";
        }
        if (movie.getDirector() != null){
            query += " director = ?,";
        }
        if (movie.getActors() != null){
            query += " actors = ?,";
        }
        if (movie.getRating() != null){
            query += " rating = ?,";
        }
        if (movie.getPoster() != null){
            query += " poster = ?,";
        }
        if (movie.getPlot() != null){
            query += " plot = ?,";
        }
        // get rid of trailing comma
        query = query.substring(0, query.length() - 1);
        query += " where id = ? ";

        //create prepared statement from the query string
        PreparedStatement ps = connection.prepareStatement(query);

        // set the parameters for the query based on the fields that need to be updated
        int currentIndex = 1;
        if (movie.getTitle() != null){
            ps.setString(currentIndex, movie.getTitle());
            currentIndex++;
        }
        if (movie.getYear() != null){
            ps.setInt(currentIndex, movie.getYear());
            currentIndex++;
        }
        if (movie.getGenre() != null){
            ps.setString(currentIndex, movie.getGenre());
            currentIndex++;
        }
        if (movie.getDirector() != null){
            ps.setString(currentIndex, movie.getDirector());
            currentIndex++;
        }
        if (movie.getActors() != null){
            ps.setString(currentIndex, movie.getActors());
            currentIndex++;
        }
        if (movie.getRating() != null){
            ps.setInt(currentIndex, movie.getRating());
            currentIndex++;
        }
        if (movie.getPoster() != null){
            ps.setString(currentIndex, movie.getPoster());
            currentIndex++;
        }
        if (movie.getPlot() != null){
            ps.setString(currentIndex, movie.getPlot());
            currentIndex++;
        }
        ps.setInt(currentIndex, movie.getId());

        //execute the darn statement
        ps.executeUpdate();
        ps.close();
    }


    public void delete(int id) throws SQLException {
        //TODO: Annihilate a movie
    }

    public void cleanUp(){
        try{
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}

