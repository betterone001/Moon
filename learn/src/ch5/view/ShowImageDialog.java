package ch5.view;
import javax.swing.*;
import java.awt.*;

public class ShowImageDialog extends JDialog   { 
    Image img;
    ShowImageDialog(Image img) { //���췽��
       setTitle("��ʾͼ��Ի���");
       this.img = img;	
       setSize(500,470);
       GiveImage image = new GiveImage();
       add(image);
       setModal(true);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    class GiveImage extends JPanel  {  //�ڲ��࣬ר��Ϊ�öԻ����ṩͼƬ
       public void paintComponent(Graphics g ) {
          super.paintComponent(g);
          g.drawImage(img,0,0,getBounds().width,getBounds().height,this);
       }
    }
}