package my.study1.entity;

import javax.persistence.*;

@Entity
public class Person extends BaseEntity {
    public Person() {
    }

    public Person(String fio, Long birthDate, Contact contact) {
        this.fio = fio;
        this.birthDate = birthDate;
        this.contact = contact;
    }

    @Column
    private String fio;

    @Column
    private Long birthDate;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Contact contact;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Long getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Long birthDate) {
        this.birthDate = birthDate;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fio='" + fio + '\'' +
                ", birthDate=" + birthDate +
                ", contact=" + contact +
                '}';
    }
}
