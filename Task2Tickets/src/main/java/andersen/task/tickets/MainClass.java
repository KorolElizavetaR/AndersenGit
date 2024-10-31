package andersen.task.tickets;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.management.InstanceNotFoundException;

import andersen.task.tickets.model.SectorHall;
import andersen.task.tickets.model.Ticket;
import andersen.task.tickets.service.TicketService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class MainClass {
	List<Ticket> tickets = new ArrayList<>();

	public static void main(String[] args) {
		TicketService service = new TicketService();
		Ticket ticket1 = service.addTicket(new Ticket("TEST1", 345,
				new Calendar.Builder().setDate(2024, 11, 30).setTimeOfDay(19, 00, 0).build()));

		service.addTicket(new Ticket("55555555555555555", 345,
				new Calendar.Builder().setDate(2024, 11, 30).setTimeOfDay(19, 00, 0).build())); // ConstraintViolation

		service.addTicket(new Ticket("TEST2", 345,
				new Calendar.Builder().setDate(2020, 11, 30).setTimeOfDay(19, 00, 0).build())); // ConstraintViolation

		service.getAllTickets().stream().forEach(System.out::println);

		try {
			System.out.println(service.getTicketByID(ticket1.getTicketID()));
		} catch (InstanceNotFoundException e) {
			System.out.printf("Ticket with id %s is not found", ticket1.getTicketID());
		}
	}

}
