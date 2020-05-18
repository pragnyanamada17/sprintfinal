package com.cap.anurag.exception;

@SuppressWarnings("serial")
public class NullPointerException extends RuntimeException{
	 public NullPointerException(String msg){
	        super(msg);
	    }

	    public NullPointerException(String msg,Throwable e){
	        super(msg,e);
	    }
}
