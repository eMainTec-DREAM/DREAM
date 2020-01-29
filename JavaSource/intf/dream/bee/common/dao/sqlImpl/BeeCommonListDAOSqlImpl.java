package intf.dream.bee.common.dao.sqlImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.dream.bee.common.dao.BeeCommonListDAO;
/**
 * dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="beeCommonListDAOTarget"
 * @spring.txbn id="beeCommonListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class BeeCommonListDAOSqlImpl extends BaseJdbcDaoSupportSql implements BeeCommonListDAO
{
	public List findNextVal(Map map) throws Exception
    {
		String seqName = CommonUtil.convertString(map.get("seqName"));
    	
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT NEXT VALUE FOR " + seqName + " AS NEXTVALUE            ");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
	public List findConfigVal(Map map) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT value$ AS value            ");
		query.append("FROM   TACONFIG                   ");
		query.append("WHERE 1=1                         ");
		query.append("AND comp_no = ?                   ");
		query.append("AND name    = ?                   ");
		
		Object[] objects = new Object[] {
				CommonUtil.convertString(map.get("compNo"))
				,CommonUtil.convertString(map.get("name"))
		};
		
		return getJdbcTemplate().queryForList(query.toString(),getObject(objects));
	}
	@Override
	public void insertTraceLogHeader(Map data) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TATRACELOG (                     ");
		query.append("	 UPDATE_TIME, TRACELOG_ID, FILE_NAME       ");
		query.append("	,USER_NO, EMP_NO, DEPT_NO                  ");
		query.append("	,DATA_CUD_TYPE, COMP_NO, OBJECT_ID         ");
		query.append("	)                                          ");
		query.append("VALUES (                                     ");
		query.append("	 ?,?,?                                     ");
		query.append("	,?,?,?                                     ");
		query.append("	,?,?,?                                     ");
		query.append("	)                                          ");
		
		Object[] objects = new Object[] {
				DateUtil.getDateTime()
				,CommonUtil.convertString(data.get("traceLogId"))
				,CommonUtil.convertString(data.get("pageName"))
				,CommonUtil.convertString(data.get("userNo"))
				,CommonUtil.convertString(data.get("empNo"))
				,CommonUtil.convertString(data.get("deptNo"))
				,CommonUtil.convertString(data.get("dataCudType"))
				,CommonUtil.convertString(data.get("compNo"))
				,CommonUtil.convertString(data.get("auditKey"))
		};
		
		this.getJdbcTemplate().update(query.toString(), getObject(objects));
		
	}
	@Override
	public void insertTraceLogDetail(final String traceLogId, final List<Map<String,String>> results) throws Exception {

		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("INSERT INTO TATRACELOGDTL (                 ");
		query.append("	 TRACELOGDTL_ID, TRACELOG_ID, FIELD_ID    ");
		query.append("	,FIELD_VALUE                              ");
		query.append("	)                                         ");
		query.append("VALUES (                                    ");
		query.append("	NEXT VALUE FOR SQATRACELOGDTL_ID,?,?      ");
		query.append(" ,?                                         ");
		query.append(" )                                          ");
		
		this.getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter() {
            
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {

                Map<String,String> temMap = results.get(i);
                ps.setString(1, traceLogId);
                ps.setString(2, CommonUtil.convertString(temMap.get("key")));
                ps.setString(3, CommonUtil.convertString(temMap.get("value")));
            }
                    
            @Override
            public int getBatchSize() {
                return results.size();
            }
          });
		
		
	} 
}