public class Main {
    public static void main(String[] args) {
        DriveDirection driveDirection = new DriveDirection(
            // Forward direction, 0 to 1
            0, 
            // Strafe (sideways) direction, 0 to 1
            0, 
            // Rotation, 0 to 1
            1, 
            // Current facing, 0 to 2pi; radians
            0
        );

        driveDirection.zero();

        WheelsState wheelsState = new WheelsState(driveDirection);


        wheelsState.optimizePos(new WheelsState(
            // current wheels pos, FR, FL, BR, BL
            Math.toRadians(-0), 
            Math.toRadians(-0), 
            Math.toRadians(-0), 
            Math.toRadians(-0)
        ));

        double[] wheelAngles = wheelsState.getAngles();
        double FR = Math.toDegrees(wheelAngles[0]);
        double FL = Math.toDegrees(wheelAngles[1]);
        double BR = Math.toDegrees(wheelAngles[2]);
        double BL = Math.toDegrees(wheelAngles[3]);

        System.out.println(Math.round(FL) + "\t  |  " + Math.round(FR));
        System.out.println("------------------");
        System.out.println(Math.round(BL) + "\t  |  " + Math.round(BR));
    }
}
