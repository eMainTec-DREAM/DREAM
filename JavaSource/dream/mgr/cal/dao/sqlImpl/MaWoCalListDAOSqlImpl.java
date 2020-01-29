package dream.mgr.cal.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.cal.dao.MaWoCalListDAO;
import dream.mgr.cal.dto.MaWoCalCommonDTO;

/**
 * Working Calendar - 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maWoCalListDAOTarget"
 * @spring.txbn id="maWoCalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoCalListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoCalListDAO
{
    /**
     * grid find
     * @author kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoCalCommonDTO
     * @return List
     */
    public List findList(MaWoCalCommonDTO maWoCalCommonDTO, User user)
    { 
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  	");
        if("ko".equals(maWoCalCommonDTO.getUserLang())){
        	query.append("SET LANGUAGE korean;			");
        }
        else
        {
        	query.append("SET LANGUAGE english;			");
        }
        query.append("SELECT ''                                       		seqNo			");
        query.append("       ,''                                       		isDelCheck		");
        query.append("       ,x.wrkcalendar_id                         		wrkCalendarId	");
        query.append("       ,																");
        query.getDate("x.cal_date", "calDate");
        query.append("		 ,datename(weekday, x.cal_date)					day				");
        query.append("       ,x.is_work isWork												");
        query.append("       ,SUBSTRING((CONVERT(VARCHAR,cal_date)),5,2)	mm				");
        query.append("       ,SUBSTRING((CONVERT(VARCHAR,cal_date)),1,4)	yyyy			");
        query.append("       ,y.description							  wrkcalList			");
        query.append("FROM   TAWRKCALENDAR x INNER JOIN TAWRKCALLIST Y						");
        query.append("	ON	 x.wrkcallist_id = y.wrkcallist_id								");
    	query.append("WHERE	 x.comp_no = '"+user.getCompNo()+"'								");
        query.append(this.getWhere(maWoCalCommonDTO, user));
//    	query.append("ORDER  BY x.cal_date													");
    	query.getOrderByQuery("x.wrkcalendar_id", "x.cal_date", maWoCalCommonDTO.getOrderBy(), maWoCalCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoCalCommonDTO.getIsLoadMaxCount(), maWoCalCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: $
     * @since   1.0
     * 
     * @param maWoCalCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoCalCommonDTO maWoCalCommonDTO,User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        if (!"".equals(maWoCalCommonDTO.getWrkCalendarId()))
        {
            query.getAndQuery("x.wrkcalendar_id", maWoCalCommonDTO.getWrkCalendarId());
            return query.toString();
        }
        //일자
        query.getAndDateQuery("x.cal_date", maWoCalCommonDTO.getFilterStartDate(), maWoCalCommonDTO.getFilterEndDate());
        // 공장 
        query.getPlantCdQuery("x.plant", maWoCalCommonDTO.getFilterPlantId(), maWoCalCommonDTO.getFilterPlantDesc(), maWoCalCommonDTO.getCompNo());
        // 근무여부
        query.getSysCdQuery("x.is_work", maWoCalCommonDTO.getFilterIsJoin(), maWoCalCommonDTO.getFilterIsJoin(), "IS_USE", maWoCalCommonDTO.getCompNo(),user.getLangId());
        
        // 근무달력
        query.getAndQuery("y.wrkcallist_id", maWoCalCommonDTO.getFilterWrkcalListId());
        
        return query.toString();
    }

    /**
     * 근무지정 
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int dayOnCalList(String compNo, String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAWRKCALENDAR          ");
        query.append("SET is_work           = 'Y'	");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  wrkcalendar_id = ?     ");
        
        Object[] objects = new Object[] {
                compNo,
                id
                };
        
        rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
    }
    
    /**
     * 근무지정 
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int dayOnLnList(String compNo, String id, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query = new QuerySqlBuffer();
        query.append("INSERT INTO TALNWRKTIME ");
        query.append("        (COMP_NO, LNWRKTIME_ID, PLANT, WRK_DATE, EQLOC_ID, DTIME, NTIME, REMARK, ETIME) ");
        query.append("        SELECT comp_no, NEXT VALUE FOR SQALNWRKTIME_ID, '"+user.getPlant()+"', (SELECT cal_date FROM TAWRKCALENDAR where comp_no='"+compNo+"' AND wrkcalendar_id='"+id+"'), eqloc_id, ISNULL(dtime,'0'),ISNULL(ntime,'0'),'',ISNULL(etime,'0') ");
        query.append("        FROM taeqloc where 1=1 ");
        query.append("            and comp_no = '"+compNo+"' ");
        query.append("            and is_operation = 'Y' ");
        query.append("            and is_use = 'Y' ");
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    
    /**
     * 휴무지정 
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int dayOffList(String compNo, String id, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("UPDATE TAWRKCALENDAR          ");
        query.append("SET is_work           = 'N'	");
        query.append("WHERE  comp_no        = ?     ");
        query.append("  AND  wrkcalendar_id = ?     ");
        
        Object[] objects = new Object[] {   
                compNo,
                id
                };
        rtnValue = this.getJdbcTemplate().update(query.toString(), objects);
        
        query = new QuerySqlBuffer();
        
        query.append("DELETE TALNWRKTIME");
        query.append("WHERE wrk_date=(SELECT y.cal_date FROM TAWRKCALENDAR y where y.comp_no='"+compNo+"' AND y.wrkcalendar_id='"+id+"') ");
        query.append("and comp_no='"+compNo+"' ");

        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    
    /**
     * 휴무지정 
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param id
     * @return
     */
    public int popupSave(String compNo, String userNo, String plant, String changeDate)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("EXEC dbo.SP_RESET_WRKCAL_BYALL '"+compNo+"', '"+plant+"','"+changeDate+"','"+userNo+"';          ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }

	@Override
	public String findTotalCount(MaWoCalCommonDTO maWoCalCommonDTO, User user)
	{
        QuerySqlBuffer query = new QuerySqlBuffer();
       
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     	");
        query.append("SELECT                                     				");
        query.append("       COUNT(1)                               			");
        query.append("FROM   TAWRKCALENDAR x INNER JOIN TAWRKCALLIST Y			");
        query.append("	ON	 x.wrkcallist_id = y.wrkcallist_id					");
    	query.append("WHERE	 x.comp_no = '"+maWoCalCommonDTO.getCompNo()+"'		");
        query.append(this.getWhere(maWoCalCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QuerySqlBuffer.listToString(resultList);
	}
}