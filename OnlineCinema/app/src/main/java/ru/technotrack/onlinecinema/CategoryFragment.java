package ru.technotrack.onlinecinema;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Set;

public class CategoryFragment extends Fragment {
    public CategoryFragment() {
    }

    private void fillLayout(LayoutInflater inflater, LinearLayout layout, Movie.TAG tag) {
        for (final Integer id : MovieStorage.movies.keySet()) {
            Movie movie = MovieStorage.movies.get(id);
            if (movie.containTag(tag)) {
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
                ((ImageView) movieView.findViewById(R.id.movie_view_image))
                        .setImageResource(movie.getPicture());
                ((ImageView) movieView.findViewById(R.id.movie_view_image))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StateClass.getInstance().setNextState(StateClass.State.DETAIL);
                                StateClass.getInstance().setMovie(id);
                                ((MainActivity) getActivity()).goNextState(StateClass.State.MAIN);
                            }
                        });
                if (!movie.isFree()) {
                    ((ImageView) movieView.findViewById(R.id.movie_view_price))
                            .setImageResource(R.drawable.dollar);
                }

                layout.addView(movieView);
            }
        }
    }

    private void fillLayout(LayoutInflater inflater, LinearLayout layout,
                            Set<Integer> idSet) {

        for (final Integer id : MovieStorage.movies.keySet()) {
            Movie movie = MovieStorage.movies.get(id);
            if (idSet.contains(id) &&
                    (StateClass.getInstance().getState() != StateClass.State.SEARCH
                            || movie.isFree() || !StateClass.getInstance().showFreeSearch)) {
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
                ((ImageView) movieView.findViewById(R.id.movie_view_image))
                        .setImageResource(movie.getPicture());
                ((ImageView) movieView.findViewById(R.id.movie_view_image))
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                StateClass.getInstance().setNextState(StateClass.State.DETAIL);
                                StateClass.getInstance().setMovie(id);
                                ((MainActivity) getActivity()).goNextState(StateClass.State.MAIN);
                            }
                        });
                if (!movie.isFree()) {
                    ((ImageView) movieView.findViewById(R.id.movie_view_price))
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

        HorizontalScrollView root = new HorizontalScrollView(inflater.getContext());
        HorizontalScrollView.LayoutParams params = new HorizontalScrollView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(params);

        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.list_layout, root, false);

        switch (StateClass.getInstance().getState()) {
            case HOT:
                ((TextView) layout.getChildAt(1)).setText("Популярное");
                fillLayout(inflater, (LinearLayout) layout.getChildAt(2), Movie.TAG.HOT);
                break;
            case DRAMA:
                ((TextView) layout.getChildAt(1)).setText("Драма");
                fillLayout(inflater, (LinearLayout) layout.getChildAt(2), Movie.TAG.DRAMA);
                break;
            case COMEDY:
                ((TextView) layout.getChildAt(1)).setText("Комедия");
                fillLayout(inflater, (LinearLayout) layout.getChildAt(2), Movie.TAG.COMEDY);
                break;
            case LATER:
                ((TextView) layout.getChildAt(1)).setText("Смотреть позже");
                fillLayout(inflater, (LinearLayout) layout.getChildAt(2),
                        StateClass.getInstance().watchLater);
                break;
            case BOUGHT:
                ((TextView) layout.getChildAt(1)).setText("Купленные");
                fillLayout(inflater, (LinearLayout) layout.getChildAt(2),
                        StateClass.getInstance().boughtMovies);
                break;
            case SEARCH:
                (layout.getChildAt(0)).setVisibility(View.VISIBLE);
                ((Switch) layout.getChildAt(0)).setChecked(StateClass.getInstance().showFreeSearch);
                ((Switch) layout.getChildAt(0)).setOnCheckedChangeListener(
                        new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                StateClass.getInstance().showFreeSearch = isChecked;
                                StateClass.getInstance().setNextState(StateClass.State.SEARCH);
                                ((MainActivity) getActivity()).goNextState(StateClass.State.MAIN);
                            }
                        });
                ((TextView) layout.getChildAt(1)).setText("Результаты поиска:");
                fillLayout(inflater, (LinearLayout) layout.getChildAt(2),
                        StateClass.getInstance().searchResult);
                break;
        }

        root.addView(layout);

        return root;
    }

}
