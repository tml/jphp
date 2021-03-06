package webserver.classes;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;
import webserver.WebserverJvmTestCase;


@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebServerLibraryTest extends WebserverJvmTestCase {
    @Test
    public void testBasic() {
        check("webserver/basic_001.php");
        check("webserver/basic_002.php");
    }

    @Test
    public void testPostBody() {
        check("webserver/post_body.php");
    }

    @Test
    public void testResponse() {
        check("webserver/response.php");
    }

    @Test
    public void testAutoloaders() {
        check("webserver/autoloaders.php", true);
    }
}
