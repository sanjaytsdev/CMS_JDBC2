package Domain.Transfer;

import Domain.DTO.contactDto;
import Domain.DTO.contactgrpDto;
import Domain.Entities.Contact;
import Domain.Entities.Contactgrp;
import Domain.VO.AddressVO;

import java.util.ArrayList;
import java.util.List;

public class TransferValues {
    public static Contactgrp fromContactgrpDto(contactgrpDto cdto) {
        Contactgrp ret = new Contactgrp();
        ret.setGroupId(cdto.getGrpId());
        ret.setGroupDesc(cdto.getGrpDesc());
        ret.setGroupName(cdto.getGrpName());
        return ret;
    }

    public static contactgrpDto fromContactgrpDto2(Contactgrp cdto) {
        contactgrpDto ret = new contactgrpDto();
        ret.setGrpId(cdto.getGroupId());
        ret.setGrpDesc(cdto.getGroupDesc());
        ret.setGrpName(cdto.getGroupDesc());
        return ret;
    }

    public static contactDto fromContact(Contact c) {
        contactDto ret = new contactDto();
        ret.setContactId(c.getContactId());
        ret.setContactgrpId(c.getContactgrpId());
        ret.setContactName(c.getContactName());
        ret.setContactPhone(c.getContactPhone());
        ret.setContactAddr(c.getAddress().getAddress());
        ret.setContactPin(c.getContactPin());
        return ret;
    }

    public static Contact fromContactDto(contactDto cdto) {
        Contact ret = new Contact();
        ret.setContactId(cdto.getContactId());
        ret.setContactgrpId(cdto.getContactgrpId());
        ret.setContactName(cdto.getContactName());
        ret.setContactPhone(cdto.getContactPhone());
        ret.setAddress(new AddressVO(cdto.getContactAddr()));
        return ret;
    }

    public static List<contactgrpDto> fromContactgroDtoList(List<Contactgrp> lst) {
        List<contactgrpDto> grp = new ArrayList<>();
        for(Contactgrp cg : lst)
            grp.add(fromContactgrpDto2(cg));
        return grp;
    }
}
