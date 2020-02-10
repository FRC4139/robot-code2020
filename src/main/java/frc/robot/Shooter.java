package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter
{
    private WPI_TalonSRX shoot1;
    private WPI_TalonSRX shoot2;

<<<<<<< HEAD:src/main/java/frc/robot/shooter.java
    public shooter(int p1, int p2)
=======
    public Shooter(int p1)
>>>>>>> ee895075d220cd677a32b8d14769e8b226cd7c62:src/main/java/frc/robot/Shooter.java
    {
        shoot1 = new WPI_TalonSRX(p1);
        shoot2 = new WPI_TalonSRX(p2);
    }

    public void shootRun(double val)
    {
        shoot1.set(val);
        shoot2.set(-val);
    }
}
