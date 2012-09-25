/**
 * @author ReDetection@gmail.com
 */
package calc;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import calc.math.Complex;
import calc.ui.SelectNumber;
import calc.ui.SelectOperation;
import calc.ui.ShowNumber;

//TODO: кнопку "назад". лень и не нужна пока. нужна ли будет?
public class Main extends MIDlet{
	private int state,operator=-1;
	private SelectNumber selectNumber;
	private Complex operand1, result;
	private SelectOperation selectOperation;
	private Display display;

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		System.gc();
		notifyDestroyed();
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		state=0;
		display = Display.getDisplay(this);
		
		selectNumber = new SelectNumber(display) {			
			public void selected(Complex number) {
				if(state==0){
					operand1=number;
					state = 2;
					display.setCurrent(selectOperation);
					System.gc();
				}
				if(state==3){
					switch(operator){
						case SelectOperation.ADD:{
							result= Complex.plus(operand1,number);
							break;
						}
						case SelectOperation.SUB:{
							result=operand1.minus(number);
							break;
						}
						case SelectOperation.MUL:{
							result=operand1.times(number);
							break;
						}
						case SelectOperation.DIV:{
							result=operand1.divides(number);
							break;
						}
						case SelectOperation.PARALLELRESISTOR:{
							result=operand1.times(number).divides(Complex.plus(operand1, number));
							break;
						}
					}
					selectNumber.addNumber(result);
					showNumber(result);
				}
			}
		};
		selectOperation = new SelectOperation() {
			public void selected(int action) {
				if(action==SelectOperation.SHOW){
					showNumber(operand1);
					return;
				}else{
					operator=action;
					state=3;
					display.setCurrent(selectNumber);
				}
			}
		};
		
		display.setCurrent(selectNumber);
	}
	private void showNumber(Complex number){
		ShowNumber showNumber = new ShowNumber(number) {
			public void showed() {
				display.setCurrent(selectNumber);
				state=0;
			}
		};
		display.setCurrent(showNumber);
	}


}
