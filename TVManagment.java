import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TVManagment extends JFrame {

    // Panel-1:

    JPanel subscriberpanel;

    JTextField subName;
    JTextField subLastName;
    JTextField subMobile;
    JTextField subCity;

    JLabel nameLBL;
    JLabel lastLBL;
    JLabel mobileLBL;
    JLabel cityLBL;

    // Panel-2:

    JPanel cyclePanel;

    JTextField startCycleFLD;
    JTextField endCycleFLD;
    JTextField numberTVFLD;
    JLabel todayLBL;
    SimpleDateFormat df;
    Date currentDate;
    JLabel startCycleLBL;
    JLabel endCycleLBL;
    JLabel numberTVLBL;


    // Panel-3:
    JCheckBox sportsCHKBX;
    JCheckBox moviesCHKBX;
    JCheckBox docCHKBX;
    JPanel packagesPanel;





    public TVManagment(){

        // Panel-1:
         subscriberpanel = new JPanel();
         Border panel1Tile=BorderFactory.createTitledBorder("Subscriber Details");
         subscriberpanel.setBorder(panel1Tile);
         subscriberpanel.setBounds(15,15,300,200);
         subscriberpanel.setLayout(new GridLayout(4,2));

         nameLBL = new JLabel("First Name");
         lastLBL = new JLabel("Last Name");
         mobileLBL = new JLabel("Mobile Number");
         cityLBL = new JLabel("City Name");

         subName = new JTextField();
         subLastName = new JTextField();
         subMobile = new JTextField();
         subCity = new JTextField();

         subscriberpanel.add(nameLBL);
         subscriberpanel.add(subName);
         subscriberpanel.add(lastLBL);
         subscriberpanel.add(subLastName);
         subscriberpanel.add(mobileLBL);
         subscriberpanel.add(subMobile);
         subscriberpanel.add(cityLBL);
         subscriberpanel.add(subCity);


        // Panel-2:
        cyclePanel = new JPanel();
        cyclePanel.setBounds(15,230,300,500);
        cyclePanel.setLayout(new GridLayout(14,1));
        Border cycleBorder=BorderFactory.createTitledBorder("Cycle Details");
        cyclePanel.setBorder(cycleBorder);

        todayLBL = new JLabel();
        df = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = new Date();
        todayLBL.setText("Today: "+ df.format(currentDate));

        startCycleLBL = new JLabel("Start Cycle Date (DD/MM/YYYY) ");
        startCycleFLD = new JTextField();

        endCycleLBL = new JLabel("End Cycle Date (DD/MM/YYYY) ");
        endCycleFLD = new JTextField();

        numberTVLBL = new JLabel("Number of TV: ");
        numberTVFLD = new JTextField();

        cyclePanel.add(todayLBL);
        cyclePanel.add(startCycleLBL);
        cyclePanel.add(startCycleFLD);
        cyclePanel.add(endCycleLBL);
        cyclePanel.add(endCycleFLD);
        cyclePanel.add(numberTVLBL);
        cyclePanel.add(numberTVFLD);

        startCycleFLD.setOpaque(false);
        endCycleFLD.setOpaque(false);
        numberTVFLD.setOpaque(false);


        // Panel-3:
        packagesPanel = new JPanel();
        packagesPanel.setBounds(330,15,300,200);
        packagesPanel.setLayout(new GridLayout(5,1));

        Border packBorder = BorderFactory.createTitledBorder("Available Packages");
        packagesPanel.setBorder(packBorder);

        JLabel packagesLBL = new JLabel("Please select your Package");

        sportsCHKBX = new JCheckBox("Sports Package");
        moviesCHKBX = new JCheckBox("Movies Package");
        docCHKBX    = new JCheckBox("Documentary Package");

        JButton subscriberBTN = new JButton("Subscribe");

        packagesPanel.add(packagesLBL);
        packagesPanel.add(sportsCHKBX);
        packagesPanel.add(moviesCHKBX);
        packagesPanel.add(docCHKBX);
        packagesPanel.add(subscriberBTN);


        sportsCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(sportsCHKBX.isSelected()){
                    DisplaySportsChannels();
                }
                else{}
            }
        });

        moviesCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(moviesCHKBX.isSelected()){
                    DisplayMoviesChannels();
                }
                else {}
            }
        });

        docCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(docCHKBX.isSelected()){
                    DisplayDocumnetaryChannels();
                }
                else{

                }
            }
        });

        subscriberBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    GetSubscriberData();
                }catch (Exception ee){

                }
            }
        });



        // Creating Panels:
        setLayout(null);
        add(subscriberpanel);
        add(cyclePanel);
        add(packagesPanel);
    }

    private void GetSubscriberData() {
    }

    private void DisplayDocumnetaryChannels() {
    }

    private void DisplayMoviesChannels() {
    }

    private void DisplaySportsChannels() {
    }

    public static void main(String[] args) {
        TVManagment tv=new TVManagment();
        tv.setVisible(true);
        tv.setBounds(100,100,1000,800);


    }
}
