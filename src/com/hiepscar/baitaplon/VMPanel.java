package com.hiepscar.baitaplon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by hoanghiep on 11/9/16.
 */
public class VMPanel extends JPanel implements ActionListener {
    private JLabel lbTitle, lbInsert, lbChange1, lbBalance, lbBag;
    private JButton btPurchase, btInsert,btReset;
    private JComboBox comboBoxInsert;
    private int balance = 0;
    private int amount=0;
    private JTextPane jTextPane;
    private SubPanel subPanel;
    public void setSubPanel(SubPanel subPanel){
        this.subPanel=subPanel;
    }

    public VMPanel() {
        initPanel();
        initComps();
        addComps();
    }
    private String bag="";

    private void initPanel() {
        setLayout(null);
        setSize(800,500);
        setLocation(0,0);
    }
    private void initComps() {
        lbTitle = new JLabel("Vending Machine");
        lbTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbTitle.setLocation(220, 10);
        lbTitle.setSize(180, 30);
        lbTitle.setForeground(Color.WHITE);

        lbInsert = new JLabel("Insert Money");
        lbInsert.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbInsert.setLocation(lbTitle.getX(), lbTitle.getY() + 30);
        lbInsert.setSize(120, 20);
        lbInsert.setForeground(Color.WHITE);

        String[] money = new String[]{"5000", "10000", "20000"};
        comboBoxInsert = new JComboBox(money);
        comboBoxInsert.setLocation(lbInsert.getX() + 130, lbInsert.getY());
        comboBoxInsert.setSize(150, 20);

        btInsert = new JButton();
        btInsert.setLocation(comboBoxInsert.getX() + 160, 15);
        btInsert.setSize(45, 100);
        btInsert.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/bt_in.png")).getImage()));
        btInsert.setActionCommand("INSERT");
        btInsert.addActionListener(this);

        lbChange1 = new JLabel("Balance");
        lbChange1.setFont(new Font("Tahoma", Font.BOLD, 15));
        lbChange1.setLocation(lbInsert.getX(), lbInsert.getY() + 30);
        lbChange1.setSize(120, 20);
        lbChange1.setForeground(Color.WHITE);

        lbBag = new JLabel("BAG");
        lbBag.setFont(new Font("Tahoma", Font.BOLD, 30));
        lbBag.setLocation(660, lbTitle.getY());
        lbBag.setSize(140, 30);
        lbBag.setHorizontalAlignment(SwingConstants.LEFT);
        lbBag.setForeground(Color.WHITE);

        jTextPane=new JTextPane();
        jTextPane.setSize(140,350);
        jTextPane.setLocation(660,50);
        jTextPane.setFocusable(false);
        jTextPane.setForeground(Color.white);
        jTextPane.setBackground(new Color(35,36,41));


        lbBalance = new JLabel();
        lbBalance.setLocation(comboBoxInsert.getX(), lbChange1.getY());
        lbBalance.setBackground(Color.WHITE);
        lbBalance.setOpaque(true);
        lbBalance.setSize(150, 20);

        btPurchase=new JButton("Purchase");
        btPurchase.setSize(140,30);
        btPurchase.setLocation(660,410);
        btPurchase.setActionCommand("PURCHASE");
        btPurchase.setBackground(Color.gray);
        btPurchase.setForeground(Color.white);
        btPurchase.addActionListener(this);

        btReset=new JButton("Reset");
        btReset.setSize(140,30);
        btReset.setLocation(660,440);
        btReset.setBackground(Color.gray);
        btReset.setForeground(Color.white);
        btReset.setActionCommand("RESET");
        btReset.addActionListener(this);
    }

    private void addComps() {
        add(lbTitle);
        add(lbInsert);
        add(btInsert);
        add(lbBag);
        add(comboBoxInsert);
        add(lbChange1);
        add(lbBalance);
        add(jTextPane);
        add(btPurchase);
        add(btReset);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        drawBackGround(g2d);
    }

    private void drawBackGround(Graphics2D g2d) {
        Image bg = new ImageIcon(getClass().getResource("/resource/bg.jpg")).getImage();
        g2d.drawImage(bg, 0, 0, GUI.WIDTH_FRAME, GUI.HEIGHT_FRAME, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "INSERT":
                balance += Integer.parseInt(comboBoxInsert.getSelectedItem().toString());
                lbBalance.setText(balance + "");
                break;
            case "RESET":
                JOptionPane.showMessageDialog(this,"Số tiền thừa của bạn là "+balance+" đ");
                lbBalance.setText("");
                subPanel.resetAmount();
                subPanel.resetBag();
                bag="";
                balance=0;
                amount=0;
                jTextPane.setText("");
                break;
            case "PURCHASE":
                doPurchase();
                break;
            default:
                break;
        }
    }

    private void doPurchase() {
        if(subPanel.isCheckQuantity()==0||subPanel.isCheckQuantity()==1){
            if(balance==0){
                JOptionPane.showMessageDialog(this,"Bạn vui lòng cho tiền vào máy");
            }else if (balance<amount){
                JOptionPane.showMessageDialog(this,"Số tiền của bạn không đủ vui lòng cho thêm ");
            }else if(balance>amount){
                JOptionPane.showMessageDialog(this,"Bạn đã mua hàng thành công. Hàng của bạn gồm:\n"+bag+"Và bạn còn thừa "+(balance-amount)+"đ");
                subPanel.saveToFile();
                subPanel.resetAmount();
                subPanel.resetBag();
                bag="";
                amount=0;
                jTextPane.setText("");
                balance=0;
                lbBalance.setText("");
                subPanel.deleteButton();
            }
        }else if(subPanel.getBagList()==null){
            JOptionPane.showMessageDialog(this,"Mời bạn chọn hàng");

        } else if(subPanel.isCheckQuantity()==2){
            JOptionPane.showMessageDialog(this,"Không đủ hàng");
        }
    }

    public void setTextBag(String s,int amount) {
        bag=s;
        this.amount=amount;
        jTextPane.setText(s);
    }

}
