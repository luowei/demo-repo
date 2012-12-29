package com.luowei.util;

import org.apache.commons.lang3.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * quartz job util
 * User: luowei
 * Date: 12-7-9
 * Time: 上午10:00
 */
public abstract class JobUtil {
    protected static Logger logger = (Logger) LoggerFactory.getLogger(JobUtil.class);

    /**
     * define a scheduler ,will be a sington instance
     */
    private static Scheduler scheduler = null;

    /**
     * get the name with default name 'QuartzScheduler' scheduler
     * @return
     */
    public static Scheduler getScheduler(){
        if(scheduler == null){
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            try {
                scheduler = schedulerFactory.getScheduler();
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return scheduler;
    }

    /**
     * get the scheduler with named shcedulerName
     * @param schedulerName   schedulerName
     * @return  Scheduler
     */
    public static Scheduler getScheduler(String schedulerName){
        if(scheduler == null){
            SchedulerFactory schedulerFactory = new StdSchedulerFactory();
            try {
                scheduler = schedulerFactory.getScheduler(schedulerName);
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
            }
        }
        return scheduler;
    }

    /**
     * scheduler a job with specefied trigger
     * @param job    job
     * @param trigger   trigger
     * @return   Date
     */
    public static Date schedulerAJobWithTrigger(JobDetail job,Trigger trigger){
        Date date = null;
        if(scheduler == null){
            scheduler = getScheduler();
        }
        try {
            if(!scheduler.checkExists(job.getKey())
                    && !scheduler.checkExists(trigger.getKey())){
                date = scheduler.scheduleJob(job,trigger);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(),e);
        }
        return date;
    }

    /**
     * Remove (delete) the <code>{@link org.quartz.Trigger}</code> with the
     * given key, and store the new given one - which must be associated
     * with the same job (the new trigger must have the job name & group specified)
     * - however, the new trigger need not have the same name as the old trigger.
     *
     * @param triggerKey identity of the trigger to replace
     * @param newTrigger
     *          The new <code>Trigger</code> to be stored.
     *
     * @return <code>null</code> if a <code>Trigger</code> with the given
     *         name & group was not found and removed from the store (and the
     *         new trigger is therefore not stored), otherwise
     *         the first fire time of the newly scheduled trigger is returned.
     */
    public static Date reSchedulerJob(TriggerKey triggerKey,Trigger newTrigger){
        Date date = null;
        try {
            if(triggerKey != null && newTrigger != null && scheduler.isStarted())
            date = scheduler.rescheduleJob(triggerKey,newTrigger);
            triggerKeyMap.remove(triggerKey.getName());
        } catch (SchedulerException e) {
            logger.error(e.getMessage(),e);
        }
        return date;
    }

    /**
     * scheduler a job with specefied trigger in a specefied shceduler
     * @param schedulerName   schedulerName
     * @param job    job
     * @param trigger    trigger
     * @return     Date
     */
    public static Date schedulerAJobWithTrigger(String schedulerName,JobDetail job,Trigger trigger){
        Date date = null;
        if(scheduler == null){
            scheduler = getScheduler(schedulerName);
        }
        try {
            if(!scheduler.checkExists(job.getKey())
                    && scheduler.checkExists(trigger.getKey())){
                date = scheduler.scheduleJob(job,trigger);
            }
        } catch (SchedulerException e) {
            logger.error(e.getMessage(),e);
        }
        return date;
    }

    /**
     * start scheduler
     * @return    Boolean
     */
    public static Boolean startScheduler(){
        Boolean flag = false;
        try {
            if(scheduler == null){
                scheduler = getScheduler();
            }
            if(!scheduler.isStarted()){
                scheduler.start();
            }
            flag = true;
            return flag;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(),e);
            return flag;
        }
    }

    /**
     * shutdown scheduler
     * @return    Boolean
     */
    public static Boolean shutdownScheduler(){
        try {
            if(scheduler != null && !scheduler.isShutdown()){
                scheduler.shutdown();
            }
            return true;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(),e);
            return false;
        }
    }

    /**
     * trigger key list
     */
    public static Map<String,TriggerKey> triggerKeyMap = new HashMap<String,TriggerKey>();

