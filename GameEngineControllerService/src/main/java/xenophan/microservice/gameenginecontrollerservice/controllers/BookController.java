package xenophan.microservice.gameenginecontrollerservice.controllers;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.ribbon.proxy.annotation.Http;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import xenophan.microservice.gameenginecontrollerservice.model.AddDto;
import xenophan.microservice.gameenginecontrollerservice.model.Book;
import xenophan.microservice.gameenginecontrollerservice.model.User;
import xenophan.microservice.gameenginecontrollerservice.repos.BookRepository;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/api/book/")
public class BookController {
    @Autowired
    EurekaClient eurekaClient;

    @Value("${controlled.app.name}")
    String appName;

    @PostConstruct
    public void test(){
        System.out.println("***BOOK APPLICATION NAME " + appName + "***");
    }

    Random rn = new Random();


    @Autowired
    protected BookRepository myRepository;

    @GetMapping("get_all")
    public ResponseEntity<List<Book>> getMyProjects(){
        return ResponseEntity.ok(myRepository.findAll());
    }

    @PostMapping("add_new/title={title}/author={author}/cost={cost}")
    public ResponseEntity<Book> addNew(@PathVariable String title, @PathVariable String author, @PathVariable int cost){
        Book book = new Book(title, author, cost);
        return ResponseEntity.ok(myRepository.save(book));
    }

    private String imgPath = "MIREA_Gerb_Colour.png";

    private InputStream get_gerb() throws FileNotFoundException {
        return new FileInputStream(new File(imgPath));
    }

    @GetMapping("/img-response")
    public void getImage(HttpServletResponse resp) throws IOException {
        final InputStream in = get_gerb();
        resp.setContentType(MediaType.IMAGE_PNG_VALUE);
        IOUtils.copy(in, resp.getOutputStream());
    }

    @RequestMapping("/menu_item/{id}")
    public ResponseEntity<AddDto> getMenu(@PathVariable long id, HttpServletRequest httpServletRequest){
        String authHeader = httpServletRequest.getHeader("Authorization");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", authHeader);
        headers.add("username", user.getMail());
        headers.add("authorities", httpServletRequest.getHeader("authorities"));
        headers.add("auth-token", httpServletRequest.getHeader("auth-token"));

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity request = new HttpEntity("1", headers);
        Application application =  eurekaClient.getApplication("MENU-SERVICE");
        InstanceInfo info= application.getInstances().get(rn.nextInt(application.getInstances().size()));
        ResponseEntity<AddDto> response = restTemplate
                .exchange(info.getHomePageUrl() + "api/menu/item/" + id, HttpMethod.GET, request, AddDto.class);
        return response;
    }

}
