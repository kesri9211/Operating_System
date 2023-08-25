package OS;

import java.util.Scanner;

public class fcfs {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter number of input");
        int n = sc.nextInt();
        int ar[] = new int[n];
        System.out.println("enter arrival time");
        for (int i = 0; i < n; i++)
            ar[i] = sc.nextInt();
        System.out.println("enter burst time");
        int bu[] = new int[n];
        for (int i = 0; i < n; i++)
            bu[i] = sc.nextInt();
        //arrange the process according to FCFS
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i-1;j++){
                if(ar[j]>ar[j+1]){
                    int temp=ar[j];
                    ar[j]=ar[j+1];
                    ar[j+1]=temp;
                    temp=bu[j];
                    bu[j]=bu[j+1];
                    bu[j+1]=temp;
                }
            }
        }
        //calculating completion time(arrival time+burst time)
        int ct[] = new int[n];
        ct[0] = ar[0] + bu[0];
        for (int i = 1; i < n; i++)
            ct[i] = ct[i - 1] + bu[i];
        //calculating turn around time(completion time-arrival time)
        int tt[] = new int[n];
        for (int i = 0; i < n; i++)
            tt[i] = ct[i] - ar[i];
        //calculation waiting time(turn around time-burst time)
        int wt[] = new int[n];
        for (int i = 0; i < n; i++)
            wt[i] = tt[i] - bu[i];
        //print turnaround time and waiting time
        double avturn = 0, avwait = 0;
        System.out.println("Processes : Arrival time : Burst time : Waiting time : Turnaround time");
        for (int i = 0; i < n; i++) {
            System.out.println("P" + (i + 1) + "\t\t\t\t" + ar[i] + "\t\t\t\t" + bu[i] + "\t\t\t\t" + wt[i] + "\t\t\t\t" + tt[i]);
            avturn += tt[i];
            avwait += wt[i];
        }
        //calculating average
        avturn = avturn / n;
        avwait = avwait / n;
        //printing average
        System.out.println("\naverage turn around time:" + avturn);
        System.out.println("average waiting time:" + avwait);
    }
}
