import java.util.Scanner;
import java.util.Random;

/**
 * Driver class for exhibition of MaxHeap
 * @author Daniel Esparza
 */
public class Project2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner kb = new Scanner(System.in);
        
        System.out.println("==========================================");
        System.out.println("Please select how to test the program:");
        System.out.println("(1) 20 sets of 100 randomly generated integers");
        System.out.println("(2) Fixed integer values 1-100");
        System.out.print("Enter choice:");
        int choice = kb.nextInt();
        if (choice == 1){
            System.out.println();
            int num = 0;
            int avgA = 0;
            int avgB = 0;
            while(num < 20){
                Random rand = new Random();
                int n = 0;
                MaxHeap<Integer> heapOne = new MaxHeap<>(100);
                MaxHeap<Integer> heapOneT = new MaxHeap<>(100);
                while(n < 100){
                    int r = rand.nextInt(1000)+1;
                    if(n == 0){
                        heapOne.add(r);
                        heapOneT.addS(r);
                        n++;
                    }
                    else{
                        boolean run = false;
                        for(int i = 1; i < n+1; i++){
                            if(r == heapOneT.getVal(i)){
                                i = n+1;
                                run = true;
                            }
                        }
                        if(!run){
                            heapOne.add(r);
                            heapOneT.addS(r);
                            n++;
                        }
                    }
                }
                avgA = avgA + heapOne.getSwapOne();
                avgB = avgB + heapOneT.getSwapTwo();
                num++;
            }
            avgA = avgA/20;
            avgB = avgB/20;
            
            System.out.println("Average swaps for series of insertions: "+ avgA);
            System.out.println("Average swaps for optimal method: "+ avgB);
        }
        else if(choice == 2){
            System.out.println();
            MaxHeap<Integer> heapTwo = new MaxHeap<>(100);
            MaxHeap<Integer> heapTwoT = new MaxHeap<>(100);
            for(int i = 1; i <= 100; i++){
                heapTwo.add(i);
                heapTwoT.addS(i);
            }
            System.out.print("Heap built using series of insertions: ");
            for(int i = 1; i <= 10; i++){
                System.out.print(heapTwo.getVal(i)+",");
            }
            System.out.println("...");
            System.out.println("Number of swaps: " + heapTwo.getSwapOne());
            for(int i = 1; i <= 10; i++){
                heapTwo.removeMax();
            }
            System.out.print("Heap after 10 removals: ");
            for(int i = 1; i <= 10; i++){
                System.out.print(heapTwo.getVal(i)+",");
            }
            System.out.println("...\n");
            
            System.out.print("Heap built using optimal method: ");
            for(int i = 1; i <= 10; i++){
                System.out.print(heapTwoT.getVal(i)+",");
            }
            System.out.println("...");            
            System.out.println("Number of swaps: " + heapTwoT.getSwapTwo());
            for(int i = 1; i <= 10; i++){
                heapTwoT.removeMax();
            }
                        System.out.print("Heap after 10 removals: ");
            for(int i = 1; i <= 10; i++){
                System.out.print(heapTwoT.getVal(i)+",");
            }
            System.out.println("...\n");
        }
    }
    
}
