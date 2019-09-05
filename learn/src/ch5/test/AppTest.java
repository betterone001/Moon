package ch5.test;
import ch5.data.*;
public class AppTest {
   public static void main(String []args) {
       GiveTestPaper initTestPaper = new RamdomInitTestPaper(); //������ʼ�Ծ����
       TestPaper testPaper= 
      initTestPaper.getTestPaper("���/��ͨ����.xls", 5);   //�õ���5����Ŀ���Ծ�
       Problem [] problem = testPaper.getAllProblem();      //�õ��Ծ��е�ȫ������
       for(int i = 0;i<problem.length;i++ ) {
           int m = i+1;
           System.out.println("��"+m+"��.");
           System.out.println(problem[i].getContent());
           if(problem[i].getIsJudge()){
               inputOne(problem[i]);
           }
           else if(problem[i].getIsChoice()){
               inputTwo(problem[i]);
           }
           System.out.println();
       }
        //ģ���û����⣺
       problem[0].setUserAnswer("B");  //ģ���û����Ĵ���B
       problem[1].setUserAnswer("A");  
       problem[2].setUserAnswer("C");  
       problem[3].setUserAnswer("A");  
       problem[0].setUserAnswer("B");  
       problem[1].setUserAnswer("D");  
       testPaper.acceptTeacher(new TeacherOne()); //����ʦ����
  }
  static void inputOne(Problem  problem){
      System.out.printf("%s\t%s\n",problem.getGiveChoiceA(),problem.getGiveChoiceB());
      System.out.println("ͼ�������"+problem.getImageName());
      System.out.println("��ȷ�𰸣�"+problem.getCorrectAnswer());
  }
  static void inputTwo(Problem problem){
      System.out.printf("%s\t%s\n",problem.getGiveChoiceA(),problem.getGiveChoiceB());
      System.out.printf("%s\t%s\n",problem.getGiveChoiceC(),problem.getGiveChoiceD());
      System.out.println("ͼ�������"+problem.getImageName());
      System.out.println("��ȷ�𰸣�"+problem.getCorrectAnswer());
  }
}