package Domain.Entities;

import Domain.VO.AddressVO;

public class Contact {
    private int contactId;
    private int contactgrpId;
    private String contactName;
    private String contactPhone;
    private AddressVO address;
    private String contactPin;

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public int getContactgrpId() {
        return contactgrpId;
    }

    public void setContactgrpId(int contactgrpId) {
        this.contactgrpId = contactgrpId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public AddressVO getAddress() {
        return address;
    }

    public void setAddress(AddressVO address) {
        this.address = address;
    }

    public String getContactPin() {
        return contactPin;
    }

    public void setContactPin(String contactPin) {
        this.contactPin = contactPin;
    }
}
