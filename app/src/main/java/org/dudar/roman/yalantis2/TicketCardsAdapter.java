package org.dudar.roman.yalantis2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

/**
 * Created by Roman on 21.04.2016.
 */
public class TicketCardsAdapter extends RecyclerView.Adapter<TicketCardsAdapter.CardViewHolder> {

    private List<Ticket> mTickets;

    public TicketCardsAdapter(List<Ticket> tickets) {
        this.mTickets = tickets;
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        private ImageView domainIcon;
        private TextView domainText;
        private TextView addressText;
        private TextView likesCount;
        private TextView registeredDate;
        private TextView daysLeft;

        public CardViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;

            domainIcon = (ImageView) cardView.findViewById(R.id.domain_icon);
            domainText = (TextView) cardView.findViewById(R.id.domain_text);
            addressText = (TextView) cardView.findViewById(R.id.address_text);
            likesCount = (TextView) cardView.findViewById(R.id.likes_count);
            registeredDate = (TextView) cardView.findViewById(R.id.registered_date);
            daysLeft = (TextView) cardView.findViewById(R.id.days_left);
        }
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CardView cardView = (CardView) inflater.inflate(R.layout.ticket_card_view, parent, false);
        return new CardViewHolder(cardView);
    }

    @Override
    public void onBindViewHolder(CardViewHolder cardHolder, int position) {
        Ticket ticket = mTickets.get(position);
        Context context = cardHolder.cardView.getContext();

        cardHolder.domainIcon.setImageDrawable(ticket.getDomainIcon(context));
        cardHolder.domainText.setText(ticket.getDomain(context));
        cardHolder.addressText.setText(ticket.getAddress());
        cardHolder.likesCount.setText(String.valueOf(ticket.getLikesCount()));
        cardHolder.registeredDate.setText(ticket.getRegistrationDate(context));
        cardHolder.daysLeft.setText(String.valueOf(ticket.getDaysLeft()) + " днів");
    }

    @Override
    public int getItemCount() {
        return mTickets.size();
    }

}
