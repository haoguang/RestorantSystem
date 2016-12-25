/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import da.JobDA;
import domain.Job;

/**
 *
 * @author khooe
 */
public class jobControl {
    JobDA job;
    
    public jobControl(){
        job = new JobDA();
    }
    
    public Job getRecord(String jobID){
        return job.getRecord(jobID);
    }
    
}
