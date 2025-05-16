package Models;

public class Worker {
    String workerName;
    int workerId;
    double monthlySalary;

    public Worker (String workerName, int workerId, double monthlySalary){
        this.workerName = workerName;
        this.workerId = workerId;
        this.monthlySalary = monthlySalary;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }
    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }
    public int getWorkerId() {
        return workerId;
    }

    public void setMonthlySalary(double monthlySalary) {
        this.monthlySalary = monthlySalary;
    }
    public double getMonthlySalary() {
        return monthlySalary;
    }
}
