package com.wapazock.doozby.HomeActivity.HomeActivityFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.wapazock.doozby.Classes.Movie;
import com.wapazock.doozby.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AllContentFragment extends Fragment {

    // View
    RecyclerView allContentRecyclerView;
    AllContentFragmentViewModel fragmentViewModel;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_content_fragement,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // View model
        fragmentViewModel = new ViewModelProvider(this).get(AllContentFragmentViewModel.class);

        // View
        allContentRecyclerView = view.findViewById(R.id.allContentRecyclerView);

        // Bind Recycler View
        bindContentRecyclerView();
    }

    // Set Recycler View -- Binds the recycler view with the data
    private void bindContentRecyclerView(){


        // New Adapter
        RecyclerViewAllContentAdapter allContentAdapter = new RecyclerViewAllContentAdapter(fragmentViewModel.getMovies().getValue(),AllContentFragment.this.getContext());

        // Assign Adapter
        allContentRecyclerView.setAdapter(allContentAdapter);

        // Add Item Decoration
        allContentRecyclerView.addItemDecoration(new RecyclerViewSpacedDecoration(15));

        // Detect changes in the data set
        fragmentViewModel.getMovies().observe(AllContentFragment.this.getActivity(), new Observer<ArrayList<Movie>>() {
            @Override
            public void onChanged(ArrayList<Movie> movies) {
                allContentAdapter.notifyDataSetChanged();
            }
        });

    }

}
