/*
 * Copyright (c) 2005-2011 Vincent Vandenschrick. All rights reserved.
 */
package org.jspresso.hrsample.model.service;

import java.util.Date;

/**
 * Services offered by the Employee entity.
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public interface EmployeeService {

  /**
   * Computes the employee age.
   * 
   * @param birthDate
   *          the employee birth date.
   * @return the computed age based on the birth date or null if the birth date
   *         is not available.
   */
  Integer computeAge(Date birthDate);
}
