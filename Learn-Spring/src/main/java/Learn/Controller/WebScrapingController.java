package Learn.Controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Controller
public class WebScrapingController {
	private static int triggerCount = 0; // 触发次数计数器

	public static void main(String[] args) {
		// 创建定时任务执行器
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

		// 每隔30秒执行一次任务
		executorService.scheduleAtFixedRate(() -> {
			try {
				triggerCount++; // 触发次数加一
				System.out.println("触发任务，当前触发次数：" + triggerCount);

				// 使用 Jsoup 获取指定网页的 HTML 内容
				Document doc = Jsoup.connect("https://www.momoshop.com.tw/edm/cmmedm.jsp?lpn=O6VA2CtphDo&n=1&mdiv=1099900000-bt_0_247_01-bt_0_247_01_P1_5_e1&ctype=B").get();

				// 解析 HTML，提取出所有的链接
				Elements links = doc.select("a[href*=GoodsDetail.jsp]");
				List<String> hrefs = new ArrayList<>();
				for (Element link : links) {
					String href = link.absUrl("href"); // 使用 absUrl 获取绝对路径
					hrefs.add(href);
				}

				// 随机选择一个链接
				if (!hrefs.isEmpty()) {
					Random random = new Random();
					int randomIndex = random.nextInt(hrefs.size());
					String randomLink = hrefs.get(randomIndex);
					// 模拟点击随机选择的链接
					sendGetRequest(randomLink);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}, 0, 3, TimeUnit.SECONDS);

		// 关闭定时任务执行器
		//executorService.shutdown();
	}

	// 发送 HTTP GET 请求
	private static void sendGetRequest(String urlString) throws IOException {
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		int responseCode = connection.getResponseCode();
		System.out.println("Sending GET request to URL: " + urlString);
		System.out.println("Response Code: " + responseCode);
		connection.disconnect();
	}
}
