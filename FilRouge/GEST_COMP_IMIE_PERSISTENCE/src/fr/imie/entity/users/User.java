package fr.imie.entity.users;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.imie.entity.skills.EvaluatedSkill;
import fr.imie.entity.skills.Skill;

/**
 * The persistent class for the utilisateur database table.
 * join fetch u.evaluatedSkills"
 */
@Entity
@Table(name="utilisateur",
schema="gestioncomp")
@NamedQueries({
@NamedQuery(name="findAllUsers",
query="Select u from User u" ),
@NamedQuery(name="findUserWithSkills",
query="Select u from User u join fetch u.evaluatedSkills where u.userId=:id" )
})
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String description;
	private Boolean avaibility;//not null
	private String login;//not null
	private String mail;
	private String password;//not null
	private String lastName;//not null
	private String firstName;//not null
	private Boolean protectedData;//not null
	private Rights rights;//not null
	private Year year; 
	private List<EvaluatedSkill> evaluatedSkills;

	public User() {
	}
	@Id
	@Column(name="utilisateur_id")
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(name="description")
	public String getDescription() {
		return this.description;
	}

	
	public void setDescription(String description) {
		this.description = description;
	}

	@Basic
	@Column(name="dispo")
	public Boolean getAvaibility() {
		return avaibility;
	}

	public void setAvaibility(Boolean avaibility) {
		this.avaibility = avaibility;
	}

	@Column(name="login")
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name="mail")
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	@Basic
	@Column(name="mdp")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Basic
	@Column(name="nom")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Basic
	@Column(name="prenom")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name="protection_donnee")
	public Boolean getProtectedData() {
		return protectedData;
	}
	
//	uni-directional many-to-one association to evaluatedSkills
	@OneToMany(
			targetEntity=EvaluatedSkill.class)
	@JoinColumn(name="utilisateur_id")
	public List<EvaluatedSkill> getEvaluatedSkills(){
		return this.evaluatedSkills;
	}

	public void setEvaluatedSkills(List<EvaluatedSkill> evaluatedSkills){
		this.evaluatedSkills=evaluatedSkills;

	}

//	public EvaluatedSkill addEvaluatedSkill(EvaluatedSkill evSkill) {
//		getEvaluatedSkills().add(evSkill);
//		evSkill.setUser(this);
//
//		return evSkill;
//	}
//
//	public EvaluatedSkill removeEvaluatedSkill(EvaluatedSkill evSkill) {
//		getEvaluatedSkills().remove(evSkill);
//		evSkill.setUser(null);
//
//		return evSkill;
//	}
//		
	public void setProtectedData(Boolean protectedData) {
		this.protectedData = protectedData;
	}

	//uni-directional many-to-one association to Rights
	@ManyToOne
	@JoinColumn(name="droits_id")
	public Rights getRights() {
		return this.rights;
	}

	public void setRights(Rights rights) {
		this.rights = rights;
	}

	//uni-directional many-to-one association to YearName
	@ManyToOne
	@JoinColumn(name="promotion_id")
	public Year getYear() {
		return this.year;
	}

	public void setYear(Year year) {
		this.year= year;
	}
	
	
	
	public User(String description, Boolean avaibility,
			String login, String mail, String password, String lastName,
			String firstName, Boolean protectedData, Rights rights, Year year) {
		super();
		
		this.description = description;
		this.avaibility = avaibility;
		this.login = login;
		this.mail = mail;
		this.password = password;
		this.lastName = lastName;
		this.firstName = firstName;
		this.protectedData = protectedData;
		this.rights = rights;
		this.year = year;
	}
	// calcul du niveau de l'�tudiant pour une liste de comp�tences pass�es en param�tre.
	public Integer scoreWithSkills( List<Skill> set){
		Integer score=0;
		for(EvaluatedSkill evSkill:evaluatedSkills){
			for (Skill s:set){
				if(s.equals(evSkill)){
					score+=evSkill.getLevel();
					break;
				}
			}

		}
		return score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}




}