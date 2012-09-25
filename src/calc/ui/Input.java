/**
 * @author ReDetection@gmail.com
 */
package calc.ui;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;

import calc.math.Complex;


public abstract class Input implements CommandListener{
	private Display display=null;
	private List choise;
	private InputAlg alg;
	private InputExp exp;
	Command cmd = new Command("OK",Command.EXIT,1);
	
	public Input(Display display) {
		super();
		this.display = display;
		alg = new InputAlg();
		exp = new InputExp();
		choise = new List("Форма числа?", Choice.IMPLICIT);
		choise.setCommandListener(this);
		choise.append("Алгебраическая", null);
		choise.append("Показательная", null);
	}
	
	public void commandAction(Command c, Displayable d) {
		if(choise.getSelectedIndex()==0)
			display.setCurrent(alg);
		else 
			display.setCurrent(exp);
	}
	
	public void show(){
		display.setCurrent(choise);
	}
	public abstract void gathered(Complex number);
	
	class InputAlg extends Form implements CommandListener {
		TextField real,imaginary;

		public InputAlg() {
			super("Введите");
			real = new TextField("Реальная часть", "", 64, TextField.ANY);
			append(real);
			imaginary = new TextField("Мнимая часть", "", 64, TextField.ANY);
			append(imaginary);
			addCommand(cmd);
			setCommandListener(this);
			
		}

		public void commandAction(Command c, Displayable d) {
			String re = real.getString().replace(',', '.') , im = imaginary.getString().replace(',', '.') ;
			try{
				gathered(Complex.fromAlg(Double.valueOf(re).doubleValue(), Double.valueOf(im).doubleValue()));
			}catch (Exception e) {}
		}
	}
	
	class InputExp extends Form implements CommandListener {
		TextField abs,phase;

		public InputExp() {
			super("Введите");
			abs = new TextField("Длина", "0", 64, TextField.ANY);
			append(abs);
			phase = new TextField("Угол", "0", 64, TextField.ANY);
			append(phase);
			addCommand(cmd);
			setCommandListener(this);
			
		}

		public void commandAction(Command c, Displayable d) {
			String ab = abs.getString().replace(',', '.') , ph = phase.getString().replace(',', '.') ;
			try{
				gathered(Complex.fromExp(Double.valueOf(ab).doubleValue(), Double.valueOf(ph).doubleValue()));
			}catch (Exception e) {	}
		}
	}
}

