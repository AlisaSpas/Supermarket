import java.util.ArrayList;

public class Store {
    ArrayList<Worker> workers;
    double overpriceFood;
    double overpriceNonfood;
    int daysTillExpiration;
    double discount;

    public void setWorkers(ArrayList<Worker> workers) {
        this.workers = workers;
    }
    public ArrayList<Worker> getWorkers() {
        return workers;
    }

    public void setOverpriceFood(double overpriceFood) {
        this.overpriceFood = overpriceFood;
    }
    public double getOverpriceFood() {
        return overpriceFood;
    }

    public void setOverpriceNonfood(double overpriceNonfood) {
        this.overpriceNonfood = overpriceNonfood;
    }
    public double getOverpriceNonfood() {
        return overpriceNonfood;
    }

    public void setDaysTillExpiration(int daysTillExpiration) {
        this.daysTillExpiration = daysTillExpiration;
    }
    public int getDaysTillExpiration() {
        return daysTillExpiration;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
    public double getDiscount() {
        return discount;
    }
}
