package LibraryManagementSystem.model;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class IssuedRecord{
private String bookId;
private String username;
private LocalDate issueDate;
private LocalDate dueDate;
private LocalDate returnDate;


public IssuedRecord(String bookId, String username, LocalDate issueDate, LocalDate dueDate) {
this.bookId = bookId;
this.username = username;
this.issueDate = issueDate;
this.dueDate = dueDate;
}


public String getBookId() { return bookId; }
public String getUsername() { return username; }
public LocalDate getIssueDate() { return issueDate; }
public LocalDate getDueDate() { return dueDate; }
public LocalDate getReturnDate() { return returnDate; }


public void setReturnDate(LocalDate returnDate) {
this.returnDate = returnDate;
}


public boolean isReturned() { return returnDate != null; }


public long getFine() {
long daysOverdue;
if (isReturned()) {
daysOverdue = ChronoUnit.DAYS.between(dueDate, returnDate);
} else {
daysOverdue = ChronoUnit.DAYS.between(dueDate, LocalDate.now());
}
return Math.max(daysOverdue, 0) * 5;
}
}