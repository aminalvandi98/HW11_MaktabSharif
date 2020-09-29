package ir.maktabsharif.entities;

import javax.persistence.*;


@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private int id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String nationalCode;
    private String birthday;
    private String password;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Role role;
    @OneToOne(cascade = CascadeType.ALL)
    private Adrress adrress;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Adrress getAdrress() {
        return adrress;
    }

    public void setAdrress(Adrress adrress) {
        this.adrress = adrress;
    }
}
