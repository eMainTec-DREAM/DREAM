package dream.mgr.cal.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.mgr.cal.dao.MaWoCalListDAO;
import dream.mgr.cal.dto.MaWoCalCommonDTO;

/**
 * Working Calendar - ��� dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maWoCalListDAOTarget"
 * @spring.txbn id="maWoCalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoCalListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoCalListDAO
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
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT ''                                       seqNo			");
        query.append("       ,''                                      isDelCheck	");
        query.append("       ,x.wrkcalendar_id                        wrkCalendarId	");
        query.append("       ,														");
        query.getDate("x.cal_date", "calDate");
        if("ko".equals(maWoCalCommonDTO.getUserLang())){
        	query.append(",TO_CHAR( TO_DATE(x.cal_date,'YYYYMMDD'), 'DAY' ) day		");
        }else{
        	query.append(",TO_CHAR( TO_DATE(x.cal_date,'YYYYMMDD'), 'DAY','NLS_DATE_LANGUAGE=ENGLISH'  ) 	day	");
        }
        query.append("       ,x.is_work isWork										");
        query.append("       ,TO_CHAR(TO_DATE(cal_date,'yyyymmdd'),'mm') 	mm		");
        query.append("       ,TO_CHAR(TO_DATE(cal_date,'yyyymmdd'),'yyyy') 	yyyy	");
        query.append("       ,y.description							  wrkcalList	");
        query.append("FROM   TAWRKCALENDAR x INNER JOIN TAWRKCALLIST Y				");
        query.append("	ON 	 x.wrkcallist_id = y.wrkcallist_id						");
    	query.append("WHERE  x.comp_no = '"+user.getCompNo()+"'						");
        query.append(this.getWhere(maWoCalCommonDTO, user));
//    	query.append("ORDER  BY x.cal_date											");
    	query.getOrderByQuery("x.cal_date", maWoCalCommonDTO.getOrderBy(), maWoCalCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoCalCommonDTO.getIsLoadMaxCount(), maWoCalCommonDTO.getFirstRow()));
    } 
    
    /**
     * Filter ����
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
        QueryBuffer query = new QueryBuffer();
        
        if (!"".equals(maWoCalCommonDTO.getWrkCalendarId()))
        {
            query.getAndQuery("x.wrkcalendar_id", maWoCalCommonDTO.getWrkCalendarId());
            return query.toString();
        }
        //����
        query.getAndDateQuery("x.cal_date", maWoCalCommonDTO.getFilterStartDate(), maWoCalCommonDTO.getFilterEndDate());
        // ���� 
        query.getPlantCdQuery("x.plant", maWoCalCommonDTO.getFilterPlantId(), maWoCalCommonDTO.getFilterPlantDesc(), maWoCalCommonDTO.getCompNo());
        // �ٹ�����
        query.getSysCdQuery("x.is_work", maWoCalCommonDTO.getFilterIsJoin(), maWoCalCommonDTO.getFilterIsJoin(), "IS_USE", maWoCalCommonDTO.getCompNo(),user.getLangId());
        
        // �ٹ��޷�
        query.getAndQuery("y.wrkcallist_id", maWoCalCommonDTO.getFilterWrkcalListId());
        
        return query.toString();
    }

    /**
     * �ٹ����� 
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
    	QueryBuffer query = new QueryBuffer();
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
     * �ٹ����� 
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
    	QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query = new QueryBuffer();
        query.append("INSERT INTO TALNWRKTIME ");
        query.append("        (COMP_NO, LNWRKTIME_ID, PLANT, WRK_DATE, EQLOC_ID, DTIME, NTIME, REMARK, ETIME) ");
        query.append("        SELECT comp_no, SQALNWRKTIME_ID.NEXTVAL, '"+user.getPlant()+"', (SELECT cal_date FROM TAWRKCALENDAR where comp_no='"+compNo+"' AND wrkcalendar_id='"+id+"'), eqloc_id, NVL(dtime,'0'),NVL(ntime,'0'),'',NVL(etime,'0') ");
        query.append("        FROM taeqloc where 1=1 ");
        query.append("            and comp_no = '"+compNo+"' ");
        query.append("            and is_operation = 'Y' ");
        query.append("            and is_use = 'Y' ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    
    /**
     * �޹����� 
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
        QueryBuffer query = new QueryBuffer();
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
        
        query = new QueryBuffer();
        
        query.append("DELETE TALNWRKTIME x");
        query.append("WHERE x.wrk_date=(SELECT y.cal_date FROM TAWRKCALENDAR y where y.comp_no='"+compNo+"' AND y.wrkcalendar_id='"+id+"') ");
        query.append("and x.comp_no='"+compNo+"' ");

        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;
    }
    
    /**
     * �޹����� 
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
        QueryBuffer query = new QueryBuffer();
        
        query.append("begin                                                     ");
        query.append("SP_RESET_WRKCAL_BYALL('"+compNo+"', '"+plant+"','"+changeDate+"','"+userNo+"' );          ");
        query.append("end;                                                      ");
        
        this.getJdbcTemplate().execute(query.toString());
        
        return 0;
    }

	@Override
	public String findTotalCount(MaWoCalCommonDTO maWoCalCommonDTO, User user)
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                                     				");
        query.append("       COUNT(1)                               			");
        query.append("FROM   TAWRKCALENDAR x INNER JOIN TAWRKCALLIST Y			");
        query.append("	ON	 x.wrkcallist_id = y.wrkcallist_id					");
    	query.append("WHERE	 x.comp_no = '"+maWoCalCommonDTO.getCompNo()+"'		");
        query.append(this.getWhere(maWoCalCommonDTO, user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}