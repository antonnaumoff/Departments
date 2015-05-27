package utils.validators;

import models.Department;
import models.Employee;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import net.sf.oval.exception.ValidationFailedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created on 25.03.15.
 */

public class OvalValidator {

    public static  Map<String, String> validate(Employee validatedObject) throws IllegalArgumentException, ValidationFailedException {

        HashMap<String, String> map = new HashMap<String, String>();
        Validator valid = new Validator();
        List<ConstraintViolation> violations = valid.validate(validatedObject);
        if (violations.size() > 0) {
            for (ConstraintViolation vio : violations) {
                map.put((((FieldContext) vio.getContext()).getField().getName()), vio.getMessage());
            }
        }
        return map;
    }

    public static  Map<String, String> validate(Department validatedObject) throws IllegalArgumentException, ValidationFailedException {

        HashMap<String, String> map = new HashMap<String, String>();
        Validator valid = new Validator();
        List<ConstraintViolation> violations = valid.validate(validatedObject);
        if (violations.size() > 0) {
            for (ConstraintViolation vio : violations) {
                map.put((((FieldContext) vio.getContext()).getField().getName()), vio.getMessage());
            }
        }
        return map;
    }


}
