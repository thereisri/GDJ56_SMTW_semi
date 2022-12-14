package com.smtw.countrypageinfo.model.service;

import static com.smtw.common.JDBCTemplate.close;
import static com.smtw.common.JDBCTemplate.commit;
import static com.smtw.common.JDBCTemplate.getConnection;
import static com.smtw.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.smtw.country.model.vo.CountryPageInfo;
import com.smtw.countrypageinfo.model.dao.CountryPageInfoDao;

public class CountryPageInfoService {
	private CountryPageInfoDao dao=new CountryPageInfoDao();
	
	public List<CountryPageInfo> searchAll(){
		Connection conn=getConnection();
		List<CountryPageInfo> result=dao.searchAll(conn);
		close(conn);
		return result;
	}
	
	
	
	public int insertInfo(CountryPageInfo c) {
		Connection conn=getConnection();
		int result=dao.insertCountry(conn,c);
		if(result>0) commit(conn);
		else rollback(conn);
		return result;
	}
	
	
}