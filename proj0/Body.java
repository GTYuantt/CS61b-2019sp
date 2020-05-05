public class Body{

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double G=6.67e-11;

	public Body(double xP, double yP, double xV, double yV, double m, String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Body(Body b){
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}

	public double calcDistance(Body b){

		double dx,dy,r2,r;
		dx=this.xxPos-b.xxPos;
		dy=this.yyPos-b.yyPos;
		r2=Math.pow(dx,2)+Math.pow(dy,2);
		r=Math.sqrt(r2);
		return r;

	}

	public double calcForceExertedBy(Body b){
		double m1=this.mass;
		double m2=b.mass;
		double r=this.calcDistance(b);
		double F=Body.G*m1*m2/Math.pow(r,2);
		return F;
	}

	public double calcForceExertedByX(Body b){
		double dx=b.xxPos-this.xxPos;
		double r=this.calcDistance(b);
		double F=this.calcForceExertedBy(b);
		double Fx=F*dx/r;
		return Fx;
	}

	public double calcForceExertedByY(Body b){
		double dy=b.yyPos-this.yyPos;
		double r=this.calcDistance(b);
		double F=this.calcForceExertedBy(b);
		double Fy=F*dy/r;
		return Fy;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double sum=0;
		for(Body b: allBodys){
			if(!this.equals(b)){
			double Fx=this.calcForceExertedByX(b);
			sum+=Fx;
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double sum=0;
		for(Body b: allBodys){
			if(!this.equals(b)){
			double Fy=this.calcForceExertedByY(b);
			sum+=Fy;
			}
		}
		return sum;
	}

	public void update(double t, double fx, double fy){
		double ax=fx/this.mass;
		double ay=fy/this.mass;
		this.xxVel=this.xxVel+ax*t;
		this.yyVel=this.yyVel+ay*t;
		this.xxPos=this.xxPos+this.xxVel*t;
		this.yyPos=this.yyPos+this.yyVel*t;
	}

	public void draw(){
		StdDraw.picture(this.xxPos,this.yyPos,"images/"+this.imgFileName);
	}





}