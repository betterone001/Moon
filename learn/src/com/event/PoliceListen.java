package com.event;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PoliceListen implements ActionListener {
    JTextField text;
    JTextField a;
    Date time = new Date();
    public void setJTexField(JTextField text, JTextField a){ this.text=text;this.a=a;}

    @Override
    public void actionPerformed(ActionEvent e) {

        a.setText(""+time);
        int n=0,m=0;
         JTextField textSource = (JTextField) e.getSource();
         String str = textSource.getText();

         if(!str.isEmpty()){
             try {
                 n=Integer.parseInt(str);
                 m=n*n;
                 text.setText(""+m);
             }catch (Exception ee){
                 textSource.setText("请输入数字");
             }
         }

    }
}
