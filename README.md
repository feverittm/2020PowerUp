# 2020PowerUp
Port of the 2018 PowerUp Game code to the 2020 framework.

Continuing to learn about the new 2020 code structure including the execution structure.

Robot has the following subsystems:

1 - Drivetrain

    a - 6 MiniCims
  
    b - 2 TalonSRX with CTRE Encoders 
  
    c - 4 VictorSPX slaved to Talon
  
    d - NavX micro (connected to RoboRio via USB)

2 - Elevator

    a - 2 RS775Pro Motors on Veraplanetary gearboxes + custom spur gearbox
  
    b - Talox SRX with CTRE Encoder
  
    c - VictorSPX slaved to Talon
  
    d - Limit Switches for both upper and lower limits

3 - Intake

    a - 2 RS550 Motors on Versa-Planetary Lites
  
    b - 'Cube' sensor
  
    c - Pneimatic lift/release

4 - Climber

    a - 2 RS775Pro Motors on Veraplanetary gearboxes + custom spur gearbox
  
    b - Talox SRX with CTRE Encoder
  
    c - VictorSPX slaved to Talon
