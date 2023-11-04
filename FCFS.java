
// Java program for implementation of FCFS
// scheduling

import java.text.ParseException;

class GFG3 {

    // Function to find the waiting time for all
    // processes
    static void findWaitingTime(int processes[], int n, int bt[], int wt[]) {
        // waiting time for first process is 0
        wt[0] = 0;

        // calculating waiting time
        for (int i = 1; i < n; i++) {
            wt[i] = bt[i - 1] + wt[i - 1];
        }
    }

    // Function to calculate turn around time
    static void findTurnAroundTime(int processes[], int n,
                                   int bt[], int wt[], int tat[]) {
        // calculating turnaround time by adding
        // bt[i] + wt[i]
        for (int i = 0; i < n; i++) {
            tat[i] = bt[i] + wt[i];
        }
    }

    //Function to calculate average time
    static void findavgTime(int processes[], int n, int bt[]) {
        int wt[] = new int[n], tat[] = new int[n];
        int total_wt = 0, total_tat = 0;

        //Function to find waiting time of all processes
        findWaitingTime(processes, n, bt, wt);

        //Function to find turn around time for all processes
        findTurnAroundTime(processes, n, bt, wt, tat);

        //Display processes along with all details
        System.out.printf("Processes Burst time Waiting" +" time Turn around time\n");

        // Calculate total waiting time and total turn
        // around time
        for (int i = 0; i < n; i++) {
            total_wt = total_wt + wt[i];
            total_tat = total_tat + tat[i];
            System.out.printf("  %d ", (i + 1));
            System.out.printf("	 %d ", bt[i]);
            System.out.printf("	 %d ", wt[i]);
            System.out.printf("	 %d\n", tat[i]);
        }
        float s = (float)total_wt /(float) n;
        int t = total_tat / n;
        System.out.printf("Average waiting time = %f", s);
        System.out.printf("\n");
        System.out.printf("Average turn around time = %d ", t);
    }

    // Driver code
    public static void main(String[] args) throws ParseException {
        //process id's
        int processes[] = {1, 2, 3};
        int n = processes.length;

        //Burst time of all processes
        int burst_time[] = {10, 5, 8};

        findavgTime(processes, n, burst_time);

    }
}

//practice 1

//import java.text.ParseException;
//
//class FCFS{
//    static void findWaitingTime(int process[],int n,int bt[],int wt[]){
//
//        wt[0]=0;
//        for(int i=1;i<n;i++){
//           wt[i]=bt[i-1]+wt[i-1];
//        }
//    }
//
//    static void findTurnAroundTime(int process[],int n, int bt[],int wt[],int tat[]){
//
//        for (int i=0;i<n;i++){
//            tat[i]=wt[i]+bt[i];
//        }
//    }
//
//    static void findavgTime(int process[],int n,int bt[]){
//        int wt[]=new int[n];
//        int tat[]=new int[n];
//        int total_wt=0;
//        int total_tat=0;
//
//        findWaitingTime(process,n,bt,wt);
//        findTurnAroundTime(process,n,bt,wt,tat);
//
//        System.out.println("Process Burst_time Waiting_Time TurnAround_Time");
//
//        for (int i=0;i<n;i++){
//            total_wt=total_wt=wt[i];
//            total_tat=total_tat+tat[i];
//
//            System.out.printf(" %d ",(i+1));
//            System.out.printf(" %d ",bt[i]);
//            System.out.printf(" %d ",wt[i]);
//            System.out.printf(" %d\n ",tat[i]);
//        }
//
//        int s = total_wt/n;
//        int j = total_tat/n;
//        System.out.println("\n");
//        System.out.println("Avrage_wt_time :"+s);
//        System.out.println("Average_tat_time :"+j);
//    }
//
//    public static void main(String args[]) throws ParseException{
//
//        int process[]={1,2,3};
//        int n = process.length;
//
//        int Burst_time[]={10,5,8};
//
//        findavgTime(process,n,Burst_time);
//    }
//}
//






























