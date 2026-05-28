package InterfaceEnhancement;

public interface DCLab {
	 void JFS();
	 void PFS();
	 default void MernStack() {
		 System.out.println("MernStack");
	}
	 
	 static void DevOps() {
		 System.out.println("Sushma mam handles the devops to entire DCL");
	}
}
