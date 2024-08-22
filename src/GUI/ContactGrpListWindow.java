package GUI;

import Presenter.ContactgrpManagerPresenter;

import javax.swing.*;
import java.awt.*;

public class ContactGrpListWindow extends JFrame {
    ContactGrpListWindow() throws Exception {
        String[] columnNames = {"ID", "Group Name", "Group Description",};
        Object[][] data = ContactgrpManagerPresenter.getGrpAsArray();
        final JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        this.setSize(600,700);
        add(table);
    }
}
