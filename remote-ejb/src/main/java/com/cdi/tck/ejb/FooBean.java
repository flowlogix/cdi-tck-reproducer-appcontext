package com.cdi.tck.ejb;

import com.cdi.tck.application.SimpleApplicationBean;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.ContextNotActiveException;
import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;

@Stateless
public class FooBean implements FooRemote {
    @Inject
    BeanManager beanManager;

    @Inject
    SimpleApplicationBean simpleApplicationBean;

    @Override
    public Double ping() {

        Context applicationContext = null;

        try {
            applicationContext = beanManager.getContext(ApplicationScoped.class);
        } catch (ContextNotActiveException e) {
            // No-op
        }

        if (applicationContext == null || !applicationContext.isActive() || simpleApplicationBean == null) {
            return null;
        }
        return simpleApplicationBean.getId();
    }

}
