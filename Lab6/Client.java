import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExtPackage.*;

import HelloApp.Hello;
import HelloApp.HelloHelper;

public class Client {
    public static void main(String[] args) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);

            // Get a reference to the Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Resolve the object reference in the Naming Service
            String name = "Hello";
            Hello helloImpl = HelloHelper.narrow(ncRef.resolve_str(name));

            // Call the sayHello method
            String response = helloImpl.sayHello();
            System.out.println("Response from server: " + response);
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
