import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


	public static void main(String[] argv) throws Exception {
		System.out.println("I am here");
		ServerSocket s = new ServerSocket(50002);
		System.out.println("Server started");
		while(true) {
			Socket t = s.accept(); // wait for client to connect
			System.out.println("server connected");
			ObjectInputStream b = new ObjectInputStream(t.getInputStream());
			IPValue received = (IPValue) b.readObject();
			PrintWriter output = new PrintWriter(t.getOutputStream(), true);
			output.println("The received IP address:\n " + received.getOne() + "." + received.getTwo() + "." + received.getThree() + "." + received.getFour() 
					+ " " + "has been received");
			if (received.getOne()== 10) {
				output.println("This is a private IP address");
			}else if (received.getOne() == 172 && (received.getTwo() >=16 && received.getTwo() <= 31)) {
				output.println("This is a private IP address");
			}else if (received.getOne() == 192 && (received.getTwo() == 168)) {
				output.println("This is a private IP address");
			}else
				output.println("This is not a private IP address");
			b.close();
			output.close();
			t.close();
			
		}
		// TODO Auto-generated method stub

	}

}
