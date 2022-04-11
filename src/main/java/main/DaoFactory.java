package main;

public class DaoFactory {
    public enum DaoType {MYSQL, IN_MEMORY};

    public static InMemoryMoviesDao getMoviesDao(DaoType daoType){

        switch(daoType){
            case IN_MEMORY:{
                return new InMemoryMoviesDao();
            }
            case MYSQL:{ // <-- added this
                return MySqlMoviesDao(config);
            }
        }
        return null;
    }
}
