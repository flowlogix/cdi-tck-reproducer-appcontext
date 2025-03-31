package com.cdi.tck.tests;

import com.cdi.tck.ejb.BarBean;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import com.cdi.tck.ejb.FooRemote;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import lombok.SneakyThrows;

@Singleton
@Startup
public class AppContextSharedTest {
    @Inject
    BarBean bar;

    @EJB(lookup = "java:global/remote-ejb/com.cdi.tck-remote-ejb-1.x-SNAPSHOT/FooBean!com.cdi.tck.ejb.FooRemote")
    FooRemote fooRemote;

    @PostConstruct
    @SneakyThrows
    void init() {
        System.out.format("barPing: %f\n", bar.compute().get());

        System.out.println("AppContextSharedTest initialized");
        System.out.format("fooPing: %f\n", fooRemote.ping());
    }
}
