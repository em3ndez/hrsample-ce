/*
 * Copyright (c) 2005-2013 Vincent Vandenschrick. All rights reserved.
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
package org.jspresso.hrsample.view;

import javax.security.auth.Subject;

import org.jspresso.framework.view.descriptor.ICardNameSelector;
import org.jspresso.hrsample.model.Employee;

/**
 * A demo card name selector.
 * 
 * @author Vincent Vandenschrick
 */
public class EmployeeCardSelector implements ICardNameSelector {

  /**
   * {@inheritDoc}
   */
  @Override
  public String getCardNameForModel(Object model, Subject subject) {
    if (model == null) {
      return null;
    } else if (((Employee) model).isMarried()) {
      return "MARRIED";
    } else {
      return "NOT_MARRIED";
    }
  }

}
