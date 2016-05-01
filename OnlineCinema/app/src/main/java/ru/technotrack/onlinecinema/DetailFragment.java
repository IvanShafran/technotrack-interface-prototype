package ru.technotrack.onlinecinema;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DetailFragment extends Fragment {

    private DetailFragment getThis() {
        return this;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Movie movie = MovieStorage.movies.get(StateClass.getInstance().getMovie());
        ((ImageView) view.findViewById(R.id.imageView)).setImageResource(movie.getPicture());
        ((TextView) view.findViewById(R.id.detail_name)).setText(movie.getName());
        ((TextView) view.findViewById(R.id.detail_actors)).setText("Актёры: " + movie.getActors());
        ((TextView) view.findViewById(R.id.detail_country)).setText("Страна: " +
                movie.getCountry());
        ((TextView) view.findViewById(R.id.detail_info)).setText(movie.getInfo());

        if (StateClass.getInstance().watchLater.contains(StateClass.getInstance().getMovie())) {
            ((Switch) view.findViewById(R.id.detail_switch)).setChecked(true);
        }

        ((Switch) view.findViewById(R.id.detail_switch)).setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (StateClass.getInstance().isLogin()) {
                            if (isChecked) {
                                Toast.makeText(getThis().getContext(),
                                        "Фильм добавлен в список Смотреть позже",
                                        Toast.LENGTH_SHORT).show();
                                StateClass.getInstance().watchLater
                                        .add(StateClass.getInstance().getMovie());
                            } else {
                                StateClass.getInstance().watchLater
                                        .remove(StateClass.getInstance().getMovie());
                            }
                        } else {
                            Toast.makeText(getThis().getContext(),
                                    "Необходимо авторизоваться",
                                    Toast.LENGTH_SHORT).show();

                            buttonView.setChecked(false);
                        }
                    }
                });

        Button button = (Button) view.findViewById(R.id.button);

        if (movie.isFree()) {
            button.setText("Смотреть");
        } else {
            if (StateClass.getInstance().isLogin()
                    && StateClass.getInstance().boughtMovies
                    .contains(StateClass.getInstance().getMovie())) {

                button.setText("Смотреть");
            } else {
                button.setText("Купить " + movie.getPrice());
            }
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (StateClass.getInstance().isLogin()) {
                    if (movie.isFree()) {
                        StateClass.getInstance().setNextState(StateClass.State.WATCH);
                        ((MainActivity)getActivity()).goNextState(StateClass.State.DETAIL);
                    } else {
                        if (StateClass.getInstance().boughtMovies
                                .contains(StateClass.getInstance().getMovie())) {
                            StateClass.getInstance().setNextState(StateClass.State.WATCH);
                            ((MainActivity)getActivity()).goNextState(StateClass.State.DETAIL);
                        } else {
                            StateClass.getInstance().setNextState(StateClass.State.BUY);
                                    ((MainActivity) getActivity()).goNextState(StateClass.State.DETAIL);
                        }
                    }
                } else {
                    if (movie.isFree()) {
                        StateClass.getInstance().setNextState(StateClass.State.WATCH);
                        ((MainActivity)getActivity()).goNextState(StateClass.State.DETAIL);
                    } else {
                        Toast.makeText(getThis().getContext(),
                                "Необходимо авторизоваться",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }
}
