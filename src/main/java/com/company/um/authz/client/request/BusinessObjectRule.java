package com.company.um.authz.client.request;

import java.util.List;

/**
 * Business Object Rules that are defined by role, to be additionally imposed on evaluating permission.
 * If Business Object is a collection, only individual items will be available as businessObject argument.
 * One rule should check only one condition like BO.project = 'airtel-ftth'.
 *
 */
public interface BusinessObjectRule {
  boolean validate(Object authenticatedUser, Object businessObject, List<String> args) throws Exception;
}
