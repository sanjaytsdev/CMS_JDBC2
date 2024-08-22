package Domain.DAO;

import Domain.Entities.Contact;
import Domain.VO.AddressVO;
import Infrastructure.SQLAccess;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class contactDao extends baseDao {
    public String insertQuery(Contact cont) {
        String queryExc = "INSERT INTO contacts values (" + cont.getContactId() + "," + cont.getContactgrpId() + ",'" + cont.getContactName() + "','" + cont.getContactPhone() + "','" + cont.getAddress().getAddress() + "','" + cont.getContactPin() + "');";
        return queryExc;
    }

    public String updateQuery(Contact cont) {
        String queryExc = "UPDATE contacts set contactid =" + cont.getContactId() + ", contactgrpid =" + cont.getContactgrpId() + ", contactname ='" + cont.getContactName() + "', contactphone ='" + cont.getContactPhone() + "', contactaddress ='" + cont.getAddress().getAddress() + "', contactpin ='" + cont.getContactPin() + "' WHERE contactid =" + cont.getContactId() + ";";
        return queryExc;
    }

    public String deleteQuery(int id){
        String queryExc = "DELETE FROM contacts WHERE contactid =" + id +";";
        return queryExc;
    }

    public String selectQuery(int id) {
        String queryExc = "SELECT * FROM contacts WHERE contactid =" + id + ";";
        return queryExc;
    }

    public String selectQueryName(String name) {
        String queryExc = "SELECT * FROM contacts WHERE contactname = '" + name + "';";
        return queryExc;
    }

    public boolean createOrupdateContact(Contact cont) throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return false;

        String queryExc = insertQuery(cont);
        System.out.println(queryExc);
        boolean result = s.executeNonQuery(queryExc);
        s.Close();
        return result;
    }

    public boolean deleteContact(int id) throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return false;

        String queryExc = deleteQuery(id);
        System.out.println(queryExc);
        boolean result = s.executeNonQuery(queryExc);
        s.Close();
        return result;
    }

    public boolean updateContact(int id, Contact cont) throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return false;

        String queryExc = updateQuery(cont);
        System.out.println(queryExc);
        boolean result = s.executeNonQuery(queryExc);
        s.Close();
        return result;
    }

    public List<Contact> listContact() throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return null;

        ResultSet rs = s.Execute("SELECT * FROM contacts");

        if (rs == null)
            return null;

        List<Contact> ret = new ArrayList<>();

        while (rs.next()) {
            int cid = rs.getInt(1);
            int cgid = rs.getInt(2);
            String cname = rs.getString(3);
            String ph = rs.getString(4);
            String addr = rs.getString(5);
            String pin = rs.getString(6);
            Contact temp = new Contact();
            temp.setContactId(cid);
            temp.setContactgrpId(cgid);
            temp.setContactName(cname);
            temp.setContactPhone(ph);
            temp.setAddress(new AddressVO(addr));
            temp.setContactPin(pin);
            ret.add(temp);
        }
        rs.close();
        s.Close();
        return ret;
    }

    public Object[][] getContactAsArray() throws Exception {
        List<Contact> rs = this.listContact();
        Object[][] data = new Object[rs.size()][3];
        int i = 0;

        for (Contact cg : rs) {
            Object[] temp = new Object[] {cg.getContactId(), cg.getContactgrpId(), cg.getContactName()};
            data[i] = temp;
            i++;
        }
        return data;
    }

    public String[] getContactNameAsArray() throws Exception {
        try {
            List<Contact> rs = listContact();
            String[] data = new String[rs.size()];
            int i = 0;

            for (Contact cg : rs) {
                data[i] = cg.getContactName();
                i++;
            }
            return data;
        } catch (Exception e) {
            e.toString();
            return new String[] {"NULL"};
        }
    }

    public String getContactNameFromId(int id) throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return null;

       ResultSet rs = s.Execute(selectQuery(id));
       if (rs == null)
           return null;
       String ns = null;
       if (rs.next())
           ns = rs.getString(3);
       rs.close();
       s.Close();
       return ns;
    }

    public boolean alreadyExists(int id) throws Exception {
        return getContactNameFromId(id) != null;
    }

    public Contact getData(String contname) {
        try {
            SQLAccess s = new SQLAccess(DRIVER_NAME);

            if (!s.Open(URL))
                return null;

            ResultSet rs = s.Execute(selectQueryName(contname));

            if (rs == null)
                return null;

            Contact temp = null;

            if (rs.next()) {
                int cid = rs.getInt(1);
                int cgid = rs.getInt(2);
                String cname = rs.getString(3);
                String ph = rs.getString(4);
                String addr = rs.getString(5);
                String pin = rs.getString(6);
                temp = new Contact();
                temp.setContactId(cid);
                temp.setContactgrpId(cgid);
                temp.setContactName(cname);
                temp.setContactPhone(ph);
                temp.setAddress(new AddressVO(addr));
                temp.setContactPin(pin);
            }
            rs.close();
            s.Close();
            return temp;
        } catch (Exception e) {
            return null;
        }
    }
}
