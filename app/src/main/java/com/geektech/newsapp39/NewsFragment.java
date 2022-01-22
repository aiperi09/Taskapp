package com.geektech.newsapp39;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.newsapp39.databinding.FragmentNewsBinding;
import com.geektech.newsapp39.models.News;

public class NewsFragment extends Fragment {
    private FragmentNewsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentNewsBinding.inflate(inflater,container,false);
       return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });


        }

    private void save() {
        String title = binding.editText.getText().toString().trim();
        News news = new News(title,System.currentTimeMillis());
        Bundle bundle = new Bundle();
        bundle.putSerializable("news",news);
        getParentFragmentManager().setFragmentResult("rk_news",bundle);
        close();
    }

    private void close() {
        NavController navController = Navigation.findNavController(requireActivity(),
                R.id.nav_host_fragment_activity_main);
        navController.navigateUp();
    }


}