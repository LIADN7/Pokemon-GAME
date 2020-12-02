package api;

public class GeoLocation implements geo_location{
	private double x , y , z;
	
	public GeoLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	@Override
	public double x() {
		// TODO Auto-generated method stub
		return this.x;
	}

	@Override
	public double y() {
		// TODO Auto-generated method stub
		return this.y;
	}

	@Override
	public double z() {
		// TODO Auto-generated method stub
		return this.z;
	}

	@Override
	public double distance(geo_location g) {
		// TODO Auto-generated method stub
		double dis = Math.pow(x - g.x(), 2) + Math.pow(y - g.y(), 2) +Math.pow(z - g.z(), 2);
		dis = Math.sqrt(dis);
		return dis;
	}
	
}
