package accessibility.forecast.marijus.weathertalkback2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import accessibility.forecast.marijus.weathertalkback2.WeatherFragment.OnListFragmentInteractionListener;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.helper.utils.ItemDataStringUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * {@link RecyclerView.Adapter} that can display a {@link WeatherItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class WeatherItemListAdapter extends RecyclerView.Adapter<WeatherItemListAdapter.ViewHolder> {

    private final OnListFragmentInteractionListener listener;
    private final Context context;
    private ArrayList<WeatherItem> weatherItems;

    public WeatherItemListAdapter(ArrayList<WeatherItem> items, Context context, OnListFragmentInteractionListener listener) {
        weatherItems = items;
        this.listener = listener;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_weatheritem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        WeatherItem currentItem = weatherItems.get(position);

        //TODO: Display name of the day and/or full date
        holder.item = currentItem;
        //TODO: Improve the view to remove summary
//        holder.condition.setText(currentItem.getSummary());
        //TODO: Improve the view to show high/low temperatures
        holder.temperature.setText(ItemDataStringUtils.getFormattedAsTemperature(currentItem.getTemperatureHigh()));
        holder.windSpeed.setText(ItemDataStringUtils.getFormattedAsWindSpeed(currentItem.getWindSpeed()));
        holder.windDirection.setText(ItemDataStringUtils.getWindDirectionDescription(currentItem.getWindBearing()));
        holder.icon.setImageResource(context.getResources().getIdentifier(ItemDataStringUtils.getFormattedIconName(currentItem.getIcon()),
                "drawable", context.getPackageName()));
//        holder.updateTime.setText(context.getString(R.string.updated, currentItem.getmTimeOfDayCreated()));

        holder.view.setOnClickListener(view -> {
            if (null != listener) {
                listener.onListFragmentInteraction(holder.item);
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

    private void setList(ArrayList<WeatherItem> dailyWeatherData) {
        weatherItems = checkNotNull(dailyWeatherData);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View view;
        public WeatherItem item;
        @BindView(R.id.tv_condition_value)
        TextView condition;
        @BindView(R.id.tv_temperature_value)
        TextView temperature;
        @BindView(R.id.tv_wind_speed_value)
        TextView windSpeed;
        @BindView(R.id.tv_wind_direction_value)
        TextView windDirection;
        @BindView(R.id.tv_time_updated)
        TextView updateTime;
        @BindView(R.id.iv_weather_icon)
        ImageView icon;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.view = view;
        }
    }

}
