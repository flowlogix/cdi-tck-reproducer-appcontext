package com.cdi.tck.rest;

import com.cdi.tck.ejb.TestRemote;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Path("/bean")
public class ApiResource {
    @GET
    public void getFooResource() throws NamingException {
        TestRemote fooRemote = (TestRemote) new InitialContext()
                .lookup("java:global/test-ejb/test-ejb/AppContextSharedTest!com.cdi.tck.ejb.TestRemote");
        fooRemote.test();
    }
}
