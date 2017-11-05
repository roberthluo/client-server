import java.io.*;
import java.net.*;
import java.util.*;

public class myClient
{
  Socket socket;
  public String ipAddress = "";
  public int port = 0;
  BufferedWriter bw;

  public myClient(String ipAddress, int port)
  {
    this.ipAddress = ipAddress;
    this.port = port;

    try{
      //Send the message to the server
      socket = new Socket(ipAddress, port);
      OutputStream os = socket.getOutputStream();
      OutputStreamWriter osw = new OutputStreamWriter(os);
      bw = new BufferedWriter(osw);

      System.out.println("Session has been established.");

    }
    catch(UnknownHostException unknownHost){
      System.err.println("You are trying to connect to an unknown host!");
    }
    catch(IOException ioException){
      ioException.printStackTrace();
    }

  }

  public void getIPAddress()
  {
    System.out.println("server:"+ socket.getRemoteSocketAddress());
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

      String sendMessage = command + "\n";

      //Get the return message from the server
      try
      {
        bw.write(sendMessage);
        bw.flush();
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        bw = new BufferedWriter(osw);
        System.out.println("Message sent to the server : "+sendMessage);
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String message = br.readLine();
        System.out.println("Message received from the server : " +message + "\n");
      }
      catch (Exception exception)
      {
        exception.printStackTrace();
      }

    }
    System.out.println("stopped");
  }


  public void disconnect()
  {
    try{
      socket.close();
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
