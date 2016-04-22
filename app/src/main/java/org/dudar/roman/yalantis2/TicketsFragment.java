package org.dudar.roman.yalantis2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.Arrays;


public class TicketsFragment extends Fragment {

    private static final String ARGS_PAGE_NUMBER = "page_number";
    private static final int LISTVIEW_PAGE = 5;


    public TicketsFragment() {
        // Required empty public constructor
    }

    public static TicketsFragment newInstance(int page) {
        TicketsFragment fragment = new TicketsFragment();
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE_NUMBER, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        int pageNumber = getArguments().getInt(ARGS_PAGE_NUMBER);
        
        if (pageNumber == LISTVIEW_PAGE) {
            ListView listView =
                    (ListView) inflater.inflate(R.layout.tickets_list_view, container, false);
            TicketsListAdapter listAdapter =
                    new TicketsListAdapter(getContext(), Ticket.ALL_TICKETS);
            listView.setAdapter(listAdapter);
            rootView = listView;
        } else {
            RecyclerView recyclerView =
                    (RecyclerView) inflater.inflate(R.layout.tickets_recycler, container, false);

            TicketCardsAdapter cardsAdapter =
                    new TicketCardsAdapter(Arrays.asList(Ticket.ALL_TICKETS));
            recyclerView.setAdapter(cardsAdapter);

            LinearLayoutManager layoutManager = new LinearLayoutManager(recyclerView.getContext());
            recyclerView.setLayoutManager(layoutManager);
            rootView = recyclerView;
        }
        return rootView;
    }

}
