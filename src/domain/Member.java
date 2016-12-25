
package domain;

import java.util.GregorianCalendar;

public class Member extends Person{
    private String memberID;
    private GregorianCalendar registerDate;
    private int point;

    public Member() {
    }

    public Member(String memberID, GregorianCalendar registerDate, int point, String firstName, String lastName, String address, String IC, GregorianCalendar DOB, String email, String phoneNo) {
        super(firstName, lastName, address, IC, DOB, email, phoneNo);
        this.memberID = memberID;
        this.registerDate = registerDate;
        this.point = point;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public GregorianCalendar getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(GregorianCalendar registerDate) {
        this.registerDate = registerDate;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return super.toString() + "Member{" + "memberID=" + memberID + ", registerDate=" + registerDate + ", point=" + point + '}';
    }

    
    
    
    
    
}
