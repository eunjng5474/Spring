package polymorphism3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tv")
public class LgTV implements TV {
	
	// Speaker 타입의 객체를 메모리에서 찾아 객체의 주소를 speaker 변수에 할당한다.
	@Autowired // Type Injection
	private Speaker speaker;
	
	public LgTV() {
		System.out.println("===> LgTV 생성");
	}
	public void powerOn() {
		System.out.println("LgTV---전원 켠다.");
	}
	public void powerOff() {
		System.out.println("LgTV---전원 끈다.");
	}
	public void volumeUp() {
		speaker.volumeUp();
	}
	public void volumeDown() {
		speaker.volumeDown();
	}
}
