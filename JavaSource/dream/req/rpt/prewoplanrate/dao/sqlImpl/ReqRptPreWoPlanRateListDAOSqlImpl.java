package dream.req.rpt.prewoplanrate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.req.rpt.prewoplanrate.dao.ReqRptPreWoPlanRateListDAO;
import dream.req.rpt.prewoplanrate.dto.ReqRptPreWoPlanRateCommonDTO;

/**
 * 작업오더 사전 계획 수립률 목록 - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqRptPreWoPlanRateListDAOTarget"
 * @spring.txbn id="reqRptPreWoPlanRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptPreWoPlanRateListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements ReqRptPreWoPlanRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptPreWoPlanRateCommonDTO
     * @return List
     */
    public List findList(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                                                             			");
        query.append("  a.plant                                                                                           	AS plantId        		");
        query.append("  ,(SELECT xx.description FROM TAPLANT xx WHERE xx.plant = a.plant)                                	AS plantDesc    		");
        query.append(" ,LEFT(a.wkor_date,6)                                                                             	AS MONTH        		");
        query.append(" ,SUM(CASE WHEN isnull(LEFT(a.cre_date,8),'19990101') < a.start_date  THEN 1.0 ELSE 0 END)         	AS mitCnt    			");
        query.append(" ,COUNT(*)                                                                                        	AS totCnt				");
        query.append(" ,CONVERT(numeric(5,2)    																									");
        query.append("  ,(  SUM(CASE WHEN isnull(LEFT(a.cre_date,8),'19990101') < a.start_date  THEN 1.0 ELSE 0 END)                    			");
        query.append("   / COUNT(*)                                                                                                        			");
        query.append("   ) *100)                                                                                        	AS mitRate        		");
        query.append("FROM TAWORKORDER a                                                                                                 			");
        query.append("WHERE 1=1     																												");
        query.append("  AND a.wo_status in('PRC', 'C')																				");
        query.append(this.getWhere(reqRptPreWoPlanRateCommonDTO, user)																				);
        query.append("GROUP BY a.plant, LEFT(a.wkor_date,6)																							");
        query.getOrderByQuery("a.plant","a.plant, LEFT(a.wkor_date,6)", reqRptPreWoPlanRateCommonDTO.getOrderBy(), reqRptPreWoPlanRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(reqRptPreWoPlanRateCommonDTO.getIsLoadMaxCount(), reqRptPreWoPlanRateCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     * 
     * @param reqRptPreWoPlanRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO, User user) throws Exception
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        //공장
        query.getCodeLikeQuery("a.plant", "(SELECT xx.description FROM TAPLANT xx WHERE xx.comp_no = '"+user.getCompNo()+"' AND a.plant = xx.plant )", 
        		reqRptPreWoPlanRateCommonDTO.getFilterPlantId(), reqRptPreWoPlanRateCommonDTO.getFilterPlantDesc());
        query.getStringEqualQuery("a.is_deleted", "N");
        //일자
        query.getAndDateQuery("a.wkor_date", reqRptPreWoPlanRateCommonDTO.getFilterStartDate(), reqRptPreWoPlanRateCommonDTO.getFilterEndDate());
        
        return query.toString();
    }
    
    public String findTotalCount(ReqRptPreWoPlanRateCommonDTO reqRptPreWoPlanRateCommonDTO, User user) throws Exception 
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT COUNT(*) FROM (					");
        query.append("SELECT                                                                                                             			");
        query.append("  a.plant                                                                                           	AS plantId        		");
        query.append("  ,(SELECT xx.description FROM TAPLANT xx WHERE xx.plant = a.plant)                                	AS plantDesc    		");
        query.append(" ,LEFT(a.wkor_date,6)                                                                             	AS MONTH        		");
        query.append(" ,SUM(CASE WHEN isnull(LEFT(a.cre_date,8),'19990101') < a.start_date  THEN 1.0 ELSE 0 END)         	AS mitCnt    			");
        query.append(" ,COUNT(*)                                                                                        	AS totCnt				");
        query.append(" ,CONVERT(numeric(5,2)    																									");
        query.append("  ,(  SUM(CASE WHEN isnull(LEFT(a.cre_date,8),'19990101') < a.start_date  THEN 1.0 ELSE 0 END)                    			");
        query.append("   / COUNT(*)                                                                                                        			");
        query.append("   ) *100)                                                                                        	AS mitRate        		");
        query.append("FROM TAWORKORDER a                                                                                                 			");
        query.append("WHERE 1=1     																												");
        query.append("  AND a.wo_status in('PRC', 'C')																				");
        query.append(this.getWhere(reqRptPreWoPlanRateCommonDTO, user)																				);
        query.append("GROUP BY a.plant, LEFT(a.wkor_date,6)																							");
        query.append(" ) a																													");

        List resultList =  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
