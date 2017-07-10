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
            //����������(ע��������)
            conn = DBUtil.getConnection();
			/*�����ݿ�������ɾ����
			 * 1.ͨ��Connection���󴴽�Statement
			 *   Statement���ķ����������Ĺ��ܾ��������ݿⷢ��sql��䣡
			 * 2.��������int executeUpdate(String sql),����Ӱ���˼���
			 */
            //ͨ��Connection �õ�Statement;

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
                System.out.println(boss.getCompany()+":"+boss.getJobName()+"�������ݿ�ɹ���");
            }else{
                System.out.println(boss.getCompany()+":"+boss.getJobName()+"�������ݿ�ʧ�ܣ�");
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



