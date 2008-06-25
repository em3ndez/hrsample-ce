/*
 * Generated by Jspresso. All rights reserved.
 */
package org.jspresso.hrsample.model;

/**
 * Team entity.
 * <p>
 * Generated by Jspresso. All rights reserved.
 * <p>
 *
 * @hibernate.mapping
 *           default-access = "org.jspresso.framework.model.persistence.hibernate.property.EntityPropertyAccessor"
 * @hibernate.joined-subclass
 *           table = "TEAM"
 *           dynamic-insert = "true"
 *           dynamic-update = "true"
 *           persister = "org.jspresso.framework.model.persistence.hibernate.entity.persister.EntityProxyJoinedSubclassEntityPersister"
 * @hibernate.joined-subclass-key
 *           column = "ID"
 * @author Generated by Jspresso
 * @version $LastChangedRevision$
 */
public interface Team extends
  org.jspresso.hrsample.model.OrganizationalUnit,
  org.jspresso.framework.model.entity.IEntity {

  /**
   * Gets the department.
   *
   * @hibernate.many-to-one
   *           cascade = "persist,merge,save-update"
   * @hibernate.column
   *           name = "DEPARTMENT_ID"
   *           not-null = "true"
   * @return the department.
   */
  org.jspresso.hrsample.model.Department getDepartment();

  /**
   * Sets the department.
   *
   * @param department
   *          the department to set.
   */
  void setDepartment(org.jspresso.hrsample.model.Department department);

  /**
   * Gets the teamMembers.
   *
   * @hibernate.set
   *           cascade = "persist,merge,save-update,refresh,evict,replicate"
   *           table = "TEAM_TEAM_MEMBERS"
   * @hibernate.key
   *           column = "TEAM_ID"
   * @hibernate.many-to-many
   *           class = "org.jspresso.hrsample.model.Employee"
   *           column = "EMPLOYEE_ID"
   * @return the teamMembers.
   */
  java.util.Set<org.jspresso.hrsample.model.Employee> getTeamMembers();

  /**
   * Sets the teamMembers.
   *
   * @param teamMembers
   *          the teamMembers to set.
   */
  void setTeamMembers(java.util.Set<org.jspresso.hrsample.model.Employee> teamMembers);

  /**
   * Adds an element to the teamMembers.
   *
   * @param teamMembersElement
   *          the teamMembers element to add.
   */
  void addToTeamMembers(org.jspresso.hrsample.model.Employee teamMembersElement);

  /**
   * Removes an element from the teamMembers.
   *
   * @param teamMembersElement
   *          the teamMembers element to remove.
   */
  void removeFromTeamMembers(org.jspresso.hrsample.model.Employee teamMembersElement);

}