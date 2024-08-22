package GUI;

import Domain.DTO.contactgrpDto;
import Domain.Entities.Contactgrp;
import Presenter.ContactgrpManagerPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class contactGrpFormDelete extends JFrame implements ActionListener {
    private JLabel  Selectlbl = new JLabel("Select Value");
    private JComboBox GroupNamebox = null;
    private JLabel groupIdLabel;
    private JLabel groupNameLabel;
    private JLabel groupDescriptionLabel;
    private JTextField groupIdText;
    private JTextField groupNameText;
    private JTextField groupDescriptionText;
    private JLabel outputLabel = new JLabel("");
    private JButton button = new JButton("Submit");
    private JButton button_list = new JButton("List");
    private String formmode = "DELETE";
    public contactGrpFormDelete(String fm) throws Exception {
        super("Group Form -" + fm);
        formmode = fm;

        String[] names = ContactgrpManagerPresenter.getGrpNames();
        GroupNamebox = new JComboBox(names);
        groupIdLabel = new JLabel("Group ID");
        groupIdText = new JTextField(5);
        groupNameLabel = new JLabel("Group Name");
        groupNameText = new JTextField(20);
        groupDescriptionLabel = new JLabel("Group Description");
        groupDescriptionText = new JTextField(20);

        setLayout(new GridLayout(7,2));
        add(Selectlbl);
        add(GroupNamebox);
        add(groupIdLabel);
        add(groupIdText);
        add(groupNameLabel);
        add(groupNameText);
        add(groupDescriptionLabel);
        add(groupDescriptionText);
        add(outputLabel);
        add(button);
        add(button_list);
        button.addActionListener(this);
        button_list.addActionListener(this);
        CalibrateUI(formmode);
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GroupNamebox.addActionListener(this);
    }
    public void CalibrateUI(String mode) {
        if ( mode == "DELETE" ) {
            this.button.setText(mode);
            groupDescriptionText.setEditable(false);
            groupNameText.setEditable(false);
            groupIdText.setEditable(false);
        } else {
            this.button.setText(mode);
        }
    }
    public static void viewHandler(contactGrpFormDelete frm) throws Exception {
        Contactgrp cg = getEntityFromUI(frm);
        if (cg.equals(null)) {
            System.out.println("Error in input");
            return;
        }

        if (!ContactgrpManagerPresenter.doesExists(cg.getGroupId())) {
            JOptionPane.showMessageDialog(null, "Group Does not  Exists");
            return;
        }

        boolean result = false;

        if (ContactgrpManagerPresenter.Delete(cg.getGroupId())) {
            JOptionPane.showMessageDialog(null, "Group Deleted");
            GUIHelper.populateComboBox(frm.GroupNamebox,ContactgrpManagerPresenter.getGrpNames());
            result =true;
        }

        if (result) {
            System.out.println("success!");
            frm.outputLabel.setText("success!");
        } else {
            System.out.println("failed");
            frm.outputLabel.setText("failed!");
        }
    }

    public static Contactgrp getEntityFromUI(contactGrpFormDelete fm) {
        Contactgrp cg = new Contactgrp();
        cg.setGroupId(Integer.parseInt(fm.groupIdText.getText()));
        cg.setGroupName(fm.groupNameText.getText());
        cg.setGroupDesc(fm.groupDescriptionText.getText());
        JOptionPane.showMessageDialog(null,cg.getGroupDesc());
        return cg;
    }

    public static contactgrpDto setUIFromEntity(contactGrpFormDelete fm, contactgrpDto cdto) {
        int grid = cdto.getGrpId();

        fm.groupIdText.setText(Integer.toString(grid));
        fm.groupNameText.setText(cdto.getGrpName());
        fm.groupDescriptionText.setText(cdto.getGrpDesc());

        return cdto;
    }

    @Override
    public void actionPerformed(ActionEvent e)  {
        Object obj = e.getSource();

        if (obj instanceof  JComboBox) {
            JComboBox box = (JComboBox) obj;
            String f = (String) box.getSelectedItem();
            if (f == null) {
                JOptionPane.showMessageDialog(null,"Failed to Retrieve Selection");
                return;
            }
            contactgrpDto grp = ContactgrpManagerPresenter.getContactGrp(f);
            if (grp == null ) {
                JOptionPane.showMessageDialog(null,"Failed to Retrieve Selection "+f);
                return;
            }
            contactGrpFormDelete.setUIFromEntity((contactGrpFormDelete)this,grp);
            return;
        }
        if (e.getActionCommand() == "List") {
            try {
                ContactGrpListWindow lst = new ContactGrpListWindow();
                lst.setVisible(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            return;
        }

        System.out.println(e);
        outputLabel.setText("Creating a group");
        try {
            viewHandler(this);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private class ItemChanged implements ActionListener {
        public void actionPerformed(ActionEvent e) { }
    }
}
