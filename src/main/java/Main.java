import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        while(true){
         Set<String> commands=new HashSet<>();
         commands.add("exit");
         commands.add("echo");
         commands.add("type");
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
         }else if(substr.equals("type")){
            if(commands.contain(str.substring(5).toLowerCase())){
                System.out.println(str.substring(5)+" is a shell builtin");
                continue;
            }else{
                System.out.println(str.substring(5)+": not found");
                continue;
            }
         }

         }
         System.out.println(str+": command not found"); // if command not found
        }
    }
}
