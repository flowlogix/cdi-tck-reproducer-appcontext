package com.cdi.tck.ejb;

import jakarta.ejb.Remote;

@Remote
public interface TestRemote {
    void test();
}
