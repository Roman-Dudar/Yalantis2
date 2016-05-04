package org.dudar.roman.yalantis2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;


public class TicketsFragment extends Fragment implements View.OnClickListener {

    protected static final int TICKET_ID_TAG_KEY = 83766452;
    protected static final String TICKET_ID_EXTRA = String.valueOf(TICKET_ID_TAG_KEY);
    private static final String TICKETS_FILTER_ID = "filter";

    public TicketsFragment() {
        // Required empty public constructor
    }

    public static TicketsFragment newInstance(int filterId) {
        TicketsFragment fragment = new TicketsFragment();
        Bundle args = new Bundle();
        args.putInt(TICKETS_FILTER_ID, filterId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView;
        int ticketsFiler = getArguments().getInt(TICKETS_FILTER_ID);

        if (ticketsFiler == ViewPagerAdapter.LIST_VIEW_PAGE) {

            ListView listView =
                    (ListView) inflater.inflate(R.layout.tickets_list_view, container, false);
            TicketsListAdapter listAdapter = new TicketsListAdapter(
                    container.getContext(),
                    Ticket.getFilteredTickets(ticketsFiler),
                    this);
            listView.setAdapter(listAdapter);

            listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    FabController fabController = null;
                    if (getActivity() != null && getActivity() instanceof FabController)
                        fabController = (FabController) getActivity();

                    if (scrollState == SCROLL_STATE_IDLE && fabController != null) {
                        fabController.showFab();
                    } else if (fabController != null) {
                        fabController.hideFab();
                    }
                }

                //not used, required by interface
                @Override
                public void onScroll(AbsListView view,
                                     int firstVisibleItem,
                                     int visibleItemCount,
                                     int totalItemCount) {
                    return;
                }
            });

            rootView = listView;

        } else {
            RecyclerView recyclerView =
                    (RecyclerView) inflater.inflate(R.layout.tickets_recycler, container, false);

            TicketCardsAdapter cardsAdapter =
                    new TicketCardsAdapter(Ticket.getFilteredTickets(ticketsFiler), this);
            recyclerView.setAdapter(cardsAdapter);

            LinearLayoutManager layoutManager =
                    new LinearLayoutManager(recyclerView.getContext());
            recyclerView.setLayoutManager(layoutManager);

            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    FabController fabController = null;
                    if (getActivity() != null && getActivity() instanceof FabController)
                        fabController = (FabController) getActivity();

                    if (newState == RecyclerView.SCROLL_STATE_IDLE && fabController != null) {
                        fabController.showFab();
                    } else if (fabController != null) {
                        fabController.hideFab();
                    }
                    super.onScrollStateChanged(recyclerView, newState);
                }
            });

            rootView = recyclerView;
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        OnItemClick(v);
    }

    private void OnItemClick(View v) {
        String ticketId = (String) v.getTag(TICKET_ID_TAG_KEY);

        Intent showDetails = new Intent(getActivity(), TicketDetailsActivity.class);
        showDetails.putExtra(TICKET_ID_EXTRA, ticketId);

        startActivity(showDetails);
    }
}
