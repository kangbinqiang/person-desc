package com.spring.person.persondesc;

import com.spring.pojo.Order;
import com.spring.producer.OrderSender;
import org.activiti.engine.EngineServices;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDescApplicationTests {

	@Autowired
	private OrderSender orderSender;

	@Autowired
	private EngineServices processEngine;

	@Test
	public void contextLoads() {
		Order order = new Order();
		order.setId(20190520);
		order.setName("firstname");
		order.setMessageId(System.currentTimeMillis() + "$" + UUID.randomUUID().toString());
		orderSender.sendOrder(order);
	}

	//部署流程
	@Test
	public void deployProcess(){
		RepositoryService repositoryService=processEngine.getRepositoryService();
		DeploymentBuilder builder=repositoryService.createDeployment();
		builder.addClasspathResource("askforleave.bpmn");
		builder.deploy();
	}


		//顺时针打印一个矩阵

		@Test
		public void test1(){
			int[][] num = new int[100][100];
			int n = 6;
			int count =1;

			for(int i=0;i<n;i++){
				for(int j =0;j<n;j++){
					num[i][j]=count++;
				}
			}
			output(num,0,n-1);
		}

		public void output(int[][] num,int start,int end){
			if(start>end || end<=0)return;
			for(int i=start;i<=end;i++){
				System.out.println(num[start][i]);
			}
			for(int i=start+1;i<=end;i++){
				System.out.println(num[i][end]);
			}

			for(int i=end-1;i>=start;i--){
				System.out.println(num[end][i]);
			}
			for(int i=end-1;i>start;i--){
				System.out.println(num[i][start]);
			}

			output(num,start+1,end-1);
		}


	//给出一个排序好的数组和一个数，求数组中连续元素的和等于所给数的子数组

	@Test
	public void test2(){
		int[] num = {1,2,2,3,4,5,6,7,8,9};
		int sum = 7;
		findSum(num,sum);
	}

	public void findSum(int[] num,int sum){
		int left=0;
		int right=0;

		for(int i=0;i<num.length;i++){
			int curSum = 0;
			left = i;
			right = i;
			while(curSum<sum){
				curSum += num[right++];
			}
			if(curSum==sum){
				for(int j=left;j<right;j++){
					System.out.print(num[j]+" ");
				}
				System.out.println();
			}
		}
	}



	//字符数组组成的所有字符串

	@Test
	public void test3(){
		//char[] cs = {'a','b','c','d','e'};
		char[] cs = {'a','b','c','d','e'};
		int length = cs.length;
		recursionSwap(cs,0,length);
	}

	public void swap(char[] cs,int index1,int index2){
		char temp = cs[index1];
		cs[index1]=cs[index2];
		cs[index2]=temp;
	}

	public void recursionSwap(char[] cs,int start,int length){
		if(start>=length-1){
			print(cs);
			return;
		}
		for(int i=start;i<length;i++){
			swap(cs,start,i);
			recursionSwap(cs,start+1,length);
			swap(cs,start,i);
		}
	}

	public void print(char[] cs){
		for(int i=0;i<cs.length;i++){
			System.out.print(cs[i]);
		}
		System.out.println();
	}
	
}
