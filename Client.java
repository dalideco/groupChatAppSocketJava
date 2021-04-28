import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    private static synchronized void leave(){
        System.out.println("leaving");
        System.exit(0);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("username: ");;
        String username = sc.nextLine();
        try{
            Socket s= new Socket("localhost", 3000);
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // sending username 
            pw.println(username);
            pw.flush();

            //getting message from server
            String welcome = (String) br.readLine();
            System.out.println(welcome);

            //using thread to get messages
            ThreadReading thread = new ThreadReading(br);
            thread.start();

            //sending  messages 
            while(true){
                
                String message = sc.nextLine();
                pw.println(message);

                pw.flush();

                if(message.equals("exit()")){
                    leave();
                    break;
                }
                
            }
            


        }catch(Exception e ){
            System.out.println("Socket not found");
        }

        sc.close();
    }
}
