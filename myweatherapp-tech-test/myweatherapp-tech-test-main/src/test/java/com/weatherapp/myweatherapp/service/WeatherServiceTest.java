package com.weatherapp.myweatherapp.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class WeatherServiceTest {

  @Mock
  private VisualcrossingRepository weatherRepo;

  @InjectMocks
  private WeatherService weatherService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testCompareDaylightHours() {
    CityInfo city1Info = new CityInfo();
    city1Info.setCurrentConditions(new CityInfo.CurrentConditions("06:00:00", "18:00:00"));

    CityInfo city2Info = new CityInfo();
    city2Info.setCurrentConditions(new CityInfo.CurrentConditions("07:00:00", "20:00:00"));

    CityInfo city3Info = new CityInfo();
    city3Info.setCurrentConditions(new CityInfo.CurrentConditions("07:00:00", "19:00:00"));

    // Mock the repository calls
    when(weatherRepo.getByCity("City1")).thenReturn(city1Info);
    when(weatherRepo.getByCity("City2")).thenReturn(city2Info);
    when(weatherRepo.getByCity("City3")).thenReturn(city3Info);

    // Testing results
    String result1 = weatherService.compareDaylightHours("City1", "City2");
    assertEquals("City2", result1);

    String result2 = weatherService.compareDaylightHours("City1", "City3");
    assertEquals("Both cities have the same daylight hours.", result2);
}



@Test
void testCheckRain() {
    CityInfo city1Info = new CityInfo();
    city1Info.setCurrentConditions(new CityInfo.CurrentConditions("06:00:00", "18:00:00"));
    city1Info.getCurrentConditions().setConditions("Rain");

    CityInfo city2Info = new CityInfo();
    city2Info.setCurrentConditions(new CityInfo.CurrentConditions("07:00:00", "19:00:00"));
    city2Info.getCurrentConditions().setConditions("Clear");

    when(weatherRepo.getByCity("City1")).thenReturn(city1Info);
    when(weatherRepo.getByCity("City2")).thenReturn(city2Info);

    String result = weatherService.checkRain("City1", "City2");
    assertEquals("It's raining in City1", result);
}


}