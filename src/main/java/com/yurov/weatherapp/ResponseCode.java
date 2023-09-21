package com.yurov.weatherapp;

/**
 * @Author Pavel Yurov
 * 21.09.2023
 */
public class ResponseCode {
    private String urlPicture;

    public String eventHandler(int code) {
        switch (code) {
            //Thunderstorm
            case 200, 201, 202, 210, 211, 212, 221, 230, 231, 232 ->
                    urlPicture = "https://openweathermap.org/img/wn/11d@2x.png";

            //Drizzle
            case 300, 301, 302, 310, 311, 312, 313, 314, 321 ->
                    urlPicture = "https://openweathermap.org/img/wn/09d@2x.png";


            //Rain
            case 500, 501, 502, 503, 504, 520, 521, 522, 531 ->
                    urlPicture = "https://openweathermap.org/img/wn/10d@2x.png";

            //Snow
            case 511, 600, 601, 602, 611, 612, 613, 615, 616, 620, 621, 622 ->
                    urlPicture = "https://openweathermap.org/img/wn/13d@2x.png";

            //Atmosphere
            case 701, 711, 721, 731, 741, 751, 761, 762, 771, 781 ->
                    urlPicture = "https://openweathermap.org/img/wn/50d@2x.png";

            //Clear
            case 800 -> urlPicture = "https://openweathermap.org/img/wn/01d@2x.png";


            //Clouds
            case 801 -> urlPicture = "https://openweathermap.org/img/wn/02d@2x.png";
            case 802 -> urlPicture = "https://openweathermap.org/img/wn/03d@2x.png";
            case 803, 804 -> urlPicture = "https://openweathermap.org/img/wn/04d@2x.png";
            default -> System.out.println("code not found");
        }
        return urlPicture;
    }
}
