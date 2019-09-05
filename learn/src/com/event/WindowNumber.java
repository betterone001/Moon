package com.event;

import javax.swing.*;
import java.awt.*;

public class WindowNumber extends JFrame {
      JTextField textInput, textShow,a;
      PoliceListen Listener ;
      public WindowNumber(){
        init();
        setBounds(100,100,150,150);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      }
      void init(){
            setLayout(new FlowLayout());
            textInput = new JTextField(10);
            textShow = new JTextField(10);
            a = new JTextField(10);
            textShow.setEditable(false);
            Listener = new PoliceListen();
            Listener.setJTexField(textShow,a);

            textInput.addActionListener(Listener);
            add(textInput);
            add(textShow);
            add(a);

      }

}
