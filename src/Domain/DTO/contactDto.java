package Domain.DTO;

public class contactDto {
    private int contactId;
    private int contactgrpId;
    private String contactName;
    private String contactPhone;
    private String contactAddr;
    private String contactPin;

    public String getContactPin() {
        return contactPin;
    }

    public void setContactPin(String contactPin) {
        this.contactPin = contactPin;
    }

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

    public String getContactAddr() {
        return contactAddr;
    }

    public void setContactAddr(String contactAddr) {
        this.contactAddr = contactAddr;
    }

}
