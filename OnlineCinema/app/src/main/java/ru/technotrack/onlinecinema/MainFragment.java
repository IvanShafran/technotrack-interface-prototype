package ru.technotrack.onlinecinema;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainFragment extends Fragment {

    private void fillLayout(LayoutInflater inflater, LinearLayout layout, Movie.TAG tag) {
        int count = 0;
        for (final Integer id : MovieStorage.movies.keySet()) {
            if (count == 3) {
                return;
            }
            Movie movie = MovieStorage.movies.get(id);
            if (movie.containTag(tag)) {
                ++count;
                ViewGroup movieView =
                        (ViewGroup) inflater.inflate(R.layout.movie_view, layout, false);

                ((TextView) movieView.findViewById(R.id.movie_view_title))
                        .setText(movie.getName());

                ((TextView) movieView.findViewById(R.id.movie_view_title))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StateClass.getInstance().setNextState(StateClass.State.DETAIL);
                                StateClass.getInstance().setMovie(id);
                                ((MainActivity) getActivity()).goNextState(StateClass.State.MAIN);
                            }
                        });
                ((ImageView)movieView.findViewById(R.id.movie_view_image))
                        .setImageResource(movie.getPicture());
                ((ImageView)movieView.findViewById(R.id.movie_view_image))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StateClass.getInstance().setNextState(StateClass.State.DETAIL);
                                StateClass.getInstance().setMovie(id);
                                ((MainActivity)getActivity()).goNextState(StateClass.State.MAIN);
                            }
                        });
                if (!movie.isFree()) {
                    ((ImageView)movieView.findViewById(R.id.movie_view_price))
                            .setImageResource(R.drawable.dollar);
                }

                layout.addView(movieView);
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_main, container, false);
        LinearLayout layout = (LinearLayout)view.findViewById(R.id.container_main);

        LinearLayout hot = (LinearLayout) inflater.inflate(R.layout.list_layout, layout, false);

        ((TextView) hot.getChildAt(1)).setText("Популярное");
        ((TextView) hot.getChildAt(1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateClass.getInstance().setNextState(StateClass.State.HOT);
                ((MainActivity)getActivity()).goNextState(StateClass.State.MAIN);
            }
        });
        fillLayout(inflater, (LinearLayout) hot.getChildAt(2), Movie.TAG.HOT);
        layout.addView(hot);

        LinearLayout drama = (LinearLayout) inflater.inflate(R.layout.list_layout, layout, false);

        ((TextView) drama.getChildAt(1)).setText("Драма");
        ((TextView) drama.getChildAt(1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateClass.getInstance().setNextState(StateClass.State.DRAMA);
                ((MainActivity) getActivity()).goNextState(StateClass.State.MAIN);
            }
        });
        fillLayout(inflater, (LinearLayout) drama.getChildAt(2), Movie.TAG.DRAMA);
        layout.addView(drama);

        LinearLayout comedy = (LinearLayout) inflater.inflate(R.layout.list_layout, layout, false);

        ((TextView) comedy.getChildAt(1)).setText("Комедия");
        ((TextView) comedy.getChildAt(1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateClass.getInstance().setNextState(StateClass.State.COMEDY);
                ((MainActivity) getActivity()).goNextState(StateClass.State.MAIN);
            }
        });
        fillLayout(inflater, (LinearLayout) comedy.getChildAt(2), Movie.TAG.COMEDY);
        layout.addView(comedy);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.big_poster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StateClass.getInstance().setMovie(0);
                StateClass.getInstance().setNextState(StateClass.State.DETAIL);
                ((MainActivity)getActivity()).goNextState(StateClass.State.MAIN);
            }
        });
    }
}
