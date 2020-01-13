package mate.academy.internetshop.controller;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import mate.academy.internetshop.library.Injector;
import org.apache.log4j.Logger;

public class InjectInitializer implements ServletContextListener {
    final static Logger logger = Logger.getLogger(InjectInitializer.class);
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
