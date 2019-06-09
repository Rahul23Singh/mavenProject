package com.mavenProject.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.internal.TestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest logger;

	public void onStart(ITestContext testContext) {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
				.format(new Date());// time stamp
		String repName = "Test-Report-" + timeStamp + ".html";
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")
				+ "/test-output/" + repName);// specify locaation
		htmlReporter.loadXMLConfig(System.getProperty("user.dir")
				+ "/extent-config.xml");

		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("HostName", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "rahul");

		htmlReporter.config().setDocumentTitle("mavenProject test project"); // title
																				// of
																				// report
		htmlReporter.config().setReportName("Functional Test Report");// name of
																		// the
																		// report
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);// location
																			// of
																			// chart
		htmlReporter.config().setTheme(Theme.DARK);
	}

	public void onTestSuccess(ITestResult itresult) {
		logger = extent.createTest(itresult.getName()); // create new entry in
														// the report
		logger.log(Status.PASS,
				MarkupHelper.createLabel(itresult.getName(), ExtentColor.GREEN)); // send
																					// the
																					// passed
																					// information
	}

	public void onTestFaliure(TestResult itresult) {
		logger = extent.createTest(itresult.getName());// create new entry in
														// the report
		logger.log(Status.FAIL,
				MarkupHelper.createLabel(itresult.getName(), ExtentColor.RED)); // send
																				// the
																				// passed
																				// information

		String screenshotPath = System.getProperty("user.dir")
				+ "\\sceenshots\\" + itresult.getName() + ".png";
		File f = new File(screenshotPath);
		if (f.exists()) {

			try {
				logger.fail("Screenshot is below:"
						+ logger.addScreenCaptureFromPath(screenshotPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void onTestSkipped(ITestResult tr) {
		logger = extent.createTest(tr.getName()); // create new entry in the
													// report
		logger.log(Status.SKIP,
				MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));
	}

	public void onFlush(ITestContext testContext) {
		extent.flush();
	}
}
