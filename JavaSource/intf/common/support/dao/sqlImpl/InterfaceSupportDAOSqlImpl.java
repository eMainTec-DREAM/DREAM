package intf.common.support.dao.sqlImpl;

import java.util.List;

import common.bean.MwareConfig;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import intf.common.batch.dto.CommonBatchDTO;
import intf.common.support.dao.InterfaceSupportDAO;
import intf.common.support.dto.InterfaceSupportDTO;

/**
 * Interface Supporter
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="interfaceSupportDAOTarget"
 * @spring.txbn id="interfaceSupportDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InterfaceSupportDAOSqlImpl  extends BaseJdbcDaoSupportSql implements InterfaceSupportDAO
{

    @Override
    public boolean isExecIntf(InterfaceSupportDTO interfaceSupportDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT is_use            ");
        query.append("FROM TAINTF              ");
        query.append("WHERE is_use   = ?       ");
        query.append("AND comp_no    = ?       ");
        query.append("AND intf_no    = ?       ");
        query.append("AND intf_type  = ?       ");
        
        Object[] objects = new Object[] {
                "Y"
                ,interfaceSupportDTO.getCompNo()
                ,interfaceSupportDTO.getIntfNo()
                ,interfaceSupportDTO.getIntfType()
        };

        String isUse = QuerySqlBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
        
		return "Y".equals(isUse)?true:false;
    }

    @Override
    public int updateInft(InterfaceSupportDTO interfaceSupportDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if(interfaceSupportDTO.getExecType()==CommonBatchDTO.MANUAL) {
            query.append("UPDATE TAINTF SET                                     ");
            query.append("    manual_last_exe_date = ?                          ");
            query.append("    ,manual_exe_time     = ?                          ");
            query.append("    ,exe_by       = (SELECT user_id                   ");
            query.append("                     FROM TAUSER                      ");
            query.append("                     WHERE user_no = ?                ");
            query.append("                       AND comp_no = ?)               ");
            query.append("    ,success_date = CASE WHEN ? ='Y' THEN ? ELSE success_date END ");
            query.append("    ,success_time = CASE WHEN ? ='Y' THEN ? ELSE success_time END ");
            query.append("WHERE 1=1                                             ");
            query.append("AND  comp_no       = ?                                ");
            query.append("AND  intf_no       = ?                                ");
            query.append("AND intf_type      = ?                                ");
        }
        else {
            query.append("UPDATE TAINTF SET                                     ");
            query.append("    last_exe_date = ?                                 ");
            query.append("    ,exe_time     = ?                                 ");
            query.append("    ,exe_by       = (SELECT user_id                   ");
            query.append("                     FROM TAUSER                      ");
            query.append("                     WHERE user_no = ?                ");
            query.append("                       AND comp_no = ?)               ");
            query.append("    ,success_date = CASE WHEN ? ='Y' THEN ? ELSE success_date END ");
            query.append("    ,success_time = CASE WHEN ? ='Y' THEN ? ELSE success_time END ");
            query.append("WHERE 1=1                                             ");
            query.append("AND  comp_no       = ?                                ");
            query.append("AND  intf_no       = ?                                ");
            query.append("AND intf_type      = ?                                ");
        }
        
        Object[] objects = new Object[] {
                DateUtil.getTimeStamp(MwareConfig.getDefaultOffset())
                ,DateUtil.getTimeStamp(MwareConfig.getDefaultOffset(), "yyyyMMdd HH:mm:ss")
                ,interfaceSupportDTO.getExeBy()
                ,interfaceSupportDTO.getCompNo()
                ,interfaceSupportDTO.getRtnYn()
                ,DateUtil.getTimeStamp(MwareConfig.getDefaultOffset())
                ,interfaceSupportDTO.getRtnYn()
                ,"".equals(interfaceSupportDTO.getSuccessTime())?DateUtil.getTimeStamp(MwareConfig.getDefaultOffset(), "yyyyMMddHHmmss"):interfaceSupportDTO.getSuccessTime()
                ,interfaceSupportDTO.getCompNo()
                ,interfaceSupportDTO.getIntfNo()
                ,interfaceSupportDTO.getIntfType()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

    @Override
    public int insertIntfLog(InterfaceSupportDTO interfaceSupportDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAINTFLOG (                                ");
        query.append("    comp_no,    intflog_id,           intf_id,         ");
        query.append("    exe_time,   exe_by,               rtnyn,           ");
        query.append("    rtnmsg,     exelog                                 ");
        query.append(")SELECT                                                ");
        query.append("    comp_no,    ?,                    intf_id,         ");
        query.append("    ?        ,  (SELECT user_id                      	 ");
        query.append("                 FROM TAUSER                           ");
        query.append("                 WHERE user_no = ?					 ");
        query.append("                   AND comp_no = ?),  ?,               ");
        query.append("    ?,          ?                                      ");
        query.append("FROM TAINTF                                            ");
        query.append("WHERE comp_no = ?                                      ");
        query.append("AND intf_no   = ?                                      ");
        query.append("AND intf_type = ?                                      ");
        
        Object[] objects = new Object[] {
                interfaceSupportDTO.getIntfLogId()
        		,DateUtil.getDate()+DateUtil.getDateTime("HH")+DateUtil.getDateTime("mm")
                ,interfaceSupportDTO.getExeBy()
                ,interfaceSupportDTO.getCompNo()
                ,interfaceSupportDTO.getRtnYn()
                ,interfaceSupportDTO.getRtnMsg()
                ,interfaceSupportDTO.getExeLog()
                ,interfaceSupportDTO.getCompNo()
                ,interfaceSupportDTO.getIntfNo()
                ,interfaceSupportDTO.getIntfType()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }

	@Override
	public String getLastSuccessDate(String compNo, String intfNo) {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT a.success_date				");
        query.append("FROM TAINTF a						");
        query.append("WHERE 1=1							");
        query.append("AND a.comp_no = ?					");
        query.append("AND a.intf_no = ?					");
        
        Object[] objects = new Object[] {
        		compNo
                ,intfNo
        };
		return QuerySqlBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
    }
	
	public String getLastSuccessTime(String compNo, String intfNo) {
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT a.success_time		");
		query.append("FROM TAINTF a				");
		query.append("WHERE 1=1					");
		query.append("AND a.comp_no = ?			");
		query.append("AND a.intf_no = ?			");
		
		Object[] objects = new Object[] {
				compNo
				,intfNo
		};
		return QuerySqlBuffer.listToString(this.getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
	}
	
	@Override
    public List getAccData(InterfaceSupportDTO interfaceSupportDTO)
    {
	    QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT acc_url           ");
        query.append("       ,acc_name         ");
        query.append("       ,acc_port         ");
        query.append("       ,acc_user         ");
        query.append("       ,acc_password     ");
        query.append("       ,acc_site         ");
        query.append("FROM TAINTF              ");
        query.append("WHERE comp_no  = ?       ");
        query.append("AND intf_no    = ?       ");
        query.append("AND intf_type  = ?       ");
        
        Object[] objects = new Object[] {
                interfaceSupportDTO.getCompNo()
                ,interfaceSupportDTO.getIntfNo()
                ,interfaceSupportDTO.getIntfType()
        };
        
        return this.getJdbcTemplate().queryForList(query.toString(), objects);
    }

    @Override
    public int updateIntfLog(InterfaceSupportDTO interfaceSupportDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("UPDATE TAINTFLOG SET                                          ");
        query.append("       exelog             = exelog+?                          ");
        query.append("WHERE  intflog_id         = ?                                 ");
        
        Object[] objects = new Object[] {
                interfaceSupportDTO.getExeLog(),
                interfaceSupportDTO.getIntfLogId()
        };
        
        return this.getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
}
