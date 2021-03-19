import java.io.Serializable;

public class Command implements Serializable{

    public static final int ADD=0;
    public static final int SUBTRACT=1;
    public static final int LOGOFF=2;
    public static final int NEW_VALUE=3;


    private int command=0;
    private Object commandData=0;

    public Command(int command, Object commandData) {
        this.command = command;
        this.commandData = commandData;
    }
    public Command(int command){
        this.command=command;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public Object getCommandData() {
        return commandData;
    }

    public void setCommandData(Object commandData) {
        this.commandData = commandData;
    }


}
