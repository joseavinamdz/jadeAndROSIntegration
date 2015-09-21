package myagents.quadcopter.gpsutils;

/**
 * Created by pmn on 9/20/15.
 */
public class GPSUtils {
    public static final int NORTH = 1;
    public static final int SOUTH = -1;

    public static final int EAST = 1;
    public static final int WEST = -1;

    public static final int UP = 1;
    public static final int DOWN = -1;

    public static final int NONE = 0;




    private GPSUtils(){};

    public static int[] generateWay(GPSPosition actual, GPSPosition objective){
        int[] dirs = new int[3];



        if (actual.getLatitude() > objective.getLatitude())
            dirs[0] = SOUTH;
        else if (actual.getLatitude() == objective.getLatitude())
            dirs[0] = NONE;
        else
            dirs[0] = NORTH;

        if (actual.getLongitude() > objective.getLongitude())
            dirs[1] = EAST;
        else if (actual.getLongitude() == objective.getLongitude())
            dirs[1] = NONE;
        else
            dirs[1] = WEST;

        if (actual.getAltitude() > objective.getAltitude())
            dirs[2] = DOWN;
        else if (actual.getAltitude() == objective.getAltitude())
            dirs[2] = NONE;
        else
            dirs[2] = UP;


        return dirs;
    }
}
