import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class shootgame extends JFrame {
    private Container cp;
    private  int count =0;

    private JLabel jlbBackground=new JLabel();  // background
    private JLabel jlbParachute_left=new JLabel(); // parachute_left
    private JLabel jlbParachute_right=new JLabel(); // parachute_right
    private JLabel jlbMissle1=new JLabel(); // missle
    private JLabel jlbMissle2=new JLabel(); // missle
    private JLabel jlbMissle3=new JLabel(); // missle
    private JLabel jlbMissle4=new JLabel(); // missle
    private JLabel jlbMissle5=new JLabel(); // missle
    private JLabel jlbboom=new JLabel();//boom
    private JLabel jlbscoreboard=new JLabel("記分板:");
    private JLabel jlbscore=new JLabel("0");
    private boolean flag=false;
    private Random rand = new Random();
    private int xi_m1;
    private int xi_m2;
    private int xi_m3;
    private int xi_m4;
    private int xi_m5;
    private int xi_p;
    private int center_xp=0, center_yp=0, center_xm1=0, center_ym1=0;
    private int center_xm2=0, center_ym2=0;
    private int center_xm3=0, center_ym3=0;
    private int center_xm4=0, center_ym4=0;
    private int center_xm5=0, center_ym5=0;

    private  Timer t1;
    private  Timer t2;

    // private ImageIcon Background=new ImageIcon("BlueSky.jpg");
    private ImageIcon Background=new ImageIcon("thunder.png");
    private ImageIcon imgparachute1=new ImageIcon("parachute_left.png");
    private ImageIcon imgparachute2=new ImageIcon("parachute_right.png");
    private ImageIcon missle1=new ImageIcon("missle.png");
    private ImageIcon missle2=new ImageIcon("missle.png");
    private ImageIcon missle3=new ImageIcon("missle.png");
    private ImageIcon missle4=new ImageIcon("missle.png");
    private ImageIcon missle5=new ImageIcon("missle.png");
    private ImageIcon boom=new ImageIcon("boom.png");

    private int targetX,targetY;
    private int origX,origY;
    private boolean isobselect=false;

    public shootgame() {
        super("跳傘");
        setSize(800,1000);
        Container con=getContentPane();
        con.setLayout(new BorderLayout());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setBounds(300,0,1000,800);
        cp=this.getContentPane();
        cp.setLayout(null);

        Image img1=imgparachute1.getImage();
        Image img11=img1.getScaledInstance(120,180,Image.SCALE_AREA_AVERAGING);
        imgparachute1.setImage(img11);
        jlbParachute_left.setIcon(imgparachute1);
        jlbParachute_left.setBounds(550,-200,269,187);
        cp.add(jlbParachute_left);

        Image img2=imgparachute2.getImage();
        Image img22=img2.getScaledInstance(120,180,Image.SCALE_AREA_AVERAGING);
        imgparachute2.setImage(img22);
        jlbParachute_right.setIcon(imgparachute2);

        Image img3=missle1.getImage();
        Image img33=img3.getScaledInstance(15,60,Image.SCALE_AREA_AVERAGING);
        jlbMissle1.setIcon(missle1);
        missle1.setImage(img33);
        jlbMissle1.setBounds(350,800,30,60);
        cp.add(jlbMissle1);

        Image img5=missle2.getImage();
        Image img55=img5.getScaledInstance(15,60,Image.SCALE_AREA_AVERAGING);
        jlbMissle2.setIcon(missle2);
        missle2.setImage(img55);
        jlbMissle2.setBounds(350,800,30,60);
        cp.add(jlbMissle2);

        Image img6=missle3.getImage();
        Image img66=img6.getScaledInstance(15,60,Image.SCALE_AREA_AVERAGING);
        jlbMissle3.setIcon(missle3);
        missle3.setImage(img66);
        jlbMissle3.setBounds(350,800,30,60);
        cp.add(jlbMissle3);

        Image img7=missle4.getImage();
        Image img77=img7.getScaledInstance(15,60,Image.SCALE_AREA_AVERAGING);
        jlbMissle4.setIcon(missle4);
        missle4.setImage(img77);
        jlbMissle4.setBounds(350,800,30,60);
        cp.add(jlbMissle4);

        Image img8=missle5.getImage();
        Image img88=img8.getScaledInstance(15,60,Image.SCALE_AREA_AVERAGING);
        jlbMissle5.setIcon(missle5);
        missle5.setImage(img88);
        jlbMissle5.setBounds(350,800,30,60);
        cp.add(jlbMissle5);

        Image img4=boom.getImage();
        Image img44=img4.getScaledInstance(250,250,Image.SCALE_AREA_AVERAGING);
        jlbboom.setIcon(boom);
        boom.setImage(img44);
        cp.add(jlbboom);

        Image imgBack=Background.getImage();
        Image imgBack1=imgBack.getScaledInstance(1000,800,Image.SCALE_AREA_AVERAGING);
        jlbBackground.setIcon(Background);
        Background.setImage(imgBack1);
        jlbBackground.setBounds(0,0,1000,800);

        jlbscoreboard.setBounds(800,20,200,100);
        jlbscoreboard.setFont(new Font("標楷體", Font.BOLD, 25));
        jlbscoreboard.setForeground(Color.white);
        jlbscore.setBounds(900,20,200,100);
        jlbscore.setFont(new Font("標楷體", Font.BOLD, 25));
        jlbscore.setForeground(Color.white);
        cp.add(jlbscoreboard);
        cp.add(jlbscore);
        cp.add(jlbBackground);
        t1=new Timer(50, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jlbMissle1.setLocation(xi_m1,jlbMissle1.getY()-5);
                jlbMissle2.setLocation(xi_m2,jlbMissle2.getY()-5);
                jlbMissle3.setLocation(xi_m3,jlbMissle3.getY()-5);
                jlbMissle4.setLocation(xi_m4,jlbMissle4.getY()-5);
                jlbMissle5.setLocation(xi_m5,jlbMissle5.getY()-5);
                if (jlbMissle1.getY()<-60||jlbMissle2.getY()<-60||jlbMissle3.getY()<-60||jlbMissle4.getY()<-60||jlbMissle5.getY()<-60&&jlbParachute_left.getY()>800){
                    t1.stop();
                    flag=true;
                }
            }
        });
        t2=new Timer(50, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jlbParachute_left.setLocation(jlbParachute_left.getX(),jlbParachute_left.getY()+5);
                center_xp=jlbParachute_left.getX() + jlbParachute_left.getWidth()/2;
                center_yp=jlbParachute_left.getY() + jlbParachute_left.getHeight()/2;
                center_xm1=jlbMissle1.getX()+jlbMissle1.getWidth()/2;
                center_ym1=jlbMissle1.getY()+jlbMissle1.getHeight()/2;
                center_xm2=jlbMissle2.getX()+jlbMissle2.getWidth()/2;
                center_ym2=jlbMissle2.getY()+jlbMissle2.getHeight()/2;
                center_xm3=jlbMissle3.getX()+jlbMissle3.getWidth()/2;
                center_ym3=jlbMissle3.getY()+jlbMissle3.getHeight()/2;
                center_xm4=jlbMissle4.getX()+jlbMissle4.getWidth()/2;
                center_ym4=jlbMissle4.getY()+jlbMissle4.getHeight()/2;
                center_xm5=jlbMissle5.getX()+jlbMissle5.getWidth()/2;
                center_ym5=jlbMissle5.getY()+jlbMissle5.getHeight()/2;
                // System.out.println("p_x= "+center_xp+"   ,p_y "+center_yp);
                // System.out.println("m_x= "+center_xm+"   ,m_y "+ center_ym);
                flag=Impact(center_xp, center_yp, center_xm1, center_ym1);
                flag=Impact(center_xp, center_yp, center_xm2, center_ym2);
                flag=Impact(center_xp, center_yp, center_xm3, center_ym3);
                flag=Impact(center_xp, center_yp, center_xm4, center_ym4);
                flag=Impact(center_xp, center_yp, center_xm5, center_ym5);
                if (flag ){
                    jlbboom.setIcon(boom);
                    jlbboom.setBounds(center_xm1-130,center_yp-90,300,250);
                    jlbboom.setBounds(center_xm2-130,center_yp-90,300,250);
                    jlbboom.setBounds(center_xm3-130,center_yp-90,300,250);
                    jlbboom.setBounds(center_xm4-130,center_yp-90,300,250);
                    jlbboom.setBounds(center_xm5-130,center_yp-90,300,250);
                    jlbParachute_left.setBounds(350,-200,269,187);
                    jlbParachute_right.setBounds(350,-200,269,187);
                    jlbMissle1.setBounds(350,-200,269,187);
                    jlbMissle2.setBounds(350,-200,269,187);
                    jlbMissle3.setBounds(350,-200,269,187);
                    jlbMissle4.setBounds(350,-200,269,187);
                    jlbMissle5.setBounds(350,-200,269,187);
                    t1.stop();
                    t2.stop();
                    count=0;
                    jlbscore.setText("0");
                    JOptionPane.showMessageDialog(null,"You are dead!  遊戲結束");
                }

                if (jlbParachute_left.getY()>800&&jlbMissle1.getY()<-60&&jlbMissle2.getY()<-60&&jlbMissle3.getY()<-60&&jlbMissle4.getY()<-60&&jlbMissle5.getY()<-60){
                    t2.stop();
                    flag=true;
                    count=count+20;
                    jlbscore.setText(Integer.toString(count));
                }
                if (count>=100){
                    JOptionPane.showMessageDialog(null," Timeout !  遊戲結束");
                    t1.stop();
                    t2.stop();
                    flag=false;
                }
            }
        });

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println(e.getExtendedKeyCode());
                switch (e.getKeyCode()){
                    case 37:  // left
                        jlbParachute_left.setIcon(imgparachute1);
                        jlbParachute_left.setLocation(jlbParachute_left.getX()-5,jlbParachute_left.getY());
                        break;
                    case 39: // right
                        jlbParachute_left.setIcon(imgparachute2);
                        jlbParachute_left.setLocation(jlbParachute_left.getX()+5,jlbParachute_left.getY());
                        break;
                    case 90: //  z
                        t1.start();
                        t2.start();
                        xi_m1=rand.nextInt(900)+1;
                        xi_m2=rand.nextInt(900)+1;
                        xi_m3=rand.nextInt(900)+1;
                        xi_m4=rand.nextInt(900)+1;
                        xi_m5=rand.nextInt(900)+1;
                        xi_p=rand.nextInt(900)+1;
                        if (flag){
                            jlbMissle1.setBounds(xi_m1,800,30,60);
                            jlbMissle2.setBounds(xi_m2,800,30,60);
                            jlbMissle3.setBounds(xi_m3,800,30,60);
                            jlbMissle4.setBounds(xi_m4,800,30,60);
                            jlbMissle5.setBounds(xi_m5,800,30,60);
                            jlbParachute_left.setBounds(xi_p,-200,269,187);
                            flag=false;
                        }
                        break;
                    case 82: // r
                        jlbscore.setText("0");
                        jlbParachute_left.setBounds(350,-200,269,187);
                        jlbMissle1.setBounds(350,800,30,60);
                        jlbMissle2.setBounds(350,800,30,60);
                        jlbMissle3.setBounds(350,800,30,60);
                        jlbMissle4.setBounds(350,800,30,60);
                        jlbMissle5.setBounds(350,800,30,60);
                        jlbboom.setIcon(null);
                        count=0;
                        t1.stop();
                        t2.stop();
                }

            }
        });

    }

    static boolean Impact(int xp,int yp, int xm, int ym){
        boolean NoneImpactFlag=false;
        long dist=1000000;

        dist=(xp-xm)*(xp-xm)+(yp-ym)*(yp-ym);

        if (dist <= 13000)NoneImpactFlag=true;

        System.out.println("dist="+dist+",NoneImpactFlag="+NoneImpactFlag);
        return NoneImpactFlag;
    } // End of Impact()
} // End of class ShootGame()