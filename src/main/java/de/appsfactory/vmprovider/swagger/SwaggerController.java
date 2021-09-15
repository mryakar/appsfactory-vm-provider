package de.appsfactory.vmprovider.swagger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Swagger controller class.
 *
 * @author mryakar
 */
@Controller
public class SwaggerController {

    public final static String SWAGGER_REDIRECT_URL_1 = "/swagger";
    public final static String SWAGGER_REDIRECT_URL_2 = "/api-docs";

    @RequestMapping({
            SWAGGER_REDIRECT_URL_1,
            SWAGGER_REDIRECT_URL_2
    })

    @ApiIgnore
    public String Home() {
        return "redirect:/swagger-ui/index.html";
    }
}
