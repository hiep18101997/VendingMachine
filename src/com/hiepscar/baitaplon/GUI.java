package com.hiepscar.baitaplon;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by hoanghiep on 11/9/16.
 */
public class GUI extends JFrame {
    public static final int WIDTH_FRAME = 800;
    public static final int HEIGHT_FRAME = 500;

    public GUI() {
        initGUI();
        initComps();
    }

    private void initComps() {
        VMPanel panel = new VMPanel();
        SubPanel subPanel = new SubPanel();
        subPanel.setVMPanel(panel);
        panel.setSubPanel(subPanel);
        add(subPanel);
        add(panel);
    }

    private void initGUI() {
        setTitle("Vending Machine");
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                showMessage();
            }
        };
        addWindowListener(windowAdapter);

    }

    public void showMessage() {
        int result = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát không ?");
        if (result == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
}
