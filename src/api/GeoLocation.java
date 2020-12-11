package api;

import gameClient.util.Point3D;

public class GeoLocation implements geo_location{
	private double x , y , z;
	
	public GeoLocation() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	public GeoLocation(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public GeoLocation(Point3D d) {
		this.x = d.x();
		this.y = d.y();
		this.z = d.z();

	}
	public GeoLocation(String s) {
		try {
            String[] a = s.split(",");
            x = Double.parseDouble(a[0]);
            y = Double.parseDouble(a[1]);
            z = Double.parseDouble(a[2]);
        }
        catch(IllegalArgumentException e) {
            System.err.println("ERR: got wrong format string for POint3D init, got:"+s+"  should be of format: x,y,x");
            throw(e);
        }
	}
	
	public GeoLocation(geo_location g) {
		this.x = g.x();
		this.y = g.y();
		this.z = g.z();
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
