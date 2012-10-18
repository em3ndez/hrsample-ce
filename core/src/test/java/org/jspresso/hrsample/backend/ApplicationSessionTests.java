/*
 * Copyright (c) 2005-2012 Vincent Vandenschrick. All rights reserved.
 *
 *  This file is part of the Jspresso framework.
 *
 *  Jspresso is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  Jspresso is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with Jspresso.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.jspresso.hrsample.backend;

import static org.junit.Assert.assertSame;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jspresso.framework.application.backend.persistence.hibernate.HibernateBackendController;
import org.jspresso.framework.application.backend.session.EMergeMode;
import org.jspresso.framework.model.persistence.hibernate.criterion.EnhancedDetachedCriteria;
import org.jspresso.hrsample.model.Employee;
import org.jspresso.hrsample.model.OrganizationalUnit;
import org.junit.Test;

/**
 * Application session integration tests.
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public class ApplicationSessionTests extends BackTestStartup {

  /**
   * Tests that uninitialized reference property are correctly deduped when
   * merged to session. See bug 773.
   */
  @Test
  public void testUninitializedPropertyDedupedWhenMerging() {
    final HibernateBackendController hbc = (HibernateBackendController) getBackendController();

    EnhancedDetachedCriteria ouCriteria = EnhancedDetachedCriteria
        .forClass(OrganizationalUnit.class);
    List<OrganizationalUnit> ous = hbc.findByCriteria(ouCriteria,
        EMergeMode.MERGE_KEEP, OrganizationalUnit.class);
    Map<Serializable, OrganizationalUnit> ousById = new HashMap<Serializable, OrganizationalUnit>();
    for (OrganizationalUnit ou : ous) {
      ousById.put(ou.getId(), ou);
    }

    EnhancedDetachedCriteria employeeCriteria = EnhancedDetachedCriteria
        .forClass(Employee.class);
    List<Employee> employees = hbc.findByCriteria(employeeCriteria,
        EMergeMode.MERGE_KEEP, Employee.class);
    for (Employee emp : employees) {
      OrganizationalUnit managedOu = (OrganizationalUnit) emp
          .straightGetProperty(Employee.MANAGED_OU);
      if (managedOu != null) {
        assertSame(managedOu, ousById.get(managedOu.getId()));
      }
    }
  }
}