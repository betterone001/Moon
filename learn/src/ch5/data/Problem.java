package ch5.data;
public class Problem {
    boolean isChoice;            //�Ƿ���ѡ����
    boolean isJudge;            //�Ƿ����ж���
    String  content;           //��Ŀ����
    String  giveChoiceA,giveChoiceB,giveChoiceC,giveChoiceD;//�ṩѡ��
    String  imageName;         //��Ŀ������ͼ�������
    String  correctAnswer="ydfgkwdyggcks";  //��Ŀ����ȷ��
    //�û��ش�ĳ�ʼ�𰸺�correctAnswer��ͬ����ֹ���������Ǹ���ȷ��
    String  userAnswer =""  ; //��ʼֵ�����ǲ����κ��ַ��Ĵ�
    public boolean getIsChoice() {
       return isChoice;
    }
    public void setIsChoice(boolean b) {
       isChoice = b;
    }
    public boolean getIsJudge() {
       return isJudge;
    }
    public void setIsJudge(boolean b) {
       isJudge = b;
    }
    public void setContent(String c) {
       content = c;
    }
    public String getContent() {
       return content;
    }
    public void setCorrectAnswer(String a) {
       correctAnswer = a;
    }
    public String getCorrectAnswer() {
       return correctAnswer;
    }
    public void setUserAnswer(String u) {
       userAnswer = u;
    }
    public String getUserAnswer() {
       return userAnswer;
    }
    public void setGiveChoiceA(String a) {
       giveChoiceA = a;
    }
    public String getGiveChoiceA() {
       return giveChoiceA;
    }
    public void setGiveChoiceB(String b) {
       giveChoiceB = b;
    }
    public String getGiveChoiceB() {
       return giveChoiceB;
    }
    public void setGiveChoiceC(String c) {
       giveChoiceC = c;
    }
    public String getGiveChoiceC() {
       return giveChoiceC;
    }
    public void setGiveChoiceD(String d) {
       giveChoiceD = d;
    }
    public String getGiveChoiceD() {
       return giveChoiceD;
    }
    public void setImageName(String c) {
       imageName = c;
    }
    public String getImageName() {
       return imageName;
    }
}