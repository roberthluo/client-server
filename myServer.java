import java.io.*;
import java.net.*;

public class myServer
{
  ObjectOutputStream out;
  ObjectInputStream in;
  String message = "";
  File file = new File("log.txt");
  public static PrintWriter writer;

  public myServer()
  {
    try
    {
      writer = new PrintWriter("log.txt", "UTF-8");
      writer.println("Start of log:");
    }
    catch (IOException e){
      e.printStackTrace();
    }
  }

  public void run(int portNumber)
  {
    while(true){
      try{
        ServerSocket requestSocket = new ServerSocket(portNumber);
        Socket connection = requestSocket.accept();

        // Needs to accept connection before continuing
        System.out.println("Session has been established.");
        in = new ObjectInputStream(connection.getInputStream());
        out = new ObjectOutputStream(connection.getOutputStream());
        out.flush();

        do{
          try{
            message = (String)in.readObject();
            writer.println("client>" + message);
            sendMessage(message);

          }
          catch(Exception e){
            System.err.println(e);
          }

        }while(!message.equals("bye"));
      }
      catch(Exception e){
        System.out.println(e);
      }
      //closing writer
      writer.println("End of log.");
      writer.close();
    }

  }

  public void sendMessage(String msg)
  {
    try{
      out.writeObject(msg);
      out.flush();
      writer.println("server>" + msg);
    }
    catch(IOException ioException){
      ioException.printStackTrace();
    }
  }

  public static void main(String args[])
  {
    myServer server = new myServer();
    System.out.println("New Server port:" + args[0]);
    int port = Integer.parseInt(args[0]);
    server.run(port);
    Runtime.getRuntime().addShutdownHook(new Thread()
    {
      public void run()
      {
        writer.close();
        System.out.println("In shutdown hook");
      }
    });
    System.out.println("Application Terminating ...");

  }
}
