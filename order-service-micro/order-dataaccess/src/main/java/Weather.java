public class Weather {
    Double temprature;

    public Weather(Double temprature) {
        this.temprature = temprature;
    }
    public Double getTemprature() {
        return this.temprature;
    }

    public String toString() {
        return temprature.toString();
    }
}
