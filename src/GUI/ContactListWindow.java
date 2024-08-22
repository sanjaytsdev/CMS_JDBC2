package GUI;

import Domain.DAO.contactDao;

import javax.swing.*;
import java.awt.*;

public class ContactListWindow extends JFrame {
   public ContactListWindow() throws Exception {
        String[] columnNames = {"ID", "Group Id", "Group Name",};
        Object[][] data = (new contactDao()).getContactAsArray();
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        this.setSize(600,700);
        add(table);
    }
}
