package com.wapazock.doozby.HomeActivity.HomeActivityFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.wapazock.doozby.Classes.Movie;
import com.wapazock.doozby.R;
import com.wapazock.doozby.Utils.RecyclerViewAllContentAdapter;
import com.wapazock.doozby.Utils.RecyclerViewSpacedDecoration;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AllContentFragment extends Fragment {

    // View
    RecyclerView allContentRecyclerView;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_content_fragement,container,false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // View
        allContentRecyclerView = view.findViewById(R.id.allContentRecyclerView);

        // Bind Recycler View
        bindContentRecyclerView();
    }

    // Set Recycler View -- Binds the recycler view with the data
    private void bindContentRecyclerView(){
        // Dummy Movie List
        ArrayList<Movie> dummyList = new ArrayList<>();
        dummyList.add(new Movie("YTTROL1H9ZX9248EHKBD1DVZ96OCQR30H06IKPG3T9U3JIXL9246XQFODE0PMRLP"));
        dummyList.add(new Movie("5DSPLF0MAW5AGEM8TNVRL0TP8JY69E1BQVY5AZTC0RYBG5DQE8MODHHA5DM9R9PN"));
        dummyList.add(new Movie("AGRIS9XF02ESD124JWJ1F76AMCM29F3CRLRKKWYEINDQW4RYR5OZFTSJAVA8C7KW"));
        dummyList.add(new Movie("Y4VS9EJJOS5TG96C0FFZEELDR9CRAC96VOJ2CU5SPJW36T1RM5DQQASZFLPI7FDN"));
        dummyList.add(new Movie("YTTROL1H9ZX9248EHKBD1DVZ96OCQR30H06IKPG3T9U3JIXL9246XQFODE0PMRLP"));
        dummyList.add(new Movie("5DSPLF0MAW5AGEM8TNVRL0TP8JY69E1BQVY5AZTC0RYBG5DQE8MODHHA5DM9R9PN"));
        dummyList.add(new Movie("AGRIS9XF02ESD124JWJ1F76AMCM29F3CRLRKKWYEINDQW4RYR5OZFTSJAVA8C7KW"));
        dummyList.add(new Movie("Y4VS9EJJOS5TG96C0FFZEELDR9CRAC96VOJ2CU5SPJW36T1RM5DQQASZFLPI7FDN"));
        dummyList.add(new Movie("YTTROL1H9ZX9248EHKBD1DVZ96OCQR30H06IKPG3T9U3JIXL9246XQFODE0PMRLP"));
        dummyList.add(new Movie("5DSPLF0MAW5AGEM8TNVRL0TP8JY69E1BQVY5AZTC0RYBG5DQE8MODHHA5DM9R9PN"));
        dummyList.add(new Movie("AGRIS9XF02ESD124JWJ1F76AMCM29F3CRLRKKWYEINDQW4RYR5OZFTSJAVA8C7KW"));
        dummyList.add(new Movie("Y4VS9EJJOS5TG96C0FFZEELDR9CRAC96VOJ2CU5SPJW36T1RM5DQQASZFLPI7FDN"));
        dummyList.add(new Movie("YTTROL1H9ZX9248EHKBD1DVZ96OCQR30H06IKPG3T9U3JIXL9246XQFODE0PMRLP"));
        dummyList.add(new Movie("5DSPLF0MAW5AGEM8TNVRL0TP8JY69E1BQVY5AZTC0RYBG5DQE8MODHHA5DM9R9PN"));
        dummyList.add(new Movie("AGRIS9XF02ESD124JWJ1F76AMCM29F3CRLRKKWYEINDQW4RYR5OZFTSJAVA8C7KW"));
        dummyList.add(new Movie("Y4VS9EJJOS5TG96C0FFZEELDR9CRAC96VOJ2CU5SPJW36T1RM5DQQASZFLPI7FDN"));


        // New Adapter
        RecyclerViewAllContentAdapter allContentAdapter = new RecyclerViewAllContentAdapter(dummyList,AllContentFragment.this.getContext());

        // Assign Adapter
        allContentRecyclerView.setAdapter(allContentAdapter);

        // Add Item Decoration
        allContentRecyclerView.addItemDecoration(new RecyclerViewSpacedDecoration(15));

    }

}
