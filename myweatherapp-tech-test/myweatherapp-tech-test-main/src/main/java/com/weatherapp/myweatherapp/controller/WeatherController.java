package com.weatherapp.myweatherapp.controller;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/weather")
public class WeatherController {

  @Autowired
  private WeatherService weatherService;

  // Fetch weather forecast for a city
  @GetMapping("/forecast/{city}")
  public ResponseEntity<CityInfo> forecastByCity(@PathVariable("city") String city) {
    CityInfo cityInfo = weatherService.forecastByCity(city);
    return ResponseEntity.ok(cityInfo);
  }

  // Compare daylight hours between two cities
  @GetMapping("/compare-daylight/{city1}/{city2}")
  public ResponseEntity<String> compareDaylightHours(
      @PathVariable("city1") String city1,
      @PathVariable("city2") String city2) {
    String result = weatherService.compareDaylightHours(city1, city2);
    return ResponseEntity.ok(result);
  }

  // Check which city is currently raining
  @GetMapping("/check-rain/{city1}/{city2}")
  public ResponseEntity<String> checkRain(
      @PathVariable("city1") String city1,
      @PathVariable("city2") String city2) {
    String result = weatherService.checkRain(city1, city2);
    return ResponseEntity.ok(result);
      }

  // Handle exceptions
  @ExceptionHandler(HttpClientErrorException.class)
  public ResponseEntity<String> handleCityNotFound(HttpClientErrorException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("City not found or invalid API request.");
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGenericException(Exception ex) {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + ex.getMessage());
  }
}