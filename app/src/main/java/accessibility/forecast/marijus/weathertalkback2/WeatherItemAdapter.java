package accessibility.forecast.marijus.weathertalkback2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.zetterstrom.com.forecast.models.DataPoint;

import java.util.ArrayList;
import java.util.List;

import accessibility.forecast.marijus.weathertalkback2.WeatherFragment.OnListFragmentInteractionListener;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.helper.ItemDataStringUtils;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DataPoint} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class WeatherItemAdapter extends RecyclerView.Adapter<WeatherItemAdapter.ViewHolder> {

    private List<WeatherItem> weatherItems;
    private final OnListFragmentInteractionListener listener;
    private final Context context;

    public WeatherItemAdapter(List<WeatherItem> items, Context context, OnListFragmentInteractionListener listener) {
        weatherItems = items;
        this.listener = listener;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_weatheritem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        WeatherItem currentItem = weatherItems.get(position);

        holder.item = currentItem;
        holder.condition.setText(currentItem.getmSummary());
        holder.temperature.setText(ItemDataStringUtils.getFormattedAsTemperature(currentItem.getmTemperature()));
        holder.windSpeed.setText(ItemDataStringUtils.getFormattedAsWindSpeed(currentItem.getmWindSpeed()));
        holder.windDirection.setText(ItemDataStringUtils.getWindDirectionDescription(currentItem.getmWindBearing()));
        holder.icon.setImageResource(context.getResources().getIdentifier(ItemDataStringUtils.getFormattedIconName(currentItem.getmIcon()),
                "drawable", context.getPackageName()));
        holder.updateTime.setText(context.getString(R.string.updated, currentItem.getmTimeOfDayCreated()));

        //TODO: Show when item was updated
        // set source to display cached or not

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != listener) {
                    listener.onListFragmentInteraction(holder.item);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return weatherItems.size();
    }

    public void replaceData(ArrayList<WeatherItem> dailyWeatherData) {
        setList(dailyWeatherData);
        notifyDataSetChanged();
    }

    private void setList(List<WeatherItem> dailyWeatherData) {
        weatherItems = checkNotNull(dailyWeatherData);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public final TextView condition;
        public final TextView temperature;
        public final TextView windSpeed;
        public final TextView windDirection;
        public final ImageView icon;
        public WeatherItem item;

        //TODO: Show when item was updated
        public final TextView updateTime;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            condition = view.findViewById(R.id.tv_condition_value);
            temperature = view.findViewById(R.id.tv_temperature_value);
            windSpeed = view.findViewById(R.id.tv_wind_speed_value);
            windDirection = view.findViewById(R.id.tv_wind_direction_value);
            icon = view.findViewById(R.id.iv_weather_icon);
            updateTime = view.findViewById(R.id.tv_time_updated);
        }
    }
}
