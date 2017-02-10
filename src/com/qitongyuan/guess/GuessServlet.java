package com.qitongyuan.guess;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GuessServlet
 */
@WebServlet("/GuessServlet")
public class GuessServlet extends HttpServlet {
	private int answer;
	public void newGame() {
		Random random= new Random();
		answer= random.nextInt(30);
	}
    public GuessServlet() {
        newGame();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		//输出的的格式
		String luckNoStr= request.getParameter("luckNo");
		System.out.println("答案："+answer);
		Integer luckNo=null;
		if (luckNoStr!=null||!luckNoStr.equals("")) {
			luckNo=Integer.parseInt(luckNoStr);
		}
		Integer times=1;//竞猜次数的初始值
		
		String timeStr= request.getParameter("times");
		if (timeStr!=null && !timeStr.equals("")) {
			times= Integer.parseInt(timeStr)+1;
		}
		if (times<5) {
		   String message="";
		   if (luckNo>answer) {
			message="很遗憾，大了些";
		}else if (luckNo<answer) {
			message="很遗憾，小了些";
		}else if (luckNo==answer) {
			message="恭喜您！ 猜对了";
			times=null;
		}
		 request.setAttribute("times", times);
		 request.setAttribute("message", message);
		}else {
			newGame();
			response.getWriter().write("游戏结束。<a href='"+request.getContextPath()+"/guess.jsp'>再来一盘</a>");
		    return;
		}
		request.getRequestDispatcher("/guess.jsp").forward(request,response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
