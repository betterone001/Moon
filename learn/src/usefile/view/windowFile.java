package usefile.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class windowFile extends JFrame implements ActionListener {
    JFileChooser fileDialog;
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem itemSave,itemOpen;
    JTextArea text;
    BufferedReader in;
    FileReader fileReader;
    BufferedWriter out;
    FileWriter fileWriter;

    windowFile(){
        init();
        setSize(300,400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    void init(){
        text =  new JTextArea(10,10);
        add(new JScrollPane(text), BorderLayout.CENTER);
        menuBar = new JMenuBar();
        menu = new JMenu("File");
        itemSave = new JMenuItem("Save File");
        itemOpen = new JMenuItem("Open File");
        itemOpen.addActionListener(this);
        itemSave.addActionListener(this);
        menu.add(itemOpen);
        menu.add(itemSave);
        menuBar.add(menu);
        setJMenuBar(menuBar);
        fileDialog = new JFileChooser();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==itemSave){
            int state=fileDialog.showSaveDialog(this);
            if(state==JFileChooser.APPROVE_OPTION){
//                text.append("\n点击了对话框上的\"确定\"按钮");
//                text.append("\n保存的文件名"+fileDialog.getSelectedFile());
                try{
                    File dir = fileDialog.getCurrentDirectory();
                    String name = fileDialog.getSelectedFile().getName();
                    File file = new File(dir,name);
                    fileWriter = new FileWriter(file);
                    out = new BufferedWriter(fileWriter);
                    out.write(text.getText());
                    out.close();
                    fileWriter.close();
                }catch(IOException exp){}
                }
//                else{
//                    text.append("\n点击了对话框的\"取消\"按钮");
//                }
            }
        else if (e.getSource()==itemOpen){
            int state=fileDialog.showSaveDialog(this);
            if(state==JFileChooser.APPROVE_OPTION){
//                text.append("\n点击了对话框上的\"确定\"按钮");
//                text.append("\n打开的文件名"+fileDialog.getSelectedFile());
                try{
                    File dir = fileDialog.getCurrentDirectory();
                    String name = fileDialog.getSelectedFile().getName();
                    File file = new File(dir,name);
                    fileReader = new FileReader(file);
                    in = new BufferedReader(fileReader);
                    String s = null;
                    while((s=in.readLine())!=null){
                        text.append(s+"\n");
                    }
                    in.close();
                    fileReader.close();
                }catch(IOException exp){}
                }

            else{
                text.append("\n点击了对话框的\"取消\"按钮");
            }
        }
    }
}
