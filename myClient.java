import java.io.*;
import java.net.*;
import java.util.*;

public class myClient{
   
    public String ipAddress = "";
    public String port = "";
    

    public myClient(String ipAddress, String port){
    	this.ipAddress = ipAddress;
	this.port = port;
    }

    public void getIPAddress(){
        // System.out.println(request.getRemoteAddr()):
    }
    
    public void startEcho(){
    }
    
    public void stopEcho(){
    }
    
    public void disconnect(){
    	System.out.println("bye");
    	System.out.println("Session is terminated.");
    }

    public static void main(String[] args){
	myClient client = new myClient(args[0], args[1]);

        System.out.println("Hello World!");
	Scanner reader = new Scanner(System.in);
	while(reader.hasNext()){
	    String command = reader.next();
            
	}
	reader.close();
    }


}
