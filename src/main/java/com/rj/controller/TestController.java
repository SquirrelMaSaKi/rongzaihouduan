package com.rj.controller;


import com.rj.job.JobAndTrigger;
import com.rj.service.JobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/quartz")
public class TestController {
    @Autowired
    private Scheduler scheduler;
    @Autowired
    private JobService jobService;

    //添加
    @RequestMapping("/add")
    public String addJob(JobAndTrigger jt) throws ClassNotFoundException, SchedulerException {
        JobDetail jobDetail = null;
        //创建job类，job名字和组名
        jobDetail = JobBuilder.newJob((Class<? extends Job>)Class.forName(jt.getJobClassName()))
                              .withIdentity(jt.getJobName(), jt.getJobGroup()).storeDurably(true).build();
        CronTrigger cronTrigger = null;
        //创建一个CronTrigger，包含了同名Job名和组名，并且有Expression表达式
        cronTrigger = TriggerBuilder.newTrigger().withIdentity(jt.getJobName(), jt.getJobGroup())
                      .withSchedule(CronScheduleBuilder.cronSchedule(jt.getCronExpression())).build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
        return "redirect:/index.jsp";
    }

    //查询
    @RequestMapping("/query")
    public String queryJob(HttpServletRequest request) {
        List<JobAndTrigger> list = jobService.queryJobs();
        request.setAttribute("jobList", list);
        return "forward:/main.jsp";
    }

    //暂停
    @RequestMapping("/pause")
    public String pauseJob(JobAndTrigger jt) throws SchedulerException {
        System.out.println("暂停Job");
        scheduler.pauseJob(JobKey.jobKey(jt.getJobName(),jt.getJobGroup()));
        return null;
    }

    //继续
    @RequestMapping("/resume")
    public String resumeJob(JobAndTrigger jt) throws SchedulerException {
        System.out.println("继续job");
        scheduler.resumeJob(JobKey.jobKey(jt.getJobName(),jt.getJobGroup()));
        return null;
    }

    //删除
    @RequestMapping("/delete")
    public String deleteJob(JobAndTrigger jt) throws SchedulerException {
        System.out.println("删除job");
        //必须顺序结束，暂停Trigger，删除scheduler调度，最后删除任务
        scheduler.pauseTrigger(TriggerKey.triggerKey(jt.getJobName(),jt.getJobGroup()));
        scheduler.unscheduleJob(TriggerKey.triggerKey(jt.getJobName(),jt.getJobGroup()));
        scheduler.deleteJob(JobKey.jobKey(jt.getJobName(),jt.getJobGroup()));
        return null;
    }

    //更新
    @RequestMapping("/update")
    public String updateJob(JobAndTrigger jt) throws SchedulerException {
        System.out.println("更新job");
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jt.getJobName(), jt.getJobGroup())
                                  .withSchedule(CronScheduleBuilder.cronSchedule(jt.getCronExpression())).build();
        scheduler.rescheduleJob(TriggerKey.triggerKey(jt.getJobName(),jt.getJobGroup()),cronTrigger );
        return null;
    }
}
