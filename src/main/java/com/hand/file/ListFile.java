package com.hand.file;

import java.io.File;

/**
 * 
 * @ClassName: ListFile
 * 
 * @Description: 遍历文件目录的树
 * 
 * @author ZhongLingYun
 * 
 * @date 2018年8月6日 上午11:57:59
 * 
 */
public class ListFile {

	public static void main(String[] args) {
		File file = new File("E:\\资料\\03、中台201807培训资料(全)");
		fileList(file, 0);
	}

	/**
	 * 
	 * @Title: fileList
	 * 
	 * @Description:
	 * 
	 * @param @param
	 *            file 文件对象
	 * @param @param
	 *            enter 层级结构起始位置，0表示从文件夹开始
	 * 
	 * @return void
	 * 
	 * @author ZhongLingYun
	 * 
	 */
	public static void fileList(File file, int enter) {
		// 判断是否为文件夹
		if (file.isDirectory()) {
			File[] fileArray = file.listFiles();
			for (int x = 0; x < fileArray.length; x++) {
				// 文件层级结构
				for (int c = 0; c < enter; c++) {
					System.out.print("-");
				}
				System.out.println(fileArray[x].getName());
				if (fileArray[x].isDirectory()) {
					fileList(fileArray[x], enter + 1);
				}
			}
		}
	}

}
