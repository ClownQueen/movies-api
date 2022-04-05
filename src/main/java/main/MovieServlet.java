package main;
import com.google.gson.Gson;
import data.Movie;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies/*")
public class MovieServlet extends HttpServlet{

    ArrayList<Movie> movies = new ArrayList<>();
    int nextId = 1;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            Movie movie = new Movie("Mother", 4, "poster", 2022, "comedy", "Mikey", "Joe mama", "Joe", 1);
            String movieString = new Gson().toJson(movie);
            out.println(movieString);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        Movie[] newMovies = new Gson().fromJson(request.getReader(), Movie[].class);
        for (Movie movie : newMovies) {
            movie.setId(nextId++);
            movies.add(movie);
        }
        try {
            PrintWriter out = response.getWriter();
            out.println("Movie(s) added");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private int getTargetIdFromURI(String uri) {
        String [] uriParts = uri.split("/");
        int targetId = Integer.parseInt(uriParts[uriParts.length - 1]);
        return targetId;
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int targetId = getTargetIdFromURI(req.getRequestURI());

        Movie updatedMovie = new Gson().fromJson(req.getReader(), Movie.class);

        for (Movie movie: movies){
            if (movie.getId() == targetId){
                if (updatedMovie.getTitle() != null){
                    movie.setTitle(updatedMovie.getTitle());
                }
                if (updatedMovie.getRating() != null){
                    movie.setRating(updatedMovie.getRating());
                }
                if (updatedMovie.getPoster() != null){
                    movie.setPoster(updatedMovie.getPoster());
                }
                if (updatedMovie.getYear() != null){
                    movie.setYear(updatedMovie.getYear());
                }
                if (updatedMovie.getGenre() != null){
                    movie.setGenre(updatedMovie.getGenre());
                }
                if (updatedMovie.getPlot() != null){
                    movie.setPlot(updatedMovie.getPlot());
                }
                if (updatedMovie.getDirector() != null){
                    movie.setDirector(updatedMovie.getDirector());
                }
            }
        }
        try {
            PrintWriter out = resp.getWriter();
            out.println(updatedMovie);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int targetId = getTargetIdFromURI(req.getRequestURI());
        System.out.println("Target id is: " + targetId);

        Movie foundMovie = null;
        for (Movie movie: movies){
            if (movie.getId() == targetId){
                foundMovie = movie;
                break;
            }
        }
        if (foundMovie != null){
            movies.remove(foundMovie);
        }

        PrintWriter out = resp.getWriter();
        out.println("Movie has been deleted");
    }
}