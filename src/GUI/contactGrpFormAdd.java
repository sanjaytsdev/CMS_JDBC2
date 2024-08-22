package GUI;

import Domain.DTO.contactgrpDto;
import Presenter.ContactgrpManagerPresenter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class contactGrpFormAdd extends JFrame implements ActionListener {
    private JLabel groupIdLabel;
    private JLabel groupNameLabel;
    private JLabel groupDescriptionLabel;
    private JTextField groupIdText;
    private JTextField groupNameText;
    private JTextField groupDescriptionText;
    private JLabel outputLabel = new JLabel("");
    private JButton button = new JButton("Submit");
    private JButton button_list = new JButton("List");
    private String formmode = "ADD";
    public contactGrpFormAdd(String fm) {
        super("Group Form -" + fm);
        formmode = fm;
        groupIdLabel = new JLabel("Group ID");
        groupIdText = new JTextField(5);
        groupNameLabel = new JLabel("Group Name");
        groupNameText = new JTextField(20);
        groupDescriptionLabel = new JLabel("Group Description");
        groupDescriptionText = new JTextField(20);

        setLayout(new GridLayout(6,2));
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
    }

    public void CalibrateUI(String mode)
    {
        if (mode == "DELETE" || mode == "EDIT") {
            this.button.setText(mode);
            groupDescriptionText.setEditable(false);
            groupNameText.setEditable(false);
        } else {
            this.button.setText(mode);
        }
    }
    public static void viewHandler(contactGrpFormAdd gf) throws Exception {
        contactgrpDto cdto = getEntityFromUI(gf);
        if (cdto.equals(null)) {
            System.out.println("Error in input");
            return;
        }

        if (ContactgrpManagerPresenter.doesExists(cdto.getGrpId())) {
            JOptionPane.showMessageDialog(null, "Group Already Exists");
            return;
        }

        boolean result = ContactgrpManagerPresenter.Add(cdto);

        if (result) {
            System.out.println("success!");
            gf.outputLabel.setText("success!");
        } else {
            System.out.println("failed");
            gf.outputLabel.setText("failed!");
        }
    }

    public static contactgrpDto getEntityFromUI(contactGrpFormAdd groupForm) {
        contactgrpDto cgdto = new contactgrpDto();
        cgdto.setGrpId(Integer.parseInt(groupForm.groupIdText.getText()));
        cgdto.setGrpName(groupForm.groupNameText.getText());
        cgdto.setGrpDesc(groupForm.groupDescriptionText.getText());
        JOptionPane.showMessageDialog(null,cgdto.getGrpDesc());
        return cgdto;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "List") {
            try {
                ContactGrpListWindow lst = new ContactGrpListWindow();
                lst.setVisible(true);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
        System.out.println(e);
        outputLabel.setText("Creating a group");
        try {
            viewHandler(this);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
