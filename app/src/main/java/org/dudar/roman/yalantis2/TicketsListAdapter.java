package org.dudar.roman.yalantis2;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Roman on 22.04.2016.
 */
public class TicketsListAdapter extends ArrayAdapter<Ticket> {

    private static View.OnClickListener mOnItemClickListener;

    public TicketsListAdapter(Context context,
                              List<Ticket> tickets,
                              View.OnClickListener listener) {
        super(context, 0, tickets);
        mOnItemClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Ticket ticket = getItem(position);

        final ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.ticket_card_view_encapsulated, parent, false);

            viewHolder.domainIcon = (ImageView) convertView.findViewById(R.id.domain_icon);
            viewHolder.domainText = (TextView) convertView.findViewById(R.id.domain_text);
            viewHolder.addressText = (TextView) convertView.findViewById(R.id.address_text);
            viewHolder.likesCount = (TextView) convertView.findViewById(R.id.likes_count);
            viewHolder.registeredDate = (TextView) convertView.findViewById(R.id.registered_date);
            viewHolder.cardView = (CardView) convertView.findViewById(R.id.ticket_card_view);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Context context = viewHolder.cardView.getContext();

        viewHolder.domainIcon.setImageDrawable(ticket.getDomainIcon(context));
        viewHolder.domainText.setText(ticket.getDomain(context));
        viewHolder.addressText.setText(ticket.getAddress());
        viewHolder.likesCount.setText(String.valueOf(ticket.getLikesCount()));
        viewHolder.registeredDate.setText(ticket.getRegistrationDate(context));

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setTag(TicketsFragment.TICKET_ID_TAG_KEY, ticket.getId());
                mOnItemClickListener.onClick(v);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        private CardView cardView;
        private ImageView domainIcon;
        private TextView domainText;
        private TextView addressText;
        private TextView likesCount;
        private TextView registeredDate;
    }
}
