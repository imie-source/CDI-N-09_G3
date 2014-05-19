package fr.imie.Iservices;

import java.util.List;

import javax.ejb.Local;

import fr.imie.entity.skills.EvaluatedSkill;
import fr.imie.entity.skills.Skill;


@Local
public interface ISkillServices {
	

//	S'auto-�valuer sur une comp�tence
//	Modifier une comp�tence de l'arbre
//	Supprimer une comp�tence

//	Evaluer une comp�tence � la hausse
//	Visualiser l'arbre des comp�tences
//	
	/**
	 * @param skill
	 * @return Skill
	 */
//	Ajouter une comp�tence dans l'arbre
	public abstract Skill insertSkill(Skill skill);
	
	

	/**
	 * @param skillToUpdate
	 * @return Skill
	 */
	public abstract Skill updateSkill(Skill skillToUpdate);
	
	/**
	 * @param skill
	 */
	public abstract void deleteSkill(Skill skill);

	public abstract Skill searchSkill(int ID);



	public abstract EvaluatedSkill searchEvSkill(int ID);


	public abstract EvaluatedSkill insertEvSkill(EvaluatedSkill evSkill);

	public abstract void deleteEvSkill(EvaluatedSkill skill);



	public abstract EvaluatedSkill updateEvSkill(EvaluatedSkill evSkillToUpdate);



	List<Skill> listSkills();
	
	 
}
