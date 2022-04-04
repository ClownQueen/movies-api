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

        BufferedReader br = request.getReader();

        Movie[] newMovies = new Gson().fromJson(br, Movie[].class);
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
}