package cn.allbs.admin.config.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.util.Map;

/**
 * 类 AllbsErrorController
 *
 * @author ChenQi
 * @date 2024/4/1
 */
public class AllbsErrorController extends BasicErrorController {

    private final ObjectMapper objectMapper;

    public AllbsErrorController(ObjectMapper objectMapper,
                                ErrorAttributes errorAttributes,
                                ErrorProperties errorProperties) {
        super(errorAttributes, errorProperties);
        this.objectMapper = objectMapper;
    }

    @Override
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> body = getErrorAttributes(request, ErrorAttributeOptions.defaults());
        HttpStatus status = getStatus(request);
        response.setStatus(status.value());
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setObjectMapper(objectMapper);
        view.setContentType(MediaType.APPLICATION_JSON_VALUE);
        return new ModelAndView(view, body);
    }
}
