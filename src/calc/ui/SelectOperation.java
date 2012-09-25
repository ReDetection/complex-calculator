/**
 * @author ReDetection@gmail.com
 */
package calc.ui;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;

public abstract class SelectOperation extends List implements CommandListener {
	public static final int ADD = 0;
	public static final int SUB = 1;
	public static final int MUL = 2;
	public static final int DIV = 3;
	public static final int PARALLELRESISTOR = 4;
	public static final int SHOW = 5;
	
	
	public SelectOperation(){
		super("Выберите действие",Choice.IMPLICIT);
		append("Сложить", null);
		append("Вычесть", null);
		append("Умножить", null);
		append("Разделить", null);
		append("x*y/(x+y)",null);
		append("Показать", null);
		setCommandListener(this);
	}
	public abstract void selected(int action );
	
	public void commandAction(Command arg0, Displayable arg1) {
		selected(getSelectedIndex());
	}
}
