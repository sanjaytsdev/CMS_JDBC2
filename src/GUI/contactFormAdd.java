package GUI;

import Domain.DTO.contactDto;
import Presenter.ContactManagerPresenter;
import Presenter.ContactgrpManagerPresenter;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class contactFormAdd extends JFrame implements ActionListener {
    private JLabel contactIdLabel;
    private JLabel contactgrpIdLabel;
    private JComboBox contactgrpIdText;
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
    private JLabel listindent = new JLabel("      ");
    private JButton ListButton = new JButton("LIST");
    private String formmode = "ADD";

    public contactFormAdd(String fm) throws Exception {
        super("Contact Form - " + fm);
        formmode = fm;

        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setGroupingUsed(false);
        NumberFormatter numberFormatter = new NumberFormatter(numberFormat);
        numberFormatter.setAllowsInvalid(false);

        contactIdLabel = new JLabel("Contact Id:");
        contactIdText = new JTextField(20);
        contactgrpIdLabel = new JLabel("Contact GroupName");
        contactgrpIdText = new JComboBox(new String[]{ "FRIENDS", "RELATIVES", "OTHERS" });
        contactgrpIdText.setBounds(50, 50,90,20);
        contactNameLabel = new JLabel("Contact Name");
        contactNameText = new JTextField(20);
        contactPhoneLabel = new JLabel("Phone");
        contactPhoneText = new JFormattedTextField(numberFormatter);
        contactPhoneText.setColumns(20);
        contactAddrLabel = new JLabel("Addr");
        contactAddrText = new JTextField(20);
        contactPinLabel = new JLabel("Pin");
        contactPinText = new JFormattedTextField(numberFormatter);
        contactPinText.setColumns(20);

        setLayout(new GridLayout(10,2));
        add(contactIdLabel);
        add(contactIdText);
        add(contactgrpIdLabel);
        add(contactgrpIdText);
        add(contactNameLabel);
        add(contactNameText);
        add(contactPhoneLabel);
        add(contactPhoneText);
        add(contactAddrLabel);
        add(contactAddrText);
        add(contactPinLabel);
        add(contactPinText);
        add(outputLabel);
        add(button);
        add(listindent);
        add(ListButton);
        button.addActionListener(this);
        ListButton.addActionListener(this);
        GUIHelper.populateComboBox(contactgrpIdText, ContactgrpManagerPresenter.getGrpNames());
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static contactDto getEntityFromUI(contactFormAdd cntfm) {
        contactDto cdto = new contactDto();
        cdto.setContactId(GUIHelper.convertToInteger(cntfm.contactIdText));
        String str  = (String)cntfm.contactgrpIdText.getSelectedItem();
        if (str == null)
            return null;
        int id = ContactgrpManagerPresenter.getContactGrpId(str);
        if (id == -1)
            return null;
        cdto.setContactgrpId(id);
        cdto.setContactName(cntfm.contactNameText.getText());
        cdto.setContactPhone(cntfm.contactPhoneText.getText());
        cdto.setContactAddr(cntfm.contactAddrText.getText());
        cdto.setContactPin(cntfm.contactPinText.getText());

        return cdto;
    }

    public static void viewHandler(contactFormAdd cntfm) throws Exception {
        contactDto cdto = getEntityFromUI(cntfm);
        if (cdto == null) {
            GUIHelper.MessageBox("Invalid retrieval of Entity");
            return;
        }
        if (cdto == null) {
            System.out.println("Error in input");
            return;
        }

        if (ContactManagerPresenter.DoesExists(cdto.getContactId())) {
            GUIHelper.MessageBox("The ID already exists");
            return ;
        }

        boolean result = ContactManagerPresenter.addContact(cdto);

        if (result) {
            System.out.println("success!");
            cntfm.outputLabel.setText("success!");
        } else {
            System.out.println("failed");
            cntfm.outputLabel.setText("failed");
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        outputLabel.setText("Creating a contact");

        String cmd = e.getActionCommand();
        GUIHelper.MessageBox("From Action Performed " + cmd);
        if (cmd.equals("LIST")) {
            try {
                ContactListWindow s = new ContactListWindow();
                s.setVisible(true);
                return;
            }
            catch( Exception ex) {
                return;
            }
        } try {
            viewHandler(this);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
