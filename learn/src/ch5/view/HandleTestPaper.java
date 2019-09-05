package ch5.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import ch5.data.*;
public class  HandleTestPaper implements ActionListener{
    TestPaperView  view;
    TestPaper   testPaper;   //��Ҫ������Ծ�
    Problem problem;         //��ǰ����Ŀ
    Toolkit tool;            //����ͼ��
    public HandleTestPaper(){
        tool = Toolkit.getDefaultToolkit();
    }
    public void setView(TestPaperView view) {
       this.view = view;
    }
    public void setTestPaper(TestPaper testPaper) {
       this.testPaper = testPaper;
    }
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==view.nextProblem){ 
          view.time.start(); //��ʼ��ʱ
          if(testPaper!=null){
            problem = testPaper.nextProblem();
            handleProblem(problem);
          }
          else {
             JOptionPane.showMessageDialog
              (view,"û������","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
          }
       } 
       if(e.getSource()==view.previousProblem){
          view.time.start(); //��ʼ��ʱ
          if(testPaper!=null){
            problem = testPaper.previousProblem();
            handleProblem(problem);
          }
          else {
            JOptionPane.showMessageDialog
              (view,"û������","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
          }
          
       } 
       if(e.getSource()==view.aProblemSubmit){  //ȷ��һ����Ŀ�Ĵ�
          String answer ="";
          if(view.choiceA.isSelected()){
             answer = answer+"A";
          }
          if(view.choiceB.isSelected()){
             answer = answer+"B";
          }
          if(view.choiceC.isSelected()){
             answer = answer+"C";
          }
          if(view.choiceD.isSelected()){
             answer = answer+"D";
          }
          if(problem==null) {
              JOptionPane.showMessageDialog
              (view,"û������","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
             return;
          }
          view.choiceA.setVisible(false);
          view.choiceB.setVisible(false);
          view.choiceC.setVisible(false);
          view.choiceD.setVisible(false); 
          view.aProblemSubmit.setVisible(false);
          problem.setUserAnswer(answer);
       } 
       if(e.getSource()==view.submit){
           testPaper.acceptTeacher(view.teacher); //�Ծ�����ʦ����
           view.renewJButton.setVisible(true);
           view.submit.setVisible(false);
           view.time.stop(); 
           view.showUsedTime.setText("������");
       } 
       if(e.getSource()==view.renewJButton) {     //����һ����Ŀ
           view.showUsedTime.setText("");
           view.usedTime = view.totalTime;
           view.showUsedTime.setText("����ʣ��ʱ��:"+view.totalTime);
           view.showContent.setText(null);
           Image img = tool.getImage("ͼ�����/renew.jpg");
           handleImage(img); 
           view.showImage.repaint();
           view.nextProblem.setVisible(true); 
           view.previousProblem.setVisible(true); 
           String problemSource= testPaper.getProblemSource(); //�õ�ԭʼ���
           GiveTestPaper initTestPaper = new RamdomInitTestPaper(); 
           testPaper=initTestPaper.getTestPaper(problemSource,testPaper.getProlemAmount());
           view.renewJButton.setVisible(false);
           view.submit.setVisible(true);  
       }
       view.choiceA.setSelected(false);
       view.choiceB.setSelected(false);
       view.choiceC.setSelected(false);
       view.choiceD.setSelected(false);
    }
    private void handleProblem(Problem problem) {
          if(problem==null) {
              JOptionPane.showMessageDialog
              (view,"û������","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
          }
          else {
              view.aProblemSubmit.setVisible(true);
              view.showContent.setText(problem.getContent());
              view.showContent.setVisible(true);
              if(problem.getIsChoice()) {
                   handelChoice();
              }
              else if(problem.getIsJudge()) {
                   handelJudge();
              }
              String imageName = problem.getImageName();
               //�û��������ͼ���ŵ�"ͼ�����"�ļ���
              Image img = tool.getImage("ͼ�����/"+imageName);
              handleImage(img);   
          }
    }
    private void handelJudge() {
          view.choiceA.setText(problem.getGiveChoiceA());
          view.choiceB.setText(problem.getGiveChoiceB());
          view.choiceA.setVisible(true);
          view.choiceB.setVisible(true);
          view.choiceC.setVisible(false);
          view.choiceD.setVisible(false);
    }
    private void handelChoice() {
          view.choiceA.setText(problem.getGiveChoiceA());
          view.choiceB.setText(problem.getGiveChoiceB());
          view.choiceC.setText(problem.getGiveChoiceC());
          view.choiceD.setText(problem.getGiveChoiceD());
          view.choiceA.setVisible(true);
          view.choiceB.setVisible(true);
          view.choiceC.setVisible(true);
          view.choiceD.setVisible(true);
    }
    private void handleImage(Image image) {
          view.showImage.setImage(image);
          view.showImage.repaint();
        
    }
}