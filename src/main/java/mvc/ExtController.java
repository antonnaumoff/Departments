package mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class ExtController {

    @RequestMapping(value = "/AuthenticateService.svc/GetAuthInfo", method = RequestMethod.POST, produces = "application/json")
    public Object index() throws Exception {
        return new ArrayList();
    }
}
