package ch5.gui;
import ch5.data.GiveTestPaper;

import ch5.data.RamdomInitTestPaper;
import ch5.data.TestPaper;
import ch5.data.TeacherOne;
import ch5.view.TestPaperView;
import ch5.view.IntegrationView;
public class AppWindow {
   public static void main(String []args) {
       String testName="";
       IntegrationView integrationView = new IntegrationView();
       GiveTestPaper initTestPaper = new RamdomInitTestPaper(); //������ʼ�Ծ����
       TestPaper testPaper= 
       initTestPaper.getTestPaper("���/��ͨ����.xls",5);   //�õ���5����Ŀ���Ծ�
       TestPaperView testView = new TestPaperView();
       testView.setTestPaper(testPaper);        //�����Ծ�
       testView.setTeacher(new TeacherOne()); //�����ľ���ʦ
       testName = "��ͨ��ѵ��";
       testView.setTestName(testName);
       testView.setTotalTime(4);//����ʱ��15����
       integrationView.addTestPaperView(testName,testView);
       initTestPaper = new RamdomInitTestPaper(); //������ʼ�Ծ����
       testPaper= initTestPaper.getTestPaper("���/java����.xls",6);  
       testView = new TestPaperView();
       testView.setTestPaper(testPaper);        
       testView.setTeacher(new TeacherOne());
       testName = "Javaѵ��"; 
       testView.setTestName(testName);
       testView.setTotalTime(10);
       integrationView.addTestPaperView(testName,testView);
   }
}