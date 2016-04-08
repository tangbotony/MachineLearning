package com.tangbo;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		final int SMALL = 30;  
		final int BIG = 20;

		ArrayList<ArrayList<Double>> dataSet = new ArrayList<ArrayList<Double>>();  
		// 产生小圆数据  
		for(int i = 0; i < SMALL;) {  
			double x = 1 + Math.random() * 2; // 1到3的随机数  
			double y = 1 + Math.random() * 2; // 1到3的随机数  
			if((x - 2) * (x - 2) + (y - 2) * (y - 2) - 1 <= 0) { //说明位于圆内  
				ArrayList<Double> smallCircle = new ArrayList<Double>();  
				smallCircle.add(x);  
				smallCircle.add(y);  
				smallCircle.add(1.0); // 类别1
				dataSet.add(smallCircle);
				i++;
			}
		}
		// 产生外围环形数据  
		for(int i = 0; i < BIG;) {  
			double x1 = Math.random() * 4;  
			double y1 = Math.random() * 4;  
			if((x1 - 2) * (x1 - 2) + (y1 - 2) * (y1 - 2) - 4 < 0 && (x1 - 2) * (x1 - 2) + (y1 - 2) * (y1 - 2) - 1 > 0) { //说明位于环形区域内  
				ArrayList<Double> ring = new ArrayList<Double>();  
				ring.add(-x1);  
				ring.add(-y1);
				ring.add(-1.0); // 类别2
				dataSet.add(ring);
				i++;
			}  
		}  
		AdaboostAlgorithm algo = new AdaboostAlgorithm();
		AdboostResult result = algo.adaboostClassify(dataSet);//训练出来的分类器
		// 产生测试数据 
		int count = 0;
		for(int i = 0; i < 10; i++) {
			ArrayList<Double> testData = new ArrayList<Double>();  
			double x1 = Math.random() * 4;
			double y1 = Math.random() * 4;
			int classType = 1;
			if((x1 - 2) * (x1 - 2) + (y1 - 2) * (y1 - 2) - 4 < 0 && (x1 - 2) * (x1 - 2) + (y1 - 2) * (y1 - 2) - 1 > 0) {//说明位于环形区域内  
				classType=-1;
			}
			testData.add(x1);
			testData.add(y1);
			System.out.println("真实类别："+classType+",机器分类："+algo.computeResult(testData, result));
			if(classType==algo.computeResult(testData, result))
				count++;
		}
		System.out.println("正确率："+count+"%");
	}  

}