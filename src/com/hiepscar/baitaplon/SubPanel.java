package com.hiepscar.baitaplon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.*;

/**
 * Created by Hoang Hiep on 11/28/2016.
 */
public class SubPanel extends JPanel implements ActionListener {
    private ItemManager itemManager = new ItemManager();
    private ArrayList<Item> list = itemManager.getItemList();
    private ArrayList<JButton> buttons= new ArrayList<JButton>();
    private VMPanel vmPanel;
    private int amount = 0;
    private String bag = "";
    private ArrayList<Item> bagList;
    private File file = new File("D:/item.txt");
    private int checkQuantity=-1;

    public SubPanel() {
        initPanel();
        initComps();
    }

    public void setVMPanel(VMPanel vmPanel) {
        this.vmPanel = vmPanel;
    }

    private void initComps() {

        for (int i = 0; i < list.size(); i++) {
            JButton button = new JButton();
            button.setText(list.get(i).getName() + "-" + list.get(i).getPrice() + "đ");
            button.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/resource/" + list.get(i).getName() + ".png")).getImage()));
            button.setForeground(Color.white);
            button.setHorizontalAlignment(SwingConstants.LEFT);
            button.setContentAreaFilled(false);
            button.addActionListener(this);
            button.setActionCommand(i + "");
            buttons.add(button);
        }
        addButton();
    }

    private void initPanel() {
        setLayout(new GridLayout(4, 2, 5, 5));
        setSize(440, 330);
        setLocation(220, 130);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bagList=list;
        for (int i = 0; i < 8; i++) {
            if (Integer.parseInt(e.getActionCommand()) == i) {
                String s = showInputDialog(this, "Bạn muốn mua bao nhiêu?");
                System.out.println(s);
                try {
                    if (Integer.parseInt(s) > 0) {
                        bag += list.get(i).getName() + " x " + Integer.parseInt(s) + "\r\n";
                        amount += list.get(i).getPrice() * Integer.parseInt(s);
                        System.out.println(bag);
                        System.out.println(amount);
                        vmPanel.setTextBag(bag, amount);
                        checkQuantity=bagList.get(i).setQuantity(Integer.parseInt(s));
                    }else if (s.equals(null)){
                        System.out.println("asdasdadasdasdasd");

                    } else {
                        showMessageDialog(this, "Bạn nhập sai rồi");
                    }
                } catch (NumberFormatException e1) {
                    showMessageDialog(this, "Bạn nhập sai rồi");
                }
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        drawBackGround(g2d);
    }

    private void drawBackGround(Graphics2D g2d) {
        Image bg = new ImageIcon(getClass().getResource("/resource/bg1.jpg")).getImage();
        g2d.drawImage(bg, 0, 0, 450, 350, null);
    }

    public void resetAmount() {
        amount = 0;
    }

    public void resetBag() {
        bag = "";
    }
    public void resetBagList(){
        bagList.clear();
    }

    public boolean saveToFile() {
        list=bagList;
            try {
                String s="";
                for (int i=0;i< list.size();i++){
                    s+=list.get(i).toString()+"\r\n";
                }
                FileOutputStream out=new FileOutputStream(file);
                out.write(s.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        return true;
    }

    public int isCheckQuantity() {
        return checkQuantity;
    }
    public void addButton(){
        for (int i=0;i<buttons.size();i++){
            add(buttons.get(i));
        }
    }
    public void deleteButton(){
        for(int i=0;i<bagList.size();i++){
            if(bagList.get(i).getQuantity()==0){
                buttons.get(i).setVisible(false);
            }

        }
    }

    public ArrayList<Item> getBagList() {
        return bagList;
    }
}
