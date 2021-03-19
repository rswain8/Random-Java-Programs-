import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServersListener implements Runnable{

    public static int value=0;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private static ArrayList<ObjectOutputStream>outs=new ArrayList<>();

    public ServersListener(ObjectInputStream is, ObjectOutputStream os){
        this.os=os;
        this.is=is;
        outs.add(os);
    }

    public void run(){
        try {
            os.writeObject(new Command(Command.NEW_VALUE,value));
            os.flush();
            while(true) {
    Command command = (Command) is.readObject();
    if (command.getCommand() == command.LOGOFF)
            break;
    else{
        int operand=(Integer)command.getCommandData();
        if(command.getCommand()==Command.ADD)
            value+=operand;
        else
            value-=operand;
    }
        //tell everyone
                for(ObjectOutputStream out:outs){
                    out.writeObject(new Command(Command.NEW_VALUE,value));
                    out.flush();
                }

            }
        }
        catch(Exception e){
            System.out.println("Error in Server Listener Main");
            e.printStackTrace();
        }
        finally {
            outs.remove(os);
        }
    }
}
