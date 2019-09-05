package ch5.data;

import javax.swing.*;
import java.awt.*;

public class TeacherOne implements Teacher {
    public void giveTestPaparScore(TestPaper testPaper){
          int correctAmount=0;//百分比计分


          if(testPaper==null){
            JOptionPane.showMessageDialog
              (null,"没答题","消息对话框",JOptionPane.WARNING_MESSAGE);
            return;
          } 
          Problem p[] = testPaper.getAllProblem();

          if(p==null||p.length==0){
            JOptionPane.showMessageDialog
              (null,"没答题","消息对话框",JOptionPane.WARNING_MESSAGE);
            return;
          } 
          for(int i=0;i<p.length;i++){
             boolean b = compare(p[i].getUserAnswer(),p[i].getCorrectAnswer());
             if(b) {
                correctAmount++;
             }
          }String w[]=new String[p.length-correctAmount];int a=0;
          for(int i=0;i<p.length;i++){
              boolean c= compare(p[i].getUserAnswer(),p[i].getCorrectAnswer());
              if(!c){
                  w[a]=p[i].getContent()+"\n"+p[i].getCorrectAnswer();
                  a++;
              }
          }

          double result = (double)correctAmount/(double)p.length;
          int r =(int)(result*100);
          String s ="共有:"+p.length+"道题."+
                    "您做对了"+correctAmount+"题,"+
                    "正确率大约"+r+"%";

            JPanel mess= new JPanel();
            mess.setLayout(new BorderLayout());
          JTextArea area=new JTextArea();
          area.setLineWrap(true);
          area.append(s);
          for(int i=0;i<w.length;i++){
              area.append(w[i]+"\n");

          }

          mess.add(area,BorderLayout.CENTER);
        mess.add(new JScrollPane(area));

          JOptionPane.showMessageDialog(null,mess,"成绩",JOptionPane.PLAIN_MESSAGE );

    }
    private boolean compare(String s,String t) {
        boolean isTrue = true;
//        for(int i=0;i<s.length();i++) {
//           String temp = ""+s.charAt(i);
//           if(!(t.contains(temp)))
//             isTrue = false;
//        }
//        for(int i=0;i<t.length();i++) {
//           String temp = ""+t.charAt(i);
//           if(!(s.contains(temp)))
//             isTrue = false;
//        }
        if(s.equals(t)){ isTrue =true;}
        else{
            isTrue =false;
        }
            return isTrue;
    }
}