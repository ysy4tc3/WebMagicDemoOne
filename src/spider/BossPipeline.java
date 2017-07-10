package spider;

import bean.Boss;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by albee on 2017/6/6.
 */
public class BossPipeline implements Pipeline {
    @Override
    public void process(ResultItems resultItems, Task task) {
        Boss boss =  (Boss) resultItems.get("boss");
        System.out.println(boss);
        if(boss==null){
            return;
        }
        Connection conn = null;
        try {
            //加载驱动类(注册驱动类)
            conn = DBUtil.getConnection();
			/*对数据库做增、删、改
			 * 1.通过Connection对象创建Statement
			 *   Statement语句的发送器，它的功能就是向数据库发送sql语句！
			 * 2.调用他的int executeUpdate(String sql),返回影响了几行
			 */
            //通过Connection 得到Statement;

            String sql = "INSERT INTO boss (publishTime,jobName,salary,addr,city,experience,education,company,des) "+
                    "VALUES " +
                    "(?,?,?,?,?,?,?,?,?)";
//                         "VALUES ("+topic.getTopicTitle()+"','"+topic.getTopicUrl()+"','"+topic.getTopicTopAnsUrl()+"','"+topic.getFollowsNum()+"','"+
//                        topic.getParentTopic()+"','"+topic.getChildTopic()+"','"+topic.getDescription()+"','"+topic.getQuestionTitleLists().get(i)+"','"+topic.getQuestionUrlLists().get(i)+"')";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,boss.getPublishTime());
            ps.setString(2,boss.getJobName());
            ps.setString(3,boss.getSalary());
            ps.setString(4,boss.getAddr());
            ps.setString(5,boss.getCity());
            ps.setString(6,boss.getExperience());
            ps.setString(7,boss.getEducation());
            ps.setString(8,boss.getCompany());
            ps.setString(9,boss.getDescribe());
            int n = ps.executeUpdate();
//                System.out.println(sql);
            if(n>0){
                System.out.println(boss.getCompany()+":"+boss.getJobName()+"存入数据库成功！");
            }else{
                System.out.println(boss.getCompany()+":"+boss.getJobName()+"存入数据库失败！");
            }
            System.out.println();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(conn != null){
                try {
                    DBUtil.closeConnection(conn);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}



