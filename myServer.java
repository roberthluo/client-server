import java.io.*;
import java.net.*;

public class myServer{
     ObjectOutputStream out;
     ObjectInputStream in;
     String message = "";
     public void run(int portNumber){
	 try{
             ServerSocket requestSocket = new ServerSocket(portNumber);
	    
	     Socket connection = requestSocket.accept();
             System.out.println("Session has been established."); 
	     in = new ObjectInputStream(connection.getInputStream());
	     out = new ObjectOutputStream(connection.getOutputStream());
	     out.flush();
	     do{
                 try{
	         message = (String)in.readObject();
		      if(message.equals("bye")){
	                 sendMessage("bye");
		      }
		 }
	 	 catch(Exception e){
	             System.err.println(e);
		 }
	     }while(!message.equals("bye"));
	 }		 
	 catch(Exception e){
	     System.out.println(e);     
	 }
     }

     public void sendMessage(String msg)
     {
         try{
             out.writeObject(msg);
	     out.flush();
	 }
	 catch(IOException ioException){
            ioException.printStackTrace();
	 }
     }

     public static void main(String args[])
     {
         myServer server = new myServer();
	 System.out.println("New Server");
	 server.run(1222);

     }
}

