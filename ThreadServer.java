import java.net.*;
import java.util.ArrayList;
import java.io.*;
public class ThreadServer extends Thread{
    private static ArrayList<Socket> sockets ; 
    private static ArrayList<PrintWriter> printers;


    static{
        sockets = new ArrayList<Socket>();
        printers = new ArrayList<PrintWriter>();
    }

    private Socket s= null;
    public ThreadServer(Socket s){
        this.s=s;
        sockets.add(s);
    }

    public void SendToAll(String message){
        for(PrintWriter printer : printers){
            printer.println(message);
            printer.flush();
        }
    }

    public void run(){
        try{
            PrintWriter pw = new PrintWriter(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            //adding the pw to printers
            printers.add(pw);

            //getting username
            String username = (String)br.readLine(); 
            SendToAll("--"+username+" connected\n");
            
            // sending welcome
            pw.println("bienvenue dans le serveur");
            pw.flush();
            
            //getting messages from client and sending them to others
            while(true){
                String message = (String) br.readLine();

                //replace this by a function that sends mails to
                //  all cllients from all sockets         
            
                if(message.equals("exit()")){
                    SendToAll(username +" left");
                    s.close();
                    sockets.remove(s);
                    printers.remove(pw);
                    break; 
                }else SendToAll(message);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
