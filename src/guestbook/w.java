package guestbook;

public class w implements Runnable {
	String f2, s2;
	public w(String f, String s) {
		f2 = f;
		s2 = s;
	}
	public void run() {
		stat.w2f1(f2, s2);
	}
}
