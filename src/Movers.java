

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

public class Movers extends MIDlet implements CommandListener {

    private Command exitCommand; // The exit command
    private Display display;     // The display for this MIDlet

    public Movers() {
        display = Display.getDisplay(this);
        exitCommand = new Command("Exit", Command.EXIT, 0);
    }

    public void startApp() {
        TextBox t = new TextBox("Hello", "Hello, World!", 256, 0);

        t.addCommand(exitCommand);
        t.setCommandListener(this);

        display.setCurrent(new Canv());
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable s) {
        if (c == exitCommand) {
            destroyApp(false);
            notifyDestroyed();
        } 
    }

}
