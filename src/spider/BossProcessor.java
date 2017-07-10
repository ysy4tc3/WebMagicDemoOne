package spider;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

import bean.Boss;

/**
 * Created by albee on 2017/6/6.
 */
public class BossProcessor  implements PageProcessor {

private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    //http://www.zhipin.com/c101270100-p100101/?ka=cpc_side_100101
    //http://www.zhipin.com/c101270100-p100101/?page=1&ka=page-1
    private static  final  String URL_LIST  = "http://www\\.zhipin\\.com/c\\d+-p\\d+/\\?page=\\d+(&ka=page-\\d+)?";
    //http://www.zhipin.com/job_detail/1411884476.html?ka=search_list_1
    private  static  final  String URL_DETAIL  ="http://www\\.zhipin\\.com/job_detail/\\d+\\.html(\\?ka=[A-Za-z0-9_]+)?";
    @Override
    public void process(Page page) {

if(page.getUrl().regex(URL_LIST).match()){
            List<String> urls = page.getHtml().xpath("//div[@class=job-list]/ul/li/a/@href").all();
            System.out.println(urls);
            page.addTargetRequests(urls);
            String nextUrl = page.getHtml().xpath("//div[@class=page]/a[@class=next]/@href").toString();
           if(nextUrl!=null||!" ".equals(nextUrl)){
               page.addTargetRequest(nextUrl);
           }
        }

if(page.getUrl().regex(URL_DETAIL).match()){
//发布时间
            String publishTime = page.getHtml().xpath("//div[@class=job-author]/span[@class=time]/text()").toString();
            //职位名称
            String jobName = page.getHtml().xpath("//div[@class=info-primary]/div[@class=name]/text()").toString();
            //薪水
            String salary = page.getHtml().xpath("//div[@class=info-primary]/div[@class=name]/span/text()").toString();
            //地址
            String addr = page.getHtml().xpath("//div[@class=location-address]/text()").toString();
            //工作时间要求
            String [] str  =  page.getHtml().xpath("//div[@class=info-primary]/p/html()").toString().split("<em class=\"vline\"></em>");
            String city =  str[0];
            String experience =  str[1];
            String education =  str[2];
            //学历
            //公司名称
            String company =  page.getHtml().xpath("//div[@class=info-company]/h3[@class=name]/a/text()").toString();
            //描述
            String describe =  page.getHtml().xpath("//div[@class=job-sec]/div/tidyText()").toString();
            Boss boss  =  new Boss();
            boss.setPublishTime(publishTime);
            boss.setJobName(jobName);
            boss.setSalary(salary);
            boss.setAddr(addr);
            boss.setCity(city);
            boss.setExperience(experience);
            boss.setEducation(education);
            boss.setCompany(company);
            boss.setDescribe(describe);
            System.out.println(boss);
            page.putField("boss",boss);
        }

    }


@Override
    public Site getSite() {
return site;
    }

public static void main(String[] args) {
        String url  =  "http://www.zhipin.com/c101270100-p100101/?page=1&ka=page-1";
        Spider.create(new BossProcessor())
                .addUrl(url)
                .addPipeline(new BossPipeline())
                .thread(1)
                .run();
    }

}