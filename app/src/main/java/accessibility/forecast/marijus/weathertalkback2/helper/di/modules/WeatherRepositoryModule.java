package accessibility.forecast.marijus.weathertalkback2.helper.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import java.util.concurrent.Executors;

import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.Local;
import accessibility.forecast.marijus.weathertalkback2.data.Remote;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;
import accessibility.forecast.marijus.weathertalkback2.data.api.DarkSkyWeatherAPISource;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherDAO;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherDatabase;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherItemLocalSource;
import accessibility.forecast.marijus.weathertalkback2.helper.AppExecutors;
import accessibility.forecast.marijus.weathertalkback2.helper.DiskIOThreadExecutor;
import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the {@link WeatherItemsRepository}.
 */
@Module
public class WeatherRepositoryModule {
    //TODO: Remove the executors
    private static final int THREAD_COUNT = 3;

    @Singleton
    @Provides
    @Local
    WeatherDataSource provideLocalDataSource(WeatherDAO dao, AppExecutors executors) {
        return new WeatherItemLocalSource(executors, dao);
    }

    @Singleton
    @Provides
    @Remote
    WeatherDataSource provideRemoteDataSource() {
        return new DarkSkyWeatherAPISource();
    }

    @Singleton
    @Provides
    WeatherDatabase provideDb(Application context) {
        return Room.databaseBuilder(context.getApplicationContext(), WeatherDatabase.class, "WeatherLocal.db")
                .allowMainThreadQueries().build();
        //TODO: Call DB on a background thread
        // https://stackoverflow.com/questions/44167111/android-room-simple-select-query-cannot-access-database-on-the-main-thread
    }

    @Singleton
    @Provides
    WeatherDAO provideWeatherDao(WeatherDatabase db) {
        return db.weatherDAO();
    }

    @Singleton
    @Provides
    AppExecutors provideAppExecutors() {
        return new AppExecutors(new DiskIOThreadExecutor(),
                Executors.newFixedThreadPool(THREAD_COUNT),
                new AppExecutors.MainThreadExecutor());
    }

}
