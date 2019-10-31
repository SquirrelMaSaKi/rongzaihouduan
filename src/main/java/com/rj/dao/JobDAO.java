package com.rj.dao;

import com.rj.job.JobAndTrigger;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface JobDAO {
    @Select("SELECT " +
            "QRTZ_JOB_DETAILS.JOB_NAME jobName, " +
            "QRTZ_JOB_DETAILS.JOB_GROUP jobGroup, " +
            "QRTZ_JOB_DETAILS.JOB_CLASS_NAME jobClassName, " +
            "QRTZ_TRIGGERS.TRIGGER_NAME triggerName, " +
            "QRTZ_TRIGGERS.TRIGGER_GROUP triggerGroup, " +
            "QRTZ_CRON_TRIGGERS.CRON_EXPRESSION cronExpression, " +
            "QRTZ_CRON_TRIGGERS.TIME_ZONE_ID timeZoneId " +
            "FROM " +
            "QRTZ_JOB_DETAILS " +
            "JOIN QRTZ_TRIGGERS " +
            "JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME " +
            "AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME " +
            "AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP")
    public List<JobAndTrigger> queryJobs();
    @Select("SELECT " +
            "QRTZ_JOB_DETAILS.JOB_NAME jobName, " +
            "QRTZ_JOB_DETAILS.JOB_GROUP jobGroup, " +
            "QRTZ_JOB_DETAILS.JOB_CLASS_NAME jobClassName, " +
            "QRTZ_TRIGGERS.TRIGGER_NAME triggerName, " +
            "QRTZ_TRIGGERS.TRIGGER_GROUP triggerGroup, " +
            "QRTZ_CRON_TRIGGERS.CRON_EXPRESSION cronExpression, " +
            "QRTZ_CRON_TRIGGERS.TIME_ZONE_ID timeZoneId " +
            "FROM " +
            "QRTZ_JOB_DETAILS " +
            "JOIN QRTZ_TRIGGERS " +
            "JOIN QRTZ_CRON_TRIGGERS ON QRTZ_JOB_DETAILS.JOB_NAME = QRTZ_TRIGGERS.JOB_NAME " +
            "AND QRTZ_TRIGGERS.TRIGGER_NAME = QRTZ_CRON_TRIGGERS.TRIGGER_NAME " +
            "AND QRTZ_TRIGGERS.TRIGGER_GROUP = QRTZ_CRON_TRIGGERS.TRIGGER_GROUP "+
            "WHERE QRTZ_JOB_DETAILS.JOB_NAME=#{jobName} and QRTZ_JOB_DETAILS.JOB_GROUP=#{jobGroup}")
    public JobAndTrigger queryJobByNameAndGroup(JobAndTrigger jt);
}