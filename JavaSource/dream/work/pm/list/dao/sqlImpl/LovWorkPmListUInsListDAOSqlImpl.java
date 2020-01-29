package dream.work.pm.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.LovWorkPmListUInsListDAO;
import dream.work.pm.list.dto.LovWorkPmListUInsListDTO;

/**
 * 에너지 측정기준주기 Lov DAO SqlImpl
 * @author  sy.yang
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovWorkPmListUInsListDAOTarget"
 * @spring.txbn id="lovWorkPmListUInsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWorkPmListUInsListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovWorkPmListUInsListDAO
{
    /**
     * 검색
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
        QuerySqlBuffer query = new QuerySqlBuffer();

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
        query.append("		,dbo.SFACODE_TO_DESC(x.pm_type,'PMI_TYPE','SYS','','"+user.getLangId()+"')		AS pmTypeDesc	");
        query.append("	 	,CONVERT(nvarchar, x.cycle) + dbo.SFACODE_TO_DESC(x.period_type, 'PERIOD_TYPE','SYS','"+user.getCompNo()+"', '"+user.getLangId()+"')	AS cycle	");
        query.append("		,(SELECT TOP 1 aa.equip_id											");
        query.append("		  FROM TAPMEQUIP aa													");
        query.append("		  WHERE aa.comp_no = x.comp_no										");
        query.append("			AND aa.pm_id = x.pm_id ) 					AS equipId			");
        query.append("		,(SELECT TOP 1 b.item_no											");
        query.append("		  FROM TAPMEQUIP a, TAEQUIPMENT b									");
        query.append("		  WHERE a.comp_no = b.comp_no										");
        query.append("		    AND a.equip_id = b.equip_id										");
        query.append("			AND a.pm_id = x.pm_id											");
        query.append("          AND a.comp_no = x.comp_no ) 				AS equipNo			");
        query.append("		,(SELECT TOP 1 b.description										");
        query.append("		  FROM TAPMEQUIP a, TAEQUIPMENT b									");
        query.append("		  WHERE a.comp_no = b.comp_no										");
        query.append("		    AND a.equip_id = b.equip_id										");
        query.append("			AND a.pm_id = x.pm_id											");
        query.append("          AND a.comp_no = x.comp_no ) 				AS equipDesc		");
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
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.getAndQuery("x.comp_no", conditionMap);
		query.getAndQuery("x.IS_LAST_VERSION", "Y");
		
		// WO_TYPE : PMU 에너지
		query.getAndQuery("x.wo_type", conditionMap);
		
		//예방작업번호
		query.getAndQuery("pm_no", lovWorkPmListUInsListDTO.getPmNo());
		// 작업명

		//작업형태
		query.getSysCdQuery("x.pm_type", lovWorkPmListUInsListDTO.getPmTypeId(), lovWorkPmListUInsListDTO.getPmTypeDesc(), "x.wo_type||'_TYPE'", user.getCompNo(),user.getLangId());
        
		//설비
		if(!"".equals(lovWorkPmListUInsListDTO.getEquipId())||!"".equals(lovWorkPmListUInsListDTO.getEquipDesc())){
			query.append("AND x.equip_id IN (SELECT b.equip_id  	");
			query.append("					  FROM TAEQUIPMENT b	");
			query.append("					 WHERE 1=1				");
	        query.getAndQuery("b.comp_no",conditionMap);
			query.getCodeLikeQuery("b.equip_id", "b.description||b.item_no", lovWorkPmListUInsListDTO.getEquipId(), lovWorkPmListUInsListDTO.getEquipDesc());
			query.append("					)						");
		}

        //부서
        query.getDeptLevelQuery("x.dept_id", lovWorkPmListUInsListDTO.getDeptId(), lovWorkPmListUInsListDTO.getDeptDesc(), user.getCompNo());
        
        // 작업그룹
        query.getWkCtrLevelQuery("x.wkctr_id", lovWorkPmListUInsListDTO.getWkCtrId(), lovWorkPmListUInsListDTO.getWkCtrDesc(), user.getCompNo());
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
        		lovWorkPmListUInsListDTO.getEmpId(), lovWorkPmListUInsListDTO.getEmpDesc());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		lovWorkPmListUInsListDTO.getPlantId(), lovWorkPmListUInsListDTO.getPlantDesc());

        // 시행여부
		query.getAndQuery("x.is_active", lovWorkPmListUInsListDTO.getIsActive());
        
		return query.toString();
	}
	
	@Override
	public String findTotalCount(LovWorkPmListUInsListDTO lovWorkPmListUInsListDTO, User user, Map<String, String> conditionMap) throws Exception
	{
		QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT                            ");
        query.append("		count(1)					");
        query.append("FROM TAPMLST x					");
		query.append("WHERE 1=1							");
		query.append(this.getWhere(lovWorkPmListUInsListDTO, user, conditionMap));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}