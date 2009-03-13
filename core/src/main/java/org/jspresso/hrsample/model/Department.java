/*
 * Generated by Jspresso. All rights reserved.
 */
package org.jspresso.hrsample.model;

/**
 * Department entity.
 * <p>
 * Generated by Jspresso. All rights reserved.
 * <p>
 *
 * @hibernate.mapping
 *           default-access = "org.jspresso.framework.model.persistence.hibernate.property.EntityPropertyAccessor"
 * @hibernate.joined-subclass
 *           table = "DEPARTMENT"
 *           dynamic-insert = "true"
 *           dynamic-update = "true"
 * persister = "org.jspresso.framework.model.persistence.hibernate.entity.persister.EntityProxyJoinedSubclassEntityPersister"
 * @hibernate.joined-subclass-key
 *           column = "ID"
 * @author Generated by Jspresso
 * @version $LastChangedRevision$
 */
public interface Department extends
  org.jspresso.hrsample.model.OrganizationalUnit,
  org.jspresso.framework.model.entity.IEntity {

  /**
   * Gets the company.
   *
   * @hibernate.many-to-one
   *           cascade = "persist,merge,save-update"
   * @hibernate.column
   *           name = "COMPANY_ID"
   *           not-null = "true"
   * @return the company.
   */
  org.jspresso.hrsample.model.Company getCompany();

  /**
   * Sets the company.
   *
   * @param company
   *          the company to set.
   */
  void setCompany(org.jspresso.hrsample.model.Company company);

  /**
   * Gets the teams.
   *
   * @hibernate.set
   *           cascade = "persist,merge,save-update,refresh,evict,replicate,delete"
   *           inverse = "true"
   * @hibernate.key
   *           column = "DEPARTMENT_ID"
   *           not-null = "true"
   * @hibernate.one-to-many
   *           class = "org.jspresso.hrsample.model.Team"
   * @return the teams.
   */
  java.util.Set<org.jspresso.hrsample.model.Team> getTeams();

  /**
   * Sets the teams.
   *
   * @param teams
   *          the teams to set.
   */
  void setTeams(java.util.Set<org.jspresso.hrsample.model.Team> teams);

  /**
   * Adds an element to the teams.
   *
   * @param teamsElement
   *          the teams element to add.
   */
  void addToTeams(org.jspresso.hrsample.model.Team teamsElement);

  /**
   * Removes an element from the teams.
   *
   * @param teamsElement
   *          the teams element to remove.
   */
  void removeFromTeams(org.jspresso.hrsample.model.Team teamsElement);

}
