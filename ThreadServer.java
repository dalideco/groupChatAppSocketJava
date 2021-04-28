import java.net.*;
import java.io.*;
public class ThreadServer extends Thread{

    Socket s=null;
    public ThreadServer(Socket s){
        this.s=s;
    }
    public void run(){
        try{
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            //getting username
            String username = (String)br.readLine(); 
            System.out.printf("--%s connected\n",username);
            
            // sending welcome
            pw.println("bienvenue dans le serveur");
            pw.flush();
            
            //getting messages from client and sending them to others
            while(true){
                String message = (String) br.readLine();

                //replace this by a function that sends mails to
                //  all cllients from all sockets
                pw.println(message);
                pw.flush();
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
