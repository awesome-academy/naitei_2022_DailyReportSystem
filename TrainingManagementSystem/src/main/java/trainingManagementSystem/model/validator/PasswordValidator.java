package trainingManagementSystem.model.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator implements ConstraintValidator<PasswordConstraint, String> {

	@Override
	public void initialize(PasswordConstraint password) {
	}

	@Override
	public boolean isValid(String password, ConstraintValidatorContext cxt) {
		Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[{!@#\\\\\\/$&*~}]).{0,}$");
		Matcher matcher = pattern.matcher(password);
		return matcher.find();
	}

}
