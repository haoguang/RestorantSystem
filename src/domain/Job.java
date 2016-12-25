
package domain;

public class Job {
    private String jobID;
    private String jobName;
    private String jobDescription;
    private double basicSalary;
    private boolean authority;

    public Job() {
    }

    public Job(String jobID, String jobName, String jobDescription, double basicSalary, boolean authority) {
        this.jobID = jobID;
        this.jobName = jobName;
        this.jobDescription = jobDescription;
        this.basicSalary = basicSalary;
        this.authority = authority;
    }

    public String getJobID() {
        return jobID;
    }

    public String getJobName() {
        return jobName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public double getBasicSalary() {
        return basicSalary;
    }

    public boolean getAuthority() {
        return authority;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public void setAuthority(boolean authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "Job{" + "jobID=" + jobID + ", jobName=" + jobName + ", jobDescription=" + jobDescription + ", basicSalary=" + basicSalary + ", authority=" + authority + '}';
    }
    
    
    
}
