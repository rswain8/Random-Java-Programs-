import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientMain {


    public static void main(String[]args){
        try {


            //creates a connection
            Socket socket=new Socket("10.195.75.25",8345);

            //make input/output streams to this client
            ObjectOutputStream os=new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream is=new ObjectInputStream(socket.getInputStream());

            ClientsListener cl=new ClientsListener(is,new AddingFrame(os));

            Thread t=new Thread(cl);
            t.start();

        }
        catch(Exception e){
            System.out.println("Error in Clinet Main");
            e.printStackTrace();
        }

    }
}
