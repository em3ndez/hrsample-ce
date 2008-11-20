/*
 * Generated by Jspresso. All rights reserved.
 */
package org.jspresso.hrsample.model;

/**
 * ContactInfo component.
 * <p>
 * Generated by Jspresso. All rights reserved.
 * <p>
 *
 * @author Generated by Jspresso
 * @version $LastChangedRevision$
 */
public interface ContactInfo extends
  org.jspresso.framework.model.component.IComponent {

  /**
   * Gets the address.
   *
   * @hibernate.property
   * @hibernate.column
   *           name = "ADDRESS"
   *           length = "256"
   * @return the address.
   */
  java.lang.String getAddress();

  /**
   * Sets the address.
   *
   * @param address
   *          the address to set.
   */
  void setAddress(java.lang.String address);

  /**
   * Gets the city.
   *
   * @hibernate.many-to-one
   *           cascade = "none"
   * @hibernate.column
   *           name = "CITY_ID"
   * @return the city.
   */
  org.jspresso.hrsample.model.City getCity();

  /**
   * Sets the city.
   *
   * @param city
   *          the city to set.
   */
  void setCity(org.jspresso.hrsample.model.City city);

  /**
   * Gets the phone.
   *
   * @hibernate.property
   * @hibernate.column
   *           name = "PHONE"
   *           length = "32"
   * @return the phone.
   */
  java.lang.String getPhone();

  /**
   * Sets the phone.
   *
   * @param phone
   *          the phone to set.
   */
  void setPhone(java.lang.String phone);

  /**
   * Gets the email.
   *
   * @hibernate.property
   * @hibernate.column
   *           name = "EMAIL"
   *           length = "128"
   * @return the email.
   */
  java.lang.String getEmail();

  /**
   * Sets the email.
   *
   * @param email
   *          the email to set.
   */
  void setEmail(java.lang.String email);

}
