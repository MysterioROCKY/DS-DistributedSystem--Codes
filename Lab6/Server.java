import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextExtPackage.*;

public class Server {
    public static void main(String[] args) {
        try {
            // Initialize the ORB (Object Request Broker)
            ORB orb = ORB.init(args, null);

            // Get reference to the RootPOA and activate the POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Create the implementation object
            HelloImpl helloImpl = new HelloImpl();

            // Get the object reference from the servant
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(helloImpl);
            HelloApp.Hello href = HelloApp.HelloHelper.narrow(ref);

            // Bind the object reference in the Naming Service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            String name = "Hello";
            NameComponent[] path = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Server ready and waiting...");

            // Wait for invocations from clients
            orb.run();
        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }
    }
}
