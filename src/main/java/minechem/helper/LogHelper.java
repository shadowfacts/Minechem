package minechem.helper;

import minechem.Config;
import minechem.Compendium;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Helper class for logging
 *
 * @author way2muchnoise
 */
public class LogHelper
{

    private static final Logger log = LogManager.getLogger(Compendium.Naming.id);

    /**
     * Used for logging when debug is turned on in the config
     *
     * @param obj object to log
     */
    public static void debug(Object obj)
    {
        if (Config.debugMode)
        {
            log(Level.INFO, obj);
        }
        else
        {
            log(Level.DEBUG, obj);
        }
    }

    /**
     * Used for logging an exception
     *
     * @param obj       object to log
     * @param exception exception to log
     * @param level     level of the log
     */
    public static void exception(Object obj, Throwable exception, Level level)
    {
        log.log(level, String.valueOf(obj), exception);
    }

    /**
     * Used for logging an exception
     *
     * @param exception exception to log
     * @param level     level of the log
     */
    public static void exception(Throwable exception, Level level)
    {
        log.log(level, exception);
    }

    /**
     * Used for logging a warning
     *
     * @param obj
     */
    public static void warn(Object obj)
    {
        log(Level.WARN, obj);
    }

    /**
     * Used for logging in any case
     *
     * @param obj object to log
     */
    public static void info(Object obj)
    {
        log(Level.INFO, obj);
    }

    /**
     * General logging method
     *
     * @param level Level of the log
     * @param obj   object to log
     */
    public static void log(Level level, Object obj)
    {
        log.log(level, String.valueOf(obj));
    }

}
