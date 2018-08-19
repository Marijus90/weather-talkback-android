package accessibility.forecast.marijus.weathertalkback2;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;

/**
 * A fragment representing a list of weather items.
 */
public class WeatherFragment extends Fragment implements WeatherContract.View {

    WeatherContract.Presenter presenter;

    private WeatherItemAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton refreshBtn;

    private ProgressBar loadingIndicator;
    private TextView noDataAvailable;
    private TextView noDataAvailableRefresh;

    private OnListFragmentInteractionListener listItemListener;

    public WeatherFragment() {
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        Context context = view.getContext();
        loadingIndicator = view.findViewById(R.id.progress_indicator);
        noDataAvailable = view.findViewById(R.id.tv_no_data_available);
        noDataAvailableRefresh = view.findViewById(R.id.tv_please_refresh);
        recyclerView = view.findViewById(R.id.list);

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new WeatherItemAdapter(new ArrayList<WeatherItem>(0), context, listItemListener);
        recyclerView.setAdapter(adapter);

        // Init refresh button
        refreshBtn = getActivity().findViewById(R.id.fab);
        if (refreshBtn != null) {
            refreshBtn.setRippleColor(getResources().getColor(R.color.colorPrimaryDark));
            refreshBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.refreshData(true);
                }
            });
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            listItemListener = (OnListFragmentInteractionListener) context;
        } else {
            // Let's not force this for now
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listItemListener = null;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        int visibility = active ? View.VISIBLE : View.GONE;
        loadingIndicator.setVisibility(visibility);
    }

    @Override
    public void displayWeatherData(ArrayList<WeatherItem> dailyWeatherData) {
        adapter.replaceData(dailyWeatherData);

        recyclerView.setVisibility(View.VISIBLE);
        showNoDataLayout(false);
        setLoadingIndicator(false);
    }

    @Override
    public void hideWeatherData() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataLayout(boolean active) {
        int visibility = active ? View.VISIBLE : View.GONE;
        noDataAvailable.setVisibility(visibility);
        noDataAvailableRefresh.setVisibility(visibility);
    }

    @Override
    public void showErrorMessage(String message) {
        noDataAvailable.setText(message);
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(WeatherItem item);
    }

}
