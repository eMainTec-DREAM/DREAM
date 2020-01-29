package dream.work.list.dept.sched.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dept.sched.list.dao.WorkListDeptSchedListDeptListDAO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedCommonDTO;
import dream.work.list.dept.sched.list.dto.WorkListDeptSchedListDeptListDTO;

/**
 * 업체별 작업스케줄탭부서별 작업 DAO
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="workListDeptSchedListDeptListDAOTarget"
 * @spring.txbn id="workListDeptSchedListDeptListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListDeptSchedListDeptListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListDeptSchedListDeptListDAO
{
	@Override
    public List findList(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 																	");
        query.append("        ''                    								seqNo		");
        query.append("        ,x.start_date        									startDate	");
        query.append("        ,x.END_DATE            								endDate		");
        query.append("        , (SELECT NVL(a.full_desc, a.description)							");
        query.append("             FROM TAEQLOC a INNER JOIN TAEQUIPMENT b						");
        query.append("               ON a.comp_no = b.comp_no AND a.eqloc_id = b.eqloc_id 		");
        query.append("            WHERE a.comp_no = x.comp_no AND b.equip_id = y.equip_id		");
        query.append("          )        											eqLocDesc	");
        query.append("        ,(SELECT description												");
        query.append("        FROM TAEQUIPMENT													");
        query.append("        WHERE comp_no = x.comp_no											");
        query.append("        AND equip_id = y.equip_id)                    		eqDesc		");
        query.append("        ,(SELECT description												");
        query.append("        FROM TAEQASMB														");
        query.append("        where comp_no = x.comp_no											");
        query.append("        AND eqasmb_id = x.EQASMB_ID)                    		eqAsmbDesc	");
        query.append("        ,x.WO_NO            									woNo		");
        query.append("        ,x.DESCRIPTION        								woDesc		");
        query.append("        ,(SELECT key_name													");
        query.append("        FROM TALANG														");
        query.append("        WHERE lang = '"+user.getLangId()+"'								");
        query.append("        AND key_type = 'CODESET'											");
        query.append("        AND key_no = 'WO_TYPE.'|| x.wo_type)					woType		");
        query.append("        ,(SELECT key_name 												");
        query.append("        FROM TALANG														");
        query.append("        WHERE lang = '"+user.getLangId()+"'								");
        query.append("        AND key_no = x.wo_type || '_TYPE.' || x.pm_type )		pmType		");
        query.append("        ,(SELECT key_name													");
        query.append("        FROM TALANG														");
        query.append("        WHERE lang = '"+user.getLangId()+"'								");
        query.append("        AND key_type = 'CODESET'											");
        query.append("        AND key_no = 'WO_STATUS.' || x.WO_STATUS)        		woStatus	");
        query.append("        ,x.perform                                        	perform		");
        query.append("        ,(SELECT emp_name													");
        query.append("        FROM TAEMP														");
        query.append("        WHERE emp_id=x.emp_id												");
        query.append("        AND comp_no = x.comp_no)                        		empDesc		");
        query.append("from TAWORKORDER x LEFT OUTER JOIN TAWOEQUIP y							");
        query.append("on x.comp_no = y.comp_no AND x.wkor_id = y.WKOR_ID						");
        query.append("where 1=1																	");
        query.append(this.getWhere(workListDeptSchedCommonDTO,workListDeptSchedListDeptListDTO,user));
        query.getOrderByQuery("x.START_DATE, x.END_DATE", workListDeptSchedListDeptListDTO.getOrderBy(), workListDeptSchedListDeptListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    public String getWhere(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO,User user)
    {
    	QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.dept_id", workListDeptSchedCommonDTO.getDeptId());
        query.append("AND x.start_date >= " + workListDeptSchedCommonDTO.getStartDate());
        query.append("AND x.end_date <= " + workListDeptSchedCommonDTO.getEndDate());
        
        query.append("AND EXISTS (SELECT 1 FROM TAEQUIPMENT a 		");
        query.append("             WHERE a.comp_no = y.comp_no 		");
        query.append("               AND a.equip_id = y.equip_id 	");
        query.append("               AND a.is_deleted = 'N'			");
        query.append("           )									");
        
        // 공장
        query.getCodeLikeQuery("x.plant", "(SELECT aa.description FROM  TAPLANT aa WHERE aa.comp_no = '"+user.getCompNo()+"' AND aa.plant = x.plant )", 
        		workListDeptSchedCommonDTO.getPlantId(), workListDeptSchedCommonDTO.getPlantDesc());
        // 설비종류
        query.getCodeLikeQuery("y.eqctg_id", "(SELECT aa.description FROM  TAEQCTG aa WHERE aa.comp_no = '"+user.getCompNo()+"' AND aa.eqctg_id = y.eqctg_id )", 
        		workListDeptSchedCommonDTO.getEqCtgId(), workListDeptSchedCommonDTO.getEqCtgDesc());

        return query.toString();
    }
    
    
    @Override
    public String findTotalCount(WorkListDeptSchedCommonDTO workListDeptSchedCommonDTO, WorkListDeptSchedListDeptListDTO workListDeptSchedListDeptListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 																	");
        query.append("        count(*)															");
        query.append("FROM TAWORKORDER x LEFT OUTER JOIN TAWOEQUIP y							");
        query.append("ON x.comp_no = y.comp_no AND x.wkor_id = y.WKOR_ID						");
        query.append("WHERE 1=1																	");
        query.append(this.getWhere(workListDeptSchedCommonDTO,workListDeptSchedListDeptListDTO,user));
               
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}