    /**
     * get a trigger which will repeat forever with in every other specified hours
     * @param groupName  group name
     * @param triggerName  trigger name
     * @param startTime  start time
     * @param intervalInHours  interval in minutes
     * @return the updated SimpleScheduleBuilder
     */
    public static Trigger initHourRepeatTrigger(String groupName,String triggerName,
                                Date startTime,Integer intervalInHours){
        SimpleTrigger trigger = null;
        if(StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(triggerName)
                && startTime!=null && intervalInHours!=null){
            trigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                    .withIdentity(triggerName,groupName)
                    .withSchedule(SimpleScheduleBuilder.repeatHourlyForever(intervalInHours))
                    .build();
            triggerKeyMap.put(triggerName, trigger.getKey());
        }
        return trigger;
    }

    /**
     * get a trigger which will repeat forever with in every other specified minutes
     * @param groupName  group name
     * @param triggerName  trigger name
     * @param startTime  start time
     * @param intervalInMinutes  interval in minutes
     * @return the updated SimpleScheduleBuilder
     */
    public static Trigger initMinuteRepeatTrigger(String groupName,String triggerName,
                                Date startTime,Integer intervalInMinutes){
        SimpleTrigger trigger = null;
        if(StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(triggerName)
                && startTime!=null && intervalInMinutes!= null){
            trigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                    .withIdentity(triggerName,groupName)
                    .startAt(startTime)
                    .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(intervalInMinutes))
                    .build();
            triggerKeyMap.put(triggerName, trigger.getKey());
        }
        return trigger;
    }

    /**
     * get a trigger which will repeat forever with every other specified seconds
     * @param groupName  group name
     * @param triggerName  trigger name
     * @param startTime  start time
     * @param intervalInSeconds  interval in seconds
     * @return a SimpleTrigger
     */
    public static Trigger initSecondRepeatTrigger(String groupName,String triggerName,
                             Date startTime,Integer intervalInSeconds){
        SimpleTrigger trigger = null;
        if(StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(triggerName)
                && startTime!=null && intervalInSeconds!=null){
            trigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                    .withIdentity(triggerName,groupName)
                    .startAt(startTime)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(intervalInSeconds))
                    .build();
        }
        triggerKeyMap.put(triggerName, trigger.getKey());
        return trigger;
    }

    /**
     * set to repeat the given number of times - 1  with an interval of the given number of seconds.
     * Note: Total count = 1 (at start time) + repeat count
     * @param groupName  group name
     * @param triggerName  trigger name
     * @param startTime  start time
     * @param count  count
     * @param seconds  seconds
     * @return a SimpleTrigger
     */
    public static Trigger initRepeatCountTrigger(String groupName,String triggerName,
                              Date startTime,Integer count,Integer seconds){
        SimpleTrigger trigger = null;
        if(StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(triggerName)
                && startTime!=null && count!=null && seconds!=null){
            trigger = (SimpleTrigger)TriggerBuilder.newTrigger()
                    .withIdentity(triggerName,groupName)
                    .startAt(startTime)
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(count,seconds))
                    .build();
            triggerKeyMap.put(triggerName, trigger.getKey());
        }
        return trigger;
    }

    /**
     * Create a CronTrigger with the given cron-expression string - which
     * is presumed to b e valid cron expression (and hence only a RuntimeException
     * will be thrown if it is not).
     * @param groupName
     * @param triggerName
     * @param startTime
     * @param cronExpression
     * @return a CronTrigger
     */
    public static  Trigger initCronTrigger(String groupName,String triggerName,
                                                 Date startTime,String cronExpression){
        CronTrigger trigger = null;
        if(StringUtils.isNotBlank(groupName) && StringUtils.isNotBlank(triggerName)
                && StringUtils.isNotBlank(cronExpression) && startTime!=null){
            trigger = (CronTrigger)TriggerBuilder.newTrigger()
            .withIdentity(triggerName,groupName)
            .startAt(startTime)
            .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
            .build();
            triggerKeyMap.put(triggerName, trigger.getKey());
        }
        return trigger;
    }

    public static Map<String,JobKey> jobKeyMap = new HashMap<String,JobKey>();

    /**
     * init a job with the specified jobName,groupName and clazz
     *
     * @param groupName    groupName
     * @param jobName      jobName
     * @param clazz    clazz
     * @return      JobDetail
     */
    public static JobDetail initJob(String groupName, String jobName, Class<? extends Job> clazz){
        JobDetail job = null;
        if(StringUtils.isNotBlank(jobName) && StringUtils.isNotBlank(groupName) && clazz!=null){
            job = JobBuilder.newJob((Class<? extends Job>) clazz)
            .withIdentity(jobName,groupName)
            .build();
            jobKeyMap.put(jobName, job.getKey());
        }
        return job;
    }

    /**
     * init durable job
     * @param groupName     groupName
     * @param jobName    jobName
     * @param clazz     clazz
     * @return    JobDetail
     */
    public static JobDetail initDurableJob(String groupName, String jobName, Class<? extends Job> clazz){
        JobDetail job = null;
        if(StringUtils.isNotBlank(jobName) && StringUtils.isNotBlank(groupName) && clazz!=null){
            job = JobBuilder.newJob((Class<? extends Job>) clazz)
                    .withIdentity(jobName,groupName)
                    .storeDurably()
                    .build();
            jobKeyMap.put(jobName, job.getKey());
        }
        return job;
    }

    /**
     * add data to JobDetail,which data were stored in map
     * @param jobDetail  jobDetail
     * @param map  map
     * @return
     */
    public static Boolean setData2Job(JobDetail jobDetail,Map<? extends String,? extends Object> map){
        if(jobDetail!=null && map!=null){
            jobDetail.getJobDataMap().putAll(map);
            return true;
        }else{
            return false;
        }
    }

    /**
     * add data to Trigger,which data were stored in map
     * @param trigger trigger
     * @param map map
     * @return
     */
    public static Boolean setData2Trigger(Trigger trigger,Map<? extends String,? extends Object> map) {
        if(trigger!=null && map!=null){
            trigger.getJobDataMap().putAll(map);
            return true;
        }else {
            return false;
        }
    }

    /**
     * delete a job with specified jobName,this oporater will delete the job from Scheduler,
     * and remove the Entry Element which key equals jobName from global varible jobkeyMap
     * @param jobName
     * @return
     */
    public static Boolean delJob(String jobName){
        Boolean flag = false;
        if(StringUtils.isNotBlank(jobName)){
            JobKey jobKey = jobKeyMap.get(jobName);
            try {
                scheduler.deleteJob(jobKey);
                jobKeyMap.remove(jobName);
                flag = true;
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
                flag = false;
            }
        }
        return flag;
    }

    /**
     * delete all jobs,this oporater will delete the job from Scheduler,
     * and remove the Entry Element which key equals jobName from global varible jobkeyMap
     * @return
     */
    public static Boolean delAllJob(){
        Boolean flag = false;
            try {
                List<JobKey> jobKeyList = new ArrayList<JobKey>();
                jobKeyList.addAll(jobKeyMap.values());
                scheduler.deleteJobs(jobKeyList);
                jobKeyMap.clear();
                flag = true;
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
                flag = false;
            }
        return flag;
    }

     /**
     * Resume (un-pause) the <code>{@link org.quartz.JobDetail}</code> with
     * the given key.
     *
     * <p>
     * If any of the <code>Job</code>'s<code>Trigger</code> s missed one
     * or more fire-times, then the <code>Trigger</code>'s misfire
     * instruction will be applied.
     * </p>
     * @param jobName   jobName
     * @return     Boolean
     */
    public static Boolean pauseJob(String jobName){
        Boolean flag = false;
        if(StringUtils.isNotBlank(jobName)){
            JobKey jobKey = jobKeyMap.get(jobName);
            try {
                scheduler.pauseJob(jobKey);
                flag = true;
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Resume (un-pause) the <code>{@link org.quartz.JobDetail}</code> with
     * the given key.
     *
     * <p>
     * If any of the <code>Job</code>'s<code>Trigger</code> s missed one
     * or more fire-times, then the <code>Trigger</code>'s misfire
     * instruction will be applied.
     * </p>
     *
     * @param jobName  jobName
     * @return    Boolean
     */
    public static Boolean resumeJob(String jobName){
        Boolean flag = false;
        if(StringUtils.isNotBlank(jobName)){
            JobKey jobKey = jobKeyMap.get(jobName);
            try {
                scheduler.resumeJob(jobKey);
                flag = true;
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
                flag = false;
            }
        }
        return flag;
    }

    /**

     * Remove the indicated <code>{@link Trigger}</code> from the scheduler.
     *
     * <p>If the related job does not have any other triggers, and the job is
     * not durable, then the job will also be deleted.</p>
     * @param triggerName   triggerName
     * @return    Boolean
     */
    public static Boolean unscheduleJob(String triggerName){
        Boolean flag = false;
        if(StringUtils.isNotBlank(triggerName)){
            TriggerKey triggerKey = triggerKeyMap.get(triggerName);
            try {
                scheduler.unscheduleJob(triggerKey);
                flag = true;
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Remove all of the indicated <code>{@link Trigger}</code>s from the scheduler.
     *
     * <p>If the related job does not have any other triggers, and the job is
     * not durable, then the job will also be deleted.</p>
     *
     * <p>Note that while this bulk operation is likely more efficient than
     * invoking <code>unscheduleJob(TriggerKey triggerKey)</code> several
     * times, it may have the adverse affect of holding data locks for a
     * single long duration of time (rather than lots of small durations
     * of time).</p>
     * @return  Boolean
     */
    public static Boolean unscheduleJobs(){
        Boolean flag = false;
        try {
            List<TriggerKey> triggerKeyList = new ArrayList<TriggerKey>();
            triggerKeyList.addAll(triggerKeyMap.values());
            scheduler.unscheduleJobs(triggerKeyList);
            flag = true;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(),e);
            flag = false;
        }
        return flag;
    }

    /**
     * delete a trigger with specified triggerName,this operate will delete the job from Scheduler,
     * and remove the Entry Element which key equals jobName from global varible jobkeyMap
     * @param triggerName     triggerName
     * @return     Boolean
     */
    public static Boolean pauseTrigger(String triggerName){
        Boolean flag = false;
        if(StringUtils.isNotBlank(triggerName)){
            TriggerKey triggerKey = triggerKeyMap.get(triggerName);
            try {
                scheduler.pauseTrigger(triggerKey);
                flag = true;
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Resume (un-pause) the <code>{@link Trigger}</code> with the given
     * key.
     *
     * <p>
     * If the <code>Trigger</code> missed one or more fire-times, then the
     * <code>Trigger</code>'s misfire instruction will be applied.
     * </p>
     * @param triggerName    triggerName
     * @return     Boolean
     */
    public static Boolean resumeTrigger(String triggerName){
        Boolean flag = false;
        if(StringUtils.isNotBlank(triggerName)){
            TriggerKey triggerKey = triggerKeyMap.get(triggerName);
            try {
                scheduler.resumeTrigger(triggerKey);
                flag = true;
            } catch (SchedulerException e) {
                logger.error(e.getMessage(),e);
                flag = false;
            }
        }
        return flag;
    }

    /**
     * Pause all triggers - similar to calling <code>pauseTriggerGroup(group)</code>
     * on every group, however, after using this method <code>resumeAll()</code>
     * must be called to clear the scheduler's state of 'remembering' that all
     * new triggers will be paused as they are added.
     * @return    Boolean
     */
    public static Boolean pauseAll(){
        Boolean flag = false;
        try {
            scheduler.pauseAll();
            flag = true;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(),e);
            flag = false;
        }
        return flag;
    }

    /**
     * Resume (un-pause) all triggers - similar to calling
     * <code>resumeTriggerGroup(group)</code> on every group.
     *
     * <p>
     * If any <code>Trigger</code> missed one or more fire-times, then the
     * <code>Trigger</code>'s misfire instruction will be applied.
     * </p>
     * @return   Boolean
     */
    public static Boolean resumeAll(){
        Boolean flag = false;
        try {
            scheduler.resumeAll();
            flag = true;
        } catch (SchedulerException e) {
            logger.error(e.getMessage(),e);
            flag = false;
        }
        return flag;
    }

    /**
     * run a job with specified trigger
     * @param job   job
     * @param trigger   trigger
     * @return
     */
    public static Date runJob(JobDetail job,Trigger trigger){
        Date date = null;
        if(job!=null && trigger!=null){
            startScheduler();
            date = schedulerAJobWithTrigger(job,trigger);
        }
        return date;
    }

//    public static Trigger getTrigger(String getNewestTriggerName){}

}
