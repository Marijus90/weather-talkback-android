package accessibility.forecast.marijus.weathertalkback2;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import accessibility.forecast.marijus.weathertalkback2.data.WeatherItem;
import accessibility.forecast.marijus.weathertalkback2.helper.di.ActivityScoped;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerFragment;

/**
 * A fragment representing a list of weather items.
 */
@ActivityScoped
public class WeatherFragment extends DaggerFragment implements WeatherContract.View {

    @Inject
    WeatherContract.Presenter presenter;

    @BindView(R.id.list)
    RecyclerView recyclerView;

    @BindView(R.id.progress_indicator)
    ProgressBar loadingIndicator;

    @BindView(R.id.tv_no_data_available)
    TextView noDataAvailable;

    @BindView(R.id.tv_please_refresh)
    TextView noDataAvailableRefresh;

    private WeatherItemAdapter adapter;
    private OnListFragmentInteractionListener listItemListener;
    private Unbinder unbinder;

    @Inject
    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        unbinder = ButterKnife.bind(this, view);

        Context context = view.getContext();

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new WeatherItemAdapter(new ArrayList<WeatherItem>(0), context, listItemListener);
        recyclerView.setAdapter(adapter);

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
        presenter.takeView(this);
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

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(WeatherItem item);
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        presenter.dropView();
        super.onDestroy();
    }

}
