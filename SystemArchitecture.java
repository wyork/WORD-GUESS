/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.GTCSoftware.wordGuess;

import java.util.logging.Logger;
import org.aspectj.lang.annotation.Pointcut;



/**
 *
 * @author Bill
 */
public class SystemArchitecture {
    
    /**
   * A join point is in the service layer if the method is defined
   * in a type in the com.xyz.someapp.service package or any sub-package
   * under that.
   */
  @Pointcut("within(com.GTCSoftware.wordGuess.service..*)")
  public void inServiceLayer() {}
    private static final Logger LOG = Logger.getLogger(SystemArchitecture.class.getName());

    
}
