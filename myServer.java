import java.io.*;
import java.net.*;

public class myServer{
     public myServer(int portNumber){
	 try{
              ServerSocket serverSocket = new ServerSocket(portNumber);
	      Socket clientSocket = serverSocket.accept();
	 }
	 catch(Exception e){
	      System.out.println(e);
	 }
     }








}
