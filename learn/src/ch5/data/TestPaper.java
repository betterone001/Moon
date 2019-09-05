package ch5.data;
public class TestPaper {  //�Ծ�
    private Problem [] problem=null;  //����ÿ����Ԫ���һ�����⣨һ��Problem����
    int index = -1;
    String  problemSource ;           //�Ծ����⣻
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
           index = problem.length-1;//�����һ����Ŀ��ֹͣ
       }
       return problem[index];
    }
    public  Problem  previousProblem() {
       index--;
       if(problem == null||problem.length==0) {
          return null;
       }

       if(index<0) {
           index =0;//����һ����Ŀ��ֹͣ
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
    public void acceptTeacher(Teacher teacher) { //����ʦ������������ģʽ��
       teacher.giveTestPaparScore(this);  //teacher����
    }
}
