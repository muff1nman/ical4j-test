package com.credera.andrewdemaria.testing.calendar.iCal4j;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Calendar;

import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.SimpleHostInfo;
import net.fortuna.ical4j.util.UidGenerator;

/**
 * - Hour long by default (Notice in the appointment)
 */
public class App  {
	public static final boolean VALIDATE = true;
	public static final boolean WRITE_TO_FILE = true;
	
	public static final String STORE_NAME = "Pep Boys Nowhere Store";
	public static final String STORE_STREET_ADDRESS = "123 Nowhere St";
	public static final String STORE_CITY = "St. Elmo";
	public static final String STORE_STATE = "CO";
	public static final String STORE_ZIP = "10298";
	public static final String STORE_DELIM = ", ";
	
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        
        // new calendar
        net.fortuna.ical4j.model.Calendar c = createCalendar();
        
        // new output stream
        CalendarOutputter out = new CalendarOutputter(VALIDATE);
        
        Writer w;
        // write it out to a stream
        try {
        	if (WRITE_TO_FILE) {
        		w = new OutputStreamWriter(new FileOutputStream(new File("ics-test-" + "" + ".ics")));
        	} else {
        		w = new OutputStreamWriter(System.out);
        	}

			out.output(c, w);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // create an event
        
        // create an empty calendar
        
        // add an event to the calendar
        
        // add alarms to the calendar
    }
    
    public static String dateForFilename(java.util.Calendar instance) {
    	return "" + 
                "Y" + instance.get(Calendar.YEAR) + 
    			"M" + instance.get(Calendar.MONTH) + 
    			"D" + instance.get(Calendar.DATE) + 
    			"H" + instance.get(Calendar.HOUR_OF_DAY) + 
    			"M" + instance.get(Calendar.MINUTE);
    }
    
    public static String dateForFilename(java.util.Date date) {
    	return date.toString();
    }
    
    public static net.fortuna.ical4j.model.Calendar createCalendar() {
    	// TODO
    	net.fortuna.ical4j.model.Calendar calendar = new net.fortuna.ical4j.model.Calendar();
    	calendar.getProperties().add(new ProdId("-//Provider Name//iCal4j//EN"));
    	calendar.getProperties().add(Version.VERSION_2_0);
    	calendar.getProperties().add(CalScale.GREGORIAN);
    	calendar.getComponents().add(createEvent());
    	return calendar;
    }
    
    
    public static VEvent createEvent() {
    	net.fortuna.ical4j.model.DateTime start = new net.fortuna.ical4j.model.DateTime(eventStartDateTime());
    	System.out.println(dateForFilename(start));
    	VEvent appointment = new VEvent(start, duration(), summary());
    	appointment.getProperties().add(generateUID());
    	appointment.getProperties().add(new Description(notes()));
    	appointment.getProperties().add(new Location(createAddress()));
    	return appointment;
    }
    
    public static Uid generateUID() {
    	UidGenerator g = new UidGenerator(new SimpleHostInfo("www.provider.com"), "1234");
    	return g.generateUid();
    }
    
    public static String summary() {
    	return "Your appointment at Provider!";
    }
    
    public static String notes() {
    	return "here in lies the notes section ofthis calader event.  notice i have very bad spleeing but am just trying to get a long string going here";
    }
    
    public static java.util.Date eventStartDateTime() {
    	// TODO check args for timezome?
    	java.util.Calendar calendar = java.util.Calendar.getInstance();
    	calendar.set(java.util.Calendar.DAY_OF_MONTH, 16);
    	calendar.set(java.util.Calendar.MONTH, java.util.Calendar.AUGUST);
    	calendar.set(java.util.Calendar.HOUR_OF_DAY, 14);
    	calendar.set(java.util.Calendar.MINUTE, 5);
    	System.out.println(calendar);
    	System.out.println(dateForFilename(calendar));
    	System.out.println(dateForFilename(calendar.getTime()));
    	return calendar.getTime();
    }
    
    public static Dur duration() {
    	return new Dur(0,1,0,0);
    }
    
//    public static VVenue createAddress() {
//    	PropertyList address = new PropertyList();
//    	address.add(new Name("Pep Boys Nowhere Store"));
//    	address.add(new StreetAddress("1234 Nowhere St."));
//    	address.add(new Country("US"));
//    	address.add(new Postalcode("12301"));
//    	return new VVenue();
//    }
    
    public static String createAddress() {
//    	return STORE_NAME + "\n" + STORE_STREET_ADDRESS + "\n" + STORE_CITY + ", " + STORE_STATE + "  " + STORE_ZIP;
    	return STORE_STREET_ADDRESS + STORE_DELIM  + STORE_CITY + STORE_DELIM  + STORE_STATE + STORE_DELIM  + STORE_ZIP; 

    }
    
    

    
}
