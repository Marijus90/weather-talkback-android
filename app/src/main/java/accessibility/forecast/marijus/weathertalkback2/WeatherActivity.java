package accessibility.forecast.marijus.weathertalkback2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import javax.inject.Inject;

import accessibility.forecast.marijus.weathertalkback2.helper.utils.ActivityUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.Lazy;
import dagger.android.support.DaggerAppCompatActivity;

public class WeatherActivity extends DaggerAppCompatActivity {

    @Inject
    WeatherPresenter presenter;

    @Inject
    Lazy<WeatherFragment> weatherFragmentProvider;

    @BindView(R.id.fab)
    FloatingActionButton refreshBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        WeatherFragment weatherFragment = (WeatherFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (weatherFragment == null) {
            weatherFragment = weatherFragmentProvider.get();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    weatherFragment, R.id.container);
        }

        refreshBtn.setRippleColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    @OnClick(R.id.fab)
    void fabClicked() {
        presenter.refreshData(true);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //TODO - Preserve data on device orientation change
        super.onSaveInstanceState(outState);
    }

}
