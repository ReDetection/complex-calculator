/**
 * @author ReDetection@gmail.com
 */
package calc.ui;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;

import calc.math.Complex;

public abstract class ShowNumber extends Form implements CommandListener{

	Command cmd = new Command("OK",Command.EXIT,1);
	public ShowNumber(Complex number) {
		super("Формы числа");
		append("Алгебраическая:\n" + number.toAlgString() +"\n\nПоказательная:\n" + number.toExpString());
		addCommand(cmd);
		setCommandListener(this);
	}

	public abstract void showed();
	public void commandAction(Command arg0, Displayable arg1) {
		showed();
	}

}
