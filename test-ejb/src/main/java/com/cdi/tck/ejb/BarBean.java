package com.cdi.tck.ejb;

import com.cdi.tck.application.SimpleApplicationBean;
import jakarta.ejb.AsyncResult;
import jakarta.ejb.Asynchronous;
import jakarta.ejb.Stateless;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.ContextNotActiveException;
import jakarta.enterprise.context.spi.Context;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;
import java.util.concurrent.Future;

@Stateless
@Asynchronous
public class BarBean {

    @Inject
    BeanManager beanManager;

    @Inject
    SimpleApplicationBean simpleApplicationBean;

    /**
     * Async computation.
     *
     * @return
     */
    public Future<Double> compute() {

        Double result = null;
        Context applicationContext = null;

        try {
            applicationContext = beanManager.getContext(ApplicationScoped.class);
        } catch (ContextNotActiveException e) {
            // No-op
        }

        if (applicationContext == null || !applicationContext.isActive() || simpleApplicationBean == null) {
            result = -1.00;
        } else {
            result = simpleApplicationBean.getId();
        }
        return new AsyncResult<Double>(result);
    }

}
