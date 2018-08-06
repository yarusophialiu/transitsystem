package model;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

class CardHandler {

    private static final Logger logger = Logger.getLogger(CardHandler.class.getName());
    private static final Handler consoleHandler = new ConsoleHandler();

    /** An array list to store the 3 most recent trips. */
    ArrayList<Trip> myTrip = new ArrayList<>();

    /** The card's owner. */
    RegularUser user;

    static AdminUser adminUser;

    CardHandler(){
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    void helpLog(Level level, String message){
        logger.log(level, message);
    }

    String recentTripString() {
        StringBuilder output = new StringBuilder();
        int i = 1;
        for (Trip trip : myTrip) {
            String tripInfo =
                    "Trip "
                            + i
                            + ": begin at "
                            + trip.getEntrance().getName()
                            + " "
                            + trip.getEnterTime()
                            + " end at "
                            + trip.getExit().getName()
                            + " using "
                            + trip.getTransportation()
                            + " "
                            + trip.getExitTime()
                            + " "
                            + "\n";
            output.append(tripInfo);
            i++;
        }
        return output.toString();
    }

    public static void setAdminUser(AdminUser newAdminUser){
        adminUser = newAdminUser;
    }

    public User getUser(){
        return user;
    }

    /** Setter for CardController.user. */
    void setUser(RegularUser user) {
        this.user = user;
    }

}
