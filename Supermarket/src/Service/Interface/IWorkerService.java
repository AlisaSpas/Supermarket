package Service.Interface;

import Models.Worker;

public interface IWorkerService {
    public Worker createWorker(String workerName, int workerId, double monthlySalary);

}
