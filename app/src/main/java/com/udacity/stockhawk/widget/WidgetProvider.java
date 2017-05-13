package com.udacity.stockhawk.widget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.udacity.stockhawk.R;


public class WidgetProvider extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        for (int i : appWidgetIds) {
            RemoteViews rv = new RemoteViews(context.getPackageName(),
                    R.layout.widget_provider_layout);

            Intent adapter = new Intent(context, StocksWidgetService.class);
            adapter.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, i);
            rv.setRemoteAdapter(R.id.list_view, adapter);

            appWidgetManager.updateAppWidget(i, rv);
            appWidgetManager.notifyAppWidgetViewDataChanged(i,
                    R.id.list_view);
        }
    }

}
