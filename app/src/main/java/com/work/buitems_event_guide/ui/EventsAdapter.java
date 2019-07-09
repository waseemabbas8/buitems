package com.work.buitems_event_guide.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.work.buitems_event_guide.Event_Info;
import com.work.buitems_event_guide.MainActivity;
import com.work.buitems_event_guide.R;
import com.work.buitems_event_guide.model.Event;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.work.buitems_event_guide.util.AppConstants.KEY_EVENT_OBJ;

public class EventsAdapter extends BaseAdapter {

    private List<Event> events;
    private ArrayList<Event> arraylist;
    private  Context context;

    public EventsAdapter(Context context, List<Event> events){
        this.events = events;
        this.context = context;
        this.arraylist = new ArrayList<>();
        this.arraylist.addAll(events);
    }

    @Override
    public int getCount() {
        return events.size();
    }

    @Override
    public Object getItem(int position) {
        return events.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.item_event_list, parent, false);
        }

        final Event event = events.get(position);

        TextView mEventName = convertView.findViewById(R.id.list_event_name);
        mEventName.setText(event.getEventName());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Event_Info.class);
                intent.putExtra(KEY_EVENT_OBJ, event);
                context.startActivity(intent);
            }
        });

        return convertView;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        events.clear();
        if (charText.length() == 0) {
            events.addAll(arraylist);
        } else {
            for (Event event : arraylist) {
                if (event.getEventName().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    events.add(event);
                }
            }
        }
        notifyDataSetChanged();
    }
}
