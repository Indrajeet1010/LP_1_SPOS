import java.util.Scanner;
import java.io.*;

public class LP {
    public static void main(String[] args) {
        int i,no_p,burst_time[],TT[],WT[];
        float avg_wait=0,avg_TT=0;
        burst_time = new int[50];
        TT= new int[50];
        WT= new int[50];
        WT[0] = 0;

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of process");
        no_p = sc.nextInt();
        System.out.println("\n enter burst time for process ");
        for (i=0;i<no_p;i++){
            System.out.print("\t P"+(i+1)+":  ");
            burst_time[i]=sc.nextInt();
        }
        for (i=1;i<no_p;i++){
            WT[i]=WT[i-1]+burst_time[i-1];
            avg_wait+=WT[i];
        }
        avg_wait/=no_p;

        for (i=0;i<no_p;i++){
            TT[i]=WT[i]+burst_time[i];
            avg_TT+=TT[i];
        }
        avg_TT/=no_p;
        System.out.println("\t Processes:");
        System.out.println("   Proess\tBrust Time\tWaiting time\tTurn around time");
        for (i=0;i<no_p;i++){
            System.out.println("\tP"+(i+1)+"\t  "+     burst_time[i]+"\t\t  "+WT[i]+"\t\t "+TT);
        }
        System.out.println("\n Avg waiting time : "+avg_wait);
        System.out.println("\n average turn around time : "+avg_TT);
    }
}
