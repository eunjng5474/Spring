package polymorphism3;

public class TVContainer {
	
	public Object getBean(String tvId) {
		if(tvId.equals("lg")) {
			return new LgTV();
		} else if(tvId.equals("samsung")) {
			return new SamsungTV();
		} else if(tvId.equals("google")) {
			return new GoogleTV();
		}
		return null;
	}
}














