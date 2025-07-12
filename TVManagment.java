import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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


    // Panel-3: Channels packages
    JCheckBox sportsCHKBX;
    JCheckBox moviesCHKBX;
    JCheckBox docCHKBX;
    JPanel packagesPanel;


    // Panel-4: Package Details
    JTextArea channelsAreaS;
    JTextArea channelsAreaM;
    JTextArea channelsAreaD;
    JPanel detailsPanel;


    // Panel-5: Check and Payments
    JLabel installFeeLBL;
    JLabel packageFeeLBL;
    JLabel totalFeeLBL;
    JPanel feePanel;

    // Panel-6: Table (Data of Subscription)
    JTable table;
    DefaultTableModel tableModel;
    JPanel p6panel;


    // Panel-7: Action Panel
    JButton saveBTN;
    JButton loadBTN;
    JButton newBTN;
    JPanel p7ActionPanel;


    // Classes and Objects
    Subscriber subscriber;
    Subscription subscription;
    int packagesSelectedPrice=0;
    int totalPrice=0;


    // Saving
    ArrayList<Subscription> listToSave = new ArrayList<>();
    File file;




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
                else{channelsAreaS.setText("");}
            }
        });

        moviesCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(moviesCHKBX.isSelected()){
                    DisplayMoviesChannels();
                }
                else {channelsAreaM.setText("");}
            }
        });

        docCHKBX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if(docCHKBX.isSelected()){
                    DisplayDocumentaryChannels();
                }
                else{
                    channelsAreaD.setText("");
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

        //Panel-4:
        detailsPanel = new JPanel();
        detailsPanel.setBounds(330,230,300,500);
        detailsPanel.setLayout(new GridLayout(3,1));

        Border p4Border = BorderFactory.createTitledBorder("Available Channels");
        detailsPanel.setBorder(p4Border);

        channelsAreaS = new JTextArea(5,1);
        channelsAreaS.setEditable(false);
        channelsAreaS.setOpaque(false);
        channelsAreaS.setLineWrap(true);

        channelsAreaM = new JTextArea(5,1);
        channelsAreaM.setEditable(false);
        channelsAreaM.setOpaque(false);
        channelsAreaM.setLineWrap(true);

        channelsAreaD = new JTextArea(5,1);
        channelsAreaD.setEditable(false);
        channelsAreaD.setOpaque(false);
        channelsAreaD.setLineWrap(true);

        detailsPanel.add(channelsAreaS);
        detailsPanel.add(channelsAreaM);
        detailsPanel.add(channelsAreaD);


        // Panel:5
        feePanel = new JPanel();
        feePanel.setBounds(645,15,200,200);
        feePanel.setLayout(new GridLayout(3,1));

        Border blackline5 = BorderFactory.createTitledBorder("Fee and Check");
        feePanel.setBorder(blackline5);

        installFeeLBL = new JLabel("Installation Fee:");
        packageFeeLBL = new JLabel("Package Fee:");
        totalFeeLBL = new JLabel("Total Amount to pay:");

        feePanel.add(installFeeLBL);
        feePanel.add(packageFeeLBL);
        feePanel.add(totalFeeLBL);



        // Panel-6:
        p6panel = new JPanel();
        p6panel.setBounds(654,230,515,500);
        p6panel.setLayout(new GridLayout(3,1));

        Border border6= BorderFactory.createTitledBorder("Our Customers");
        p6panel.setBorder(border6);

        // Table:
        // 1- table model
        tableModel = new DefaultTableModel();

        // 2- Columns
        table = new JTable(tableModel);
        tableModel.addColumn("First Name");
        tableModel.addColumn("Last Name");
        tableModel.addColumn("Phone Number");
        tableModel.addColumn("Start Cycle");
        tableModel.addColumn("End Cycle");
        tableModel.addColumn("Total Fee");

        // 3- Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);
        p6panel.add(scrollPane);



        // Panel-7:
        p7ActionPanel = new JPanel();
        p7ActionPanel.setBounds(860,15,300,200);
        p7ActionPanel.setLayout(new GridLayout(4,1));

        Border border7 = BorderFactory.createTitledBorder("Action Tab");
        p7ActionPanel.setBorder(border7);

        saveBTN = new JButton("Save Subscription");
        loadBTN = new JButton("Load Subscription");
        newBTN = new JButton("New Subscription");

        p7ActionPanel.add(newBTN);
        p7ActionPanel.add(saveBTN);
        p7ActionPanel.add(loadBTN);

        saveBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SaveSubscriptionToDisk();
            }
        });

        newBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NewSubscription();
            }
        });

        loadBTN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Subscription> k= LoadDataFromDisk();
            }
        });



        // Creating Panels:
        setLayout(null);
        add(subscriberpanel);
        add(cyclePanel);
        add(packagesPanel);
        add(detailsPanel);
        add(feePanel);
        add(p6panel);
        add(p7ActionPanel);
    }

    private ArrayList<Subscription> LoadDataFromDisk() {

        ArrayList<Subscription> s = new ArrayList<>();
        file = new File("D:\\Java\\codec.txt");

        try{
            InputStream is = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(is);
            s = (ArrayList) ois.readObject();
            ois.close();
            is.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        for(Subscription sub: s)
        {
            DisplaySubscriptionInTable(sub);
        }
        return s;
    }

    private void DisplaySubscriptionInTable(Subscription sub) {

        //Displaying Data from disk into table
        tableModel.addRow(new Object[]{
                sub.getSubscriber().getfName(),
                sub.getSubscriber().getlName(),
                sub.getSubscriber().getPhone(),
                sub.getCycle().getStartDate(),
                sub.getCycle().getEndDate(),
                sub.getTotalFee()
        });
    }

    private void NewSubscription() {

        subName.setText("");
        subLastName.setText("");
        subCity.setText("");
        subMobile.setText("");

        startCycleFLD.setText("");
        endCycleFLD.setText("");
        numberTVFLD.setText("");

        installFeeLBL.setText("Installation Fee: ");
        packageFeeLBL.setText("Packages Fee: ");
        totalFeeLBL.setText("Total Amount to Pay");

        moviesCHKBX.setSelected(false);
        docCHKBX.setSelected(false);
        sportsCHKBX.setSelected(false);
    }

    private void SaveSubscriptionToDisk() {

        listToSave.add(subscription);
        file = new File("D:\\Java\\codec.txt");

        try{
            OutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);

            // Saving the list of Subscription
            oos.writeObject(listToSave);
            oos.flush();
            oos.close();
            os.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void GetSubscriberData() throws ParseException {
        Date currentDate = new Date();

        subscriber = new Subscriber(
                subName.getText(),
                subLastName.getText(),
                subCity.getText(),
                Integer.parseInt(subMobile.getText())
        );

        Date startCycle = df.parse(startCycleFLD.getText());
        Date endCycle   = df.parse(endCycleFLD.getText());

        SubscriptionCycle cycle = new SubscriptionCycle(
                df.format(startCycle),
                df.format(endCycle)
        );

        subscription = new Subscription(
                Integer.parseInt(numberTVFLD.getText()),
                subscriber,
                cycle,
                df.format(currentDate)
        );

        installFeeLBL.setText("Installation Fee: " + subscription.getTotalFee() + " $");

        ShowPrice();
    }

    private void ShowPrice() {

        if(docCHKBX.isSelected())
            packagesSelectedPrice += DisplayDocumentaryChannels();

        else if (moviesCHKBX.isSelected())
            packagesSelectedPrice += DisplayMoviesChannels();
        else if (sportsCHKBX.isSelected())
            packagesSelectedPrice += DisplaySportsChannels();


        packageFeeLBL.setText("Packages Fee: " + packagesSelectedPrice + " $");
        totalPrice = subscription.getTotalFee() + packagesSelectedPrice;

        totalFeeLBL.setText("Total Amount to Pay: "+totalPrice+" $");

    }

    private int DisplayDocumentaryChannels() {

        DocumentaryChannel m1 = new DocumentaryChannel("NAT GEO1","SP1","Heavy1",25001);
        DocumentaryChannel m2 = new DocumentaryChannel("NAT GEO2","SP2","Heavy2",25002);
        DocumentaryChannel m3 = new DocumentaryChannel("NAT GEO3","SP3","Heavy3",25003);
        DocumentaryChannel m4 = new DocumentaryChannel("NAT GEO4","SP4","Heavy4",25004);
        DocumentaryChannel m5 = new DocumentaryChannel("NAT GEO5","SP5","Heavy5",25005);
        DocumentaryChannel m6 = new DocumentaryChannel("NAT GEO6","SP6","Heavy6",25006);

        ArrayList<DocumentaryChannel> documentaryChannels = new ArrayList<>();
        documentaryChannels.add(m1);
        documentaryChannels.add(m2);
        documentaryChannels.add(m3);
        documentaryChannels.add(m4);
        documentaryChannels.add(m5);
        documentaryChannels.add(m6);


        String docChannelString = "";
        int packagePrice=0;

        for(int i=0;i < documentaryChannels.size();i++)
        {
            docChannelString +=
                    "      "+ documentaryChannels.get(i).getChannelName()
                    + "                      "+ documentaryChannels.get(i).getLanguage()
                    + "                      "+ documentaryChannels.get(i).getPrice()
                    + "\n";
            packagePrice += documentaryChannels.get(i).getPrice();
        }
        channelsAreaD.setText(docChannelString); return packagePrice;
    }

    private int DisplayMoviesChannels() {
        MovieChannel m6 = new MovieChannel("NAT GEO6","SP6","Heavy6",25006);
        MovieChannel m1 = new MovieChannel("NAT GEO1","SP1","Heavy1",25001);
        MovieChannel m2 = new MovieChannel("NAT GEO2","SP2","Heavy2",25002);
        MovieChannel m3 = new MovieChannel("NAT GEO3","SP3","Heavy3",25003);
        MovieChannel m4 = new MovieChannel("NAT GEO4","SP4","Heavy4",25004);
        MovieChannel m5 = new MovieChannel("NAT GEO5","SP5","Heavy5",25005);

        ArrayList<MovieChannel> movieList = new ArrayList<>();
        movieList.add(m1);
        movieList.add(m2);
        movieList.add(m3);
        movieList.add(m4);
        movieList.add(m5);
        movieList.add(m6);


        String movieChannelString = "";
        int packagePrice=0;

        for(int i=0;i < movieList.size();i++)
        {
            movieChannelString +=
                    "      "+ movieList.get(i).getChannelName()
                            + "                      "+ movieList.get(i).getLanguage()
                            + "                      "+ movieList.get(i).getPrice()
                            + "\n";
            packagePrice += movieList.get(i).getPrice();
        }
        channelsAreaM.setText(movieChannelString); return packagePrice;

    }

    private int DisplaySportsChannels() {
        SportChannel m1 = new SportChannel("NAT GEO1","SP1","Heavy1",25001);
        SportChannel m2 = new SportChannel("NAT GEO2","SP2","Heavy2",25002);
        SportChannel m3 = new SportChannel("NAT GEO3","SP3","Heavy3",25003);
        SportChannel m4 = new SportChannel("NAT GEO4","SP4","Heavy4",25004);
        SportChannel m5 = new SportChannel("NAT GEO5","SP5","Heavy5",25005);
        SportChannel m6 = new SportChannel("NAT GEO6","SP6","Heavy6",25006);

        ArrayList<SportChannel> sportList = new ArrayList<>();
        sportList.add(m1);
        sportList.add(m2);
        sportList.add(m3);
        sportList.add(m4);
        sportList.add(m5);
        sportList.add(m6);


        String sportChannelString = "";
        int packagePrice = 0;

        for(int i=0;i < sportList.size();i++)
        {
            sportChannelString +=
                    "      "+ sportList.get(i).getChannelName()
                            + "                      "+ sportList.get(i).getLanguage()
                            + "                      "+ sportList.get(i).getPrice()
                            + "\n";
            packagePrice += sportList.get(i).getPrice();
        }
        channelsAreaS.setText(sportChannelString); return packagePrice;
    }

    public static void main(String[] args) {
        TVManagment tv=new TVManagment();
        tv.setVisible(true);
        tv.setBounds(20,10,1200,800);


    }
}
