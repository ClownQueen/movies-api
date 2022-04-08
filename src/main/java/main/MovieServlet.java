package main;
import com.google.gson.Gson;
import data.Movie;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "MovieServlet", urlPatterns = "/movies/*")
public class MovieServlet extends HttpServlet{

//    private InMemoryMoviesDao dao = new InMemoryMoviesDao();
    private MySqlMoviesDao dao = new MySqlMoviesDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("application/json");

        try {
            PrintWriter out = response.getWriter();
            String movieString = new Gson().toJson(dao.all().toArray());
            out.println(movieString);
        }catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");

        Movie[] newMovies = new Gson().fromJson(request.getReader(), Movie[].class);

        try {
            dao.insertAll(newMovies);
            PrintWriter out = response.getWriter();
            out.println("Movie(s) added");
        } catch(IOException | SQLException e) {
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
        updatedMovie.setId(targetId);


        try {
            dao.update(updatedMovie);
            PrintWriter out = resp.getWriter();
            out.println(updatedMovie);
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int targetId = getTargetIdFromURI(req.getRequestURI());
        System.out.println("Target id is: " + targetId);
        try {
            dao.delete(targetId);
            PrintWriter out = resp.getWriter();
            out.println("Movie has been deleted");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void destroy(){
        dao.cleanUp();
    }
}