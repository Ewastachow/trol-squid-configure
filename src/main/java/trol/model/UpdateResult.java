package trol.model;

public class UpdateResult {
    private boolean success;
    private String message;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void fail(){
        success=false;
    }

    public void fail(String message){
        success = false;
        this.message = message;
    }

    public void success(){
        success=true;
    }
}
