package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import entity.Subject;

public class SubjectDao extends BaseDao {

	public int searchCount() {
		int count = 0;
		try {
			getStatement();
			Statement stat = conn.createStatement();

			String sql = "select count(id) from subject";
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;

	}

	public int searchCount(Subject condition) {
		int count = 0;
		try {
			getStatement();
			Statement stat = conn.createStatement();
			String where = " where 1=1 ";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}

			String sql = "select count(id) from subject " + where;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return count;

	}

	public List<Subject> searchByBegin(int begin, int num) {
		List<Subject> list = new ArrayList<Subject>();

		try {
			getStatement();
			Statement stat = conn.createStatement();

			String sql = "select * from subject limit " + begin + "," + num;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("name"));
				list.add(sub);
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list;

	}

	public List<Subject> searchAll() {
		List<Subject> list = new ArrayList<Subject>();
		try {
			getStatement();

			String sql = "select * from subject";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("name"));
				list.add(sub);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;

	}

	public boolean add(Subject sub) {
		boolean flag = false;

		try {
			String sql = "insert into subject (name,stuNums) " + " values(?,?)";
			getPreparedStatement(sql);
			pstat.setString(1, sub.getName());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;
	}

	public Subject searchById(int id) {
		Subject sub = null;
		try {
			getStatement();

			String sql = "select * from subject where id=" + id;
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("name"));
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return sub;
	}

	public List<Subject> searchByIds(String ids) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			getStatement();

			String sql = "select * from subject where id in( " + ids + " )";
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("name"));
				list.add(sub);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean modify(Subject sub) {
		boolean flag = false;

		try {
			String sql = "update subject set name=? where id=?";
			getPreparedStatement(sql);
			pstat.setString(1, sub.getName());
			pstat.setInt(2, sub.getId());
			int rs = pstat.executeUpdate();
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;

	}

	public boolean modify2(List<Subject> list) {
		boolean flag = false;

		try {
			for (int i = 0; i < list.size(); i++) {
				Subject sub = list.get(i);
				String sql = "update subject set name=? where id=?";
				getPreparedStatement(sql);
				pstat.setString(1, sub.getName());
				pstat.setInt(2, sub.getId());
				int rs = pstat.executeUpdate();
				if (rs > 0) {
					flag = true;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;

	}

	public boolean delete(int id) {
		boolean flag = false;

		try {

			String sql = "delete from subject where id=?";
			getConnection();

			conn.setAutoCommit(false);
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			int rs = pstat.executeUpdate();

//			if (rs > 0) {
//				flag = true;
//			} else {
//
//				return flag;
//			}
//			sql = "update student set bsdj_id = null where sub_id=?";
//			pstat = conn.prepareStatement(sql);
//			pstat.setInt(1, id);
//
//			rs = pstat.executeUpdate();

			conn.commit();
			if (rs > 0) {
				flag = true;
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return flag;
	}

	public List<Subject> searchByCondition(Subject condition, int begin,
			int yeNum) {
		List<Subject> list = new ArrayList<Subject>();
		try {
			getStatement();

			String where = " where 1=1 ";
			if (condition.getName() != null) {
				where += " and name like '%" + condition.getName() + "%'";
			}

			String sql = "select * from subject " + where + " limit " + begin
					+ "," + yeNum;
			rs = stat.executeQuery(sql);
			while (rs.next()) {
				Subject sub = new Subject();
				sub.setId(rs.getInt("id"));
				sub.setName(rs.getString("name"));
				list.add(sub);
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				closeAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}

}
