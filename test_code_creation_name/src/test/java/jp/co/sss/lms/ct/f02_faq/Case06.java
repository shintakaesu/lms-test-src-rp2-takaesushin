package jp.co.sss.lms.ct.f02_faq;

import static jp.co.sss.lms.ct.util.WebDriverUtils.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/**
 * 結合テスト よくある質問機能
 * ケース06
 * @author holy
 */
@TestMethodOrder(OrderAnnotation.class)
@DisplayName("ケース06 カテゴリ検索 正常系")
public class Case06 {

	/** 前処理 */
	@BeforeAll
	static void before() {
		createDriver();
	}

	/** 後処理 */
	@AfterAll
	static void after() {
		closeDriver();
	}

	@Test
	@Order(1)
	@DisplayName("テスト01 トップページURLでアクセス")
	void test01() {
		//トップページのURLにアクセスする
		goTo("http://localhost:8080/lms");

		//タイトル判定
		assertEquals("ログイン | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(2)
	@DisplayName("テスト02 初回ログイン済みの受講生ユーザーでログイン")
	void test02() {
		//初回ログイン済みの受講生ユーザーでログイン
		webDriver.findElement(By.id("loginId")).clear();
		webDriver.findElement(By.id("loginId")).sendKeys("StudentAA04");
		webDriver.findElement(By.id("password")).clear();
		webDriver.findElement(By.id("password")).sendKeys("StudentAA041");

		//ログインボタンを押下
		webDriver.findElement(By.cssSelector(".btn.btn-primary")).click();

		//コース詳細画面が表示されるまで待機
		visibilityTimeout(By.id("open-all-panel"), 5);

		//タイトル判定
		assertEquals("コース詳細 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(3)
	@DisplayName("テスト03 上部メニューの「ヘルプ」リンクからヘルプ画面に遷移")
	void test03() {
		//機能のドロップダウンをクリック
		webDriver.findElement(By.linkText("機能")).click();

		//ヘルプ画面が表示されるまで待機
		visibilityTimeout(By.linkText("ヘルプ"), 5);

		//ヘルプリンクを押下
		webDriver.findElement(By.linkText("ヘルプ")).click();

		//タイトル判定
		assertEquals("ヘルプ | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(4)
	@DisplayName("テスト04 「よくある質問」リンクからよくある質問画面を別タブに開く")
	void test04() {
		//よくある質問リンクを押下
		webDriver.findElement(By.linkText("よくある質問")).click();

		//ウィンドウのハンドル取得
		Object[] windowHandles = webDriver.getWindowHandles().toArray();
		webDriver.switchTo().window((String) windowHandles[1]);

		//タイトル判定
		assertEquals("よくある質問 | LMS", webDriver.getTitle());

		//エビデンス取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(5)
	@DisplayName("テスト05 カテゴリ検索で該当カテゴリの検索結果だけ表示")
	void test05() {
		//研修関係カテゴリのリンクを押下
		webDriver.findElement(By.linkText("【研修関係】")).click();

		//検索結果が表示されるまで待機
		visibilityTimeout(By.cssSelector("[id^='question-h']"), 5);

		//検索結果を取得
		List<WebElement> resultLists = webDriver.findElements(By.cssSelector("[id^='question-h']"));

		//該当の質問が表示されているか判定
		for (WebElement resultList : resultLists) {
			assertTrue(resultList.isDisplayed());
		}

		//検索結果へ遷移
		scrollTo(String.valueOf(webDriver.findElement(By.className("sorting_asc")).getLocation().getY()));

		// エビデンスを取得
		getEvidence(new Object() {
		});
	}

	@Test
	@Order(6)
	@DisplayName("テスト06 検索結果の質問をクリックしその回答を表示")
	void test06() {
		//質問を押下
		webDriver.findElement(By.id("question-h[${status.index}]")).click();

		//押下した質問の怪盗が表示されているか判定
		assertTrue(webDriver.findElement(By.id("answer-h[${status.index}]")).isDisplayed());

		// エビデンスを取得
		getEvidence(new Object() {
		});
	}

}
