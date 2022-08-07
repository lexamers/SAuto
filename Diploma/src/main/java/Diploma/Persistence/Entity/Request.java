package Diploma.Persistence.Entity;

import Diploma.Service.RequestStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity (name="Request")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", referencedColumnName = "id")
    private User user;

    @Column
    @Enumerated
    private RequestStatus status;

    @Column
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column(name = "request_comment")
    private String requestComment;

    @OneToMany(mappedBy = "Request", fetch=FetchType.EAGER)
    private List<Request> requestItemsList;
    private RequestItem item;

    public Request() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getRequestComment() {
        return requestComment;
    }

    public void setRequestComment(String requestNotes) {
        this.requestComment = requestNotes;
    }

    public void setRequestItemsList(List<Request> requestItemsList) {
        this.requestItemsList = requestItemsList;
    }
    public void getRequestItemsList() {
        this.requestItemsList = requestItemsList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request order = (Request) o;
        return Objects.equals(id, order.id) && Objects.equals(user, order.user) && Objects.equals(status, order.status) && Objects.equals(date, order.date) && Objects.equals(address, order.address) && Objects.equals(firstName, order.firstName) && Objects.equals(lastName, order.lastName) && Objects.equals(phoneNumber, order.phoneNumber) && Objects.equals(email, order.email) && Objects.equals(requestComment, order.requestComment) && Objects.equals(requestItemsList, order.requestItemsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, status, date, address, firstName, lastName, phoneNumber, email, requestComment, requestItemsList);
    }

    public Double requestTotal(){
        Double result = 0.00;
        for(RequestItem item:getRequestItemsList()) {
            result += item.getQuantity();
        }
        return result;
    }

    public Boolean isFormed(){
        return status== RequestStatus.Formed;
    }

    public Boolean isSent(){
        return status==RequestStatus.Sent;
    }

    public Boolean isInProcessing(){
        return status==RequestStatus.InProcessing;
    }
    public Boolean isReceived(){ return status==RequestStatus.Received;}
}
