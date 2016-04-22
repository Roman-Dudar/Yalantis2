package org.dudar.roman.yalantis2;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.format.DateFormat;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Roman on 24.03.2016.
 */
public class Ticket {

    private String mId;
    private int mDomainId;
    private int mStatus;
    private String mDescription;
    private Date mRegistrationDate;
    private Date mDeadline;
    private String mAssignee;
    private int mLikesCount;
    private String mAddress;

    protected static final Ticket[] ALL_TICKETS = {
        new Ticket("CE-123124", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-456747", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-234234", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-348944", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-567774", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-995434", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-156432", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-075321", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-546787", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе"),
        new Ticket("CE-243669", 0, 0, "Ой-ой-ой как все плохо", "Где-то в космосе")
    };

    protected static final int[] IMAGES_IDS = {
            R.drawable.v, R.drawable.c, R.drawable.d, R.drawable.b
    };

    private static final int[] DOMAINS_ICONS = {
            R.drawable.ic_public_property, R.drawable.ic_public_property,
            R.drawable.ic_public_property, R.drawable.ic_public_property,
            R.drawable.ic_public_property, R.drawable.ic_public_property
    };

    private Ticket(String mId, int mDomainId, int mStatus, String mDescription, String mAddress) {
        this.mId = mId;
        this.mDomainId = mDomainId;
        this.mStatus = mStatus;
        this.mDescription = mDescription;
        this.mAddress = mAddress;
        mAssignee = "Как обычно";
        mLikesCount = 10;

        long dayMs = 24 * 60 * 60 * 1000;
        Date today = new Date();
        mRegistrationDate = today;
        mDeadline = new Date(today.getTime() + (5 * dayMs));
    }


    protected String getDomain(Context context){
        String[] allDomains = context.getResources().getStringArray(R.array.ticket_domains);
        return allDomains[mDomainId];
    }

    protected Drawable getDomainIcon(Context context) {
        Drawable domainIcon = ContextCompat.getDrawable(context, DOMAINS_ICONS[mDomainId]);
        return domainIcon;
    }

    protected String getAddress(){
        return mAddress;
    }

    protected String getDeadline(Context context) {
        return DateFormat.getMediumDateFormat(context).format(mDeadline);
    }

    protected String getRegistrationDate(Context context) {
        return DateFormat.getMediumDateFormat(context).format(mRegistrationDate);
    }

    protected int getDaysLeft() {
        if (mStatus == 1) throw new RuntimeException("Called getDaysLeft() for closed Ticket");
        return daysBetween(new Date(), mDeadline);
    }

    protected int getLikesCount() {
        return mLikesCount;
    }

    private static final int daysBetween(Date date1, Date date2) {
        int daysBetween;

        Calendar day1 = Calendar.getInstance();
        Calendar day2 = Calendar.getInstance();
        day1.setTime(date1);
        day2.setTime(date2);

        if (day1.get(Calendar.YEAR) == day2.get(Calendar.YEAR)) {
            daysBetween = Math.abs(day1.get(Calendar.DAY_OF_YEAR) - day2.get(Calendar.DAY_OF_YEAR));
        } else {
            if (day2.get(Calendar.YEAR) > day1.get(Calendar.YEAR)) {
                //swap them
                Calendar temp = day1;
                day1 = day2;
                day2 = temp;
            }
            int extraDays = 0;

            int dayOneOriginalYearDays = day1.get(Calendar.DAY_OF_YEAR);

            while (day1.get(Calendar.YEAR) > day2.get(Calendar.YEAR)) {
                day1.add(Calendar.YEAR, -1);
                // getActualMaximum() important for leap years
                extraDays += day1.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            daysBetween = extraDays - day2.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays;
        }

        return daysBetween;
    }


}
