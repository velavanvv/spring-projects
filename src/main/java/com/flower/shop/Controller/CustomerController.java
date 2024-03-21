package com.flower.shop.Controller;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
/* import org.springframework.data.jpa.domain.JpaSort.Path; */

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.flower.shop.Entity.ApiResponse;
import com.flower.shop.Entity.CustomerEntity;
import com.flower.shop.Entity.imgEntity;
import com.flower.shop.Repository.CustomerRepository;
import com.flower.shop.Repository.imageRepository;
import com.flower.shop.Service.CustomerService;
import com.flower.shop.Service.imageService;

/*  import jakarta.persistence.criteria.Path;  */

@RestController

public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    private imageService imageService;
    @Autowired
    private imageRepository imageRepository;
    @Autowired
    private CustomerRepository customRepository;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/log")
    public ModelAndView re() {
        ModelAndView mav = new ModelAndView("Login");
        return mav;
    }

    @GetMapping("/gallery/{customerId}")
    public ModelAndView gallery(@PathVariable("CustomerId") int id, Model model) throws IOException {
        CustomerEntity entity = customerService.getCustomer(id);
        imgEntity imgEntity = imageRepository.findByCustomerId(entity);
        String imageDirectory = "src/main/resources/static/img";
        String[] img = imgEntity.getName().split(",");

        List<byte[]> imageBytes = new ArrayList<byte[]>();
        for (String imgename : img) {
            byte[] imagebytes = imageService.getImages(imageDirectory, imgename);
            imageBytes.add(imagebytes);
        }

        ModelAndView mav = new ModelAndView("Gallery");
        model.addAttribute("images", imageBytes);
        return mav;
    }

    @GetMapping("/home/{customerId}")
    public ModelAndView home(@PathVariable("customerId") int id, Model model) {
        CustomerEntity entity = customerService.getCustomer(id);

        ModelAndView mav = new ModelAndView("Home");
        model.addAttribute("customer", entity);
        return mav;
    }

    @PostMapping("/uplode/{customerId}")
    public ModelAndView uplodImage(@RequestParam("file") MultipartFile file, @PathVariable("customerId") int id)
            throws Exception {

        String uplodeDir = "src/main/resources/static/img";
        CustomerEntity customer = customRepository.findByCustomerId(id);
        imgEntity entity = imageRepository.findByCustomerId(customer);
        if (entity != null) {
            String imageName = entity.getName();
            imageName += imageService.saveImage(uplodeDir, file) + ",";
            entity.setName(imageName);
            imageRepository.save(entity);
        } else {
            String imageName = "";
            imgEntity newEntity = new imgEntity();
            imageName += imageService.saveImage(uplodeDir, file) + ",";

            newEntity.setCustomerId(customer);
            newEntity.setName(imageName);
            imageRepository.save(newEntity);
        }
        /*
         * String filePath=uplodeDir+"/"+file.getOriginalFilename();
         * file.transferTo(new File(filePath));
         * File uplodePath = new File(uplodeDir);
         * if (!uplodePath.exists()) {
         * uplodePath.mkdirs();
         * }
         * File uplodeFile = new File(uplodePath.getAbsolutePath(),
         * file.getOriginalFilename());
         * file.transferTo(uplodeFile);
         * CustomerEntity entity = customerService.getCustomer(id);
         * customerService.saveimage(file, entity);
         */

        ModelAndView mav = new ModelAndView("redirect:/home/" + id);
        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute CustomerEntity loginRequest, Model model) {
        String email = loginRequest.getCustomerEmail();
        String password = loginRequest.getCustomerPassword();

        CustomerEntity customer = customerService.getCustomer(email);

        boolean user = customerService.checkCustomer(email, password);
        if (user == true) {

            ModelAndView mav = new ModelAndView("redirect:/home/" + customer.getCustomerId());
            return mav;

        } else {
            ModelAndView mav = new ModelAndView("Login");
            model.addAttribute("error", "invalid");
            return mav;

        }
    }

    @GetMapping("/reg")
    public ModelAndView reg() {
        ModelAndView mav = new ModelAndView("Register");
        return mav;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody CustomerEntity user) {

        customerService.saveCustomer(user);
        ApiResponse response = new ApiResponse(true, "Registration successful");

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);

    }
}
