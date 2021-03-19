import javax.swing.*;
import java.awt.event.ActionEvent;

public class FinalCalculator extends JFrame {

    JTextField txt_ttw;
    JTextField txt_finalWeight;
    JLabel lbl_numTerms;
    JTextField txt_gradeWanted;
    JTextField txt_oneG;
    JTextField txt_twoG;
    JTextField txt_threeG;
    JTextField txt_fourG;
    JTextField txt_fiveG;

    JLabel lbl_ttw;
    JComboBox<String> cb_numTerms;
    JLabel lbl_finalWeight;
    JLabel lbl_gradeWanted;
    JLabel lbl_oneG;
    JLabel lbl_twoG;
    JLabel lbl_threeG;
    JLabel lbl_fourG;
    JLabel lbl_fiveG;
    JLabel lbl_finalGrade;

    JButton calculate;
    JButton clear;

    public FinalCalculator(){
        super("Final Calculator");
        setSize(330,550);
        setLayout(null);
          txt_ttw=new JTextField();
          txt_finalWeight=new JTextField();
          lbl_numTerms=new JLabel();
          txt_gradeWanted=new JTextField();
          txt_oneG=new JTextField();
          txt_twoG=new JTextField();
          txt_threeG=new JTextField();
          txt_fourG=new JTextField();
          txt_fiveG=new JTextField();


        lbl_ttw=new JLabel("Total Term Weight");
        lbl_finalWeight=new JLabel("Final Weight");
        cb_numTerms=new JComboBox<String>();
        cb_numTerms.addItem("1");
        cb_numTerms.addItem("2");
        cb_numTerms.addItem("3");
        cb_numTerms.addItem("4");
        cb_numTerms.addItem("5");


        lbl_gradeWanted=new JLabel("Grade Wanted");
       lbl_oneG=new JLabel("Term 1 Grade");
        lbl_twoG=new JLabel("Term 2 Grade");
        lbl_threeG=new JLabel("Term 3 Grade");
        lbl_fourG=new JLabel("Term 4 Grade");
        lbl_fiveG=new JLabel("Term 5 Grade");
        lbl_finalGrade=new JLabel();



        txt_ttw.setBounds(150,20,100,20);
        txt_finalWeight.setBounds(150,60,100,20);
        cb_numTerms.setBounds(150,100,100,20);
        txt_gradeWanted.setBounds(150,140,100,20);
        txt_oneG.setBounds(150,180,100,20);
        txt_twoG.setBounds(150,220,100,20);
        txt_threeG.setBounds(150,260,100,20);
        txt_fourG.setBounds(150,300,100,20);
        txt_fiveG.setBounds(150,340,100,20);

        lbl_finalGrade.setBounds(35,440,200,50);
        lbl_ttw.setBounds(20,20,200,20);
        lbl_finalWeight.setBounds(20,60,200,20);
        lbl_numTerms.setBounds(20,100,200,20);
        lbl_gradeWanted.setBounds(20,140,200,20);
        lbl_oneG.setBounds(20,180,200,20);
        lbl_twoG.setBounds(20,220,200,20);
        lbl_threeG.setBounds(20,260,200,20);
        lbl_fourG.setBounds(20,300,200,20);
        lbl_fiveG.setBounds(20,340,200,20);


         /*       txt_ttw
        txt_finalWeight
        txt_numTerms
        txt_gradeWanted
        txt_oneG
        txt_twoG
        txt_threeG
        txt_fourG
        txt_fiveG */
         add(txt_ttw);
         add(txt_finalWeight);
         add(cb_numTerms);
         add(txt_gradeWanted);
         add(txt_oneG);
         add(txt_twoG);
         add(txt_threeG);
         add(txt_fourG);
         add(txt_fiveG);

         add(lbl_finalGrade);
        add(lbl_ttw);
        add(lbl_finalWeight);
        add(lbl_numTerms);
        add(lbl_gradeWanted);
        add(lbl_oneG);
        add(lbl_twoG);
        add(lbl_threeG);
        add(lbl_fourG);
        add(lbl_fiveG);
        cb_numTerms.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e){
                changeTerms();
            }
        });

        clear=new JButton("Clear");
        clear.setBounds(35,375,200,20);
        clear.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                clear();
            }
        });
        add(clear);
        calculate=new JButton("Calculate");
        calculate.setBounds(35,400,200,20);
        calculate.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
               calculateA();
            }
        });
        add(calculate);
        setVisible(true);
    }

    public void clear(){
        txt_ttw.setText("");
        txt_finalWeight.setText("");

        txt_gradeWanted.setText("");
        txt_oneG.setText("");
        txt_twoG.setText("");
        txt_threeG.setText("");
        txt_fourG.setText("");
        txt_fiveG.setText("");
        lbl_finalGrade.setText("");
    }
    public void calculateA(){
        double ttw=Double.valueOf(txt_ttw.getText());
        double finalW=Double.valueOf(txt_finalWeight.getText());
        double numberofTerms=Double.valueOf(cb_numTerms.getSelectedItem().toString());
        double gradeWanted=Double.valueOf(txt_gradeWanted.getText());

        double one=0;
        double two=0;
        double three=0;
        double  four=0;
        double five=0;

        if(numberofTerms>=1)
         one=Double.valueOf(txt_oneG.getText());
        if(numberofTerms>=2)
        two=Double.valueOf(txt_twoG.getText());
        if(numberofTerms>=3)
         three=Double.valueOf(txt_threeG.getText());
        if(numberofTerms>=4)
        four=Double.valueOf(txt_fourG.getText());
        if(numberofTerms==5)
         five=Double.valueOf(txt_fiveG.getText());

       double averageTerm=(one+two+three+four+five)/numberofTerms;

System.out.println(averageTerm+" "+gradeWanted+" "+finalW/100+" "+ttw/100);
        double finalTerms=averageTerm*(ttw/100);

        double amountOfPointsToGet=gradeWanted-finalTerms;


        System.out.println(averageTerm-gradeWanted);
        lbl_finalGrade.setText(""+100*((gradeWanted-(averageTerm*(ttw/100)))/finalW));

    }
    public void changeTerms() {
    String num=cb_numTerms.getSelectedItem().toString();
    Double n=Double.valueOf(num);
    if(n<5)
        txt_fiveG.setEditable(false);
    else
        txt_fiveG.setEditable(true);
    if(n<4)
        txt_fourG.setEditable(false);
    else
        txt_fourG.setEditable(true);
    if(n<3)
        txt_threeG.setEditable(false);
    else
        txt_threeG.setEditable(true);
    if(n<2)
        txt_twoG.setEditable(false);
    else
        txt_twoG.setEditable(true);

    }

    public static void main(String[]args){
        FinalCalculator c=new FinalCalculator();
    }

}
