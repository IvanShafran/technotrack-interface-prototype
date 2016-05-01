package ru.technotrack.onlinecinema;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        goNextState(StateClass.State.MAIN);

        MovieStorage.init();

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            StateClass.getInstance().searchResult = new HashSet<>();

            for (Integer id : MovieStorage.movies.keySet()) {
                if (MovieStorage.movies.get(id).getName().toLowerCase()
                        .contains(query.toLowerCase())) {
                    StateClass.getInstance().searchResult.add(id);
                }
            }

            StateClass.getInstance().setNextState(StateClass.State.SEARCH);
            goNextState(StateClass.State.MAIN);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (StateClass.getInstance().getState() != StateClass.State.MAIN) {
            StateClass.State prev = null;
            StateClass.State newNext = StateClass.State.MAIN;
            switch (StateClass.getInstance().getState()) {
                case DETAIL:
                    prev = StateClass.getInstance().prevForDetail;
                    break;
                case REGISTER:
                    prev = StateClass.State.LOGIN;
                    newNext = StateClass.getInstance().getNextState();
                    break;
                case BUY:
                    prev = StateClass.State.DETAIL;
                    break;
                case LOGIN:
                    prev = StateClass.getInstance().getNextState();
                    break;
                case WATCH:
                    prev = StateClass.State.DETAIL;
                    break;
                case HOT:
                    prev = StateClass.State.MAIN;
                    break;
                case DRAMA:
                    prev = StateClass.State.MAIN;
                    break;
                case COMEDY:
                    prev = StateClass.State.MAIN;
                    break;
                case LATER:
                    prev = StateClass.State.MAIN;
                    break;
                case BOUGHT:
                    prev = StateClass.State.MAIN;
                    break;
                case SEARCH:
                    prev = StateClass.State.MAIN;
                    break;
            }

            StateClass.getInstance().setNextState(prev);
            goNextState(newNext);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_bar_search);
        SearchView searchView =
                (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setQueryHint("Введите фильм");
        searchView.setIconifiedByDefault(false);
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_bar_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setLoginText() {
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        View header = navView.getHeaderView(0);
        Menu menu = navView.getMenu();

        if (StateClass.getInstance().isLogin()) {
            (menu.findItem(R.id.nav_login)).setTitle("Logout");
            ImageView imageView = (ImageView) header.findViewById(R.id.avatar_image);
            imageView.setVisibility(View.VISIBLE);
            TextView name = (TextView) header.findViewById(R.id.login_text);
            name.setVisibility(View.VISIBLE);
            name.setText(StateClass.getInstance().getEmail());
            TextView email = (TextView) header.findViewById(R.id.mail_text);
            email.setText("Вы залогинены!");
            email.setVisibility(View.VISIBLE);
        } else {
            (menu.findItem(R.id.nav_login)).setTitle("Login");
            ImageView imageView = (ImageView) header.findViewById(R.id.avatar_image);
            imageView.setVisibility(View.GONE);
            TextView name = (TextView) header.findViewById(R.id.login_text);
            name.setVisibility(View.GONE);
            TextView email = (TextView) header.findViewById(R.id.mail_text);
            email.setVisibility(View.GONE);
        }
    }

    public void goNextState(StateClass.State newNextState) {
        setLoginText();
        if (StateClass.getInstance().getNextState() == null) {
            return;
        }

        FragmentManager fm = getSupportFragmentManager();

        switch (StateClass.getInstance().getNextState()) {
            case MAIN:
                fm.beginTransaction().replace(R.id.content_main, new MainFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.MAIN);
                break;
            case REGISTER:
                fm.beginTransaction().replace(R.id.content_main, new RegisterFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.REGISTER);
                break;
            case LOGIN:
                fm.beginTransaction().replace(R.id.content_main, new LoginFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.LOGIN);
                break;
            case DETAIL:
                StateClass.State state = StateClass.getInstance().getState();
                if (state == StateClass.State.MAIN || state == StateClass.State.HOT
                    || state == StateClass.State.DRAMA || state == StateClass.State.COMEDY
                    || state == StateClass.State.LATER || state == StateClass.State.BOUGHT
                        || state == StateClass.State.SEARCH) {
                    StateClass.getInstance().prevForDetail = state;
                }
                fm.beginTransaction().replace(R.id.content_main, new DetailFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.DETAIL);
                break;
            case BUY:
                fm.beginTransaction().replace(R.id.content_main, new BuyFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.BUY);
                break;
            case WATCH:
                fm.beginTransaction().replace(R.id.content_main, new WatchFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.WATCH);
                break;
            case HOT:
                fm.beginTransaction().replace(R.id.content_main, new CategoryFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.HOT);
                break;
            case DRAMA:
                fm.beginTransaction().replace(R.id.content_main, new CategoryFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.DRAMA);
                break;
            case COMEDY:
                fm.beginTransaction().replace(R.id.content_main, new CategoryFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.COMEDY);
                break;
            case LATER:
                fm.beginTransaction().replace(R.id.content_main, new CategoryFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.LATER);
                break;
            case BOUGHT:
                fm.beginTransaction().replace(R.id.content_main, new CategoryFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.BOUGHT);
                break;
            case SEARCH:
                fm.beginTransaction().replace(R.id.content_main, new CategoryFragment()).commit();
                StateClass.getInstance().setState(StateClass.State.SEARCH);
                break;
        }

        StateClass.getInstance().setNextState(newNextState);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id)  {
            case R.id.nav_main:
                StateClass.getInstance().setNextState(StateClass.State.MAIN);
                goNextState(StateClass.State.MAIN);
                break;
            case R.id.nav_login:
                if (StateClass.getInstance().isLogin()) {
                    StateClass.getInstance().setLogin(false);
                    setLoginText();
                } else {
                    StateClass.getInstance().setNextState(StateClass.State.LOGIN);
                    StateClass.State newNextState = StateClass.getInstance().getState();
                    goNextState(newNextState);
                }
                break;
            case R.id.nav_bought:
                if (!StateClass.getInstance().isLogin()) {
                    Toast.makeText(this,
                            "Необходимо авторизоваться",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                StateClass.getInstance().setNextState(StateClass.State.BOUGHT);
                goNextState(StateClass.State.MAIN);
                break;
            case R.id.nav_later:
                if (!StateClass.getInstance().isLogin()) {
                    Toast.makeText(this,
                            "Необходимо авторизоваться",
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                StateClass.getInstance().setNextState(StateClass.State.LATER);
                goNextState(StateClass.State.MAIN);
                break;
            case R.id.nav_popular:
                StateClass.getInstance().setNextState(StateClass.State.HOT);
                goNextState(StateClass.State.MAIN);
                break;
            case R.id.nav_comedy:
                StateClass.getInstance().setNextState(StateClass.State.COMEDY);
                goNextState(StateClass.State.MAIN);
                break;
            case R.id.nav_drama:
                StateClass.getInstance().setNextState(StateClass.State.DRAMA);
                goNextState(StateClass.State.MAIN);
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
