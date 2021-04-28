import java.io.*;
import java.net.*;


public class Server {
    public static void main(String[] args) {
        ServerSocket ss = null;

        try {
            ss= new ServerSocket(3000);
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }

        while(true){
            try{
                Socket s = ss.accept(); 
                ThreadServer thread = new ThreadServer(s);
                thread.start();
            }catch(Exception e ){
                e.printStackTrace();
            }
        }

    }
}
