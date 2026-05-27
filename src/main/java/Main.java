import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    static final String concatpaths = System.getenv("PATH");
    static final Set<String> commands=new HashSet<>();
    static{
        commands.add("exit");
        commands.add("echo");
        commands.add("type");
    }
    public static void main(String[] args) throws Exception {
        Scanner sc=new Scanner(System.in);
        while(true){
         System.out.print("$ "); //starting of our code
         //BufferedReader bf=new BufferedReader(new InputStreamReader(System.in));
         //String str=bf.readLine();
         String str=sc.nextLine();
         String lower=str.toLowerCase();
         if(lower.equals("exit")) break; //exit from terminal

         if(lower.startsWith("echo")){
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
         }else if(lower.startsWith("type")){
            if(commands.contains(str.substring(5).toLowerCase())){
                System.out.println(str.substring(5)+" is a shell builtin");
                continue;
            }else{
                boolean typeFound=checkExternalPrograms(str);
                if(typeFound) continue;
                //Negative case:
                System.out.println(str.substring(5)+": not found");
                continue;
            }
         }else{
            String arr[]=str.split(" ");
            boolean externalProgramFound=checkExternalPrograms(arr[0]);
            if(externalProgramFound){
                ProcessBuilder pb=new ProcessBuilder(arr);
                Process process=pb.start();
                BufferedReader reader=new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while((line=reader.readLine())!=null){
                    System.out.println(line);
                }
                continue;
            }
         }

         System.out.println(str+": command not found"); // if command not found
        }
        sc.close();
    }
     public static boolean checkExternalPrograms(String str){
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
                         found=true; //we found that particular command/executable so now mark found and break so we can show option to enter new command
                         break;
                    }
                }
                if(found) return true;
                return false;
    }
}
