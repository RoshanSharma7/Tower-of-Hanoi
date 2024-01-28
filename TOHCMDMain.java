import java.util.*;
import java.util.Scanner;

class TowerOfHanoi{
    private int numDisks;
    private Stack <Integer> towerA;
    private Stack <Integer> towerB;
    private Stack <Integer> towerC;

    public TowerOfHanoi(int numDisks){
        this.numDisks = numDisks;
        this.towerA = new Stack<>();
        this.towerB = new Stack<>();
        this.towerC = new Stack<>();
        initializeTower();
    }

    private void initializeTower(){
        for(int i = numDisks; i>0; i--){
            towerA.push(i);
        }
    }

    public void solve(){
        moveDisks(numDisks, towerA, towerC, towerB);
    }

    private void moveDisks(int disks, Stack<Integer> source, Stack<Integer> target, Stack<Integer>auxiliary){
        if(disks>0){
            moveDisks(disks - 1, source, auxiliary, target);    // Move disk 1 form source to auxitiary
            int disk = source.pop();
            target.push(disk);
            System.out.println("Move Disk " + disk + " Form " + sourceName(source) + " To " + sourceName(target));
            moveDisks(disks - 1, auxiliary, target, source);    // Move disks 1 form auxiliary to target
        }
    }

    private String sourceName(Stack<Integer>tower){
        if(tower == towerA){
            return "Tower A";
        }
        else if(tower == towerB){
            return "Tower B";
        }
        else if(tower == towerC){
            return "Tower C";
        }
        return "";
    }
}

public  class TOHCMDMain{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int numDisks; 

        System.out.println("");
        System.out.println("--------------------------------");
        System.out.println("|        Tower Of Hanoi        |");
        System.out.println("--------------------------------");
        System.out.println("");

        System.out.print("Enter the Disk number : ");
        numDisks = sc.nextInt();
        System.out.println("");
        TowerOfHanoi tower = new TowerOfHanoi(numDisks);
        tower.solve();
        System.out.println("");

        sc.close();
    }
}