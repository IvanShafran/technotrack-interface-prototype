package ru.technotrack.onlinecinema;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        ((Button) getActivity().findViewById(R.id.login_vk)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StateClass.getInstance().setLogin(true);
                        StateClass.getInstance().setEmail("vk@gmail.com");
                        ((MainActivity) getActivity()).goNextState(null);
                        Toast.makeText(getActivity(), "Вы залогинены!", Toast.LENGTH_SHORT).show();
                    }
                });

        ((Button) getActivity().findViewById(R.id.login_fb)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StateClass.getInstance().setLogin(true);
                        StateClass.getInstance().setEmail("fb@gmail.com");
                        ((MainActivity) getActivity()).goNextState(null);
                        Toast.makeText(getActivity(), "Вы залогинены!", Toast.LENGTH_SHORT).show();
                    }
                });

        ((Button) getActivity().findViewById(R.id.button_login)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StateClass.getInstance().setLogin(true);
                        String str = ((TextView) view.findViewById(R.id.login_email))
                                .getText().toString();

                        StateClass.getInstance().setEmail(str);
                        ((MainActivity) getActivity()).goNextState(null);
                        Toast.makeText(getActivity(), "Вы залогинены!", Toast.LENGTH_SHORT).show();
                    }
                });

        ((Button) getActivity().findViewById(R.id.button_to_register)).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StateClass.State newNextState = StateClass.getInstance().getNextState();
                        StateClass.getInstance().setNextState(StateClass.State.REGISTER);
                        ((MainActivity) getActivity()).goNextState(newNextState);
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

}
