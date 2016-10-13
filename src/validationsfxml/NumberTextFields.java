package validationsfxml;

import javafx.scene.control.TextField;


public class NumberTextFields extends TextField{

	public NumberTextFields(){
		this.setPromptText("Enter Only Number");
	}
	
	@Override
	public void replaceText(int i,int i1,String string){
		if(string.matches("[0-9]")||string.isEmpty()){
			super.replaceText(i,i1,string);
			
		}
	}
}
