package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Trainer extends Model
{
  public String firstname;
  public String lastname;
  public String email;
  public String password;
  public String address;
  public double height;


  @OneToMany(cascade = CascadeType.ALL)
  public List<Member> members = new ArrayList<Member>();
  //public List<Assessment> assessments = new ArrayList<Assessment>();

  public Trainer(String firstname, String lastname, String email, String password, String address)
  {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
    this.address = address;
  }


  public static Trainer findByEmail(String email)
  {
    return find("email", email).first();
  }

  public boolean checkPassword(String password)
  {
    return this.password.equals(password);
  }
}
