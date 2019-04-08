package dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.AddrDAO;
import db.DBCon;

public class AddrDAOImpl implements AddrDAO {
	private static String selectAddrListSql = "select * from(\r\n" + 
			" select rownum as rown, addr.* from\r\n" + 
			" (select * from address $where$ order by ad_num) addr\r\n" + 
			" where rownum<=?)\r\n" + 
			" where rown>=?";
	private static String selectAddrCount = 
			"select count(1) from address $where$ ";
	private static String selectAddr = "select * from address where 1=1 and ad_num=? ";
	
	@Override
	public List<Map<String, String>> selectAddrList(Map<String, String> addr) {
		String adDong = addr.get("ad_dong");
		String sql = selectAddrListSql.replace("$where$", "");
		try {
			if(adDong!=null) {
				sql = selectAddrListSql.replace("$where$", 
						" where ad_dong like '%' || ? || '%' ");
			}
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			ps.setString(1, addr.get("lNum"));
			ps.setString(2, addr.get("sNum"));
			if(adDong!=null) {
				ps.setString(1, adDong);
				ps.setString(2, addr.get("lNum"));
				ps.setString(3, addr.get("sNum"));
			}
			ResultSet rs = ps.executeQuery();
			List<Map<String,String>> addrList = new ArrayList<>();
			while(rs.next()) {
				Map<String,String> address = new HashMap<>();
				address.put("ad_num",rs.getString("ad_num"));
				address.put("ad_sido",rs.getString("ad_sido"));
				address.put("ad_gugun",rs.getString("ad_gugun"));
				address.put("ad_dong",rs.getString("ad_dong"));
				address.put("ad_lee",rs.getString("ad_lee"));
				address.put("ad_bunji",rs.getString("ad_bunji"));
				address.put("ad_ho",rs.getString("ad_ho"));
				addrList.add(address);
			}
			return addrList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return null;
	}

	@Override
	public int selectTotalAddrCnt(Map<String,String> addr) {
		String adDong = addr.get("ad_dong");
		String sql = selectAddrCount.replace("$where$", "");
		try {
			if(adDong!=null) {
				sql = selectAddrCount.replace("$where$", 
						" where ad_dong like '%' || ? || '%' ");
			}
			PreparedStatement ps = DBCon.getCon().prepareStatement(sql);
			if(adDong!=null) {
				ps.setString(1, adDong);
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBCon.close();
		}
		return 0;
	}

	@Override
	public Map<String, String> selectAddr(Map<String, String> addr) {
		try {
			PreparedStatement ps = DBCon.getCon().prepareStatement(selectAddr);
			ps.setString(1, addr.get("ad_num"));
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Map<String,String> address = new HashMap<>();
				address.put("ad_num",rs.getString("ad_num"));
				address.put("ad_sido",rs.getString("ad_sido"));
				address.put("ad_gugun",rs.getString("ad_gugun"));
				address.put("ad_dong",rs.getString("ad_dong"));
				address.put("ad_lee",rs.getString("ad_lee"));
				address.put("ad_bunji",rs.getString("ad_bunji"));
				address.put("ad_ho",rs.getString("ad_ho"));
				return address;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBCon.close();
		}
		return null;
	}
}
