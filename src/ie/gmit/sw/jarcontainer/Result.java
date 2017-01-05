package ie.gmit.sw.jarcontainer;

public class Result 
{
	
	// fields for instability
		private Class cls;
		private double ceScore;
		private double caScore;
		private double instabilityScore;
		
		public Result() {}

		public Result(Class cl, double ce, double ca, double i) {
			super();
			this.cls = cl;
			this.ceScore = ce;
			this.caScore = ca;
			this.instabilityScore = i;
		}

		public Class getCl() {
			return cls;
		}

		public void setCl(Class cl) {
			this.cls = cl;
		}

		public double getCe() {
			return ceScore;
		}

		public void setCe(double ce) {
			this.ceScore = ce;
		}

		public double getCa() {
			return caScore;
		}

		public void setCa(double ca) {
			this.caScore = ca;
		}

		public double getI() {
			return instabilityScore;
		}

		public void setI(double i) {
			this.instabilityScore = i;
		}
}
