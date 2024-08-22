package Presenter;

import Domain.DTO.contactDto;
import Domain.Entities.Contact;
import Domain.Transfer.TransferValues;
import Services.contactService;

import java.util.List;

public class ContactManagerPresenter {
    public ContactManagerPresenter() { }

    public static boolean DoesExists(int id) throws Exception {
        return contactService.isContactExists(id);
    }

    public static List<contactDto> Get() {
        try {
            return contactService.retrieveContacts();
        } catch (Exception e) {
            return null;
        }
    }

    public static contactDto getData(String str) {
        return contactService.getData(str);
    }

    public static String[] getContactNameAsArray() throws Exception {
        return contactService.getContactNameAsArray();
    }

    public static boolean addContact(contactDto cont) {
        Contact cont1 = TransferValues.fromContactDto(cont);
        return contactService.addContact(cont1);
    }

    public static boolean Delete(int id) {
        return contactService.Delete(id);
    }

}
