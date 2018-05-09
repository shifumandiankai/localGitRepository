package com.wadejerry.scms.task;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wadejerry.scms.utils.db.DBTools;


@DisallowConcurrentExecution
public class PartitionTask implements Job {


	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		DBTools.autoAddPartition();
		
	}

}
