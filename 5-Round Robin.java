package OS;
import java.util.*;
class Process4 {
   int pid;
   int arrivalTime;
   int burstTime;
   int startTime;
   int completionTime;
   int turnaroundTime;
   int waitingTime;
   int responseTime;
}
public class Roundrobin2 {
   public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
int n; // Number of processes
int tq; // Time quantum for Round Robin Process4[] p = new Process4[100]; // Array to store process
information
       float avgTurnaroundTime;
       float avgWaitingTime;
       float avgResponseTime;
       float cpuUtilization;
       int totalTurnaroundTime = 0;
       int totalWaitingTime = 0;
       int totalResponseTime = 0;
       int totalIdleTime = 0;
       float throughput;
       int[] burstRemaining = new int[100];
       int idx;
System.out.println("Enter the number of processes: "); n = scanner.nextInt();
 System.out.println("Enter time quantum: "); tq = scanner.nextInt();
       // Input process information
for (int i = 0; i < n; i++) {
p[i] = new Process4();
System.out.println("Enter arrival time of process " + (i
+ 1) + ": ");
           p[i].arrivalTime = scanner.nextInt();
System.out.println("Enter burst time of process " + (i + 1) + ": ");
           p[i].burstTime = scanner.nextInt();
           burstRemaining[i] = p[i].burstTime;
           p[i].pid = i + 1;
}
       Queue<Integer> q = new LinkedList<>();
       int currentTime = 0;
       q.add(0);
       int completed = 0;
int[] mark = new int[100]; Arrays.fill(mark, 0); mark[0] = 1;
       while (completed != n) {
           idx = q.poll();
if (burstRemaining[idx] == p[idx].burstTime) { p[idx].startTime = Math.max(currentTime,
p[idx].arrivalTime);
               totalIdleTime += p[idx].startTime - currentTime;
               currentTime = p[idx].startTime;
           }
           if (burstRemaining[idx] - tq > 0) {
               burstRemaining[idx] -= tq;
               currentTime += tq;
           } else {
               currentTime += burstRemaining[idx];
               burstRemaining[idx] = 0;
               completed++;
               // Calculate process times

                p[idx].completionTime = currentTime;
               p[idx].turnaroundTime = p[idx].completionTime -
p[idx].arrivalTime;
               p[idx].waitingTime = p[idx].turnaroundTime -
p[idx].burstTime;
               p[idx].responseTime = p[idx].startTime -
p[idx].arrivalTime;
               // Update total times
               totalTurnaroundTime += p[idx].turnaroundTime;
               totalWaitingTime += p[idx].waitingTime;
               totalResponseTime += p[idx].responseTime;
}
           for (int i = 1; i < n; i++) {
               if (burstRemaining[i] > 0 && p[i].arrivalTime <=
currentTime && mark[i] == 0) {
                   q.add(i);
mark[i] = 1; }
           }
           if (burstRemaining[idx] > 0) {
               q.add(idx);
           }
           if (q.isEmpty()) {
               for (int i = 1; i < n; i++) {
                   if (burstRemaining[i] > 0) {
                       q.add(i);
mark[i] = 1;
break; }
} }
}
       // Calculate average times, CPU utilization, and throughput
       avgTurnaroundTime = (float) totalTurnaroundTime / n;
       avgWaitingTime = (float) totalWaitingTime / n;
       avgResponseTime = (float) totalResponseTime / n;
       cpuUtilization = ((p[n - 1].completionTime - totalIdleTime) /
(float) p[n - 1].completionTime) * 100;

throughput = (float) n / (p[n - 1].completionTime -
p[0].arrivalTime);
       // Display process details and results
System.out.println("\n#P\tAT\tBT\tST\tCT\tTAT\tWT\tRT\n");
for (int i = 0; i < n; i++) {
System.out.println(p[i].pid + "\t" + p[i].arrivalTime +
"\t" + p[i].burstTime + "\t" + p[i].startTime +
                   "\t" + p[i].completionTime + "\t" +
p[i].turnaroundTime + "\t" + p[i].waitingTime + "\t" +
                   p[i].responseTime);
}
System.out.println("Average Turnaround Time = " + avgTurnaroundTime);
System.out.println("Average Waiting Time = " + avgWaitingTime);
      // System.out.println("Average Response Time = " +
avgResponseTime);
      // System.out.println("CPU Utilization = " + cpuUtilization +
"%");
       //System.out.println("Throughput = " + throughput + "
process/unit time");
} }
