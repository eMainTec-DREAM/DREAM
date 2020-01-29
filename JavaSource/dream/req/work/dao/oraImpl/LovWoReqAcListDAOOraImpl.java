package dream.req.work.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.work.dao.LovWoReqAcListDAO;
import dream.req.work.dto.LovWoReqAcListDTO;

/**
 * 작업요청 팝업
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 *
 * @spring.bean id="lovWoReqAcListDAOTarget"
 * @spring.txbn id="lovWoReqAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWoReqAcListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovWoReqAcListDAO
{
    /**
     * 작업 검색
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param lovWoReqAcListDTO
     * @param loginUser
     * @return
     */
	public List findWoReqAcList(LovWoReqAcListDTO lovWoReqAcListDTO, User loginUser, Map<String, String> conditionMap) {
		
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("       x.woreq_id 										AS woReqId		");
        query.append("       ,x.woreq_no 										AS woReqNo		");
        query.append("       ,x.description 									AS woReqDesc	");
        query.append("       ,SFACODE_TO_DESC(x.woreq_status,'WOREQ_STATUS','SYS','','"+loginUser.getLangId()+"')	AS woReqStatusDesc	");
        query.append("       ,( SELECT a.description                   							");
        query.append("          FROM TAEQUIPMENT a												");
        query.append("			WHERE a.comp_no = x.comp_no										");
        query.append("			AND	a.equip_id = x.equip_id    )  				AS equipDesc	");
        query.append("       ,(SELECT a.full_desc                          						");
        query.append("         FROM TAEQLOC a                    								");
        query.append("         WHERE a.comp_no = x.comp_no                                   	");
        query.append("          AND	a.eqloc_id = x.eqloc_id    )	   			AS eqLocDesc   	");
        query.append("       ,(SELECT description												");
        query.append("		   FROM TADEPT														");
        query.append("		  WHERE comp_no = x.comp_no											");
        query.append("			AND dept_id = x.req_dept_id		) 				AS reqDept		");
        query.append("		 ,SFACODE_TO_DESC(x.woreq_type,'WOREQ_TYPE','SYS','','"+loginUser.getLangId()+"')		AS woReqTypeDesc	");
        query.append("		 ,(SELECT emp_name													");
        query.append("		   FROM TAEMP														");
        query.append("		   WHERE comp_no = x.comp_no										");
        query.append("			AND emp_id = x.req_emp_id		) 				AS reqEmp		");
        query.append("FROM TAWOREQ x                                                        	");
        query.append("WHERE 1=1                                                                 ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.append(this.getWhere(lovWoReqAcListDTO,loginUser));
        
        query.getOrderByQuery("x.woreq_no DESC", lovWoReqAcListDTO.getOrderBy(), lovWoReqAcListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovWoReqAcListDTO.getIsLoadMaxCount(), lovWoReqAcListDTO.getFirstRow()));
	}
	
    private String getWhere(LovWoReqAcListDTO lovWoReqAcListDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();

        String startDate = lovWoReqAcListDTO.getFilterReqStartDate();
        String endDate = lovWoReqAcListDTO.getFilterReqEndDate();
        String compNo  =loginUser.getCompNo();

        //요청번호
        query.getAndQuery("x.woreq_no", lovWoReqAcListDTO.getFilterWoReqNo());
        //요청업명
        query.getLikeQuery("x.description", lovWoReqAcListDTO.getFilterWoReqDesc());
        //요청일자
        query.getAndDateQuery("x.req_date", startDate, endDate);
        //부서
        query.getDeptLevelQuery("x.rec_dept_id", lovWoReqAcListDTO.getFilterReqDeptId(), lovWoReqAcListDTO.getFilterReqDeptDesc(), compNo);
        //설비
        if(!"".equals(lovWoReqAcListDTO.getFilterEquipId())||!"".equals(lovWoReqAcListDTO.getFilterEquipDesc())){
        	query.append("AND x.equip_id IN (SELECT a.equip_id  					");
        	query.append("					FROM TAEQUIPMENT a						");
        	query.append("					WHERE a.comp_no = x.comp_no				");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getCodeLikeQuery("a.equip_id", "a.description||a.item_no",
        			lovWoReqAcListDTO.getFilterEquipId(), lovWoReqAcListDTO.getFilterEquipDesc());
            query.append("					)										");
        }
        
        //요청자
        query.getCodeLikeQuery("x.req_emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.req_emp_id AND a.comp_no='"+compNo+"')", 
        		lovWoReqAcListDTO.getFilterReqEmpId(), lovWoReqAcListDTO.getFilterReqEmpDesc());
        //위치
        query.getEqLocLevelQuery("x.eqloc_id", lovWoReqAcListDTO.getFilterEqLocId(), lovWoReqAcListDTO.getFilterEqLocDesc(), compNo);

        //상태
        query.getSysCdQuery("x.woreq_status", lovWoReqAcListDTO.getFilterWoReqStatus(), lovWoReqAcListDTO.getFilterWoReqStatusDesc(), "WOREQ_STATUS", compNo,loginUser.getLangId());
        
        return query.toString();
    }

	@Override
	public String findTotalCount(LovWoReqAcListDTO lovWoReqAcListDTO, User loginUser, Map<String, String> conditionMap) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT               	");
        query.append("      count(1)        ");
        query.append("FROM TAWOREQ x        ");
        query.append("WHERE 1=1             ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.append(this.getWhere(lovWoReqAcListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QueryBuffer.listToString(resultList);
	}
}