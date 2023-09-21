package com.yurov.weatherapp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text getCloudCover;

    @FXML
    private Button getData;

    @FXML
    private Text getFeelingTemperature;

    @FXML
    private Text getHumidity;

    @FXML
    private Text getSunriseTime;

    @FXML
    private Text getSunsetTime;

    @FXML
    private Text getTemperature;

    @FXML
    private TextField getTownField;

    @FXML
    private Text getVisibility;

    @FXML
    private Text getWindSpeed;

    @FXML
    private ImageView image;

    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String inputTown = getTownField.getText().trim();
            String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q=" + inputTown + "&appid=cd2d59b184eaf84ad97a27b69e1ea625&units=metric");
            if (!output.isEmpty()) {
                try {
                    JSONObject jsonObject = new JSONObject(output);
                    getTemperature.setText(jsonObject.getJSONObject("main").getInt("temp") + " \u2103");
                    getFeelingTemperature.setText("Feel like : " + jsonObject.getJSONObject("main").getInt("feels_like") + " \u2103");
                    getSunriseTime.setText("Sunrise : " + formatDateTime(jsonObject.getJSONObject("sys").getLong("sunrise")));
                    getSunsetTime.setText("Sunset : " + formatDateTime(jsonObject.getJSONObject("sys").getLong("sunset")));
                    getVisibility.setText("Visibility : " + jsonObject.getString("visibility") + " m");
                    getHumidity.setText("Humidity : " + jsonObject.getJSONObject("main").getInt("humidity") + " %");
                    getWindSpeed.setText("Wind speed : " + jsonObject.getJSONObject("wind").getDouble("speed") + " m/s");

                    JSONArray weatherArray = jsonObject.getJSONArray("weather");
                    JSONObject weatherObject = (JSONObject) weatherArray.get(0);
                    getCloudCover.setText(String.valueOf(weatherObject.get("description")));

                    ResponseCode responseCode = new ResponseCode();
                    JSONObject weatherCodeObject = (JSONObject) weatherArray.get(0);
                    System.out.println(Integer.parseInt(String.valueOf(weatherCodeObject.get("id"))));
                    image.setImage(new Image(responseCode.eventHandler(Integer.parseInt(String.valueOf(weatherCodeObject.get("id"))))));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public static String getUrlContent(String urlAddress) {
        StringBuilder weatherData = new StringBuilder();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                weatherData.append(line + "\n");
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Incorrect input... Town not found");
        }
        return weatherData.toString();
    }

    private static String formatDateTime(long inputDate) {
        String outputDate;
        Instant sunriseDate = Instant.ofEpochSecond(inputDate);
        outputDate = (sunriseDate.atZone(ZoneId.systemDefault()).getHour() + ":" + sunriseDate.atZone(ZoneId.systemDefault()).getMinute());
        return outputDate;
    }
}
























   /* @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String userCity = city.getText().trim();
            String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q="+userCity+"&appid=cd2d59b184eaf84ad97a27b69e1ea625&units=metric");
            if(!output.isEmpty()) {
                try {
                    JSONObject jsonObject = new JSONObject(output);
                    tempInfo.setText("Temperature : " + jsonObject.getJSONObject("main").getDouble("temp")+" C");
                    feelingUs.setText("Feel like : " + jsonObject.getJSONObject("main").getDouble("feels_like"));
                    maxValue.setText("Max. Temperature: " + jsonObject.getJSONObject("main").getDouble("temp_max"));
                    minValue.setText("Min. Temperature: " + jsonObject.getJSONObject("main").getDouble("temp_min"));
                    pressureData.setText("Pressure: " + jsonObject.getJSONObject("main").getDouble("pressure"));
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
    public static String getUrlContent(String urlAddress){
        StringBuffer weatherDataSource = new StringBuffer();
        try {
            URL url = new URL(urlAddress);
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            while ((line=bufferedReader.readLine())!=null){
                weatherDataSource.append(line+"\n");
            }
            bufferedReader.close();
        }catch (Exception e){
            System.out.println("town not found");
        }
        return weatherDataSource.toString();
    }*/


