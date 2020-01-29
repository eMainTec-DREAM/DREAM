package dream.req.rpt.emwogenrate.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.DateUtil;
import common.util.QueryBuffer;
import dream.req.rpt.emwogenrate.dao.ReqRptEmWoGenRateListDAO;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;

/**
 * 사후 작업오더 발생률 목록  - List DAO implements
 * @author js.lee
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="reqRptEmWoGenRateListDAOTarget"
 * @spring.txbn id="reqRptEmWoGenRateListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptEmWoGenRateListDAOOraImpl  extends BaseJdbcDaoSupportOra implements ReqRptEmWoGenRateListDAO
{

    /**
     * grid find
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptEmWoGenRateCommonDTO
     * @return List
     */
    public List findList(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                		");
        query.append("    b.plant                               plantId        		");
        query.append("   ,(SELECT c.description FROM TAPLANT c WHERE c.comp_no = a.comp_no AND c.plant = b.plant)     plantDesc    	");
        query.append("   ,SUBSTR(b.wkor_date,1,6)               month        		");
        query.append("   ,SUM(CASE WHEN a.woreq_gen_type = 'REQ'  THEN 1 ELSE 0 END)                                  mitCnt        ");
        query.append("   ,COUNT(*)                              totCnt        		");
        query.append("   ,ROUND(  (  SUM(CASE WHEN a.woreq_gen_type = 'REQ'  THEN 1.0 ELSE 0 END)        							");
        query.append("       / COUNT(*)                                        		");
        query.append("     ) *100,2)                            mitRate        		");
        query.append("FROM TAWOREQRES a INNER JOIN TAWORKORDER b             		");
        query.append("ON a.comp_no = b.comp_no AND a.wkor_id = b.wkor_id    		");
        query.append("WHERE 1=1                                 		       		");
        query.append(this.getWhere(reqRptEmWoGenRateCommonDTO, user));
        query.append("GROUP BY a.comp_no, b.plant,SUBSTR(b.wkor_date,1,6)      		");
        query.getOrderByQuery("a.comp_no", "a.comp_no, b.plant,SUBSTR(b.wkor_date,1,6)", reqRptEmWoGenRateCommonDTO.getOrderBy(), reqRptEmWoGenRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(reqRptEmWoGenRateCommonDTO.getIsLoadMaxCount(), reqRptEmWoGenRateCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  js.lee
     * @version $Id: $
     * @since   1.0
     *   
     * @param reqRptEmWoGenRateCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user) throws Exception
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("a.comp_no", user.getCompNo());
        query.getAndQuery("b.is_deleted", "N");
        query.append(" AND b.wo_status IN('PRC', 'C')		");
        query.append(" AND b.plant IS NOT NULL				");
        
        // 월
        String fromMonth = reqRptEmWoGenRateCommonDTO.getFilterStartDate();
        String toMonth   = reqRptEmWoGenRateCommonDTO.getFilterEndDate();
        
        query.getAndDateQuery("b.wkor_date", fromMonth +"01", DateUtil.plusLastDayOfMonth(toMonth));
        
        //공장코드
        query.getCodeLikeQuery("b.plant", "(SELECT c.description FROM  TAPLANT c WHERE c.comp_no = '"+user.getCompNo()+"' AND c.plant = b.plant )", 
        		reqRptEmWoGenRateCommonDTO.getFilterPlantId(), reqRptEmWoGenRateCommonDTO.getFilterPlantDesc());
                
        return query.toString();
    }

    public String findTotalCount(
            ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User user) throws Exception {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            	    ");
        query.append("    COUNT(1)										        ");
        query.append("FROM (                                            	    ");
        query.append("    SELECT                                            	");
        query.append("        	COUNT(1)										");
        query.append("    FROM TAWOREQRES a INNER JOIN TAWORKORDER b            ");
        query.append("    ON a.comp_no = b.comp_no AND a.wkor_id = b.wkor_id    ");
        query.append("    WHERE 1=1                                 		    ");
        query.append(this.getWhere(reqRptEmWoGenRateCommonDTO, user));
        query.append("    GROUP BY a.comp_no, b.plant,SUBSTR(b.wkor_date,1,6)   ");
        query.append(")                                                         ");

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }    
}
