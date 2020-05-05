public class NBody{

	public static void main(String[] args) {
		/* Collecting All Needed Input*/
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = NBody.readRadius(filename);
		Body[] bodies = NBody.readBodies(filename);

		/* Drawing the Background and Bodies*/
		String imageToDraw = "images/starfield.jpg";
		StdDraw.enableDoubleBuffering();
		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0,0,imageToDraw);
		for(Body b:bodies){
			b.draw();
		}
		StdDraw.show();

		/*Creating an animation*/
		double time = 0;
		while (time <= T){
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];
			for(int i=0;i<bodies.length;i++){
				xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
				yForces[i]=bodies[i].calcNetForceExertedByY(bodies);
			}
			for(int i=0;i<bodies.length;i++){
				bodies[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0,0,imageToDraw);
			for(Body b:bodies){
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time+=dt;



		}



		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}






	}

	public static double readRadius(String fileName){
		In in = new In(fileName);
		in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String fileName){
		In in = new In(fileName);
		int N = in.readInt();
		in.readDouble();
		Body[] allBodys = new Body[N];
		for (int i=0;i<N ;i++ ) {
			Double xxPos = in.readDouble();
			Double yyPos = in.readDouble();
			Double xxVel = in.readDouble();
			Double yyVel = in.readDouble();
			Double mass = in.readDouble();
			String img = in.readString();
			allBodys[i]=new Body(xxPos,yyPos,xxVel,yyVel,mass,img);
		}


		return allBodys;

	}

}