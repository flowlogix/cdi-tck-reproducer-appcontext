package com.cdi.tck.tests;

import com.cdi.tck.ejb.BarBean;
import com.cdi.tck.ejb.TestRemote;
import jakarta.ejb.EJB;
import com.cdi.tck.ejb.FooRemote;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.inject.Inject;
import lombok.SneakyThrows;

@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AppContextSharedTest implements TestRemote {
    @Inject
    BarBean bar;

    @EJB(lookup = "java:global/remote-ejb/test-ejb/FooBean!com.cdi.tck.ejb.FooRemote")
    FooRemote fooRemote;

    @Override
    @SneakyThrows
    public void test() {
        System.out.format("barPing: %f\n", bar.compute().get());

        System.out.println("AppContextSharedTest initialized");
        System.out.format("fooPing: %f\n", fooRemote.ping());
    }
}
