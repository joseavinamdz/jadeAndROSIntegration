package myagents.quadcopter.gpsutils;

import edu.wpi.rail.jrosbridge.messages.geometry.Vector3;

import java.util.DoubleSummaryStatistics;

/**
 * Created by pmn on 9/20/15.
 */
public class GPSUtils {
    public static final Float NORTH = (float) 1;
    public static final Float SOUTH = (float) -1;

    public static final Float EAST = (float) 1;
    public static final Float WEST = (float) -1;

    public static final Float UP = (float) 1;
    public static final Float DOWN = (float) -1;

    public static final Float NONE = (float) 0;




    private GPSUtils(){};

    public static Vector3 generateWay(GPSPosition actual, GPSPosition objective){
        Float[] dirs = new Float[3];

        System.out.println(objective);


        if (objective.getLatitude().isNaN())
            dirs[0] = NONE;
        else if (actual.getLatitude() > objective.getLatitude())
            dirs[0] = SOUTH;
        else
            dirs[0] = NORTH;

        if (objective.getLongitude().isNaN())
            dirs[1] = NONE;
        else if (actual.getLongitude() > objective.getLongitude())
            dirs[1] = EAST;
        else
            dirs[1] = WEST;

        if (objective.getAltitude().isNaN())
            dirs[2] = NONE;
        else if (actual.getAltitude() > objective.getAltitude())
            dirs[2] = DOWN;
        else
            dirs[2] = UP;

        for (int i=0; i<3; i++)
            dirs[i] = dirs[i]*2;

        return new Vector3(dirs[0], dirs[1], dirs[2]);
    }
}
