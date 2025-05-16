package Service.Implementation;

import Models.Worker;
import Service.Interface.IWorkerService;

public class WorkerService implements IWorkerService {
    @Override
    public Worker createWorker(String workerName, int workerId, double monthlySalary) {
        Worker worker = new Worker(workerName, workerId, monthlySalary);
        return worker;
    }
}
