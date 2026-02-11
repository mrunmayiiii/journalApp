package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.apiResponse.weatherResponse;

import net.engineeringdigest.journalApp.cache.appCache;
import net.engineeringdigest.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apikey;

    //private final static String API="http://api.weatherstack.com/current?access_key=accesskey&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private appCache appCache;

    @Autowired
    private RedisService redisService;

    /// /deserializing json to pojo object
    public weatherResponse getAPI(String city) {
        weatherResponse response = redisService.get("weather_of_" + city, weatherResponse.class);
        if(response !=null){
            return response;
        }
        else{

            String finalapi=appCache.APPCACHE.get("weather-api").replace(Placeholders.accesskey,apikey).replace(Placeholders.CITY,city);
            ResponseEntity<weatherResponse> responseEntity = restTemplate.exchange(finalapi, HttpMethod.GET,null, weatherResponse.class);
            weatherResponse body= responseEntity.getBody();
            if(body != null){
                redisService.set("weather_of_" + city, body, 300l);
            }
            return body;
        }


        //for post external api
//        String requestBody="{\n"+
//                "\"userName\":\"mrunmai2\",\n"+
//                "\"password\":\"mrunmai2\",\n"+
//         "}     ";
       // HttpEntity<String>httpEntity=new HttpEntity<>(requestBody);

    }
}
