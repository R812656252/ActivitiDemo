package com.java1234.activiti.ActivitiHelloWorld;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Create25Tables{
    /*
     * Create activiti related 25 tables
     */
	@Test
	public void createTableswithXml(){
		//读取 activiti.cfg.xml文件以配置文件
		ProcessEngineConfiguration processEngineConfiguration = 
				ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		//获取流程引擎对象
		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
	}
}
