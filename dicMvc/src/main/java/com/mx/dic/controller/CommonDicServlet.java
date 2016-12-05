package com.mx.dic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.mx.micro.executor.ExecutorFactory;
import com.nh.esb.core.NhCmdRequest;
import com.nh.esb.core.NhCmdResult;

/**
 * Servlet implementation class CommonDicServlet
 */
public class CommonDicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public CommonDicServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String requestName = request.getParameter("requestName");
		if (requestName.equalsIgnoreCase("ListDic")) {
			NhCmdRequest nhCmdRequest = new NhCmdRequest();
			nhCmdRequest.setCmdName("ListDic");
			nhCmdRequest.setCmdData("");
			nhCmdRequest.setToSysId("dicService");
			try {
				NhCmdResult nhCmdResult = ExecutorFactory.getExecutor("")
						.execNhCmd(nhCmdRequest);
				JSONObject jsonResult = JSONObject.fromObject(nhCmdResult
						.getResultData());
				JSONObject ret = new JSONObject();
				ret.put("total", jsonResult.get("size"));
				ret.put("rows", jsonResult.get("rows"));
				String retStr = ret.toString();
				System.out.println(retStr);
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().write(retStr);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}
}
