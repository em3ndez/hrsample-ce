/*
 * Generated by Design2see. All rights reserved.
 */
package com.d2s.framework.hrsample.model;

/**
 * Traceable component.
 * <p>
 * Generated by Design2see. All rights reserved.
 * <p>
 *
 * @author Generated by Design2see
 * @version $LastChangedRevision$
 */
public interface Traceable {

  /**
   * Gets the createTimestamp.
   *
   * @hibernate.property
   *           type = "timestamp"
   * @hibernate.column
   *           name = "CREATE_TIMESTAMP"
   * @return the createTimestamp.
   */
  java.util.Date getCreateTimestamp();

  /**
   * Sets the createTimestamp.
   *
   * @param createTimestamp
   *          the createTimestamp to set.
   */
  void setCreateTimestamp(java.util.Date createTimestamp);

  /**
   * Gets the lastUpdateTimestamp.
   *
   * @hibernate.property
   *           type = "timestamp"
   * @hibernate.column
   *           name = "LAST_UPDATE_TIMESTAMP"
   * @return the lastUpdateTimestamp.
   */
  java.util.Date getLastUpdateTimestamp();

  /**
   * Sets the lastUpdateTimestamp.
   *
   * @param lastUpdateTimestamp
   *          the lastUpdateTimestamp to set.
   */
  void setLastUpdateTimestamp(java.util.Date lastUpdateTimestamp);

}