import java.io.*;
import java.net.*;
import java.util.*;


public class myClient
{
  Socket client;
  ObjectOutputStream out;
  ObjectInputStream in;
  String message;
  public String ipAddress = "";
  public int port = 0;


  public myClient(String ipAddress, int port)
  {
    this.ipAddress = ipAddress;
    this.port = port;

    try{
      client = new Socket("localhost", port);
      out = new ObjectOutputStream(client.getOutputStream());
      out.flush();
      in = new ObjectInputStream(client.getInputStream());
    }
    catch(UnknownHostException unknownHost){
      System.err.println("You are trying to connect to an unknown host!");
    }
    catch(IOException ioException){
      ioException.printStackTrace();
    }
    System.out.println("Session has been established.");
  }

  public void getIPAddress()
  {
    System.out.println("server:"+ client.getRemoteSocketAddress());
  }

  public void startEcho()
  {

    System.out.println("listening");
    Scanner reader = new Scanner(System.in);
    while(true){
      String command = reader.next();
      if(command.equals("stopEcho")){
        break;
      }
      sendMessage(command);
    }
    System.out.println("stopped");
  }

  void sendMessage(String msg)
  {
      try{
          out.writeObject(msg);
          out.flush();
          System.out.println("echo>" + msg);
      }
      catch(IOException ioException){
          ioException.printStackTrace();
      }
  }

  public void disconnect()
  {
    try{
      client.close();
    }
    catch(IOException ioException){
        ioException.printStackTrace();
    }
    System.out.println("bye");
    System.out.println("Session is terminated.");
  }

  public static void main(String[] args)
  {

    myClient client = new myClient(args[0], Integer.parseInt(args[1]));

    Scanner reader = new Scanner(System.in);

    while(!reader.equals("bye")){
      String command = reader.next();
      System.out.print("operation>");
      if(command.equals("whoAMi")){
        System.out.println("whoAMIi");
        client.getIPAddress();
      }
      else if(command.equals("startEcho")){
        client.startEcho();
      }
      else if(command.equals("bye")){
        break;
      }
    }
    client.disconnect();
    reader.close();
    System.out.println("End of client session.");
  }


}
