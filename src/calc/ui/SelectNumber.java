/**
 * @author ReDetection@gmail.com
 */
package calc.ui;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

import calc.math.Complex;

import java.util.Vector;

public abstract class SelectNumber extends List implements CommandListener{
	private SelectNumber link;
	private Input input;
	private Vector vector = new Vector();
	
	public SelectNumber(Display d) {
		super("Комплексные числа",Choice.IMPLICIT);
		link=this;
		this.append("Новое число", null);
		this.setCommandListener(this);
		vector.addElement("null");//чтобы не запутаться в индексах
		
		input = new Input(d){
			public void gathered(Complex number) {
				addNumber(number);
				link.selected(number);
			}
		};
		
	}
	public void addNumber(Complex number){
		link.append(number.toString(), null);
		vector.addElement(number);
	}
	public void commandAction(Command c, Displayable d) {
		if(getSelectedIndex()==0)
			input.show();
		else
			link.selected((Complex) vector.elementAt(getSelectedIndex()));
		
	}

	public abstract void selected(Complex number);

}
