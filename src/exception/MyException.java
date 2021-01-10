package exception;

import model.AlertBox;

public class MyException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public MyException(String title, String message){
		AlertBox.display(title, message);
	}
}
