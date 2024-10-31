package andersen.task.tickets.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.mifmif.common.regex.Generex;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class Ticket {
	private static final Generex TICKET_ID_GENERATOR = new Generex("[a-zA-Z1-9]{1,4}");
	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Getter
	private static final BigDecimal MAX_BACKPACK_WEIGHT = new BigDecimal("20.250");
	private final char[] ticketID;
	@Setter
	@Getter
	@Size (max = 10)
	private String consertHall;
	@Override
	public String toString() {
		return "Ticket [ticketID=" + getTicketID() + ", consertHall=" + consertHall + ", eventCode="
				+ eventCode + ", createdAt=" + getCreatedAt() + ", startsAt=" + getStartsAt() + ", isPromo=" + isPromo
				+ ", stadiumSector=" + stadiumSector + ", price=" + price + ", MAX_BACKPACK_WEIGHT = " + MAX_BACKPACK_WEIGHT + "]";
	}

	@Min (value = 100)
	@Max (value = 999)
	@Setter
	@Getter
	private int eventCode;
	private final Date createdAt;
	@Future
	private Calendar startsAt;
	@Getter
	@Setter
	private boolean isPromo;
	@Getter
	@Setter
	private SectorHall stadiumSector;
	@Getter
	@Setter
	@Positive
	private BigDecimal price;
	
	public Ticket() {
		char[] generatedId = TICKET_ID_GENERATOR.random().toCharArray();
		ticketID = new char[generatedId.length];
		for (int i = 0; i < generatedId.length; i++) {
			this.ticketID[i] = generatedId[i];
		}
		this.createdAt = new Date();
	}
	
	public Ticket(String consertHall, int eventCode, Calendar startsAt) {
		this();
		this.consertHall = consertHall;
		this.eventCode = eventCode;
		this.startsAt = startsAt;
	}
	
	public Ticket(String consertHall, int eventCode, Calendar startsAt, boolean isPromo, SectorHall stadiumSector, double price)
	{
		this(consertHall, eventCode, startsAt);
		this.isPromo = isPromo;
		this.stadiumSector = stadiumSector;
		this.price = new BigDecimal(price).setScale(2, RoundingMode.HALF_UP);
	}
	
	public String getTicketID()
	{
		return String.copyValueOf(ticketID);
	}

	public String getCreatedAt() {
		return DATETIME_FORMAT.format(createdAt);
	}
	
	public String getStartsAt() {
		return DATETIME_FORMAT.format(startsAt.getTime());
	}
}
