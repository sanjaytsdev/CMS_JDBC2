package Services;

import Domain.DAO.contactDao;
import Domain.DAO.contactgrpDao;
import Domain.DTO.contactgrpDto;
import Domain.Entities.Contact;
import Domain.Entities.Contactgrp;
import Domain.Transfer.TransferValues;

import java.util.ArrayList;
import java.util.List;

public class contactGrpService {
    public static String[] getGroupNames() throws Exception {
        return (new contactgrpDao()).getGroupNameAsArray();
    }

    public static Object[][] getGroupAsArray() {
        try {
            return (new contactgrpDao()).getGroupAsArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean createGroup(Contactgrp cg) throws Exception {
        return (new contactgrpDao()).createOrUpdateGrp(cg);
    }

    public static List<contactgrpDto> retrieveContactGroups() {
        try {
            List<Contactgrp> lst = (new contactgrpDao()).getGroups();
            List<contactgrpDto> ret = new ArrayList<>();
            for (Contactgrp rs : lst)
                ret.add(TransferValues.fromContactgrpDto2(rs));
            return ret;
        } catch (Exception e) {
            return null;
        }
    }

    public static Contactgrp getGroup(String str) {
        return (new contactgrpDao()).getData(str);
    }

    public static boolean doesExists(int id) throws Exception {
        return (new contactgrpDao()).alreadyExists(id);
    }

    public static boolean canWeDelete(int id) throws Exception {
        List<Contact> cnt = (new contactDao()).listContact();
        for (Contact c : cnt)
            if (c.getContactgrpId() == id)
                return false;
        return true;
    }

    public static int getContactManagerId(String str) {
        Contactgrp grp = null;
        try {
            grp = (new contactgrpDao()).getData(str);
            if (grp == null)
                return -1;
            return grp.getGroupId();
        } catch (Exception e) {
            return -1;
        }
    }

    public static boolean Delete(int id) {
        try {
            if (!(canWeDelete(id)))
                return false;
            return (new contactgrpDao()).deleteContactGrp(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}