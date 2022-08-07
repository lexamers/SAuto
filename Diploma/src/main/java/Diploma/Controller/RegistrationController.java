package Diploma.Controller;

import Diploma.Translator;
import Diploma.Persistence.Entity.Role;
import Diploma.Persistence.Entity.User;
import Diploma.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Controller
public class RegistrationController extends SuperController{

    @Autowired
    private PasswordEncoder passwordEncoder;// кодировщик паролей

    @Autowired
    private UserService userService;// обслуживание пользователей

    @GetMapping("/registration") //регистрация
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam(value = "firstName") String firstName,// имя
                               @RequestParam(value = "lastName") String lastName, //фамилия
                               @RequestParam(value = "phoneNumber") String phoneNumber, //телефон
                               @RequestParam(value = "email") String email,
                               @RequestParam(value = "area") String area,// область
                               @RequestParam(value = "district") String district,// район
                               @RequestParam(value = "locality") String locality,// населенный пункт
                               @RequestParam(value = "password") String password,//пароль
                               @RequestParam(value = "passwordCheck") String passwordCheck,//проверка пароля
                               HttpServletResponse httpResponse,
                               Model model

    ) throws IOException {
        Pattern firstNamePattern = Pattern.compile("^[A-Za-zА-Яа-яёЁ\\- ]+$");
        Pattern lastNamePattern = Pattern.compile("^[A-Za-zА-Яа-яёЁ\\- ]+$");
        Pattern phoneNumberPattern = Pattern.compile("^(\\d{2}|[(]?[0-9]{2}[)])(\\d{7})$");
        Pattern emailPattern = Pattern.compile("^(?:[A-Za-z0-9!#$%&'*+\\=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+\\=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])$");
        Pattern areaPattern = Pattern.compile("^[A-Za-zА-Яа-яёЁ\\- ]+$");
        Pattern districtPattern = Pattern.compile("^[A-Za-zА-Яа-яёЁ\\- ]+$");
        Pattern localityPattern = Pattern.compile("^[A-Za-zА-Яа-яёЁ\\- ]+$");
        List<String> errors = new ArrayList<>();

        if (!firstNamePattern.matcher(firstName).matches()){
            errors.add(Translator.toLocale("validation.firstname"));
        }
        if (!lastNamePattern.matcher(lastName).matches()){
            errors.add(Translator.toLocale("validation.lastname"));
        }
        if (!phoneNumberPattern.matcher(phoneNumber).matches()){
            errors.add(Translator.toLocale("validation.phone"));
        }
        if (!emailPattern.matcher(email).matches()){
            errors.add(Translator.toLocale("validation.email"));
        }
        if (!areaPattern.matcher(area).matches()){
            errors.add(Translator.toLocale("validation.area"));
        }
        if (!districtPattern.matcher(district).matches()){
            errors.add(Translator.toLocale("validation.district"));
        }
        if (!localityPattern.matcher(locality).matches()){
            errors.add(Translator.toLocale("validation.locality"));
        }
        if (!password.equals(passwordCheck) ||
                password.length() < 8){
            errors.add(Translator.toLocale("validation.passwordmissmatch"));
        }

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setArea(area);
        user.setDistrict(district);
        user.setLocality(locality);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNumber(phoneNumber);

        Role role = new Role();
        role.setId();
        user.setRole(role);

        try {
            userService.addUser(user);
        } catch (Exception e) {
            errors.add(Translator.toLocale("validation.useralreadyexists"));
        }

        if (!errors.isEmpty()) {
            model.addAttribute("regErrors", errors);
            return "registration";
        }
        httpResponse.sendRedirect("/signIn");
        return null;
    }
}
