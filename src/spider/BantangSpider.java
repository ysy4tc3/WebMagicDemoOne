package spider;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class BantangSpider implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	@Override
	public void process(Page page) {
		String cover =  page.getHtml().xpath("//div[@class=sno]/div[@class=topic-item]//img/@src").toString();
	    String title =  page.getHtml().xpath("//div[@class=sno]/div[@class=topic-item]//p[@class=title]/html()").toString();
	    String authorAvatar =  page.getHtml().xpath("//div[@class=sno]/div[@class=topic-item]//div[@class=info]//div[@class=avatar]/@style").toString();
	    authorAvatar = authorAvatar.replaceAll("background-image: url\\('","");
	    authorAvatar = authorAvatar.replaceAll("'\\);","");
	    String authorName =  page.getHtml().xpath("//div[@class=sno]/div[@class=topic-item]//div[@class=info]//p[@class=nickname]/text()").toString();
	    String likeNum =  page.getHtml().xpath("//div[@class=sno]/div[@class=topic-item]//div[@class=info]//span[@class=stat]/text()").toString();
	    System.out.println("cover:"+cover);
	    System.out.println("title:"+title);
	    System.out.println("authorAvatar:"+authorAvatar);
	    System.out.println("authorName:"+authorName);
	    System.out.println("likeNum:"+likeNum);
	}
	
	public static void main(String[] args){
		String url = "http://www.ibantang.com";
		Spider.create(new BantangSpider())
			.addUrl(url)
			.thread(1)
			.run();
	}

}
