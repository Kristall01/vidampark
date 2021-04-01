package hu.g14de.i18n.messages;

public interface Message
{

	String original();
	String getValue(StringBuilder baseBuilder, Object... params);

}
