package dream.req.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.work.dao.ReqWorkListDAO;
import dream.req.work.dto.ReqWorkCommonDTO;

/**
 * 작업요청 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqWorkListDAOTarget"
 * @spring.txbn id="reqWorkListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWorkListDAOOraImpl extends BaseJdbcDaoSupportOra implements ReqWorkListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkCommonDTO
     * @return List
     */
    public List findList(ReqWorkCommonDTO reqWorkCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT																										");
        query.append("		 ''																					AS seqNo			");
        query.append("	   , ''																					AS isDelCheck		");
        query.append("	   , x.woreq_id																			AS woReqId			");
        query.append("	   , x.woreq_no																			AS woReqNo			");
        query.append("	   ,																										");
        query.getDate("x.req_date", "reqDate");
        query.append("     ,                                   																		");
        query.getDate("x.req_com_date", "reqComDate");
        query.append("	   , x.description																		AS reqDesc			");
        query.append("	   , (SELECT a.item_no     																					");
        query.append("			FROM TAEQUIPMENT a																					");
        query.append("		   WHERE a.comp_no = x.comp_no																			");
        query.append("			 AND a.equip_id = x.equip_id) 													AS equipNo			");
        query.append("	   , (SELECT a.description																					");
        query.append("			FROM TAEQUIPMENT a																					");
        query.append("		   WHERE a.comp_no = x.comp_no																			");
        query.append("		     AND a.equip_id = x.equip_id) 													AS equipDesc		");
        query.append("	   , (SELECT a.full_desc																					");
        query.append("			FROM TAEQLOC a																						");
        query.append("			WHERE a.comp_no = x.comp_no																			");
        query.append("			AND	a.eqloc_id = x.eqloc_id) 													AS eqLocDesc		");
        query.append("     , x.woreq_status      																AS woReqStatus 		");
        query.append("	   , SFACODE_TO_DESC(x.woreq_status,'WOREQ_STATUS','SYS','','"+user.getLangId()+"')		AS woReqStatusDesc	");
        query.append("	   , (SELECT a.emp_name																						");
        query.append("		    FROM TAEMP a																						");
        query.append("		   WHERE a.comp_no = x.comp_no																			");
        query.append("		 	 AND a.emp_id = x.req_emp_id) 													AS reqBy			");
        query.append("     , (SELECT a.description          																		");
        query.append("          FROM TADEPT a                 																		");
        query.append("         WHERE a.comp_no = x.comp_no   																		");
        query.append("           AND a.dept_id = x.req_dept_id) 												AS reqDept 			");
        query.append("     , (SELECT a.description          																		");
        query.append("          FROM TAPLANT a                   																	");
        query.append("         WHERE a.comp_no = x.comp_no      																	");
        query.append("           AND a.plant = x.plant) plantDesc            														");
        query.append("	   , (SELECT param1																							");
        query.append("	   		FROM TACDSYSD																						");
        query.append("	   	   WHERE list_type = 'WOREQ_TYPE'																		");
        query.append("	   		 AND cdsysd_no = x.woreq_type)													AS param			");
        query.append("     , x.woreq_type      																	AS woReqType		");
        query.append("     , (SELECT description																					");
        query.append("     		FROM tacdsysd																						");
        query.append("     	   WHERE list_type='WOREQ_TYPE'																			");
        query.append("     		 AND cdsysd_no = x.woreq_type)													AS woReqTypeDesc	");
        query.append("     , CASE WHEN x.req_time IS NULL                                          									");
        query.append("            THEN ''                                                          									");
        query.append("            ELSE SUBSTR(x.req_time, 9, 2) ||':'|| SUBSTR(x.req_time, 11, 2)  									");
        query.append("             END                          												AS reqTime          ");
        query.append("     , (SELECT a.description             																		");
        query.append("     	    FROM TAWKCTR a                 																		");
        query.append("         WHERE a.comp_no  = x.comp_no    																		");
        query.append("        	 AND a.wkctr_ID = x.rec_wkctr_id)												AS recWkCtrDesc		");
        query.append("     , x.rec_emp_id																		AS RECEMPID			");
        query.append("     , (SELECT a.emp_name																						");
        query.append("     		FROM TAEMP a																						");
        query.append("     	   WHERE a.comp_no = x.comp_no																			");
        query.append("     		 AND a.emp_id = x.rec_emp_Id)													AS RECEMPDESC		");
        query.append("	   , (SELECT a.full_desc 																					");
        query.append("			FROM TAEQASMB a 																					");
        query.append("		   WHERE a.comp_no = x.comp_no																			");
        query.append("			 AND a.eqasmb_id = x.eqasmb_id) 												AS REQEQASMBDESC	");
        query.append("		, 						");
        query.getDate("x.eqdn_start_date", "EQDNDATE");
        query.append("		, 						");
        query.getTime("x.eqdn_start_time", "EQDNTIME");
        query.append("	FROM TAWOREQ x																								");
        query.append(" WHERE 1 = 1																									");
        query.append(this.getWhere(reqWorkCommonDTO,user));
        query.getOrderByQuery("x.woreq_id Desc", reqWorkCommonDTO.getOrderBy(), reqWorkCommonDTO.getDirection());
            
        return getJdbcTemplate().queryForList(query.toString(reqWorkCommonDTO.getIsLoadMaxCount(), reqWorkCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     *
     * @param reqWorkCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(ReqWorkCommonDTO reqWorkCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = user.getCompNo();
        String lang = user.getLangId();

        query.getAndQuery("x.comp_no", user.getCompNo());
        if (!"".equals(reqWorkCommonDTO.getWoReqId()))
        {
            query.getAndNumKeyQuery("x.woreq_id", reqWorkCommonDTO.getWoReqId());
            return query.toString();
        }
        query.getAndDateQuery("x.req_date", reqWorkCommonDTO.getFilterReqStartDate(), reqWorkCommonDTO.getFilterReqEndDate());
        query.getSysCdQuery("x.woreq_status", reqWorkCommonDTO.getFilterWoReqStatusId(), reqWorkCommonDTO.getFilterWoReqStatusDesc(), "WOREQ_STATUS", compNo,lang);
        query.getSysCdQuery("x.req_priority", reqWorkCommonDTO.getFilterReqPriorityId(), reqWorkCommonDTO.getFilterReqPriorityDesc(), "REQ_PRIORITY", compNo,lang);
        query.getLikeQuery("x.description", reqWorkCommonDTO.getFilterWoReqDesc());
        query.getLikeQuery("x.request", reqWorkCommonDTO.getFilterRequest());
        if(!"".equals(reqWorkCommonDTO.getFilterEquipId())||!"".equals(reqWorkCommonDTO.getFilterEquipDesc())){
        	query.append("AND x.equip_id IN (SELECT a.equip_id  					");
        	query.append("					FROM TAEQUIPMENT a						");
        	query.append("					WHERE a.comp_no = x.comp_no				");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getCodeLikeQuery("a.equip_id", "a.description||a.item_no",
        			reqWorkCommonDTO.getFilterEquipId(), reqWorkCommonDTO.getFilterEquipDesc());
            query.append("					)										");
        }
        query.getEqLocLevelQuery("x.eqloc_id", reqWorkCommonDTO.getFilterEqLocId(), reqWorkCommonDTO.getFilterEqLocDesc(), compNo);
        query.getCodeLikeQuery("x.req_emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.req_emp_id AND a.comp_no='"+compNo+"')",
        		reqWorkCommonDTO.getFilterReqEmpId(), reqWorkCommonDTO.getFilterReqEmpDesc());
        query.getDeptLevelQuery("x.req_dept_id", reqWorkCommonDTO.getFilterReqDeptId(), reqWorkCommonDTO.getFilterReqDeptDesc(), compNo);
        
        //처리부서
        query.getCodeLikeQuery("x.rec_emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.rec_emp_id AND a.comp_no='"+compNo+"')",
        		reqWorkCommonDTO.getFilterRecEmpId(), reqWorkCommonDTO.getFilterRecEmpDesc());
        //처리자
        query.getDeptLevelQuery("x.rec_dept_id", reqWorkCommonDTO.getFilterRecDeptId(), reqWorkCommonDTO.getFilterRecDeptDesc(), compNo);
        
        //접수공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
        		reqWorkCommonDTO.getFilterPlantId(), reqWorkCommonDTO.getFilterPlantDesc());
        //요청번호
        query.getLikeQuery("x.woreq_no", reqWorkCommonDTO.getFilterWoReqNo());
        
        //공장코드
        query.getCodeLikeQuery("x.req_plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.req_plant )", 
        		reqWorkCommonDTO.getFilterReqPlant(), reqWorkCommonDTO.getFilterReqPlantDesc());
        
        //처리작업그룹
        query.getWkCtrLevelQuery("x.rec_wkctr_id", reqWorkCommonDTO.getFilterRecWkCtrId(), reqWorkCommonDTO.getFilterRecWkCtrDesc(), compNo);
        
        
        //제외하고....
        if(!"".equals(reqWorkCommonDTO.getNotWoReqStatusId())){
            for(String notWoReqStatus:reqWorkCommonDTO.getNotWoReqStatusId().split("\\+")){
                query.append("AND x.woreq_status != '"+notWoReqStatus+"' ");
            }
        }

        return query.toString();
    }


    public int deleteList(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("DELETE FROM TAWOREQ                 ");
        query.append("WHERE woreq_id       = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'     ");

        rtnValue = this.getJdbcTemplate().update(query.toString());

        return rtnValue;

    }

    public int deleteResList(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;

        query.append("DELETE FROM TAWOREQRES                ");
        query.append("WHERE woreq_id         = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'     ");

        rtnValue = this.getJdbcTemplate().update(query.toString());

        return rtnValue;

    }

	@Override
	public String findTotalCount(ReqWorkCommonDTO reqWorkCommonDTO, User user) throws Exception {
		QueryBuffer query = new QueryBuffer();

        query.append("SELECT									");
        query.append("       COUNT(1)                           ");
        query.append("FROM TAWOREQ x							");
        query.append("WHERE 1 = 1								");
        query.append(this.getWhere(reqWorkCommonDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}

}