import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        while(true){
         System.out.print("$ ");
         //BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
         //String str=bf.readLine();
         Scanner sc=new Scanner(System.in);
         String str=sc.nextLine();
         System.out.println(str+": command not found");
        }
    }
}
