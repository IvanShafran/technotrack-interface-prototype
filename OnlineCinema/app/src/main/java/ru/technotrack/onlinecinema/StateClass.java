package ru.technotrack.onlinecinema;

import java.util.HashSet;
import java.util.Set;

public class StateClass {
    private static StateClass ourInstance = new StateClass();

    public static StateClass getInstance() {
        return ourInstance;
    }

    private StateClass() {
    }

    public enum State {
        MAIN, LOGIN, REGISTER, DETAIL, BUY, WATCH, HOT, LATER, DRAMA, COMEDY, BOUGHT, SEARCH
    }

    private int movie;
    private boolean login = false;
    private State state = State.MAIN, nextState = State.MAIN;
    private String email;
    public Set<Integer> watchLater = new HashSet<>();
    public Set<Integer> boughtMovies = new HashSet<>();
    public Set<Integer> searchResult;
    public boolean showFreeSearch = false;
    public State prevForDetail;

    public int getMovie() {
        return movie;
    }

    public void setMovie(int movie) {
        this.movie = movie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getNextState() {
        return nextState;
    }

    public void setNextState(State nextState) {
        this.nextState = nextState;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
