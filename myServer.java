import java.io.*;
import java.net.*;

public class myServer
{
  ObjectOutputStream out;
  ObjectInputStream in;
  String message = "";
  File file = new File("log.txt");
  public static PrintWriter writer;
  ServerSocket requestSocket;
  private static Socket socket;

  public static void main(String args[])
  {
    try
    {

      int port = Integer.parseInt(args[0]);
      ServerSocket serverSocket = new ServerSocket(port);
      socket = serverSocket.accept();
      System.out.println("Server Started and listening to the port "+ port);

      //Server is running always. This is done using this while(true) loop
      while(true)
      {
        //Reading the message from the client
        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String message = br.readLine();

        if(message == null){
          socket = serverSocket.accept();
        }
        System.out.println("Message received from client is "+message);

        String returnMessage;
        try
        {
            returnMessage = "server:" + message + "\n";
        }
        catch(Exception e)
        {
            //Input was not a number. Sending proper message back to client.
            returnMessage = "Please send a proper message\n";
        }

        //Sending the response back to the client.
        OutputStream os = socket.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os);
        BufferedWriter bw = new BufferedWriter(osw);
        bw.write(returnMessage);
        System.out.println("Message sent to the client is "+returnMessage);
        bw.flush();
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    // finally
    // {
    //   try
    //   {
    //     socket.close();
    //   }
    //   catch(Exception e){}
    //   }
    // }
  }

}
