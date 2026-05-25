import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        while(true){
         System.out.print("$ "); //starting of our code
         //BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
         //String str=bf.readLine();
         Scanner sc=new Scanner(System.in);
         String str=sc.nextLine();
         if(str.equalsIgnoreCase("Exit")) break; //exit from terminal
         if(str.length()>=4){
         String substr=str.substring(0,4).toLowerCase();
         if(substr.equals("echo")){
            if(str.length()==4){
                System.out.println("ECHO is on.");
                continue;
            }else{
                String text=str.substring(5, str.length()).trim();
                if(str.length()==0){
                    System.out.println("ECHO is on.");
                    continue;
                }else{
                    System.out.println(text);
                    continue;
                }
            }
         }
         }
         System.out.println(str+": command not found"); // if command not found
        }
        sc.close();
    }
}
