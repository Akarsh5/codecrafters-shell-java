import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.io.*;
import java.nio.file.*;

public class Main {
    public static void main(String[] args) throws Exception {
        String concatpaths="";
        if(args.length>1){
            concatpaths=args[0].substring(6,args[0].length()-1);
        }
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
            if(commands.contains(str.substring(5).toLowerCase())){
                System.out.println(str.substring(5)+" is a shell builtin");
                continue;
            }else{
                String command=str.substring(5).toLowerCase();
                //type with PATH recursion? 
                //Positive case, let's say PATH is defined we need to check it for all the executables and find the one with xyz if command is "type xyz"
                String paths[]=concatpaths.split(File.pathSeparator);
                boolean found=false;
                for(String path:paths){
                    Path p=Paths.get(path+File.separator+command);
                    boolean res=Files.isExecutable(p);
                    if(res){
                         System.out.println(command+" is "+p.toString());
                         found=true;
                         break;
                    }
                }
                if(found) continue;
                //Negative case:
                System.out.println(str.substring(5)+": not found");
                continue;
            }
         }

         }
         System.out.println(str+": command not found"); // if command not found
        }
    }
}
