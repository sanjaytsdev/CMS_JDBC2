package Presenter;

import Domain.DTO.contactgrpDto;
import Domain.Entities.Contactgrp;
import Domain.Transfer.TransferValues;
import Services.contactGrpService;

import java.util.List;

public class ContactgrpManagerPresenter {
    public ContactgrpManagerPresenter() {}

    public static boolean doesExists(int id) throws Exception {
        return contactGrpService.doesExists(id);
    }

    public static List<contactgrpDto> Get() {
        try {
            return contactGrpService.retrieveContactGroups();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int getContactGrpId(String str) {
        return contactGrpService.getContactManagerId(str);
    }

    public static contactgrpDto getContactGrp(String str) {
        return TransferValues.fromContactgrpDto2(contactGrpService.getGroup(str));
    }

    public static String[] getGrpNames() throws Exception {
        return contactGrpService.getGroupNames();
    }

    public static Object[][] getGrpAsArray() {
        return contactGrpService.getGroupAsArray();
    }

    public static List<contactgrpDto> getAllGrps() {
        return contactGrpService.retrieveContactGroups();
    }

    public static boolean Add(contactgrpDto cont) throws Exception {
        Contactgrp cont1 = TransferValues.fromContactgrpDto(cont);
        return contactGrpService.createGroup(cont1);
    }

    public static boolean Delete(int id) {
        return contactGrpService.Delete(id);
    }
}
