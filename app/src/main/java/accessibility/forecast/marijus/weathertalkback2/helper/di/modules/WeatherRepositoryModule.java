package accessibility.forecast.marijus.weathertalkback2.helper.di.modules;

import android.app.Application;
import android.arch.persistence.room.Room;

import javax.inject.Singleton;

import accessibility.forecast.marijus.weathertalkback2.data.Local;
import accessibility.forecast.marijus.weathertalkback2.data.Remote;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherDataSource;
import accessibility.forecast.marijus.weathertalkback2.data.WeatherItemsRepository;
import accessibility.forecast.marijus.weathertalkback2.data.api.DarkSkyWeatherAPISource;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherDAO;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherDatabase;
import accessibility.forecast.marijus.weathertalkback2.data.local.WeatherItemLocalSource;
import dagger.Module;
import dagger.Provides;

/**
 * This is used by Dagger to inject the required arguments into the {@link WeatherItemsRepository}.
 */
@Module
public class WeatherRepositoryModule {

    @Singleton
    @Provides
    @Local
    WeatherDataSource provideLocalDataSource(WeatherDAO dao) {
        return new WeatherItemLocalSource(dao);
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
        return Room.databaseBuilder(context.getApplicationContext(), WeatherDatabase.class, "WeatherLocal.db").build();
    }

    @Singleton
    @Provides
    WeatherDAO provideWeatherDao(WeatherDatabase db) {
        return db.weatherDAO();
    }

}
