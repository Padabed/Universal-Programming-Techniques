package pl.edu.pjwstk.utp.unitTests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RegexUtility {

	private final Pattern _pattern;
	
	public RegexUtility(String pattern) {
		//
		_pattern = Pattern.compile(pattern);
	}
	
	public boolean matches(String input) {
		Matcher matcher = _pattern.matcher(input);
		return matcher.matches();
	}
}