package dream.work.list.energy.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.energy.dao.WorkListEnergyMstrListDAO;
import dream.work.list.energy.dto.WorkListEnergyMstrListCommonDTO;

/**
 * 에너지관리 - 에너지값 목록 dao
 * @author  sy.yang
 * @version $Id: WorkListEnergyMstrListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
 * @since   1.0
 * @spring.bean id="workListEnergyMstrListDAOTarget"
 * @spring.txbn id="workListEnergyMstrListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WorkListEnergyMstrListDAOOraImpl extends BaseJdbcDaoSupportOra implements WorkListEnergyMstrListDAO
{
    /**
     * grid find
     * @author  sy.yang
     * @version $Id: WorkListEnergyMstrListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @return List
     */
    public List findList(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																		    ");
        query.append("		''															AS SEQNO		");
        query.append("		,''															AS ISDELCHECK	");
        query.append("		,x.pminslist_id												AS PMINSLISTID	");
        query.append("	  	,x.plant													AS PLANTNO		");
        query.append("	  	,(SELECT aa.description 													");
        query.append("        FROM TAPLANT aa															");
        query.append("        WHERE aa.comp_no = x.comp_no												");
        query.append("        AND aa.plant = x.plant)									AS PLANTDESC	");
        query.append("		,(SELECT item_no														    ");
        query.append("		  FROM TAEQUIPMENT														    ");
        query.append("		  WHERE comp_no = x.comp_no												    ");
        query.append("		   AND equip_id = x.equip_id) 								AS EQUIPNO		");
        query.append("		,(SELECT description													    ");
        query.append("		  FROM TAEQUIPMENT													    	");
        query.append("		  WHERE comp_no = x.comp_no											    	");
        query.append("		   AND equip_id = x.equip_id) 								AS EQUIPDESC	");
        query.append("      ,x.description                                              AS DESCRIPTION  ");
        query.append("		,x.wkor_date												AS WKORDATE	    ");
        query.append("      ,x.measure_time									  			AS MEASURETIME	");
        query.append("		,(SELECT aa.dept_no													    	");
        query.append("		  FROM TADEPT aa														    ");
        query.append("		  WHERE aa.comp_no = x.comp_no											    ");
        query.append("		   AND aa.dept_id = x.dept_id) 								AS DEPTNO	    ");
        query.append("		,(SELECT aa.description													    ");
        query.append("		  FROM TADEPT aa														    ");
        query.append("		  WHERE aa.comp_no = x.comp_no										        ");
        query.append("		   AND aa.dept_id = x.dept_id) 								AS DEPTDESC	    ");
        query.append("		,(SELECT aa.wkctr_no													 	");
        query.append("		  FROM TAWKCTR aa														    ");
        query.append("		  WHERE aa.comp_no = x.comp_no												");
        query.append("		   AND aa.wkctr_id = x.wkctr_id) 							AS WKCTRNO		");
        query.append("		,(SELECT aa.description														");
        query.append("		  FROM TAWKCTR aa														    ");
        query.append("		  WHERE aa.comp_no = x.comp_no												");
        query.append("		   AND aa.wkctr_id = x.wkctr_id) 							AS WKCTRDESC	");
        query.append("		,(SELECT aa.emp_no														 	");
        query.append("		  FROM TAEMP aa															    ");
        query.append("		  WHERE aa.comp_no = x.comp_no												");
        query.append("		   AND aa.emp_id = x.emp_id) 								AS EMPNO		");
        query.append("		,(SELECT aa.emp_name														");
        query.append("		  FROM TAEMP aa															    ");
        query.append("		  WHERE aa.comp_no = x.comp_no												");
        query.append("		   AND aa.emp_id = x.emp_id) 								AS EMPDESC		");
        query.append("		,x.pm_type													AS PMTYPE		");
        query.append("		,SFACODE_TO_DESC(x.pm_type,'PMU_TYPE','SYS','','"+user.getLangId()+"')				AS PMTYPEDESC			");
        query.append("		,x.pmsched_status											AS PMISCHEDSTATUS	");
        query.append("		,SFACODE_TO_DESC(x.pmsched_status,'PMSCHED_STATUS','SYS','','"+user.getLangId()+"')	AS PMISCHEDSTATUSDESC	");
        query.append("		,x.pm_id													AS PMID			");
        query.append("		,(select a.pm_no from tapmlst a where a.comp_no = x.comp_no and a.pm_id = x.pm_id)	AS PMNO			");
        query.append("		,x.remark													AS REMARK		");
        query.append("FROM  TAPMINSLIST x															    ");
        query.append("WHERE 1=1																		    ");
        query.append(this.getWhere(workListEnergyMstrListCommonDTO,user));
        query.getOrderByQuery("x.wkor_date,x.description,x.pminslist_id", workListEnergyMstrListCommonDTO.getOrderBy(), workListEnergyMstrListCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(workListEnergyMstrListCommonDTO.getIsLoadMaxCount(), workListEnergyMstrListCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author sy.yang
     * @version $Id: WorkListEnergyMstrListDAO.java,v 1.0 20155/12/02 08:25:47 sy.yang Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteSchedList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("DELETE FROM TAPMINSSCHED				");
    	query.append("WHERE pminslist_id	= '"+id+"'		");
    	query.append("  AND comp_no	 		= '"+compNo+"'	");
    	query.append("	AND pmsched_status  <> 'C'			");
    	
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    public int deleteList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
		
    	query.append("DELETE FROM TAPMINSLIST				");
    	query.append("WHERE pminslist_id	= '"+id+"'		");
    	query.append("  AND comp_no	 		= '"+compNo+"'	");
    	query.append("	AND pmsched_status  <> 'C'			");

    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    public int deletePoint(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;

    	query.append("DELETE FROM TAPMINSPOINT				");
    	query.append("WHERE pminslist_id	= '"+id+"'		");
    	query.append("  AND comp_no			= '"+compNo+"'	");
    	query.append("	AND pmsched_status  <> 'C'			");
    	
    	rtnValue = this.getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    /**
     * Filter 조건
     * @author  sy.yang
     * @version $Id: WorkListEnergyMstrListDAO.java,v 1.0 2015/12/02 09:14:12 sy.yang Exp $
     * @since   1.0
     * 
     * @param workListEnergyMstrListCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO, User user)
    {        
    	String lang = user.getLangId();
    	
        QueryBuffer query = new QueryBuffer();
        String startDate = workListEnergyMstrListCommonDTO.getFilterStartDate();
        String endDate = workListEnergyMstrListCommonDTO.getFilterEndDate();
        String compNo  = user.getCompNo();
        
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        query.getStringEqualQuery("x.IS_DELETED", "N");
        
        if (!"".equals(workListEnergyMstrListCommonDTO.getPminslistId()))
        {
            query.getAndQuery("x.pminslist_id", workListEnergyMstrListCommonDTO.getPminslistId());
            return query.toString();
        }
        
        //점검일자
        query.getAndDateQuery("x.wkor_date", startDate, endDate);

        //작업종류
    	query.getSysCdQuery("x.wo_type", workListEnergyMstrListCommonDTO.getFilterWoTypeId(), workListEnergyMstrListCommonDTO.getFilterWoTypeDesc(), "WO_TYPE", compNo,lang);
    	//작업형태
    	query.getSysCdQuery("x.pm_type", workListEnergyMstrListCommonDTO.getFilterPmTypeId(), workListEnergyMstrListCommonDTO.getFilterPmTypeDesc(), "x.wo_type||'_TYPE'", compNo,lang);
        
    	//예방작업번호
    	if(!"".equals(workListEnergyMstrListCommonDTO.getFilterPmNo())){
    		query.append("AND EXISTS (SELECT 1  			");
        	query.append("				FROM TAPMLST		");
        	query.append("			   WHERE 1=1			");
        	query.append("			   	 AND pm_id=x.pm_id	");
        	query.getStringEqualQuery("comp_no", compNo);
        	query.getAndQuery("pm_no", workListEnergyMstrListCommonDTO.getFilterPmNo());
        	query.append("			 )						");
    	}
    	
        //부서
        query.getDeptLevelQuery("x.dept_id", workListEnergyMstrListCommonDTO.getFilterDeptId(), workListEnergyMstrListCommonDTO.getFilterDeptDesc(), compNo);
      
        //설비
        if(!"".equals(workListEnergyMstrListCommonDTO.getFilterEquipId())||!"".equals(workListEnergyMstrListCommonDTO.getFilterEquipDesc())){
        	query.append("AND x.equip_id IN (SELECT b.equip_id  					");
        	query.append("					 FROM TAEQUIPMENT b						");
        	query.append("					 WHERE 1=1								");
        	query.getStringEqualQuery("b.comp_no", compNo);
        	query.getCodeLikeQuery("b.equip_id", "b.description||b.item_no", workListEnergyMstrListCommonDTO.getFilterEquipId(), workListEnergyMstrListCommonDTO.getFilterEquipDesc());
            query.append("					)										");
        }
        
        // 작업그룹
        query.getWkCtrLevelQuery("x.wkctr_id", workListEnergyMstrListCommonDTO.getFilterWkCtrId(), workListEnergyMstrListCommonDTO.getFilterWkCtrDesc(), compNo);
        
        //담당자
        query.getCodeLikeQuery("x.emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.emp_id AND a.comp_no=x.comp_no)", 
                workListEnergyMstrListCommonDTO.getFilterEmpId(), workListEnergyMstrListCommonDTO.getFilterEmpDesc());
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+user.getCompNo()+"' AND a.plant = x.plant )", 
        		workListEnergyMstrListCommonDTO.getFilterPlantId(), workListEnergyMstrListCommonDTO.getFilterPlantDesc());
        
        //점검작업상태
        query.getSysCdQuery("x.pmsched_status", workListEnergyMstrListCommonDTO.getFilterPmSchedStatusId(), workListEnergyMstrListCommonDTO.getFilterPmSchedStatusDesc(), "PMSCHED_STATUS", "x.comp_no", user.getLangId());

        //작업명
        query.getLikeQuery("x.description", workListEnergyMstrListCommonDTO.getFilterPmiDesc());
        
        //작업번호
        query.getLikeQuery("x.pminslist_id", workListEnergyMstrListCommonDTO.getFilterWoNo());
        
        //최신버전의 설비의 작업만 보여줌.
        query.append("AND NOT EXISTS (SELECT a.equip_id							");
    	query.append("					 FROM  TAEQUIPMENT a            		");
    	query.append("					 WHERE 1=1                    			");
    	query.append("					 AND   a.equip_id = x.equip_id			");
    	query.getStringEqualQuery("a.comp_no", user.getCompNo());
    	query.getAndQuery("a.is_last_version", "N");
        query.append("									)						");
        
        return query.toString();
    }

    public String findTotalCount(WorkListEnergyMstrListCommonDTO workListEnergyMstrListCommonDTO,User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append(" SELECT                                                        ");
        query.append("        COUNT(1)                                               ");
        query.append(" FROM TAPMINSLIST x                                          ");
        query.append(" WHERE 1=1                                                    ");
        query.append(this.getWhere(workListEnergyMstrListCommonDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }

}