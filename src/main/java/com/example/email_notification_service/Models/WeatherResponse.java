package com.example.email_notification_service.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    @JsonProperty("name")
    private String cityName;

    @JsonProperty("main")
    private MainWeather mainWeather;

    public String getCityName() {
        return cityName;
    }

    private List<MainWeather> weather;

    public List<MainWeather> getWeather() {
        return weather;
    }

    public void setWeather(List<MainWeather> weather) {
        this.weather = weather;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public MainWeather getMainWeather() {
        return mainWeather;
    }

    public void setMainWeather(MainWeather mainWeather) {
        this.mainWeather = mainWeather;
    }

    public class MainWeather {

        @JsonProperty("temp")
        private double temperature;

        @JsonProperty("humidity")
        private int humidity;

        public double getTemperature() {
            return temperature;
        }

        public void setTemperature(double temperature) {
            this.temperature = temperature;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }
    }
}