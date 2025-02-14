package com.weatherapp.myweatherapp.service;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherService {

  @Autowired
  private VisualcrossingRepository weatherRepo;

  // Fetch weather data for a city
  public CityInfo forecastByCity(String city) {
    return weatherRepo.getByCity(city);
  }

  // Helper method to calculate daylight hours in minutes
  private long calculateDaylightMinutes(CityInfo cityInfo) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime sunrise = LocalTime.parse(cityInfo.getCurrentConditions().getSunrise(), formatter);
    LocalTime sunset = LocalTime.parse(cityInfo.getCurrentConditions().getSunset(), formatter);
    return java.time.Duration.between(sunrise, sunset).toMinutes();
  }

  // Compare daylight hours between two cities
  public String compareDaylightHours(String city1, String city2) {
    CityInfo city1Info = weatherRepo.getByCity(city1);
    CityInfo city2Info = weatherRepo.getByCity(city2);

    long city1Daylight = calculateDaylightMinutes(city1Info);
    long city2Daylight = calculateDaylightMinutes(city2Info);

    if (city1Daylight > city2Daylight) {
      return city1;
    } else if (city2Daylight > city1Daylight) {
      return city2;
    } else {
      return "Both cities have the same daylight hours.";
    }
  }

  // Check if it's raining in a city
  public boolean isRaining(String city) {
    CityInfo cityInfo = weatherRepo.getByCity(city);
    return cityInfo.getCurrentConditions().getConditions().toLowerCase().contains("rain");
  }

  // Compare rain between two cities
  public String checkRain(String city1, String city2) {
    boolean isCity1Raining = isRaining(city1);
    boolean isCity2Raining = isRaining(city2);

    if (isCity1Raining && isCity2Raining) {
      return "It's raining in both cities.";
    } else if (isCity1Raining) {
      return "It's raining in " + city1;
    } else if (isCity2Raining) {
      return "It's raining in " + city2;
    } else {
      return "It's not raining in either city.";
    }
  }
}