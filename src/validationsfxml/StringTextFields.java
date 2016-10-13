package validationsfxml;

import javafx.scene.control.TextField;

public class StringTextFields extends TextField{
	public StringTextFields(){
		this.setPromptText("Enter Only String");
	}
	@Override
	public void replaceText(int i,int i1,String string){
		if(string.matches("[a-zA-Z]")||string.isEmpty()){
			super.replaceText(i,i1,string);
			
		}
	}
}
