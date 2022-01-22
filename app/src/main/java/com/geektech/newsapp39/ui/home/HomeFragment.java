package com.geektech.newsapp39.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.newsapp39.R;
import com.geektech.newsapp39.databinding.FragmentHomeBinding;
import com.geektech.newsapp39.models.News;

import java.util.ArrayList;

public class HomeFragment extends Fragment  {

    private FragmentHomeBinding binding;
    private NewsAdapter adapter;

    private ArrayList<News> list = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
       return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
        initFragmentResultListener();
        initRv();

    }

    private void initRv() {
        adapter = new NewsAdapter();
        binding.newsRu.setAdapter(adapter);

        adapter.setOnItemClickListener(new NewsAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int pos) {

            }

            @Override
            public void onLongItemClick(int pos) {
             new AlertDialog.Builder(getContext()).setTitle("Delete").setMessage("You are sure?")
                     .setNegativeButton("No",null)
             .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                 @Override
                 public void onClick(DialogInterface dialog, int which) {
                     News news;
                     news = adapter.addNews(pos);
                     adapter.deleteNews(pos);
                 }
             }).show();
            }
        });
    }


    private void initFragmentResultListener() {
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result)
                    {
                        News news = (News) result.getSerializable("news");
                        list.add(news);
                        adapter.addNewsList(list);
                        Log.e("TAG", "task =  " + news.getTitle());


                    }
                });
        initRv();
    }


    private void initListener() {
        binding.fab.setOnClickListener(view1 -> {
            openFragment();
        });

    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

}



