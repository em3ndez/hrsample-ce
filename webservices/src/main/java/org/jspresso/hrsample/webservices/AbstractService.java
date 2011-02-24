/*
 * Copyright (c) 2005-2011 Vincent Vandenschrick. All rights reserved.
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
package org.jspresso.hrsample.webservices;

import java.util.Locale;

import org.jspresso.framework.application.startup.AbstractBackendStartup;


/**
 * Abstract base service implementation.
 * 
 * @version $LastChangedRevision$
 * @author Vincent Vandenschrick
 */
public abstract class AbstractService extends AbstractBackendStartup {
  
  /**
   * Constructs a new <code>AbstractService</code> instance.
   * 
   */
  protected AbstractService() {
    start();
  }

  /**
   * {@inheritDoc}
   */
  public void start() {
    startController();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected String getApplicationContextKey() {
    return "hrsample-backend-context";
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Locale getStartupLocale() {
    return Locale.ENGLISH;
  }

}
