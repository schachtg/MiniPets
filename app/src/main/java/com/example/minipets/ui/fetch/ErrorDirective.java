package com.example.minipets.ui.fetch;

public class ErrorDirective extends FetchDirective{
    protected String error_msg  = null;

    public ErrorDirective(){
        this.error_msg  = null;
    }

    public String getErrorMsg(){
        return this.error_msg;
    }


    public void appendToErrorMsg(String more_error){
        // if we already have an error message add the new one on to the end
        if(this.error_msg != null && more_error != null){
            this.error_msg = this.error_msg + "\n" + more_error;
        }
        //if we didn't already have an error message, then this new one is the only message
        else if(more_error != null){
            this.error_msg = more_error;
        }
        //if the new message is somehow empty, silently do nothing
    }


    public boolean hasMessage(){
        boolean msg_exists = false;
        if(this.error_msg != null){
            msg_exists = true;
        }
        return msg_exists;
    }
}
