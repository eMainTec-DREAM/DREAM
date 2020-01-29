package dream.work.rpt.woendrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.work.rpt.woendrate.dao.WorkRptWoEndRateListDAO;
import dream.work.rpt.woendrate.dto.WorkRptWoEndRateCommonDTO;

/**
 * 작업오더 일마감 처리율 목록  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="workRptWoEndRateListDAOTarget"
 * @spring.txbn id="workRptWoEndRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptWoEndRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements WorkRptWoEndRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptWoEndRateCommonDTO
     * @return List
     */
    public List findList(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 																												");
        query.append("  ''																									AS seqNo		");
        query.append("  , a.plant																							AS plantId		");
        query.append("  ,(SELECT xx.description FROM TAPLANT xx WHERE xx.plant = a.plant)                                	AS plantDesc	");
        query.append(" ,SUBSTR(a.wkor_date,0,6) 																			AS month		");
        query.append(" ,SUM(CASE WHEN a.end_date >= a.close_date  THEN 1.0 ELSE 0 END) 										AS mitCnt		");
        query.append(" ,COUNT(*) 																							AS totCnt		");
        query.append(" ,ROUND((  SUM(CASE WHEN a.end_date >= a.close_date  THEN 1.0 ELSE 0 END)												");
        query.append("       / COUNT(*)																										");
        query.append("     ) *100,2) 																						AS mitRate		");
        query.append("FROM TAWORKORDER a																									");
        query.append("WHERE 1=1																												");
        query.append(" AND a.wo_status in ('PRC', 'C')																						");
        query.append(this.getWhere(workRptWoEndRateCommonDTO, user));
        query.append("GROUP BY a.plant, substr(a.wkor_date,0,6)																				");
        query.getOrderByQuery("a.plant, substr(a.wkor_date,0,6)", workRptWoEndRateCommonDTO.getOrderBy(), workRptWoEndRateCommonDTO.getDirection());
       
        return getJdbcTemplate().queryForList(query.toString(workRptWoEndRateCommonDTO.getIsLoadMaxCount(), workRptWoEndRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param workRptWoEndRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        //공장
        query.getCodeLikeQuery("a.plant", "(SELECT xx.description FROM TAPLANT xx WHERE xx.comp_no = '"+user.getCompNo()+"' AND a.plant = xx.plant )", 
        		workRptWoEndRateCommonDTO.getFilterPlantId(), workRptWoEndRateCommonDTO.getFilterPlantDesc());
        query.getStringEqualQuery("a.is_deleted", "N");
        //일자
//        query.getAndDateQuery("a.wkor_date", workRptWoEndRateCommonDTO.getFilterStartDate(), workRptWoEndRateCommonDTO.getFilterEndDate());
        String fromMonth = workRptWoEndRateCommonDTO.getFilterStartDate();
        String toMonth   = workRptWoEndRateCommonDTO.getFilterEndDate();
        
        if (fromMonth != null && !"".equals(fromMonth) && toMonth != null && !"".equals(toMonth))
        {
        	// toMonth가 오늘이후면 오늘까지만 조회
        	if(DateUtil.compareDate(DateUtil.getDate(), DateUtil.plusLastDayOfMonth(toMonth)))
        	{
        		query.getAndDateQuery("a.wkor_date", fromMonth +"01", DateUtil.getDate());
        	}
        	else{
        		query.getAndDateQuery("a.wkor_date", fromMonth +"01", DateUtil.plusLastDayOfMonth(toMonth));
        	}
        }
        
        return query.toString();
    }

    public String findTotalCount(
            WorkRptWoEndRateCommonDTO workRptWoEndRateCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT COUNT(*) FROM (																								");
        query.append("SELECT 																												");
        query.append("  a.plant																								AS plantId		");
        query.append("  ,(SELECT xx.description FROM TAPLANT xx WHERE xx.plant = a.plant)                                	AS plantDesc	");
        query.append(" ,SUBSTR(a.wkor_date,0,6) 																			AS month		");
        query.append(" ,SUM(CASE WHEN a.end_date >= a.close_date  THEN 1.0 ELSE 0 END) 										AS mitCnt		");
        query.append(" ,COUNT(*) 																							AS totCnt		");
        query.append(" ,ROUND((  SUM(CASE WHEN a.end_date >= a.close_date  THEN 1.0 ELSE 0 END)												");
        query.append("       / COUNT(*)																										");
        query.append("     ) *100,2) 																						AS mitRate		");
        query.append("FROM TAWORKORDER a																									");
        query.append("WHERE 1=1																												");
        query.append(" AND a.wo_status in ('PRC', 'C')																						");
        query.append(this.getWhere(workRptWoEndRateCommonDTO, user));
        query.append("GROUP BY a.plant, SUBSTR(a.wkor_date,0,6)																				");
        query.append(" ) a																													");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
