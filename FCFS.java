import java.util.*;
public class os {
   public static void main(String args[]){
       Scanner sc=new Scanner(System.in);
       System.out.println("enter number of input");
       int n= sc.nextInt();
       int ar[]=new int [n];
       System.out.println("enter arrival time");
       for(int i=0;i<n;i++)
           ar[i]=sc.nextInt();
       System.out.println("enter burst time");
       int bu[]=new int[n];
       for(int i=0;i<n;i++)
           bu[i]=sc.nextInt();
       //calculating completion time(arrival time+burst time)
       int ct[]=new int[n];
       ct[0]=ar[0]+bu[0];
       for(int i=1;i<n;i++)
           ct[i]=ct[i-1]+bu[i];
       //calculating turnaround time(completion time-arrival time)
       int tt[]=new int[n];
       for(int i=0;i<n;i++)
           tt[i]=ct[i]-ar[i];
       //calculation waiting time(turnaround time-burst time)
       int wt[]=new int[n];
       for(int i=0;i<n;i++)
           wt[i]=tt[i]-bu[i];
       //print turnaround time and waiting time
       double avturn=0,avwait=0;
       System.out.println("Turnaround time  :  Waiting time");
       for(int i=0;i<n;i++) {
           System.out.println(tt[i] + "  :  " + wt[i]);
           avturn += tt[i];
           avwait += wt[i];
       }
       //calculating average
       avturn=avturn/n;
       avwait=avwait/n;
       //printing average
       System.out.println("\naverage turn around time:"+avturn);
       System.out.println("average waiting time:"+avwait);
   }
}
