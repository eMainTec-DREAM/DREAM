package dream.work.pm.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.LovWorkPmListUInsListDAO;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;

/**
 * ������ ���������ֱ� Lov DAO OraImpl
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovWorkPmListUInsListDAOTarget"
 * @spring.txbn id="lovWorkPmListUInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWorkPmListUInsListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovWorkPmListUInsListDAO
{
    /**
     * �˻�
     * @author  sy.yang
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovWorkPmListUInsListDTO
     * @param loginUser
     * @return
     */
    public List findPmUInsListAcList(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User user, Map<String, String> conditionMap) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT 																	");
		query.append("	 	''           	   								AS SeqNo			");
		query.append("	 	,x.pm_no 										AS pmNo				");
		query.append("	 	,x.pm_id 										AS pmId				");
		query.append("	 	,x.plant 										AS plant			");
		query.append("	 	,(SELECT aa.description 											");
        query.append("		  FROM TAPLANT aa 													");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("		   AND aa.plant = x.plant)						AS plantDesc		");
		query.append("	 	,x.description 									AS description		");
        query.append("		,x.pm_type										AS pmType			");
        query.append("		,SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+user.getLangId()+"')		AS pmTypeDesc	");
        query.append("	 	,x.cycle || SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE','SYS','"+user.getCompNo()+"', '"+user.getLangId()+"')	AS cycle	");
        query.append("		,(SELECT aa.equip_id												");
        query.append("		  FROM TAPMEQUIP aa													");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("			AND aa.pm_id = x.pm_id											");
        query.append("          AND rownum = 1 ) 							AS equipId			");
        query.append("		,(SELECT b.item_no													");
        query.append("		  FROM TAPMEQUIP a, TAEQUIPMENT b									");
        query.append("		  WHERE a.comp_no = b.comp_no										");
        query.append("		    AND a.equip_id = b.equip_id										");
        query.append("			AND a.pm_id = x.pm_id											");
        query.append("          AND a.comp_no = x.comp_no										");
        query.append("          AND rownum = 1 ) 							AS equipNo			");
        query.append("		,(SELECT b.description												");
        query.append("		  FROM TAPMEQUIP a, TAEQUIPMENT b									");
        query.append("		  WHERE a.comp_no = b.comp_no										");
        query.append("		    AND a.equip_id = b.equip_id										");
        query.append("			AND a.pm_id = x.pm_id											");
        query.append("          AND a.comp_no = x.comp_no										");
        query.append("          AND rownum = 1 ) 							AS equipDesc		");
        query.append("		,x.wrkcallist_id								AS WRKCALLISTNO	    ");
        query.append("		,(SELECT aa.description												");
        query.append("		  FROM TAWRKCALLIST aa												");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("		   AND aa.wrkcallist_id = x.wrkcallist_id)		AS WRKCALLISTDESC	");
        query.append("		,x.work_number									AS workNumber	    ");
        query.append("		,(SELECT aa.dept_no													");
        query.append("		  FROM TADEPT aa													");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("		   AND aa.dept_id = x.dept_id) 					AS DEPTNO	    	");
        query.append("		,(SELECT aa.description												");
        query.append("		  FROM TADEPT aa													");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("		   AND aa.dept_id = x.dept_id) 					AS DEPTDESC	    	");
        query.append("		,(SELECT aa.wkctr_no 												");
        query.append("		  FROM TAWKCTR aa 													");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("		   AND aa.wkctr_id = x.wkctr_id)				AS WKCTRNO			");
        query.append("		,(SELECT aa.description												");
        query.append("		  FROM TAWKCTR aa													");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("		   AND aa.wkctr_id = x.wkctr_id) 				AS WKCTRDESC		");
        query.append("		,(SELECT aa.emp_no													");
        query.append("		  FROM TAEMP aa														");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("		   AND aa.emp_id = x.emp_id) 					AS EMPNO			");
        query.append("		,(SELECT aa.emp_name												");
        query.append("		  FROM TAEMP aa														");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("		   AND aa.emp_id = x.emp_id) 					AS EMPDESC			");
        query.append("	 	,x.is_active									AS isActive			");
        query.append("	 	,x.remark										AS remark			");
		query.append("FROM TAPMLST x															");
		query.append("WHERE 1=1																	");
		query.append(this.getWhere(lovWorkPmListUInsListDTO, user, conditionMap));
		query.append("ORDER BY x.pm_no												");
        
		return getJdbcTemplate().queryForList(query.toString());
    }
    
	private String getWhere(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QueryBuffer query = new QueryBuffer();
		
		query.getAndQuery("x.comp_no", conditionMap);
		query.getAndQuery("x.IS_LAST_VERSION", "Y");
		
		// WO_TYPE : PMU ������
		query.getAndQuery("x.wo_type", conditionMap);

//		// ���࿩��
//		query.getAndQuery("x.is_active", conditionMap);
		
		//�����۾���ȣ
		query.getAndQuery("pm_no", lovWorkPmListUInsListDTO.getPmNo());
		// �۾���

		//�۾�����
		query.getSysCdQuery("x.pm_type", lovWorkPmListUInsListDTO.getPmTypeId(), lovWorkPmListUInsListDTO.getPmTypeDesc(), "x.wo_type||'_TYPE'", user.getCompNo(),user.getLangId());
        
		//����
		if(!"".equals(lovWorkPmListUInsListDTO.getEquipId())||!"".equals(lovWorkPmListUInsListDTO.getEquipDesc())){
			query.append("AND x.equip_id IN (SELECT b.equip_id  	");
			query.append("					  FROM TAEQUIPMENT b	");
			query.append("					 WHERE 1=1				");
	        query.getAndQuery("b.comp_no",conditionMap);
			query.getCodeLikeQuery("b.equip_id", "b.description||b.item_no", lovWorkPmListUInsListDTO.getEquipId(), lovWorkPmListUInsListDTO.getEquipDesc());
			query.append("					)						");
		}

        //�μ�
        query.getDeptLevelQuery("x.dept_id", lovWorkPmListUInsListDTO.getDeptId(), lovWorkPmListUInsListDTO.getDeptDesc(), user.getCompNo());
        
        // �۾��׷�
        query.getWkCtrLevelQuery("x.wkctr_id", lovWorkPmListUInsListDTO.getWkCtrId(), lovWorkPmListUInsListDTO.getWkCtrDesc(), user.getCompNo());
        
        //�����
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
        		lovWorkPmListUInsListDTO.getEmpId(), lovWorkPmListUInsListDTO.getEmpDesc());
        
        //�����ڵ�
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		lovWorkPmListUInsListDTO.getPlantId(), lovWorkPmListUInsListDTO.getPlantDesc());

        // ���࿩��
		query.getAndQuery("x.is_active", lovWorkPmListUInsListDTO.getIsActive());
        
		return query.toString();
	}
	
	@Override
	public String findTotalCount(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User user,
			Map<String, String> conditionMap) throws Exception {

		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("		count(1)					");
        query.append("FROM TAPMLST x					");
		query.append("WHERE 1=1							");
		query.append(this.getWhere(lovWorkPmListUInsListDTO, user, conditionMap));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}