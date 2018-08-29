package com.java1234.activiti.ActivitiHelloWorld;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;

public class HelloWorldProcess {

	private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
	
	/*
	 * 本过程中涉及到的表：`act_re_deployment``act_re_procdef``act_ge_bytearray`
	 */
    @Test
    public void deploy(){
    	Deployment deployment = processEngine.getRepositoryService() //部署service
					.createDeployment()//创建部署
					.addClasspathResource("diagrams/helloWorld.bpmn")//加载bpmn资源文件
					.addClasspathResource("diagrams/helloWorld.png")//加载png资源文件
					.name("HelloWorld流程")//流程名称
					.deploy();//部署
    	System.out.println("ID: "+deployment.getId());
    	System.out.println("Name: "+deployment.getName());
    }
    
    /*
     * 本过程中涉及到的表：`act_ru_execution``act_ru_identitylink``act_ru_task`
     * `act_hi_actinst``act_hi_identitylink``act_hi_procinst``act_hi_taskinst`
     */
    @Test
    public void start(){
    	ProcessInstance processInstance = processEngine.getRuntimeService()//获取运行时service
    			.startProcessInstanceByKey("HelloWorld");
    	System.out.println(processInstance.getName());
    }
    
    @Test
    public void findTask(){
    	List<Task> taskList = processEngine.getTaskService()//获取任务相关service
    			.createTaskQuery()//创建任务查询
    			.taskAssignee("Robin")//查询某个人的权利
    			.list();
    	for(Task task : taskList ){
    		System.out.println(task.getAssignee());
    		this.finish(task.getId());
    	}
    }
    
    /*
     * 本过程中涉及到的表：将会清空**_ru_**表
     */
    @Test
    public void finish(String taskId){
		processEngine.getTaskService()
    	.complete(taskId );
    }
}
