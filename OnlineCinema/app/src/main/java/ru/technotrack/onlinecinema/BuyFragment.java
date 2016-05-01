package ru.technotrack.onlinecinema;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BuyFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bought, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Movie movie = MovieStorage.movies.get(StateClass.getInstance().getMovie());
        ((TextView) view.findViewById(R.id.buy_title)).setText(movie.getName());
        ((Button) view.findViewById(R.id.buy_button)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StateClass.getInstance().boughtMovies
                                .add(StateClass.getInstance().getMovie());
                        ((MainActivity) getActivity()).goNextState(StateClass.State.MAIN);
                        Toast.makeText(getActivity(), "Фильм куплен и добавлен в купленные",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        ((Button) view.findViewById(R.id.buy_button)).setText("Купить за " + movie.getPrice());
    }
}
