import java.util.Scanner;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
         System.out.print("$ ");
         BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
         String str=bf.readLine();
         System.out.println(str+": command not found");
    }
}
