import static org.junit.Assert.*;
import org.junit.Test;

public class HelloWorldTest {
	public HelloWorld helloworld = new HelloWorld();
	@Test
	public void TestHello() {
		helloworld.Hello();
		assertEquals("Hello World!", helloworld.getStr());
	}

}