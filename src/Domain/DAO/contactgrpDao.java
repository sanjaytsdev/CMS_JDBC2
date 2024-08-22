package Domain.DAO;

import Infrastructure.SQLAccess;
import Domain.Entities.Contactgrp;
import GUI.GUIHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class contactgrpDao extends baseDao {
    public String insertQuery(Contactgrp cg) {
        String queryExc = "INSERT INTO contactgrp values (" + cg.getGroupId() + ",'" + cg.getGroupName() + "','" + cg.getGroupDesc() + "');";
        return queryExc;
    }

    public String updateQuery(Contactgrp cg) {
        String queryExc = "UPDATE contactgrp SET grpname ='" + cg.getGroupName() + "', grpdesc ='" + cg.getGroupDesc() + "' WHERE grpid =" + cg.getGroupId() + ";";
        return queryExc;
    }

    public String deleteQuery(int id){
        String queryExc = "DELETE FROM contactgrp WHERE grpid =" + id +";";
        return queryExc;
    }

    public String selectQuery(int id) {
        String queryExc = "SELECT * FROM contactgrp WHERE grpid =" + id + ";";
        return queryExc;
    }

    public String selectQueryName(String name) {
        String queryExc = "SELECT * FROM contactgrp WHERE grpname = '" + name + "';";
        return queryExc;
    }

    public Object[][] getGroupAsArray() throws Exception {
        List<Contactgrp> rs = getGroups();
        Object[][] data = new Object[rs.size()][3];
        int i = 0;

        for (Contactgrp cg : rs) {
            Object[] temp = new Object[] {cg.getGroupId(), cg.getGroupName(), cg.getGroupDesc()};
            data[i] = temp;
            i++;
        }
        return data;
    }

    public List<Contactgrp> getGroups() throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return null;

        ResultSet rs = s.Execute("SELECT * FROM contactgrp; ");

        if (rs == null)
            return null;

        List<Contactgrp> retval = new ArrayList<>();
        while (rs.next()) {
            int cgid = rs.getInt(1);
            String cgname = rs.getString(2);
            String cgdesc = rs.getString(3);
            Contactgrp temp = new Contactgrp();
            temp.setGroupId(cgid);
            temp.setGroupName(cgname);
            temp.setGroupDesc(cgdesc);
            retval.add(temp);
        }
        rs.close();
        s.Close();
        return retval;
    }

    public String[] getGroupNameAsArray() {
        try {
            List<Contactgrp> rs = getGroups();
            String[] data = new String[rs.size()];

            int i = 0;
            for (Contactgrp cg : rs) {
                data[i] = cg.getGroupName();
                i++;
            }
            return data;
        } catch (Exception e) {
            e.toString();
            return new String[] {"Empty"};
        }
    }

    public Contactgrp getData(String grpname) {
        try {
            SQLAccess s = new SQLAccess(DRIVER_NAME);

            if (!s.Open(URL))
                return null;

            ResultSet rs = s.Execute(selectQueryName(grpname));

            if (rs == null)
                return null;

            Contactgrp ns = null;

            if (rs.next()) {
                ns = new Contactgrp();
                ns.setGroupId(rs.getInt(1));
                ns.setGroupName(rs.getString(2));
                ns.setGroupDesc(rs.getString(3));
            }
            rs.close();
            s.Close();
            return ns;
        } catch (Exception e) {
            return null;
        }
    }

    public String getNameFromId(int id) throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return null;

        ResultSet rs = s.Execute(selectQuery(id));

        if (rs == null)
            return null;

        String ns = null;

        if (rs.next())
            ns = rs.getString(2);

        rs.close();
        s.Close();
        return ns;
    }

    public boolean alreadyExists(int id) throws Exception {
        return getNameFromId(id) != null;
    }

    public boolean createOrUpdateGrp(Contactgrp cg) throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            throw new Exception(URL + "Can't open file");

        String Query = insertQuery(cg);

        GUIHelper.MessageBox(Query);

        boolean result = s.executeNonQuery(Query);
        s.Close();
        return result;
    }

    public boolean deleteContactGrp(int id) throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return false;

        String Query = deleteQuery(id);

        boolean result = s.executeNonQuery(Query);
        s.Close();
        return result;
    }

    public boolean updateContactGrp(int id, Contactgrp cg) throws Exception {
        SQLAccess s = new SQLAccess(DRIVER_NAME);

        if (!s.Open(URL))
            return false;

        String Query = updateQuery(cg);

        boolean result = s.executeNonQuery(Query);
        s.Close();
        return result;
    }
}
