package edi.com.myideodigital2;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Edi on 7/11/2016.
 */
public class Data {



    private CoordEntity coord;


    private String base;


    private MainEntity main;


    private WindEntity wind;


    private RainEntity rain;


    private CloudsEntity clouds;
    private double dt;


    private SysEntity sys;
    private double id;
    private String name;
    private double cod;


    private List<WeatherEntity> weather;

    public CoordEntity getCoord() {
        return coord;
    }



    public String getBase() {
        return base;
    }



    public MainEntity getMain() {
        return main;
    }



    public WindEntity getWind() {
        return wind;
    }



    public RainEntity getRain() {
        return rain;
    }



    public CloudsEntity getClouds() {
        return clouds;
    }


    public SysEntity getSys() {
        return sys;
    }



    public double getId() {
        return id;
    }


    public String getName() {
        return name;
    }



    public double getCod() {
        return cod;
    }



    public List<WeatherEntity> getWeather() {
        return weather;
    }



    public static class CoordEntity {
        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }



        public double getLat() {
            return lat;
        }


    }

    public static class MainEntity {
        private double temp;
        private double pressure;
        private double humidity;
        private double temp_min;
        private double temp_max;

        public double getTemp() {
            return temp;
        }



        public double getPressure() {
            return pressure;
        }



        public double getHumidity() {
            return humidity;
        }



        public double getTemp_min() {
            return temp_min;
        }



        public double getTemp_max() {
            return temp_max;
        }


    }

    public static class WindEntity {
        private double speed;
        private double deg;

        public double getSpeed() {
            return speed;
        }



        public double getDeg() {
            return deg;
        }


    }

    public static class RainEntity {
        @SerializedName("1h")
        private double value1h;

        public double getValue1h() {
            return value1h;
        }


    }

    public static class CloudsEntity {
        private double all;

        public double getAll() {
            return all;
        }


    }

    public static class SysEntity {
        private double type;
        private double id;
        private double message;
        private String country;
        private double sunrise;
        private double sunset;

        public double getType() {
            return type;
        }

        public double getId() {
            return id;
        }

        public double getMessage() {
            return message;
        }

        public String getCountry() {
            return country;
        }


        public double getSunrise() {
            return sunrise;
        }


        public double getSunset() {
            return sunset;
        }

    }

    public static class WeatherEntity {
        private double id;
        private String main;
        private String description;
        private String icon;

        public double getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }

    }
}
