package com.weatherapp.myweatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class CityInfo {

  @JsonProperty("address")
  private String address;

  @JsonProperty("description")
  private String description;

  @JsonProperty("currentConditions")
  private CurrentConditions currentConditions;

  @JsonProperty("days")
  private List<Days> days;

  // Getters
  public String getAddress() {
    return address;
  }

  public String getDescription() {
    return description;
  }

  public CurrentConditions getCurrentConditions() {
    return currentConditions;
  }

  public List<Days> getDays() {
    return days;
  }

  // Nested class for current conditions
  public static class CurrentConditions {

    @JsonProperty("temp")
    private String currentTemperature;

    @JsonProperty("sunrise")
    private String sunrise;

    @JsonProperty("sunset")
    private String sunset;

    @JsonProperty("feelslike")
    private String feelslike;

    @JsonProperty("humidity")
    private String humidity;

    @JsonProperty("conditions")
    private String conditions;

    public CurrentConditions(String sunrise, String sunset) {
      this.sunrise = sunrise;
      this.sunset = sunset;
    }

    // Getters
    public String getCurrentTemperature() {
      return currentTemperature;
    }

    public String getSunrise() {
      return sunrise;
    }

    public String getSunset() {
      return sunset;
    }

    public String getFeelslike() {
      return feelslike;
    }

    public String getHumidity() {
      return humidity;
    }

    public String getConditions() {
      return conditions;
    }
    
    // Setters
    public void setSunrise(String sunrise) {
      this.sunrise = sunrise;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    
  }

  // Nested class for daily weather data
  public static class Days {
    @JsonProperty("datetime")
    private String date;

    @JsonProperty("temp")
    private String currentTemperature;

    @JsonProperty("tempmax")
    private String maxTemperature;

    @JsonProperty("tempmin")
    private String minTemperature;

    @JsonProperty("conditions")
    private String conditions;

    @JsonProperty("description")
    private String description;

    // Getters
    public String getDate() {
      return date;
    }

    public String getCurrentTemperature() {
      return currentTemperature;
    }

    public String getMaxTemperature() {
      return maxTemperature;
    }

    public String getMinTemperature() {
      return minTemperature;
    }

    public String getConditions() {
      return conditions;
    }

    public String getDescription() {
      return description;
    }
  }

  // Set current condition
  public void setCurrentConditions(CurrentConditions currentConditions) {
    this.currentConditions = currentConditions;
  }
}