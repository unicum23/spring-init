package my.study1.entity;

import javax.persistence.Entity;

@Entity
public class Contact extends BaseEntity {

    private String phoneMobile;
    private String addressReg;
    private String addressAct;

    public Contact() {
    }

    public Contact(String phoneMobile, String addressReg, String addressAct) {
        this.phoneMobile = phoneMobile;
        this.addressReg = addressReg;
        this.addressAct = addressAct;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getAddressReg() {
        return addressReg;
    }

    public void setAddressReg(String addressReg) {
        this.addressReg = addressReg;
    }

    public String getAddressAct() {
        return addressAct;
    }

    public void setAddressAct(String addressAct) {
        this.addressAct = addressAct;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "phoneMobile='" + phoneMobile + '\'' +
                ", addressReg='" + addressReg + '\'' +
                ", addressAct='" + addressAct + '\'' +
                '}';
    }
}
