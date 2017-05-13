package com.udacity.stockhawk.widget;


import android.content.Context;
import android.database.Cursor;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.udacity.stockhawk.R;
import com.udacity.stockhawk.data.Contract;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


public class StocksWidgetFactory implements RemoteViewsService.RemoteViewsFactory{

    private Context context;
    private Cursor cursor;
    final private DecimalFormat dollarFormatWithPlus;
    final private DecimalFormat dollarFormat;


    public StocksWidgetFactory(Context context) {
        this.context = context;
        dollarFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        dollarFormatWithPlus = (DecimalFormat) NumberFormat.getCurrencyInstance(Locale.US);
        dollarFormatWithPlus.setPositivePrefix("+$");
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onDataSetChanged() {
        if (cursor != null) {
            cursor.close();
        }
        cursor = context.getContentResolver().query(Contract.Quote.URI, new String[]{Contract.Quote._ID, Contract.Quote.COLUMN_SYMBOL, Contract.Quote.COLUMN_PRICE,
                Contract.Quote.COLUMN_ABSOLUTE_CHANGE}, null, null, null, null);
    }
    @Override
    public void onDestroy() {
        if(cursor!=null){
            cursor.close();
        }
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        cursor.moveToPosition(position);
        RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.list_item_quote);
        if(cursor.moveToPosition(position)) {

            rv.setTextViewText(R.id.symbol,
                    cursor.getString(Contract.Quote.POSITION_SYMBOL));

            rv.setTextViewText(R.id.price,
                    dollarFormat.format(cursor.getFloat(Contract.Quote.POSITION_PRICE)));

            float rawAbsoluteChange = cursor.getFloat(Contract.Quote.POSITION_ABSOLUTE_CHANGE);
            String changeString = dollarFormatWithPlus.format(rawAbsoluteChange);

            if (rawAbsoluteChange > 0) {
                rv.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_green);
            } else {
                rv.setInt(R.id.change, "setBackgroundResource", R.drawable.percent_change_pill_red);
            }
            rv.setTextViewText(R.id.change,
                    changeString);
        }
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
