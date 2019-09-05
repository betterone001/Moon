package ch5.data;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
public class RamdomInitTestPaper implements GiveTestPaper {   //����������Ծ�����
   TestPaper testPaper ;//�Ծ�
   File fileExcel;
   Problem [] problem; //����Ծ��һ���⣨problem�ĵ�Ԫ���һ�����⼴һ��Problem����
   InputStream in = null;
   Workbook wb = null;  //��װExcel,Workbook��jxl���е���
   Sheet sheet = null;  //��װExcel�е�sheet��Sheet��jxl���е���
   LinkedList<Integer> list;     //�����ȡ����ʱ��
   public RamdomInitTestPaper() {
      testPaper = new TestPaper();
      list = new LinkedList<Integer>();
   }
   public TestPaper getTestPaper(String excelFileName,int amount) {
      boolean b =setExcel(excelFileName);  //�����û��������ĵ��ӱ��
      if(b) {
        try {
           randomGiveProblem(amount);//�������amount�����⣬��������randomGiveProblem����
        }
        catch(ArrayIndexOutOfBoundsException e){
           System.out.println("������������ͣ��������");
           System.exit(0);
        }
        testPaper.setProblem(problem);//�Ծ������õ�һ��������problem
        return testPaper;         //�����Ծ�
      }
      else {
        JOptionPane.showMessageDialog
        (null,"û��Ԥ�����","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
        return null;
      }
   }
   private boolean setExcel(String excelFileName){ 
        boolean b =true;
        try { 
           fileExcel =new File(excelFileName);
           in =new FileInputStream(fileExcel);
           testPaper.setProblemSource(fileExcel.getAbsolutePath());//�Ծ����������Դ
        }
        catch(IOException exp){
           JOptionPane.showMessageDialog
        (null,"û��Ԥ�����Excel","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
            b = false;
        } 
        try { 
           wb=Workbook.getWorkbook(in);
           in.close();
        }
        catch(Exception exp){
           b = false;
        } 
        return b;
   } 
   private void randomGiveProblem(int amount) { //�������amount������,����problem������
        list.clear();
        if(wb==null) {
         JOptionPane.showMessageDialog
        (null,"û��Ԥ�����Excel","��Ϣ�Ի���",JOptionPane.WARNING_MESSAGE);
           return ;
        }
        sheet = wb.getSheet(0);//�õ�Excel�еĵ�һ��sheet��������0��ʼ��
        int rowsAmount = sheet.getRows();     //�õ�sheet��������
        //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
        int realAmount = Math.min(amount,rowsAmount-1);//ʵ�ʳ�ȡ����������
        problem = new Problem[realAmount];              //���ڴ�����������problem
        for(int i=0;i<rowsAmount-1;i++){  //��1��rowsAmount-1���ַ�������
           list.add(i+1);
        }
        Random random=new Random();
        for(int i=0;i<problem.length;i++) { 
            int m = random.nextInt(list.size());//[0,list.size())��һ�������
            int index =list.remove(m);//ɾ��list�ĵ�m���ڵ㣬ͬʱ�ýڵ�����
            Cell [] cell = sheet.getRow(index); //����sheet�еĵ�index��
            //ע��ԭʼExcel����sheet�е�0�в������⣬���û������˵��
            //cell�ĵ�0�����������ݣ�������0��ʼ
            problem[i] = new Problem(); 
            int number = i+1;
            problem[i].setContent("��"+number+"��."+cell[0].getContents());//���������
            problem[i].setCorrectAnswer(cell[1].getContents().trim());//����Ĵ�
            problem[i].setGiveChoiceA(cell[2].getContents().trim());  //�����Aѡ��
            problem[i].setGiveChoiceB(cell[3].getContents().trim());  //�����Bѡ�� 
            problem[i].setGiveChoiceC(cell[4].getContents().trim());  //�����Cѡ��
            problem[i].setGiveChoiceD(cell[5].getContents().trim());  //�����D��
            String typeStr = cell[6].getContents().trim();//��������ͣ��жϻ�ѡ��
            //ע�⣬��Ϊ������ͼ������typeStr�����֣�p,p#,x,x#:
            if(typeStr.equalsIgnoreCase("p")){
                  problem[i].setIsJudge(true);
                  problem[i].setIsChoice(false);
                  problem[i].setImageName("havenot.jpg");
            }
            if(typeStr.equalsIgnoreCase("x")) {
                  problem[i].setIsJudge(false);
                  problem[i].setIsChoice(true);
                  problem[i].setImageName("havenot.jpg");
            }
            if(typeStr.startsWith("p#")||typeStr.startsWith("P#")) {
                  problem[i].setIsJudge(true);
                  problem[i].setIsChoice(false);
                  String imageName = typeStr.substring(typeStr.indexOf("#")+1);  
                  problem[i].setImageName(imageName);  
            }
            if(typeStr.startsWith("x#")||typeStr.startsWith("X#")) {
                 problem[i].setIsJudge(false);
                 problem[i].setIsChoice(true);
                 String imageName = typeStr.substring(typeStr.indexOf("#")+1);
                 problem[i].setImageName(imageName);  
            }
        }
   }
}