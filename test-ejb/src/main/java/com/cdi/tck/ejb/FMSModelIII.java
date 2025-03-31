package com.cdi.tck.ejb;

import com.cdi.tck.application.SimpleApplicationBean;
import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.ejb.Timeout;
import jakarta.ejb.Timer;
import jakarta.ejb.TimerService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.enterprise.inject.spi.BeanManager;
import jakarta.inject.Inject;

@Stateless
public class FMSModelIII implements FMS {

    private static final String CLIMB_COMMAND = "ClimbCommand";
    private static final String DESCEND_COMMAND = "DescendCommand";
    private static volatile boolean applicationScopeActive;
    private static volatile double beanId;
    private static volatile boolean sameBean;

    private static boolean climbed;
    private static boolean descended;

    @Resource
    TimerService timerService;

    @Inject
    BeanManager beanManager;

    @Inject
    Instance<SimpleApplicationBean> simpleApplicationBeanInstance;

    @SuppressWarnings("checkstyle:MagicNumber")
    public void climb() throws Exception {
        timerService.createTimer(200, CLIMB_COMMAND);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public void descend() throws Exception {
        timerService.createTimer(100, DESCEND_COMMAND);
    }

    @Timeout
    public void timeout(Timer timer) {
        if (beanManager.getContext(ApplicationScoped.class).isActive()) {
            applicationScopeActive = true;
            if (beanId > 0.0) {
                if (beanId == simpleApplicationBeanInstance.get().getId()) {
                    sameBean = true;
                }
            } else {
                beanId = simpleApplicationBeanInstance.get().getId();
            }
        }
        // CDITCK-221 - applicationScopeActive, beanId and sameBean have been set and are testable
        if (timer.getInfo().equals(CLIMB_COMMAND)) {
            climbed = true;
        }
        if (timer.getInfo().equals(DESCEND_COMMAND)) {
            descended = true;
        }
    }

    public boolean isApplicationScopeActive() {
        return applicationScopeActive;
    }

    public boolean isSameBean() {
        return sameBean;
    }

    public static boolean isClimbed() {
        return climbed;
    }

    public static boolean isDescended() {
        return descended;
    }

    public static void reset() {
        beanId = 0.0d;
        climbed = false;
        descended = false;
        applicationScopeActive = false;
        sameBean = false;
    }

}
