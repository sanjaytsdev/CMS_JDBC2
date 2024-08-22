package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class MenuActionListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String f = e.getActionCommand();
        if (f.equals("Group Add")) {
            contactGrpFormAdd form = new contactGrpFormAdd("ADD");
            return ;
        } else if (f.equals("Group Edit")) {
            GUIHelper.MessageBox("Not implemented");
            return;
        } else if (f.equals("Group Delete")) {
            try {
                contactGrpFormDelete form = new contactGrpFormDelete("DELETE");
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (f.equals("Contact Add")) {
            try {
                contactFormAdd form = new contactFormAdd("ADD");
                return;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (f.equals("Contact Delete")) {
            try {
                contactFormDelete form = new contactFormDelete("DELETE");
                return;
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        } else if (f.equals("Contact Edit")) {
            GUIHelper.MessageBox("Not implemented");
            return;
        } else if (f.equals("Exit")) {
            System.exit(0);
        }
    }
}


public class MainWindow extends JFrame {
    static JMenuBar mb;
    static JMenu m1;
    static JMenu m2;
    static JMenuItem it1, it2, it3, it4, it5, it6, it7;
    static JFrame f;

    public static void main(String [] args) {
        f = new JFrame("Menu demo");
        mb = new JMenuBar();

        m1 = new JMenu("Contact Group");

        it1 = new JMenuItem("Group Add");
        it2 = new JMenuItem("Group Edit");
        it3 = new JMenuItem("Group Delete");
        it4 = new JMenuItem("Exit");

        m2 = new JMenu("Contact");

        it5 = new JMenuItem("Contact Add");
        it6 = new JMenuItem("Contact Edit");
        it7 = new JMenuItem("Contact Delete");

        m1.add(it1);
        m1.add(it2);
        m1.add(it3);
        m1.add(it4);
        m2.add(it5);
        m2.add(it6);
        m2.add(it7);
        it1.addActionListener(new MenuActionListener());
        it2.addActionListener(new MenuActionListener());
        it3.addActionListener(new MenuActionListener());
        it4.addActionListener(new MenuActionListener());
        it5.addActionListener(new MenuActionListener());
        it6.addActionListener(new MenuActionListener());
        it7.addActionListener(new MenuActionListener());

        mb.add(m1);
        mb.add(m2);

        f.setJMenuBar(mb);

        f.setSize(500, 500);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
