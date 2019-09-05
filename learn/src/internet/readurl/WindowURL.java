package internet.readurl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class WindowURL extends JFrame implements ActionListener {
    JButton button;
    URL url;
    JTextField text;
    JEditorPane editPane;
    byte b[]=new byte[9];
    Thread thread;

     WindowURL(){
        text=new JTextField(20);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
