package foo.bar.commands;

import java.util.Date;

/**
 * A generic command class. Upon instantiation, a new Date will be created to
 * store the time the command was issued.
 */
public abstract class Command {
    private final Date commandDate;

    protected Command() {
        commandDate = new Date();
    }

    public Date getCommandDate() {
        return commandDate;
    }
}
