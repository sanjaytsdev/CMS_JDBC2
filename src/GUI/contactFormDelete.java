package GUI;

import Domain.DTO.contactDto;
import Presenter.ContactManagerPresenter;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class contactFormDelete extends JFrame implements ActionListener {
    private JLabel ComboboxidLabel = new JLabel("Select Name");
    private JComboBox NameToSelect = new JComboBox(new String[]{""});
    private JLabel contactIdLabel;
    private JLabel contactGrpIdLabel;
    private JTextField contactGrpIdText;
    private JLabel contactNameLabel;
    private JLabel contactPhoneLabel;
    private JLabel contactAddrLabel;
    private JLabel contactPinLabel;

    private JTextField contactIdText;
    private JTextField contactNameText;
    private JFormattedTextField contactPhoneText;
    private JTextField contactAddrText;
    private JFormattedTextField contactPinText;

    private JLabel outputLabel = new JLabel("");
    private JButton button = new JButton("Submit");
    private String formmode = "DELETE";

    public contactFormDelete(String fm) throws Exception {
        super("Contact Form -" + fm);
        formmode = fm ;
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setAllowsInvalid(false);

        contactIdLabel = new JLabel("Contact Id:");
        contactIdText = new JTextField(20);
        contactGrpIdLabel = new JLabel("Contact Groupid");
        contactGrpIdText = new JTextField(20);
        contactGrpIdText.setBounds(50, 50,90,20);
        contactNameLabel = new JLabel("Contact Name");
        contactNameText = new JTextField(20);
        contactPhoneLabel = new JLabel("Phone");
        contactPhoneText = new JFormattedTextField(numberFormatter);
        contactPhoneText.setColumns(20);
        contactAddrLabel = new JLabel("Addr1");
        contactAddrText = new JTextField(20);
        contactPinLabel = new JLabel("Pin");
        contactPinText = new JFormattedTextField(numberFormatter);
        contactPinText.setColumns(20);

        setLayout(new GridLayout(10,2));
        add(ComboboxidLabel);
        add(NameToSelect);
        add(contactIdLabel);
        add(contactIdText);
        contactIdText.setEditable(false);
        add(contactGrpIdLabel);
        add(contactGrpIdText);
        contactGrpIdText.setEditable(false);
        add(contactNameLabel);
        add(contactNameText);
        contactNameText.setEditable(false);
        add(contactPhoneLabel);
        add(contactPhoneText);
        contactPhoneText.setEditable(false);
        add(contactAddrLabel);
        add(contactAddrText);
        contactAddrText.setEditable(false);
        add(contactPinLabel);
        add(contactPinText);
        contactPinText.setEditable(false);
        add(outputLabel);
        add(button);
        button.addActionListener(this);
        GUIHelper.populateComboBox(NameToSelect, ContactManagerPresenter.getContactNameAsArray());
        NameToSelect.addActionListener(this);
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static contactDto getEntityFromUI(contactFormDelete cf) {
        contactDto cdto = new contactDto();
        cdto.setContactId(GUIHelper.convertToInteger(cf.contactIdText));
        cdto.setContactName(cf.contactNameText.getText());
        cdto.setContactPhone(cf.contactPhoneText.getText());
        cdto.setContactAddr(cf.contactAddrText.getText());
        cdto.setContactPin(cf.contactPinText.getText());

        return cdto;
    }

    public static void viewHandler(contactFormDelete cf) throws Exception {
        contactDto cdto = getEntityFromUI(cf);
        if (cdto.equals(null)) {
            System.out.println("Error in input");
            return;
        }

        if (!ContactManagerPresenter.DoesExists(cdto.getContactId())) {
            GUIHelper.MessageBox("Contact does not exists");
            return;
        }

        boolean result = ContactManagerPresenter.Delete(cdto.getContactId());

        if (result) {
            System.out.println("success!");
            cf.outputLabel.setText("success!");
            GUIHelper.populateComboBox(cf.NameToSelect,ContactManagerPresenter.getContactNameAsArray());
        } else {
            System.out.println("failed");
            cf.outputLabel.setText("failed");
        }
    }


    public static contactDto setUIfromEntity(contactFormDelete cf,contactDto cdto) {
        cf.contactIdText.setText(Integer.toString(cdto.getContactId()));
        cf.contactGrpIdText.setText(Integer.toString(cdto.getContactgrpId()));
        cf.contactNameText.setText(cdto.getContactName());
        cf.contactPhoneText.setText(cdto.getContactPhone());
        cf.contactAddrText.setText(cdto.getContactAddr());
        cf.contactPinText.setText(cdto.getContactPin());

        return cdto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        outputLabel.setText("Creating a contact");
        Object obj = e.getSource();

        if (obj instanceof JComboBox) {
            JComboBox box = (JComboBox) obj;
            String f = (String) box.getSelectedItem();
            if (f == null) {
                JOptionPane.showMessageDialog(null,"Failed to Retrieve Selection");
                return;
            }
            contactDto grp = ContactManagerPresenter.getData(f);
            if (grp == null ) {
                JOptionPane.showMessageDialog(null,"Failed to Retrieve Selection "+f);
                return;
            }
            contactFormDelete.setUIfromEntity((contactFormDelete)this,grp);
            return ;
        }
        try {
            viewHandler(this);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
