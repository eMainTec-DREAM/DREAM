package dream.work.rpt.eqeng.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.rpt.eqeng.dao.WorkRptEqEngDetailListDAO;
import dream.work.rpt.eqeng.dto.WorkRptEqEngCommonDTO;
import dream.work.rpt.eqeng.dto.WorkRptEqEngDetailListDTO;
import dream.work.rpt.eqeng.form.WorkRptEqEngDetailListForm;

/**
 * 에너지사용량(설비별) 상세 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workRptEqEngDetailListDAOTarget"
 * @spring.txbn id="workRptEqEngDetailListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkRptEqEngDetailListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkRptEqEngDetailListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param workRptEqEngListForm
     * @param loginUser
     * @return List
     */
    public List findDetailList(WorkRptEqEngDetailListForm workRptEqEngDetailListForm, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                         					");
        query.append("         ''     							seqNo	");
        query.append("       , SUBSTR(x.usage_date,1,4) ||'-'||         ");
        query.append("         SUBSTR(x.usage_date,5,2)      USAGEDATE  ");
        query.append("       , ROUND(SUM(x.USAGE),2)      		USAGE	");
        query.append("FROM TAFEMSMONUSAGE x                				");
        query.append("WHERE 1=1                            				");
        query.append(this.getWhere(workRptEqEngDetailListForm,loginUser));
        query.append("GROUP BY x.comp_no, x.equip_id, x.usage_date      ");
        query.append("ORDER BY x.comp_no, x.equip_id, x.usage_date		");

        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(WorkRptEqEngDetailListForm workRptEqEngDetailListForm,User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        WorkRptEqEngCommonDTO workRptEqEngCommonDTO = workRptEqEngDetailListForm.getWorkRptEqEngCommonDTO();
        WorkRptEqEngDetailListDTO workRptEqEngDetailListDTO = workRptEqEngDetailListForm.getWorkRptEqEngDetailListDTO();
        
        query.append("AND EXISTS (SELECT 1 							");
        query.append("              FROM TAEQUIPMENT a 				");
        query.append("             WHERE a.comp_no = x.comp_no 		");
        query.append("               AND a.equip_id = x.equip_id	");
        query.getAndQuery("a.is_last_version", "Y");
        query.getAndQuery("a.is_deleted", "N");
        query.getAndQuery("a.plant", workRptEqEngDetailListDTO.getPlantId());
        query.getAndQuery("a.eqloc_id", workRptEqEngDetailListDTO.getEqLocId());
        query.getAndQuery("a.usage_dept", workRptEqEngDetailListDTO.getUsageDeptId());
        query.getAndQuery("a.eqctg_id", workRptEqEngDetailListDTO.getEqCtgId());
        query.getAndQuery("a.equip_id", workRptEqEngDetailListDTO.getEquipId());
        query.append("                    )       					");

        String fromDate = workRptEqEngCommonDTO.getFilterYear()+"01";
        String toDate   = workRptEqEngCommonDTO.getFilterYear()+"12";
        
        if (fromDate != null && !"".equals(fromDate) && !"null".equals(fromDate))
        {
            query.append("AND x.usage_date >= '"+fromDate+"'     ");
        }
        
        if (toDate != null && !"".equals(toDate) && !"null".equals(toDate))
        {
            query.append("AND x.usage_date <= '"+toDate+"'       ");
        }         
        
        return query.toString();
    }
    
}