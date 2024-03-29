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
public class RamdomInitTestPaper implements GiveTestPaper {   //将试题放入试卷（出卷）
   TestPaper testPaper ;//试卷
   File fileExcel;
   Problem [] problem; //组成试卷的一套题（problem的单元存放一道试题即一个Problem对象）
   InputStream in = null;
   Workbook wb = null;  //封装Excel,Workbook是jxl包中的类
   Sheet sheet = null;  //封装Excel中的sheet，Sheet是jxl包中的类
   LinkedList<Integer> list;     //随机抽取试题时用
   public RamdomInitTestPaper() {
      testPaper = new TestPaper();
      list = new LinkedList<Integer>();
   }
   public TestPaper getTestPaper(String excelFileName,int amount) {
      boolean b =setExcel(excelFileName);  //设置用户存放试题的电子表格
      if(b) {
        try {
           randomGiveProblem(amount);//随机给出amount道试题，见类后面的randomGiveProblem方法
        }
        catch(ArrayIndexOutOfBoundsException e){
           System.out.println("试题必须有类型，请检查题库");
           System.exit(0);
        }
        testPaper.setProblem(problem);//试卷上设置的一套试题是problem
        return testPaper;         //返回试卷
      }
      else {
        JOptionPane.showMessageDialog
        (null,"没有预备题库","消息对话框",JOptionPane.WARNING_MESSAGE);
        return null;
      }
   }
   private boolean setExcel(String excelFileName){ 
        boolean b =true;
        try { 
           fileExcel =new File(excelFileName);
           in =new FileInputStream(fileExcel);
           testPaper.setProblemSource(fileExcel.getAbsolutePath());//试卷设置题库来源
        }
        catch(IOException exp){
           JOptionPane.showMessageDialog
        (null,"没有预备题库Excel","消息对话框",JOptionPane.WARNING_MESSAGE);
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
   private void randomGiveProblem(int amount) { //随机给出amount道试题,放入problem数组中
        list.clear();
        if(wb==null) {
         JOptionPane.showMessageDialog
        (null,"没有预备题库Excel","消息对话框",JOptionPane.WARNING_MESSAGE);
           return ;
        }
        sheet = wb.getSheet(0);//得到Excel中的第一个sheet（索引从0开始）
        int rowsAmount = sheet.getRows();     //得到sheet的总行数
        //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
        int realAmount = Math.min(amount,rowsAmount-1);//实际抽取的试题数量
        problem = new Problem[realAmount];              //用于存放试题的数组problem
        for(int i=0;i<rowsAmount-1;i++){  //将1至rowsAmount-1数字放入链表
           list.add(i+1);
        }
        Random random=new Random();
        for(int i=0;i<problem.length;i++) { 
            int m = random.nextInt(list.size());//[0,list.size())中一个随机数
            int index =list.remove(m);//删除list的第m个节点，同时得节点数字
            Cell [] cell = sheet.getRow(index); //返回sheet中的第index行
            //注意原始Excel表中sheet中第0行不是试题，是用户输入的说明
            //cell的第0列是试题内容，索引从0开始
            problem[i] = new Problem(); 
            int number = i+1;
            problem[i].setContent("第"+number+"题."+cell[0].getContents());//试题的内容
            problem[i].setCorrectAnswer(cell[1].getContents().trim());//试题的答案
            problem[i].setGiveChoiceA(cell[2].getContents().trim());  //试题的A选择
            problem[i].setGiveChoiceB(cell[3].getContents().trim());  //试题的B选择 
            problem[i].setGiveChoiceC(cell[4].getContents().trim());  //试题的C选择
            problem[i].setGiveChoiceD(cell[5].getContents().trim());  //试题的D答案
            String typeStr = cell[6].getContents().trim();//试题的类型（判断或选择）
            //注意，因为试题有图像，所以typeStr有四种：p,p#,x,x#:
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