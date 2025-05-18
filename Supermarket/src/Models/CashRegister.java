package Models;

public class CashRegister {
    int workerId;
    int number;

    public CashRegister(int number){
        this.number = number;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
    public int getWorkerId() {
        return workerId;
    }

    public void setNumber(int number) {
        this.number = number;
    }
    public int getNumber() {
        return number;
    }
}
