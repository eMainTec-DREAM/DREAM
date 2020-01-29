package dream.req.rpt.emwogenrate.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.DateUtil;
import common.util.QuerySqlBuffer;
import dream.req.rpt.emwogenrate.dao.ReqRptEmWoGenDetailDAO;
import dream.req.rpt.emwogenrate.dto.ReqRptEmWoGenRateCommonDTO;

/**
 * 작업의뢰 작업발행율 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqRptEmWoGenDetailDAOTarget"
 * @spring.txbn id="reqRptEmWoGenDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqRptEmWoGenDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements ReqRptEmWoGenDetailDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param reqRptEmWoGenRateCommonDTO
     * @param loginUser
     * @return List
     * @throws Exception 
     */
    public List findDetail(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;     ");
        query.append("SELECT 												");
        query.append("     a.wo_no							woNo			");
        query.append("    ,a.description					woName			");
        query.append("    ,a.wkor_date						wkorDate		");
        query.append("    ,(SELECT aa.item_no FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) 	 equipNo		");
        query.append("    ,(SELECT aa.description FROM TAEQUIPMENT aa WHERE b.comp_no = aa.comp_no AND b.equip_id = aa.equip_id) equipName 		");
        query.append("    ,(SELECT aa.description FROM TADEPT aa WHERE a.comp_no = aa.comp_no AND a.dept_id = aa.dept_id) 		 workDept 		");
        query.append("    ,(SELECT aa.emp_name FROM TAEMP aa WHERE a.comp_no = aa.comp_no AND a.emp_id = aa.emp_id) 			 WOMANAGER 		");
        query.append("    ,d.woreq_no						woReqNo			");
        query.append("    ,d.description 					woReqDesc		");
        query.append("    ,d.req_date						appReqDate		");
        query.append("    ,(SELECT aa.description FROM TADEPT aa WHERE d.comp_no = aa.comp_no AND d.req_dept_id = aa.dept_id) 	 woReqDept		");
        query.append("    ,(SELECT aa.emp_name FROM TAEMP aa WHERE d.comp_no = aa.comp_no AND d.req_emp_id = aa.emp_id) 		 appReqBy		");
        query.append("    ,b.equip_id        				equipId			");
        query.append("    ,a.wkor_id         				wkorId			");
        query.append("    ,d.woreq_id        				woReqId			");
        query.append("	  ,(SELECT aa.param1 FROM TACDSYSD aa WHERE aa.list_Type = a.wo_type+'_TYPE' AND aa.cdsysd_no = a.pm_type) param 		");
        query.append("    ,(SELECT aa.eqctg_type FROM TAEQUIPMENT aa WHERE aa.comp_no = b.comp_no AND aa.equip_id = b.equip_id) eqctg_type		");
        query.append("FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no= b.comp_no AND a.wkor_id = b.wkor_id	");
        query.append("INNER JOIN TAWOREQRES c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id					");
        query.append("INNER JOIN TAWOREQ d ON c.comp_no = d.comp_no AND c.woreq_id = d.woreq_id						");
        query.append("WHERE 1=1												");
        query.append(this.getWhere(reqRptEmWoGenRateCommonDTO,loginUser));
        query.getOrderByQuery("a.wkor_id", "a.wkor_date ASC", reqRptEmWoGenRateCommonDTO.getOrderBy(), reqRptEmWoGenRateCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(reqRptEmWoGenRateCommonDTO.getIsLoadMaxCount(), reqRptEmWoGenRateCommonDTO.getFirstRow()));
    }
    
    public String getWhere(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO,User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("a.plant", reqRptEmWoGenRateCommonDTO.getPlantId());
        query.append("AND a.wo_status IN('PRC', 'C')		");

        // 월
        String fromMonth = reqRptEmWoGenRateCommonDTO.getMonth();
        
        query.getAndDateQuery("a.wkor_date", fromMonth +"01", DateUtil.plusLastDayOfMonth(fromMonth));
        
        return query.toString();
    }
    
    public String findTotalCount(ReqRptEmWoGenRateCommonDTO reqRptEmWoGenRateCommonDTO, User loginUser) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                          ");
        query.append("    COUNT(1)                                    ");
        query.append("FROM TAWORKORDER a INNER JOIN TAWOEQUIP b ON a.comp_no= b.comp_no AND a.wkor_id = b.wkor_id	");
        query.append("INNER JOIN TAWOREQRES c ON a.comp_no = c.comp_no AND a.wkor_id = c.wkor_id					");
        query.append("INNER JOIN TAWOREQ d ON c.comp_no = d.comp_no AND c.woreq_id = d.woreq_id						");
        query.append("WHERE 1=1										  ");
        query.append(this.getWhere(reqRptEmWoGenRateCommonDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }
    
}