package com.gbiac.fams.business.safecheck.safecheckconfig.entity;

import java.util.List;
import java.util.Map;

public class FamsAircontrolSafecheckconfigListEntity  implements java.io.Serializable {
    private String projectName;
    private List<FamsAircontrolSafecheckconfigEntity> configList;
    private List<Map> configMapList;

    public FamsAircontrolSafecheckconfigListEntity() {
    }

    public FamsAircontrolSafecheckconfigListEntity(String projectName, List<FamsAircontrolSafecheckconfigEntity> configList, List<Map> configMapList) {
        this.projectName = projectName;
        this.configList = configList;
        this.configMapList = configMapList;

    }


    public List<Map> getConfigMapList() {
        return configMapList;
    }

    public void setConfigMapList(List<Map> configMapList) {
        this.configMapList = configMapList;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setConfigList(List<FamsAircontrolSafecheckconfigEntity> configList) {
        this.configList = configList;
    }

    public String getProjectName() {
        return projectName;
    }

    public List<FamsAircontrolSafecheckconfigEntity> getConfigList() {
        return configList;
    }
}
