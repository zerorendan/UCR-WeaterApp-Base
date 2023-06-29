package cr.ac.ucr.weather.presentation.home

import ForecastTitle
import android.annotation.SuppressLint
import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cr.ac.ucr.weather.R
import cr.ac.ucr.weather.core.utils.EpochConverter
import cr.ac.ucr.weather.core.utils.SetError
import cr.ac.ucr.weather.core.utils.constant.ErrorMessage
import cr.ac.ucr.weather.core.utils.constant.GraphicalUserInterface
import cr.ac.ucr.weather.domain.model.ForecastModel
import cr.ac.ucr.weather.presentation.component.CircularProgressBar
import cr.ac.ucr.weather.presentation.component.CurrentWeatherDetailRow
import cr.ac.ucr.weather.presentation.component.ErrorCard
import cr.ac.ucr.weather.presentation.component.ForecastLazyRow


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel, onNavigateToSearchCityScreen: () -> Unit) {
    val homeCurrentWeatherState by viewModel.homeForecastState.collectAsState()
    val activity = (LocalContext.current as? Activity)

    Scaffold(modifier = Modifier.fillMaxSize()) {
        BackgroundImage()
        MenuIcon { onNavigateToSearchCityScreen() }
        WeatherSection(homeCurrentWeatherState) { activity?.finish() }
    }
}

@Composable
private fun BackgroundImage() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.background),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun MenuIcon(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 24.dp, end = 24.dp),
        contentAlignment = Alignment.TopEnd
    ) {
        IconButton(
            modifier = Modifier.size(24.dp),
            onClick = onClick
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_menu),
                contentDescription = null,
                tint = Color.White
            )
        }
    }
}

@Composable
private fun WeatherSection(currentWeatherState: HomeForecastState, errorCardOnClick: () -> Unit) {
    when (currentWeatherState) {
        is HomeForecastState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressBar(modifier = Modifier.size(LocalConfiguration.current.screenWidthDp.dp / 3))
            }
        }

        is HomeForecastState.Success -> {
            if (currentWeatherState.forecast != null) {
                CurrentWeatherSection(currentWeatherState.forecast)
                DetailsSection(currentWeatherState.forecast)
            }
        }

        is HomeForecastState.Error -> {
            ErrorCard(
                modifier = Modifier.fillMaxSize(),
                errorTitle = currentWeatherState.errorMessage ?: ErrorMessage.UNKNOWN_ERROR,
                errorDescription = SetError.errorCard(
                    currentWeatherState.errorMessage ?: ErrorMessage.UNKNOWN_ERROR
                ),
                errorButtonText = GraphicalUserInterface.OK_BUTTON,
                errorCardOnClick,
                cardModifier = Modifier
                    .fillMaxWidth()
                    .height(LocalConfiguration.current.screenHeightDp.dp / 4 + 48.dp)
                    .padding(horizontal = 64.dp)
            )
        }
    }
}

@Composable
private fun CurrentWeatherSection(todayForecast: ForecastModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = todayForecast.cityDTO.cityName,
            style = MaterialTheme.typography.h2
        )
        Text(
            text = "${todayForecast.weatherList[0].weatherData.temp.toInt()}${GraphicalUserInterface.DEGREE_ICON}",
            style = MaterialTheme.typography.h1
        )
        Text(
            text = todayForecast.weatherList[0].weatherStatus[0].description,
            style = MaterialTheme.typography.h3,
            color = Color.Gray
        )
        Text(
            text = "H:${todayForecast.cityDTO.coordinate.longitude}°  L:${todayForecast.cityDTO.coordinate.latitude}°",
            style = MaterialTheme.typography.h3
        )
    }
}

@Composable
private fun DetailsSection(forecast: ForecastModel) {
    Box(
        modifier = Modifier.fillMaxSize(),
        Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 2),
            backgroundColor = MaterialTheme.colors.onSurface,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                ForecastSection(forecast)
                WeatherDetailSection(forecast)
            }
        }
    }
}

@Composable
private fun ForecastSection(forecastData: ForecastModel) {
    ForecastTitle(text = R.string.hourly_forecast.toString())
    ForecastLazyRow(forecasts = forecastData.weatherList.take(8))
    ForecastTitle(text = R.string.daily_forecast.toString())
    ForecastLazyRow(forecasts = forecastData.weatherList.takeLast(32))
}

@Composable
private fun WeatherDetailSection(currentWeather: ForecastModel) {
    CurrentWeatherDetailRow(
        title = R.string.temp.toString(),
        text = "${currentWeather.weatherList[0].weatherData.temp}${GraphicalUserInterface.DEGREE_ICON}",
        subTitle = R.string.feels_like.toString(),
        subText = "${currentWeather.weatherList[0].weatherData.feelsLike}${GraphicalUserInterface.DEGREE_ICON}"
    )
    CurrentWeatherDetailRow(
        title = R.string.cloudiness.toString(),
        text = "${currentWeather.weatherList[0].cloudiness.cloudiness}%",
        subTitle = R.string.humidity.toString(),
        subText = "${currentWeather.weatherList[0].weatherData.humidity}%"
    )
    CurrentWeatherDetailRow(
        title = R.string.sunrise.toString(),
        text = "${EpochConverter.readTimestamp(currentWeather.cityDTO.sunrise)}AM",
        subTitle = R.string.sunset.toString(),
        subText = "${EpochConverter.readTimestamp(currentWeather.cityDTO.sunset)}PM"
    )
    CurrentWeatherDetailRow(
        title = R.string.wind.toString(),
        text = "${currentWeather.weatherList[0].wind.speed}${R.string.metric}",
        subTitle = R.string.pressure.toString(),
        subText = "${currentWeather.weatherList[0].weatherData.pressure}"
    )
}