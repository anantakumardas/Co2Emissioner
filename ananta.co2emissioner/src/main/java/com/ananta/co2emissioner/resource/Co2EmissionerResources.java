package com.ananta.co2emissioner.resource;



import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.ListResourceBundle;

public class Co2EmissionerResources extends ListResourceBundle {


	private static ResourceBundle bundle = ResourceBundle.getBundle(Co2EmissionerResources.class.getName());
	public static final String ERROR = bundle.getString(Co2EmissionerResourcesID.ERROR);
	public static final String CAUSE = bundle.getString(Co2EmissionerResourcesID.CAUSE);
	public static final String ACTION = bundle.getString(Co2EmissionerResourcesID.ACTION);

	@Override
	protected Object[][] getContents() {
		return contents;
	}


	public final Object contents[][]=
		{ 

			{ Co2EmissionerResourcesID.ERROR, "ERROR" },
			{ Co2EmissionerResourcesID.CAUSE, "CAUSE" },
			{ Co2EmissionerResourcesID.ACTION, "ACTION" },			 
			{ Co2EmissionerResourcesID.WARNING, "WARNING"},
			{ Co2EmissionerResourcesID.SEVERE, "SEVERE"},

			{ Co2EmissionerResourcesID.ERROR_INVALID_ARGUMENT, " The argument is invalid."},
			{ Co2EmissionerResourcesID.CAUSE_INVALID_ARGUMENT, " The argument is not supported ."},
			{ Co2EmissionerResourcesID.ACTION_INVALID_ARGUMENT, " Provide a valid argument."},

			{ Co2EmissionerResourcesID.INFO_USE_HELP, "\n Use the \"-help\" option for more information about arguments."},
			
		};

	public static String getMessage(String key) {
		return getMessage(bundle, key, null) ;
	}

	public static String getMessage(String key, String argValue) {
		return getMessage(bundle, key, new String[]{argValue}) ;
	}

	public static String getMessage(String key, String[] args) {
		return getMessage(bundle, key, args) ;
	}

	protected static final String getMessage(ResourceBundle bundle, String key, Object[] arguments) {
		String res = null;
		try {
			if (key.startsWith("CHGHOST-")) {
				if (bundle.containsKey(key)) {
					if (key.contains("-"+ ERROR)) {

						String keyCode = key.replace("-" + ERROR, "");
						res = "\n [" + keyCode + "-" + ERROR + "] " + MessageFormat.format(bundle.getString(keyCode + "-" + ERROR), arguments);
						if (bundle.containsKey(keyCode + "-" + CAUSE)) {
							res += "\n [" + keyCode + "-" + CAUSE + "] " + MessageFormat.format(bundle.getString(keyCode + "-" + CAUSE), arguments);
						}
						if (bundle.containsKey(keyCode + "-" + ACTION)) {
							res += "\n [" + keyCode + "-" + ACTION + "] " + MessageFormat.format(bundle.getString(keyCode + "-" + ACTION), arguments);
						} 
					} else {
						res = MessageFormat.format(bundle.getString(key), arguments);
						res = "[" + key + "] " + res;
					}
				} else {
					res = MessageFormat.format(bundle.getString(key), arguments);
				}
			} else {
				res = MessageFormat.format(bundle.getString(key), arguments);
			}
		} catch (MissingResourceException me) {

		}
		return res;
	}

}
