package com.xiaojiu.studylibs.view;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2018/2/27 0027.
 */

public class AFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Log.e("info","Fragment--onAttach()");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("info","Fragment--onCreate()");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e("info","Fragment--onCreateView()");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("info","Fragment--onActivityCreated()");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("info","Fragment--onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("info","Fragment--onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("info","Fragment--onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("info","Fragment--onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("info","Fragment--onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("info","Fragment--onDestroy()");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.e("info","Fragment--onDetach()");
    }
}
