package com.rainier.beans;

import java.util.List;

public class ModulesBean {
	private int moduleId;
	private String mainModule;
	private String modulePath;
	private List<ModulesBean> subModules;

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getMainModule() {
		return mainModule;
	}

	public void setMainModule(String mainModule) {
		this.mainModule = mainModule;
	}

	public String getModulePath() {
		return modulePath;
	}

	public void setModulePath(String modulePath) {
		this.modulePath = modulePath;
	}

	public List<ModulesBean> getSubModules() {
		return subModules;
	}

	public void setSubModules(List<ModulesBean> subModules) {
		this.subModules = subModules;
	}
	
	

}
