package dream.req.work.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.req.work.dao.LovWoReqAcListDAO;
import dream.req.work.dto.LovWoReqAcListDTO;

/**
 * �۾���û �˾�
 * @author  syyang
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="lovWoReqAcListDAOTarget"
 * @spring.txbn id="lovWoReqAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWoReqAcListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovWoReqAcListDAO
{
    /**
     * �۾� �˻�
     * @author  syyang
     * @version $Id: $
     * @since   1.0
     * 
     * @param lovWoReqAcListDTO
     * @param loginUser
     * @return
     */
	public List findWoReqAcList(LovWoReqAcListDTO lovWoReqAcListDTO, User user, Map<String, String> conditionMap)
	{
		
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("       x.woreq_id 										AS woReqId		");
        query.append("       ,x.woreq_no 										AS woReqNo		");
        query.append("       ,x.description 									AS woReqDesc	");
        query.append("       ,dbo.SFACODE_TO_DESC(x.woreq_status,'WOREQ_STATUS','SYS','','"+user.getLangId()+"')	AS woReqStatusDesc	");
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
        query.append("		 ,dbo.SFACODE_TO_DESC(x.woreq_type,'WOREQ_TYPE','SYS','','"+user.getLangId()+"')		AS woReqTypeDesc	");
        query.append("		 ,(SELECT emp_name													");
        query.append("		   FROM TAEMP														");
        query.append("		   WHERE comp_no = x.comp_no										");
        query.append("			AND emp_id = x.req_emp_id		) 				AS reqEmp		");
        query.append("FROM TAWOREQ x                                                        	");
        query.append("WHERE 1=1                                                                 ");
        query.getAndQuery("x.comp_no", conditionMap);
        query.append(this.getWhere(lovWoReqAcListDTO,user));

        query.getOrderByQuery("x.woreq_id","x.woreq_no DESC", lovWoReqAcListDTO.getOrderBy(), lovWoReqAcListDTO.getDirection());

        
        return getJdbcTemplate().queryForList(query.toString(lovWoReqAcListDTO.getIsLoadMaxCount(), lovWoReqAcListDTO.getFirstRow()));
    }
	
    private String getWhere(LovWoReqAcListDTO lovWoReqAcListDTO, User loginUser)
    {      
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String startDate = lovWoReqAcListDTO.getFilterReqStartDate();
        String endDate = lovWoReqAcListDTO.getFilterReqEndDate();
        String compNo  =loginUser.getCompNo();

        //��û��ȣ
        query.getAndQuery("x.woreq_no", lovWoReqAcListDTO.getFilterWoReqNo());
        //��û����
        query.getLikeQuery("x.description", lovWoReqAcListDTO.getFilterWoReqDesc());
        //��û����
        query.getAndDateQuery("x.req_date", startDate, endDate);
        //�μ�
        query.getDeptLevelQuery("x.rec_dept_id", lovWoReqAcListDTO.getFilterReqDeptId(), lovWoReqAcListDTO.getFilterReqDeptDesc(), compNo);
        //����
        if(!"".equals(lovWoReqAcListDTO.getFilterEquipId())||!"".equals(lovWoReqAcListDTO.getFilterEquipDesc()))
        {
        	query.append("AND x.equip_id IN (SELECT a.equip_id  					");
        	query.append("					FROM TAEQUIPMENT a						");
        	query.append("					WHERE a.comp_no = x.comp_no				");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getCodeLikeQuery("a.equip_id", "a.description||a.item_no",
        			lovWoReqAcListDTO.getFilterEquipId(), lovWoReqAcListDTO.getFilterEquipDesc());
            query.append("					)										");
        }
        
        //��û��
        query.getCodeLikeQuery("x.req_emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.req_emp_id AND a.comp_no='"+compNo+"')", 
        		lovWoReqAcListDTO.getFilterReqEmpId(), lovWoReqAcListDTO.getFilterReqEmpDesc());
        //��ġ
        query.getEqLocLevelQuery("x.eqloc_id", lovWoReqAcListDTO.getFilterEqLocId(), lovWoReqAcListDTO.getFilterEqLocDesc(), compNo);

        //����
        query.getSysCdQuery("x.woreq_status", lovWoReqAcListDTO.getFilterWoReqStatus(), lovWoReqAcListDTO.getFilterWoReqStatusDesc(), "WOREQ_STATUS", compNo,loginUser.getLangId());
        
        return query.toString();
    }
	
	
	@Override
	public String findTotalCount(LovWoReqAcListDTO lovWoReqAcListDTO, User loginUser, Map<String, String> conditionMap) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
		
        query.append("SELECT               	");
        query.append("      count(1)        ");
        query.append("FROM TAWOREQ x        ");
        query.append("WHERE 1=1             ");
        query.getAndQuery("x.comp_no", conditionMap);
        
        query.append(this.getWhere(lovWoReqAcListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        
        return QuerySqlBuffer.listToString(resultList);
	}
}