package pro.sry.EmploeeMockito.service;

import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;
import pro.sry.EmploeeMockito.WorkingExceptions.IncorrectFirstNameException;
import pro.sry.EmploeeMockito.WorkingExceptions.IncorrectLastNameException;
@Service
public class ValidateService {
    public String validateLastName(String lastName) {
        if (!StringUtils.isAlpha(lastName)) {
            throw new IncorrectLastNameException();
        }
        return StringUtils.capitalize(lastName.toLowerCase());
    }

    public String validateFirstName(String firstName) {
        String[] firstNames = firstName.split("-");
        for (int i = 0; i < firstNames.length; i++) {
            if (!StringUtils.isAlpha(firstNames[i])) {
                throw new IncorrectFirstNameException();
            }
            firstNames[i] = StringUtils.capitalize(firstNames[i].toLowerCase());
        }
        return String.join("-", firstNames);
    }
}
