package org.dudar.roman.yalantis2;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TicketDetailsActivity extends AppCompatActivity {

    private RecyclerView mImagesRecycler;
    private TextView mDomain;
    private TextView mStatus;
    private TextView mCreatedDate;
    private TextView mRegisteredDate;
    private TextView mDeadline;
    private TextView mAssignee;
    private TextView mDescription;
    private ActionBar mActionBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        mImagesRecycler = (RecyclerView) findViewById(R.id.images_recycler);
        mImagesRecycler.setLayoutManager(layoutManager);

        ImagesAdapter adapter = new ImagesAdapter(Ticket.IMAGES_IDS);
        mImagesRecycler.setAdapter(adapter);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        mDomain = (TextView) findViewById(R.id.domain_text);
        mStatus = (TextView) findViewById(R.id.status);
        mCreatedDate = (TextView) findViewById(R.id.created_date);
        mRegisteredDate = (TextView) findViewById(R.id.registered_date);
        mDeadline = (TextView) findViewById(R.id.deadline_date);
        mAssignee = (TextView) findViewById(R.id.assignee);
        mDescription = (TextView) findViewById(R.id.description);

        String ticketId = getIntent().getExtras().getString(TicketsFragment.TICKET_ID_EXTRA);
        Ticket ticket = Ticket.getById(ticketId);

        populateData(ticket);
    }

    public void onViewClick(View v) {
        String name = v.getClass().getSimpleName();
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void populateData(Ticket ticket) {
        setTitle(ticket.getId());

        mDomain.setText(ticket.getDomain(this));
        mStatus.setText(ticket.getStatusString(this));
        mDeadline.setText(ticket.getDeadline(this));
        mRegisteredDate.setText(ticket.getRegistrationDate(this));
    }
}