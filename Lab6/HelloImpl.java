import HelloApp.HelloPOA;

public class HelloImpl extends HelloPOA {
    @Override
    public String sayHello() {
        return "Hello from the CORBA server!! Sanyam Agrawal - SE21UCSE192 - CSE3";
    }
}
