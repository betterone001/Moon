package ch5.view;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
public class IntegrationView extends JFrame{
    JTabbedPane tabbedPane; //��ѡ�����TestPaperView��ͼ
    public IntegrationView(){
        tabbedPane= new JTabbedPane(JTabbedPane.LEFT);//������� 
        tabbedPane.validate();
        add(tabbedPane,BorderLayout.CENTER); 
        setBounds(100,100,1200,560);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public void addTestPaperView(String cardName,TestPaperView view){
       tabbedPane.add(cardName,view);
       validate();
    }
}