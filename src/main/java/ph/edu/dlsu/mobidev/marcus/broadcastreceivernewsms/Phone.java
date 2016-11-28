package ph.edu.dlsu.mobidev.marcus.broadcastreceivernewsms;

/**
 * Created by marcus on 10/26/2016.
 */
public class Phone {

    //constant should be placed here
    public static final String TABLE = "phone";
    public static final String NUM= "num";
    public static final String MESSAGE= "message";
    public static final String ID= "id";


    private String message;
    private String senderNum;
    private String id;

    public Phone(String id,String senderNum,String message) {
        this.id=id;
        this.message = message;
        this.senderNum = senderNum;
    }

    public Phone(){



    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSenderNum() {
        return senderNum;
    }

    public void setSenderNum(String senderNum) {
        this.senderNum = senderNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "senderNum=" + senderNum +
                ", message=" + message +
                '}';
    }




}
