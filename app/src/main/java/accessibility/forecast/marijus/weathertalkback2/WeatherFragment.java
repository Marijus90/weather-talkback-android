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
import android.zetterstrom.com.forecast.models.Forecast;

import accessibility.forecast.marijus.weathertalkback2.dummy.DummyContent;
import accessibility.forecast.marijus.weathertalkback2.dummy.DummyContent.DummyItem;

/**
 * A fragment representing a list of weather items.
 */
public class WeatherFragment extends Fragment implements WeatherContract.View {

    WeatherContract.Presenter presenter;

    private WeatherItemAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton refreshBtn;

    private OnListFragmentInteractionListener mListener;

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

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            adapter = new WeatherItemAdapter(DummyContent.ITEMS, mListener);
            recyclerView.setAdapter(adapter);
        }

        // Set up refreshBtn
        // Init refresh button
        refreshBtn = getActivity().findViewById(R.id.fab);
        //TODO - Set ripple and onClick listener on the fab
        if (refreshBtn != null) {
            refreshBtn.setRippleColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        // Set up loading indicator

        // TODO: Swipe to refresh?

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
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
        mListener = null;
    }

    @Override
    public void setLoadingIndicator(boolean active) {
        //TODO: Implement this
    }

    @Override
    public void displayWeatherData(Forecast forecast) {
//        adapter.replaceData(tasks);

        recyclerView.setVisibility(View.VISIBLE);
//        noDataView.setVisibility(View.GONE);
    }

    @Override
    public void hideWeatherData() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showNoDataError(String description) {
//        noDataView.setVisibility(View.VISIBLE);
//        noDataView.setText(description);
    }

    @Override
    public void showErrorMessage(String description) {
        //TODO: Show server error toast
    }

    @Override
    public void showTimeSinceLastDataRefresh() {
        //TODO: Implement this
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(DummyItem item);
    }
}
