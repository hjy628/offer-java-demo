package chap9.visitor;

import java.util.Date;

/**
 * @auther: hjy
 * @Date: 2020/4/23 17:23
 * @Description:
 */

public class ProjectElement implements Element {

    private String projectName;
    private String projectContent;
    private String visitorName;
    private Date visitorTime;

    public ProjectElement(String projectName, String projectContent) {
        this.projectName = projectName;
        this.projectContent = projectContent;
    }

    @Override
    public void accept(Visitor visitor) {
            visitor.visit(this);
    }

    public void signature(String visitorName,Date visitorTime){
        this.visitorName = visitorName;
        this.visitorTime = visitorTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectContent() {
        return projectContent;
    }

    public void setProjectContent(String projectContent) {
        this.projectContent = projectContent;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public Date getVisitorTime() {
        return visitorTime;
    }

    public void setVisitorTime(Date visitorTime) {
        this.visitorTime = visitorTime;
    }

    @Override
    public String toString() {
        return "{" +
                "projectName:'" + projectName + '\'' +
                ", projectContent:'" + projectContent + '\'' +
                ", visitorName:'" + visitorName + '\'' +
                ", visitorTime:" + visitorTime +
                '}';
    }
}
