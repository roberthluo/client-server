import java.io.*;
import java.net.*;

public class myServer
{
  //Declaring input and output streams and socket
  ObjectOutputStream out;
  ObjectInputStream in;
  public static PrintWriter writer;
  private static Socket socket;

  public static void main(String args[])
  {
    try{
      PrintWriter writer = new PrintWriter("log.txt", "UTF-8");
    }
    catch(Exception e){
      e.printStackTrace();
    }
    try
    {
      int port = Integer.parseInt(args[0]);
      ServerSocket serverSocket = new ServerSocket(port);

      //socket is connected to client
      socket = serverSocket.accept();
      System.out.println("Session has been established.");

      //Server is running always. This is done using this while(true) loop
      while(true)
      {
        //Reading the message from the client.
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String message = br.readLine();

        //Server accepts new connections when client socket is terminated
        if(message == null){
          System.out.println("Session is terminated.");
          socket = serverSocket.accept();
          System.out.println("Session has been established.");
          is = socket.getInputStream();
          isr = new InputStreamReader(is);
          br = new BufferedReader(isr);
          message = br.readLine();
        }
        else{
          System.out.println("Message received from client is "+message);
        }

        String returnMessage;
        try
        {
            returnMessage = message + "\n";
        }
        catch(Exception e)
        {
            //Input was not correct. Sending proper message back to client.
            returnMessage = "Please send a proper message\n";
        }

        //Sending the response back to the client.
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(returnMessage);
        bw.flush();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    writer.close();
  }

}
