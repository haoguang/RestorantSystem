
package domain;

public class Tables {
    private int tableNo;
    private int seat;
    private String status;
    private String holdingOrder;

    public Tables() {
    }

    public Tables(int tableNo, int seat, String status) {
        this.tableNo = tableNo;
        this.seat = seat;
        this.status = status;
    }
    public Tables(int tableNo, int seat, String status, String holdingOrder) {
        this.tableNo = tableNo;
        this.seat = seat;
        this.status = status;
        this.holdingOrder = holdingOrder;
    }

    public int getTableNo() {
        return tableNo;
    }

    public void setTableNo(int tableNo) {
        this.tableNo = tableNo;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHoldingOrder() {
        return holdingOrder;
    }

    public void setHoldingOrder(String holdingOrder) {
        this.holdingOrder = holdingOrder;
    }

    @Override
    public String toString() {
        return "Tables{" + "tableNo=" + tableNo + ", seat=" + seat + ", status=" + status + '}';
    }
    
    
}
