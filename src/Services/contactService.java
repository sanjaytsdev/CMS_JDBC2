package Services;

import Domain.DAO.contactDao;
import Domain.DTO.contactDto;
import Domain.Entities.Contact;
import Domain.Transfer.TransferValues;

import java.util.ArrayList;
import java.util.List;

public class contactService {
    public static boolean isContactExists(int id) throws Exception {
        return (new contactDao()).alreadyExists(id);
    }

    public static List<contactDto> retrieveContacts () throws Exception {
        List<Contact> ret = (new contactDao()).listContact();
        List<contactDto> retval = new ArrayList<>();

        for (Contact c : ret) {
            retval.add(TransferValues.fromContact(c));
        }
        return retval;
    }

    public static boolean Delete(int id) {
        try {
            return (new contactDao()).deleteContact(id);
        } catch (Exception e) {
            return false;
        }
    }

    public static String[] getContactNameAsArray() throws Exception {
        return (new contactDao()).getContactNameAsArray();
    }

    public static boolean addContact(Contact cont) {
        try {
            return (new contactDao()).createOrupdateContact(cont);
        } catch (Exception e) {
            return false;
        }
    }

    public static contactDto getData(String str) {
        Contact c = (new contactDao()).getData(str);
        return TransferValues.fromContact(c);
    }
}
