package ch5.data;
public class TestPaper {  //试卷
    private Problem [] problem=null;  //数组每个单元存放一道试题（一个Problem对象）
    int index = -1;
    String  problemSource ;           //试卷的题库；
    public void setProblem(Problem [] problem){
        this.problem = problem;
    }
    public  Problem  getProblem(int i) {
       if(problem == null||problem.length==0||i>=problem.length||i<0) {
          return null;
       }

       return problem[i];
    } 
    public  Problem  nextProblem() {
       index++;
       if(problem == null||problem.length==0) {
          return null;
       }

       if(index==problem.length) {
           index = problem.length-1;//到最后一个题目，停止
       }
       return problem[index];
    }
    public  Problem  previousProblem() {
       index--;
       if(problem == null||problem.length==0) {
          return null;
       }

       if(index<0) {
           index =0;//到第一个题目，停止
       }
       return problem[index];
    }
    public  Problem [] getAllProblem(){
       if(problem == null||problem.length==0) {
          return null;
       }

       return problem;
    }
    public int getProlemAmount(){
       if(problem == null) {
          return 0;
       }
       return problem.length;
    }
    public void setProblemSource(String source){
       problemSource = source;   
    }
    public String getProblemSource(){
       return problemSource;
    }
    public void acceptTeacher(Teacher teacher) { //让老师来批卷（访问者模式）
       teacher.giveTestPaparScore(this);  //teacher批卷
    }
}
