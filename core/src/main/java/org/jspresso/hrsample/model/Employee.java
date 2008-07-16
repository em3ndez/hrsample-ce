/*
 * Generated by Jspresso. All rights reserved.
 */
package org.jspresso.hrsample.model;

/**
 * Employee entity.
 * <p>
 * Generated by Jspresso. All rights reserved.
 * <p>
 *
 * @hibernate.mapping
 *           default-access = "org.jspresso.framework.model.persistence.hibernate.property.EntityPropertyAccessor"
 * @hibernate.class
 *           table = "EMPLOYEE"
 *           dynamic-insert = "true"
 *           dynamic-update = "true"
 * persister = "org.jspresso.framework.model.persistence.hibernate.entity.persister.EntityProxyJoinedSubclassEntityPersister"
 * @author Generated by Jspresso
 * @version $LastChangedRevision$
 */
public interface Employee extends
  org.jspresso.hrsample.model.Nameable,
  org.jspresso.hrsample.model.Traceable,
  org.jspresso.framework.model.entity.IEntity,
  org.jspresso.hrsample.model.service.EmployeeService {

  /**
   * @hibernate.id generator-class = "assigned" column = "ID" type = "string"
   *               length = "36"
   * <p>
   * {@inheritDoc}
   */
  java.io.Serializable getId();

  /**
   * @hibernate.version column = "VERSION" unsaved-value = "null"
   * <p>
   * {@inheritDoc}
   */
  Integer getVersion();

  /**
   * Gets the firstName.
   *
   * @hibernate.property
   * @hibernate.column
   *           name = "FIRST_NAME"
   *           length = "32"
   * @return the firstName.
   */
  java.lang.String getFirstName();

  /**
   * Sets the firstName.
   *
   * @param firstName
   *          the firstName to set.
   */
  void setFirstName(java.lang.String firstName);

  /**
   * Gets the ssn.
   *
   * @hibernate.property
   * @hibernate.column
   *           name = "SSN"
   *           length = "10"
   *           unique-key = "EMP_SSN_UNQ"
   * @return the ssn.
   */
  java.lang.String getSsn();

  /**
   * Sets the ssn.
   *
   * @param ssn
   *          the ssn to set.
   */
  void setSsn(java.lang.String ssn);

  /**
   * Gets the birthDate.
   *
   * @hibernate.property
   *           type = "date"
   * @hibernate.column
   *           name = "BIRTH_DATE"
   * @return the birthDate.
   */
  java.util.Date getBirthDate();

  /**
   * Sets the birthDate.
   *
   * @param birthDate
   *          the birthDate to set.
   */
  void setBirthDate(java.util.Date birthDate);

  /**
   * Gets the hireDate.
   *
   * @hibernate.property
   *           type = "date"
   * @hibernate.column
   *           name = "HIRE_DATE"
   * @return the hireDate.
   */
  java.util.Date getHireDate();

  /**
   * Sets the hireDate.
   *
   * @param hireDate
   *          the hireDate to set.
   */
  void setHireDate(java.util.Date hireDate);

  /**
   * Gets the gender.
   *
   * @hibernate.property
   * @hibernate.column
   *           name = "GENDER"
   *           length = "1"
   *           not-null = "true"
   * @return the gender.
   */
  java.lang.String getGender();

  /**
   * Sets the gender.
   *
   * @param gender
   *          the gender to set.
   */
  void setGender(java.lang.String gender);

  /**
   * Gets the contact.
   *
   * @hibernate.component
   *           prefix = "CONTACT_"
   * @return the contact.
   */
  org.jspresso.hrsample.model.ContactInfo getContact();

  /**
   * Sets the contact.
   *
   * @param contact
   *          the contact to set.
   */
  void setContact(org.jspresso.hrsample.model.ContactInfo contact);

  /**
   * Gets the events.
   *
   * @hibernate.list
   *           cascade = "persist,merge,save-update,refresh,evict,replicate,delete"
   * @hibernate.key
   *           column = "EVENTS_EMPLOYEE_ID"
   * @hibernate.one-to-many
   *           class = "org.jspresso.hrsample.model.Event"
   * @hibernate.list-index
   *           column = "EVENTS_SEQ"
   * @return the events.
   */
  java.util.List<org.jspresso.hrsample.model.Event> getEvents();

  /**
   * Sets the events.
   *
   * @param events
   *          the events to set.
   */
  void setEvents(java.util.List<org.jspresso.hrsample.model.Event> events);

  /**
   * Adds an element to the events.
   *
   * @param eventsElement
   *          the events element to add.
   */
  void addToEvents(org.jspresso.hrsample.model.Event eventsElement);

  /**
   * Adds an element to the events at the specified index. If the index is out
   * of the list bounds, the element is simply added at the end of the list.
   *
   * @param index
   *          the index to add the events element at.
   * @param eventsElement
   *          the events element to add.
   */
  void addToEvents(int index, org.jspresso.hrsample.model.Event eventsElement);

  /**
   * Removes an element from the events.
   *
   * @param eventsElement
   *          the events element to remove.
   */
  void removeFromEvents(org.jspresso.hrsample.model.Event eventsElement);

  /**
   * Gets the teams.
   *
   * @hibernate.set
   *           cascade = "none"
   *           table = "TEAM_TEAM_MEMBERS"
   *           inverse = "true"
   * @hibernate.key
   *           column = "EMPLOYEE_ID"
   * @hibernate.many-to-many
   *           class = "org.jspresso.hrsample.model.Team"
   *           column = "TEAM_ID"
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
   * Gets the managedOu.
   *
   * @hibernate.many-to-one
   *           cascade = "persist,merge,save-update,refresh,evict,replicate"
   * @hibernate.column
   *           name = "MANAGED_OU_ID"
   *           unique = "true"
   * @return the managedOu.
   */
  org.jspresso.hrsample.model.OrganizationalUnit getManagedOu();

  /**
   * Sets the managedOu.
   *
   * @param managedOu
   *          the managedOu to set.
   */
  void setManagedOu(org.jspresso.hrsample.model.OrganizationalUnit managedOu);

  /**
   * Gets the age.
   *
   * @return the age.
   */
  java.lang.Integer getAge();

}
