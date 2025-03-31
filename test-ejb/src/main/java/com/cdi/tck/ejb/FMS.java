package com.cdi.tck.ejb;

import jakarta.ejb.Local;

@Local
public interface FMS {

    void climb() throws Exception;

    void descend() throws Exception;

    boolean isSameBean();

    boolean isApplicationScopeActive();

}
