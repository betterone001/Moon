package ch5.view;
import ch5.data.Teacher;
import ch5.data.TestPaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestPaperView extends JPanel implements ActionListener{
   TestPaper testPaper;      //����ͼ��Ҫ��ʾ���Ծ�
   public Teacher teacher ;  //������ʦ
   public JTextArea showContent;    //��ʾ��������
   public ImageJPanel showImage;    //��ʾ�����ͼ��
   public JCheckBox choiceA,choiceB,choiceC,choiceD;//��ʾѡ������
   public JButton nextProblem,previousProblem;  //ѡ����һ�⣬��һ��İ�ť
   public JButton aProblemSubmit;  //ȷ��ĳ����Ļش��ѡ��
   public JButton renewJButton   ;  //���¿�ʼ��
   public JButton submit;  //����
   HandleTestPaper handleTestPaper; //������testPaper�Ծ��е�����
   public int  totalTime = 0;      //������ʱ����λ�֣�
   public int  usedTime  = totalTime;
   public javax.swing.Timer time;          //���Լ�ʱ��
   public JLabel showUsedTime   ;          //��ʾ��ʱ
   JLabel testName ;//��ʾ��������

   public TestPaperView() {
      time = new Timer(1000,this);//ÿ��1���Ӽ�ʱһ�Σ�����ActionEvent����������Ϊ�������
      initView();
      registerListener();
   } 
   public void setTeacher(Teacher teacher){
      this.teacher = teacher;
   }
   public void initView() {
      setLayout(new BorderLayout()); 
      showImage = new ImageJPanel();
      showContent = new JTextArea(12,12);
      showContent.setToolTipText("���������ͼ����ͼ�ϵ������ɵ����ۿ�");
      showContent.setForeground(Color.blue);
      showContent.setWrapStyleWord(true);
      showContent.setLineWrap(true); //�����Զ�
      showContent.setFont(new Font("����",Font.BOLD,18));
      choiceA = new JCheckBox("A");
      choiceB = new JCheckBox("B");
      choiceC = new JCheckBox("C");
      choiceD = new JCheckBox("D");
      choiceA.setVisible(false);
      choiceB.setVisible(false);
      choiceC.setVisible(false);
      choiceD.setVisible(false);
      nextProblem = new JButton("��һ��Ŀ");
      previousProblem = new JButton("��һ��Ŀ");
      aProblemSubmit = new JButton("ȷ��"); 
      aProblemSubmit.setVisible(false); 
      renewJButton = new JButton("����һ��");
      renewJButton.setVisible(false);
      submit = new JButton("����");  
      JPanel pNorth = new JPanel();
      pNorth.setBackground(Color.cyan) ;
      showUsedTime = new JLabel();
      testName = new JLabel();
      testName.setFont(new Font("����",Font.BOLD,18));
      pNorth.add(testName);
      pNorth.add(new JLabel("������һ�����һ�ⰴť��ʼ����")); 
      pNorth.add(submit);
      pNorth.add(renewJButton);
      pNorth.add(showUsedTime); 
      add(pNorth,BorderLayout.NORTH);
      JPanel pCenter = new JPanel();
      pCenter.setLayout(new GridLayout(1,2));
      pCenter.add(new JScrollPane(showContent));
      pCenter.add(showImage);
//      pCenter.add();
      add(pCenter,BorderLayout.CENTER);
      JPanel pSouth = new JPanel();
      pSouth.setLayout(new GridLayout(2,1));
      JPanel oneInPSouth = new JPanel();
      JPanel twoInPSouth = new JPanel();
      oneInPSouth.setBackground(Color.green) ;
      oneInPSouth.setBackground(Color.pink) ;
      oneInPSouth.add(choiceA);
      oneInPSouth.add(choiceB);
      oneInPSouth.add(choiceC);
      oneInPSouth.add(choiceD);
      oneInPSouth.add(aProblemSubmit);
      twoInPSouth.add(nextProblem);
      twoInPSouth.add(previousProblem);
      pSouth.add(oneInPSouth);
      pSouth.add(twoInPSouth);          
      add(pSouth,BorderLayout.SOUTH);
      validate();
   }
   public void registerListener(){
      handleTestPaper = new HandleTestPaper();
      nextProblem.addActionListener(handleTestPaper);
      previousProblem.addActionListener(handleTestPaper);
      aProblemSubmit.addActionListener(handleTestPaper);
      submit.addActionListener(handleTestPaper);
      renewJButton.addActionListener(handleTestPaper);
      handleTestPaper.setView(this);
   }
   public void setTestPaper(TestPaper testPaper) {
      this.testPaper = testPaper;
      handleTestPaper.setTestPaper(testPaper);
   }
   public void actionPerformed(ActionEvent e){
      showUsedTime.setText("����ʣ��ʱ��:"+usedTime--);
      if(usedTime==3){//lzh
         JOptionPane.showMessageDialog
                 (this,"û��ʱ����","������",JOptionPane.WARNING_MESSAGE);
         return;
      }
      if(usedTime == 0){
          time.stop();
          showUsedTime.setText("�뽻��");
          nextProblem.setVisible(false); 
          previousProblem.setVisible(false); 
      }
//      usedTime--;
   }
   public void setTestName(String name){
      testName.setText("��"+name+"��");
   }
   public void setTotalTime(int n) {

      totalTime = n;
      usedTime = n;
      showUsedTime.setText("����ʣ��ʱ��:"+usedTime);
   }
}
