package OS;
import java.util.*;
class Process1 {
    int id;
    int burstTime;
    int arrivalTime;
    int pri;
    int waitingTime;
    int turnaroundTime;

    public Process1(int id, int burstTime, int arrivalTime, int pri ) {
        this.id = id;
        this.burstTime = burstTime;
        this.arrivalTime = arrivalTime;
        this.waitingTime = 0;
        this.turnaroundTime = 0;
        this.pri=pri;
    }
}

public class priority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input: Number of processes
        System.out.print("Enter the number of processes: ");
        int n = sc.nextInt();

        List<Process1> processes = new ArrayList<>();

        // Input: Burst time, arrival time and priority for each process
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter burst time for Process " + i + ": ");
            int burstTime = sc.nextInt();
            System.out.print("Enter arrival time for Process " + i + ": ");
            int arrivalTime = sc.nextInt();
            System.out.print("Enter priority of Process " + i + ": ");
            int pri = sc.nextInt();
            processes.add(new Process1(i, burstTime, arrivalTime, pri));
        }

        int currentTime = 0;
        int totalWaitingTime = 0;
        int totaltat=0;

        System.out.println("\nProcess\tBurst Time\tArrival Time\tpriority Time\tWaiting Time\tTurnaround Time");

        while (!processes.isEmpty()) {
            // Filter processes that have arrived
            List<Process1> arrivedProcesses = new ArrayList<>();
            for (Process1 p : processes) {
                if (p.arrivalTime <= currentTime) {
                    arrivedProcesses.add(p);
                }
            }

            // Sort arrived processes based Priority
            Collections.sort(arrivedProcesses, (p1, p2) -> p1.pri - p2.pri);

            if (arrivedProcesses.isEmpty()) {
                // No processes have arrived; increment time
                currentTime++;
            } else {
                Process1 shortestJob = arrivedProcesses.get(0);
                processes.remove(shortestJob);
                //calculate WT nad TAT
                int waitingTime = currentTime - shortestJob.arrivalTime;
                int turnaroundTime = waitingTime + shortestJob.burstTime;
                //store WT and TAT of each process
                shortestJob.waitingTime = waitingTime;
                shortestJob.turnaroundTime = turnaroundTime;

                totalWaitingTime += waitingTime;
                totaltat+=turnaroundTime;
                currentTime += shortestJob.burstTime;   //update current time

                // Output: Process details
                System.out.println(shortestJob.id + "\t\t\t" + shortestJob.burstTime + "\t\t\t" +
                        shortestJob.arrivalTime + "\t\t\t\t"+ shortestJob.pri+ "\t\t\t\t" + shortestJob.waitingTime + "\t\t\t\t" +
                        shortestJob.turnaroundTime);
            }
        }

        // Calculate and display average waiting time
        double averageWaitingTime = (double) totalWaitingTime / n;
        double averagetat=(double) totaltat/n;
        System.out.println("\nAverage Waiting Time: " + averageWaitingTime);
        System.out.println("\nAverage Turn around Time: " + averagetat);
        sc.close();
    }
}
