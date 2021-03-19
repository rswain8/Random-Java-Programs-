import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientsListener implements Runnable{



    private ObjectInputStream is;
    private AddingFrame frame;

    public ClientsListener(ObjectInputStream is,AddingFrame frame){

        this.is=is;
        this.frame=frame;

    }

    public void run(){
        try {

            while(true) {
                Command command = (Command) is.readObject();
                if (command.getCommand() == command.NEW_VALUE)
                    frame.updateValue((Integer)command.getCommandData());

                }


        }
        catch(Exception e){
            System.out.println("Error in clients Listener Main");
            e.printStackTrace();
        }

    }
}
