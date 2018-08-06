package com.hand.file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * @ClassName: FilePOI
 * 
 * @Description:获取学生的名单，然后随机选出名单中一部分学生
 * 
 * @author ZhongLingYun
 * 
 * @date 2018年8月6日 下午3:17:25
 * 
 */
public class Filepoi {
	public static void main(String[] args) throws IOException {
		createRandomStudent();

	}

	/**
	 * 
	 * @Title: readXml
	 * 
	 * @Description: 讀取exel中所有的學生名字
	 * 
	 * @param @param
	 *            fileName
	 * @param @return
	 *            List<String> list中包含所有的學生名單
	 * @param @throws
	 *            IOException
	 * 
	 * @return List<String>
	 * 
	 * 
	 * @author ZhongLingYun
	 * 
	 */
	@SuppressWarnings("resource")
	public static List<String> readXml(String fileName) throws IOException {
		 // 判断是否是excel2007格式
		boolean isE2007 = false;
		if (fileName.endsWith("xlsx")) {
			isE2007 = true;
		}
		// 建立输入流
		InputStream input = new FileInputStream(fileName); 
		Workbook wb = null;
		// 根据文件格式(2003或者2007)来初始化
		if (isE2007) {
			wb = new XSSFWorkbook(input);
		} else {
			wb = new HSSFWorkbook(input);
		}
		List<String> listString = new ArrayList<String>();
		int tableCount = wb.getNumberOfSheets();
		for (int i = 0; i < tableCount; i++) {
			Sheet sheet = wb.getSheetAt(i);
			int lastRow = sheet.getLastRowNum();
			for (int x = 1; x <= lastRow; x++) {
				Row row = sheet.getRow(x);
				Cell cell = row.getCell(2);
				listString.add(cell.getStringCellValue());
			}

		}
		return listString;
	}

	/**
	 * 
	 * @Title: createRandomStudent
	 * 
	 * @Description: 产生随机的学生名单
	 * 
	 * @param @throws
	 *            IOException
	 * 
	 * @return void
	 * 
	 * @author ZhongLingYun
	 * 
	 */
	public static void createRandomStudent() throws IOException {
		List<String> listString = readXml("E:\\培训班成员分组-Java&HAP.xlsx");
		int count = listString.size();
		System.out.println("本次总共学生为" + count + "人");
		System.out.println("请输入需要取的人数");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		boolean flag = false;
		int sum = 0;
		while (!flag) {
			sum = scanner.nextInt();
			if (sum > 0 && sum <= count) {
				flag = true;
			} else {
				System.out.println("输入不合法请重新输入，本次可选择的人数是" + count);

			}
		}
		Set<Integer> set = new HashSet<Integer>();
		Random random = new Random();
		while (set.size() < sum) {
			set.add(random.nextInt(75) + 1);
		}
		System.out.println("名单如下");
		Iterator<Integer> iterator = set.iterator();
		while (iterator.hasNext()) {
			System.out.println(listString.get(iterator.next()));
		}
	}
}