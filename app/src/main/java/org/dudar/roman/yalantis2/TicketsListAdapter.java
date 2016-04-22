package org.dudar.roman.yalantis2;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * Created by Roman on 22.04.2016.
 */
public class TicketsListAdapter extends ArrayAdapter<Ticket> {


    public TicketsListAdapter(Context context, Ticket[] objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
