package com.sjrnr.hamza.widgets101;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

/**
 * Created by Hamza Fetuga on 8/6/2016.
 */
public class AppWidget101Provider extends AppWidgetProvider{

    @Override
    public void onUpdate(Context context,
                         AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        final int n = appWidgetIds.length;
        Log.d(getClass().getSimpleName(), "in OnUpdate() " + n);
        //Do this for each app widget that belongs to this provider

        for (int i = 0; i < n; i++){
            int appWidgetId = appWidgetIds[i];

            // Create an Intent to change the number
            Intent intent = new Intent(context, getClass());
            intent.setAction("GENERATE_NEW");
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);


            // Get the layout for the App Widget and attach an on-click listener
            // to the button
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_lay);
            views.setTextViewText(R.id.textView, Integer.toString(WidgetHelpers.generateRandomNumber()));
            views.setOnClickPendingIntent(R.id.button, pendingIntent);
            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }


    //0138037630
    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        Log.d(getClass().getSimpleName(), "clicked");
        // to generate number and update view
        if (intent.getAction().equalsIgnoreCase("GENERATE_NEW")){

            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            //thinking I should reset the intents here

        ComponentName thisAppWidgetComponentName =
                new ComponentName(context.getPackageName(),getClass().getName()
                );
        int[] appWidgetIds = manager.getAppWidgetIds(
                thisAppWidgetComponentName);
        onUpdate(context, manager, appWidgetIds);
            //manager.updateAppWidget(appWidgetId, remoteViews);
            }


    }


}
