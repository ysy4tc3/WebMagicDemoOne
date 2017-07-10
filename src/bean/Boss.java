package bean;

/**
 * Created by albee on 2017/6/6.
 */
public class Boss {
private String publishTime;
    private String jobName;
    private String salary;
    private String addr;
    private String city;
    private String experience;
    private String education;
    private String company;
    private String describe;

    public String getPublishTime() {
return publishTime;
    }

public void setPublishTime(String publishTime) {
this.publishTime = publishTime;
    }

public String getJobName() {
return jobName;
    }

public void setJobName(String jobName) {
this.jobName = jobName;
    }

public String getSalary() {
return salary;
    }

public void setSalary(String salary) {
this.salary = salary;
    }

public String getAddr() {
return addr;
    }

public void setAddr(String addr) {
this.addr = addr;
    }

public String getCity() {
return city;
    }

public void setCity(String city) {
this.city = city;
    }

public String getExperience() {
return experience;
    }

public void setExperience(String experience) {
this.experience = experience;
    }

public String getEducation() {
return education;
    }

public void setEducation(String education) {
this.education = education;
    }

public String getCompany() {
return company;
    }

public void setCompany(String company) {
this.company = company;
    }

public String getDescribe() {
return describe;
    }

public void setDescribe(String describe) {
this.describe = describe;
    }

@Override
    public String toString() {
return "Boss{" +
"publishTime='" + publishTime + '\'' +
", jobName='" + jobName + '\'' +
", salary='" + salary + '\'' +
", addr='" + addr + '\'' +
", city='" + city + '\'' +
", experience='" + experience + '\'' +
", education='" + education + '\'' +
", company='" + company + '\'' +
", describe='" + describe + '\'' +
'}';
    }
}



