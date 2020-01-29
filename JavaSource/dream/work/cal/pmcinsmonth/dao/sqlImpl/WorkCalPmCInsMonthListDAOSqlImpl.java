package dream.work.cal.pmcinsmonth.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.CommonUtil;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.work.cal.pmcinsmonth.dao.WorkCalPmCInsMonthListDAO;
import dream.work.cal.pmcinsmonth.dto.WorkCalPmCInsMonthCommonDTO;

/**
 * 월간예방일정 - 목록 dao
 * @author  kim21017
 * @version $Id: WorkCalPmCInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="workCalPmCInsMonthListDAOTarget"
 * @spring.txbn id="workCalPmCInsMonthListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkCalPmCInsMonthListDAOSqlImpl extends BaseJdbcDaoSupportSql implements WorkCalPmCInsMonthListDAO
{
	/**
     * grid find
     * @author  kim21017
     * @version $Id: WorkCalPmCInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param workCalPmCInsMonthCommonDTO
     * @return List
     */
    public List findSchedList(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user)
    {

        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("  SELECT                                                          ");
        query.append("        ''                seqNo                                   ");
        query.append("      , ''                isDelCheck                              ");
        query.append("      , x.pm_id           pmId                                    ");
        query.append("      , z.pm_no           pmNo                                    ");
        query.append("      , z.description     pmDesc                                  ");
        query.append("      , x.pmequip_id      pmEquipId                               ");
        query.append("      , x.pminsdsched_id  pminsdschedId                           ");
        query.append("      , x.pminsdlist_id   pminsdlistId,                            ");
        query.getDate("x.plan_date", "planDate");
        query.append("      , x.sched_date      SCHEDDATE                               ");
        query.append("      , a.item_no                                                                                             equipNo                     ");
        query.append("      , a.description                                                                                         equipDesc                   ");
        query.append("      , (SELECT full_desc FROM TAEQLOC WHERE comp_no = a.comp_no AND eqloc_id = a.eqloc_id)                   eqLocDesc                   ");
        query.append("      , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = z.dept_id)            deptDesc                    ");
        query.append("      , (CASE WHEN x.pminsdlist_id IS NOT NULL THEN 'Y' ELSE 'N' END)                                         isActive                    ");
        query.append("      , ISNULL((SELECT (CASE WHEN a.pmsched_status = 'C' THEN 'Y' ELSE 'N' END )FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id),'N') isComplete                        ");
        query.append("      , (CASE WHEN (ISNULL((SELECT (CASE WHEN a.pmsched_status = 'C' THEN 'Y' ELSE 'N' END) FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id),'N')) = 'Y'                ");
        query.append("              THEN ((SELECT                                                                                                               ");
        query.getDate(                           "a.wkor_date", "");
        query.append("                       FROM TAPMINSDLIST a                                                                                                ");
        query.append("                      WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id)) ELSE '' END)         insDate                    ");
        query.append("      , (SELECT a.emp_id FROM TAPMINSDLIST a WHERE a.comp_no = x.comp_no AND a.pminsdlist_id = x.pminsdlist_id)       insById                     ");
        query.append("      , (SELECT a.emp_name FROM TAEMP a WHERE a.comp_no = x.comp_no AND a.emp_id = (SELECT b.emp_id FROM TAPMINSDLIST b WHERE b.comp_no = x.comp_no AND b.pminsdlist_id = x.pminsdlist_id))   insByDesc                       ");
        query.append("      , x.sched_date                                              ");
        query.append("      , x.pmsched_status  pmStatusCode                            ");
        query.append("      , x.equip_id        equipId                                 ");
        query.append("      , z.product_id      productId                               ");
        query.append("      , (SELECT a.description FROM TAPRODUCT a WHERE a.product_id = z.product_id)                     productDesc                                 ");
        query.append("      , z.emp_id          empId                                                                                                                   ");
        query.append("      , (SELECT a.emp_name FROM TAEMP a WHERE z.emp_id = a.emp_id) empDesc                                                                        ");
        query.append("      , (SELECT param2 FROM TACDSYSD WHERE cdsysd_no = z.pm_type AND list_type= z.wo_type+'_TYPE')   param                                        ");
        query.append("      , x.is_deleted                                                                                                                              ");
        query.append("  FROM TAPMINSDSCHED x INNER JOIN TAPMLST z ON x.comp_no = z.comp_no AND x.pm_id = z.pm_id                                                        ");
        query.append("                       LEFT OUTER JOIN TAEQUIPMENT a ON x.comp_no = a.comp_no AND x.equip_id = a.equip_id AND a.is_deleted='N'                    ");
        query.append("  WHERE 1=1                                                   ");
        query.append(this.getWhere(workCalPmCInsMonthCommonDTO,user));
        query.getOrderByQuery("x.pminsdsched_id", "x.plan_date", workCalPmCInsMonthCommonDTO.getOrderBy(), workCalPmCInsMonthCommonDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(workCalPmCInsMonthCommonDTO.getIsLoadMaxCount(), workCalPmCInsMonthCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: WorkCalPmCInsMonthListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param day
     * @param status
     * @param compNo
     * @param deptId
     * @param deptDesc
     * @return
     * @throws Exception
     */
    private String getWhere(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO,User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String yyyymmdd    = CommonUtil.getRowDateToNum(workCalPmCInsMonthCommonDTO.getYyyymmdd());
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("z.pm_type", "CINS");
        query.getStringEqualQuery("z.wo_type", "PMI");
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        query.getAndNumKeyQuery("x.pminsdsched_id", workCalPmCInsMonthCommonDTO.getPmInsDSchedId());
      
        //예방점검번호
        query.getLikeQuery("z.pm_no", workCalPmCInsMonthCommonDTO.getFilterPmNo());
        
        //년월
        query.append("  AND x.sched_date like        '"+ yyyymmdd +"%'                ");
        
        query.getSysCdQuery("x.pmsched_status", workCalPmCInsMonthCommonDTO.getSchedType(), "", "PMSCHED_STATUS", user.getCompNo(), user.getLangId());
        
        //설비
        query.getCodeLikeQuery("a.equip_id", "a.description+a.item_no",workCalPmCInsMonthCommonDTO.getFilterEquipId(), workCalPmCInsMonthCommonDTO.getFilterEquipDesc());
        
        //위치
        query.getEqLocLevelQuery("a.eqloc_id", workCalPmCInsMonthCommonDTO.getFilterEqLocId(), workCalPmCInsMonthCommonDTO.getFilterEqLocDesc(), user.getCompNo());
        
        //부서명
        query.getDeptLevelQuery("z.dept_id", workCalPmCInsMonthCommonDTO.getFilterDeptId(), workCalPmCInsMonthCommonDTO.getFilterDeptDesc(), user.getCompNo());
        
        //담당자
        query.getCodeLikeQuery("z.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = z.emp_id AND a.comp_no=z.comp_no)", workCalPmCInsMonthCommonDTO.getFilterEmpId(), workCalPmCInsMonthCommonDTO.getFilterEmpDesc());

        //최신설비
        query.getAndQuery("a.is_last_version", "Y");
        
        return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: WorkCalPmCInsMonthListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSched(String id, User user)
    {
    	
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
         String pmInsDSchedId = id;
    	
    	int result = 0;
        
        query.append("DELETE FROM TAPMINSDSCHED   ");
        query.append("WHERE comp_no = ?           ");
        query.append("  AND pminsdsched_id =  ?   ");

        Object[]  objects = new Object[]{
        		user.getCompNo()
        		,pmInsDSchedId
        };
        
        return  this.getJdbcTemplate().update(query.toString(),getObject(objects));
    }
    
    public void SP_PM_MAKE_TO_PMI(String pminsschedId, User user ) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("begin                                                       ");
        query.append("SP_PM_MAKE_TO_PMI('"+user.getCompNo()+"',"+pminsschedId+"); ");
        query.append("end;                                                        ");

        this.getJdbcTemplate().execute(query.toString());
    }
  
   public String checkSched(String pmInsSchedId, User user)
   {
	   QuerySqlBuffer query = new QuerySqlBuffer();
   	
   	query.append("SELECT  PMSCHED_STATUS         ");
   	query.append("FROM TAPMINSDSCHED             ");
       query.append("WHERE comp_no = ?              ");
       query.append("  AND PMINSDSCHED_ID =  ?      ");
       Object[] objects = new Object[]{
       		user.getCompNo()
       		,pmInsSchedId
       };
       
       return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString(),getObject(objects)));
   }
   
   public int updateDeleteTagSched(String pmInsSchedId, User user)
   {
   	
	   QuerySqlBuffer query = new QuerySqlBuffer();
   	
   	int result = 0;
   	
       query.append("UPDATE TAPMINSDLIST  SET      ");
       query.append("         IS_DELETED = 'Y'     ");
       query.append("        ,DELETE_BY  = ?       ");
       query.append("        ,DELETE_TIME = ?      ");
       query.append("WHERE COMP_NO = ?             ");
	   query.append("  AND PMINSDLIST_ID = (        ");
	   query.append("                         SELECT PMINSDLIST_ID              ");
	   query.append("                         FROM TAPMINSDSCHED                ");
	   query.append("                         WHERE COMP_NO = ?                 ");
	   query.append("                              AND PMINSDSCHED_ID = ?       ");
	   query.append("                        )                                  ");
	   Object[] objects = new Object[]{
	   		user.getEmpId()
	   		,DateUtil.getDateTime()
	      		,user.getCompNo()
	      		,user.getCompNo()
	      		,pmInsSchedId
	      };
	   result = this.getJdbcTemplate().update(query.toString(),getObject(objects));
       
       
       query.setClear();
       query.append("UPDATE TAPMINSDSCHED  SET     ");
       query.append("         IS_DELETED = 'Y'     ");
       query.append("        ,DELETE_BY  = ?       ");
       query.append("        ,DELETE_TIME = ?     ");
       query.append("WHERE COMP_NO = ?              ");
       query.append("  AND PMINSDSCHED_ID =  ?      ");
       objects = new Object[]{
       		user.getEmpId()
       		,DateUtil.getDateTime()
       		,user.getCompNo()
       		,pmInsSchedId
       };
       
       return  this.getJdbcTemplate().update(query.toString(),getObject(objects));
   }
   
   public int updateResultSchedDetail(String id, String scheDate, User user)
   {
	   QuerySqlBuffer query = new QuerySqlBuffer();
   	
   	String pmInsSchedId = id;
   	
   	query.append("UPDATE  TAPMINSDLIST SET        ");
   	query.append("           WKOR_DATE     = ?    ");
   	query.append("          ,START_DATE    = ?    ");
   	query.append("          ,END_DATE      = ?    ");
   	query.append("WHERE COMP_NO = ?               ");
   	query.append("    AND PMINSDLIST_ID = (        ");
   	query.append("                         SELECT PMINSDLIST_ID        ");
   	query.append("                         FROM TAPMINSDSCHED          ");
   	query.append("                         WHERE COMP_NO = ?           ");
   	query.append("                              AND PMINSDSCHED_ID = ? ");
   	query.append("                        )                            ");

   	Object[] objects = new Object[]{
   			scheDate
   			,scheDate
   			,scheDate
   			,user.getCompNo()
   			,user.getCompNo()
   			,pmInsSchedId
   	};
   	
   	return this.getJdbcTemplate().update(query.toString(),getObject(objects));
   	
   }
   public int updateScheduleDate(String id, String scheDate, User user)
   {
    QuerySqlBuffer query = new QuerySqlBuffer();
    
       String pmInsSchedId = id;
    
       query.append("UPDATE TAPMINSDSCHED  SET      ");
       query.append("     SCHED_DATE = ?           ");
       query.append("WHERE comp_no = ?             ");
       query.append("  AND pminsdsched_id =  ?      ");
       Object[] objects = new Object[]{
             scheDate
            ,user.getCompNo()
            ,pmInsSchedId
       };
       
       return this.getJdbcTemplate().update(query.toString(),getObject(objects));
       
   }  
   
   @Override
   public String findTotalCount(WorkCalPmCInsMonthCommonDTO workCalPmCInsMonthCommonDTO, User user)
   {
       QuerySqlBuffer query = new QuerySqlBuffer();
       
       query.append("SELECT                                            	");
       query.append("       COUNT(1)                                   	");
       query.append("  FROM TAPMINSDSCHED x INNER JOIN TAPMLST z ON x.comp_no = z.comp_no AND x.pm_id = z.pm_id                                                        ");
       query.append("                       LEFT OUTER JOIN TAEQUIPMENT a ON x.comp_no = a.comp_no AND x.equip_id = a.equip_id AND a.is_deleted='N'                    ");
       query.append("  WHERE 1=1                                                   ");
       query.append(this.getWhere(workCalPmCInsMonthCommonDTO,user));

       List resultList=  getJdbcTemplate().queryForList(query.toString());
       return QuerySqlBuffer.listToString(resultList);
   }
}