import java.io.*;

public class ThreadReading extends Thread{
    BufferedReader br ;
    public ThreadReading(BufferedReader br){
        this.br= br;
    }

    public void run(){
        try{
            while(true){
                String message = (String)br.readLine();
                System.out.println(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
