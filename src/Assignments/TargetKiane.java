
	package Assignments;

	import java.io.IOException;

	import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
	import org.jointheleague.ecolban.rpirobot.IRobotInterface;
	import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

	public class TargetKiane extends IRobotAdapter {
		Sonar sonar = new Sonar();
		
		public TargetKiane(IRobotInterface iRobot) {
			super(iRobot);
		}

		public static void main(String[] args) throws Exception {
			System.out.println("Try event listner, rev Monday 2030");
			IRobotInterface base = new SimpleIRobot();
			TargetKiane rob = new TargetKiane (base);
			rob.setup();
			while(rob.loop()){}
			rob.shutDown();
			
		}

		
		
		private void setup() throws Exception {
			//SETUP CODE GOES HERE!!!!!
		}
		

		private boolean loop() throws Exception{
			//LOOP CODE GOES HERE!!!!!
			readSensors(100);
			int[] lightBumpReadings = getLightBumps();
			driveDirect(200,200);
			if(lightBumpReadings[2]> 0 && lightBumpReadings [3] > 0){
				driveDirect(-200,-200);
				sleep(300);
				driveDirect(-200,200);
				sleep(300);
			}
			 if(lightBumpReadings[0]> 0){
				driveDirect(200,-200);
				sleep(500);
				
			}
			 else{
				 driveDirect(200,400);}
			
			if(getInfraredByte() == 248){
				driveDirect(100, 200);
				sleep(100);
			}else if(getInfraredByte() == 244 ){
				driveDirect(200, 100);
				sleep(100);
			}else if(getInfraredByte() == 252 ){
				driveDirect(200, 200);
				sleep(100);
			}else if(getInfraredByte() == 173 ){
				driveDirect(500, 500);
				sleep(100);
			}
			
			if(isHomeBaseChargerAvailable()){
				driveDirect(0, 0);
				return false;
			}
			
			return true;}
		
		
		
		private void sleep(int amt){
			try {
				Thread.sleep(amt);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void shutDown() throws IOException {
			reset();
			stop();
			closeConnection();
		}
	}


