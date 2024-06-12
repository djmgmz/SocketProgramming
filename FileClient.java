import java.net.*;
import java.io.*;

/* Group Member Names
Gomez, Dominic Joel
Soriano, John Angelo
*/

public class FileClient
{
	public static void main(String[] args)
	{
		String sServerAddress = args[0];
		int nPort = Integer.parseInt(args[1]);
		
		try
		{
			Socket clientEndpoint = new Socket(sServerAddress, nPort);
			
			System.out.println("Client: Connected to server at" + clientEndpoint.getRemoteSocketAddress());
			
			DataOutputStream dosWriter = new DataOutputStream(clientEndpoint.getOutputStream());
			dosWriter.writeUTF("Client: Hello from client" + clientEndpoint.getLocalSocketAddress());
			
			DataInputStream disReader = new DataInputStream(clientEndpoint.getInputStream());
			System.out.println(disReader.readUTF());

			FileOutputStream fos = new FileOutputStream("Received.txt");

			byte[] buffer = new byte[4096];
			int bytesRead;
			while ((bytesRead = disReader.read(buffer)) != -1) {
				fos.write(buffer, 0, bytesRead);
			}

			System.out.println("Client: File received and saved as Received.txt");

			clientEndpoint.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("Client: Connection is terminated.");
		}
	}
}