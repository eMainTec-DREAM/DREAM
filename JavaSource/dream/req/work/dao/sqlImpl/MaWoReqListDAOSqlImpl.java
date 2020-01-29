package dream.req.work.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.req.work.dao.MaWoReqListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;

/**
 * 작업요청 - 목록 dao
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoReqListDAOTarget"
 * @spring.txbn id="maWoReqListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoReqListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoReqListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @return List
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT																																	");
        query.append("		 ''																												AS seqNo			");
        query.append("	   , ''																												AS isDelCheck		");
        query.append("	   , x.woreq_id																										AS woReqId			");
        query.append("	   , x.woreq_no																										AS woReqNo			");
        query.append("	   ,																																	");
        query.getDate("x.req_date", "reqDate");
        query.append("	   ,																																	");
        query.append("      CASE WHEN x.req_time IS NULL                                              		");
        query.append("           THEN ''                                                              		");
        query.append("           ELSE SUBSTRING(x.req_time, 9, 2) +':'+ SUBSTRING(x.req_time, 11, 2)  		");
        query.append("           END                reqTime		");
        query.append("	   ,									");
        query.getDate("x.req_com_date", "reqComDate");
        query.append("	   , x.description																									AS reqDesc			");
        query.append("	   , (SELECT a.item_no     																												");
        query.append("			FROM TAEQUIPMENT a																												");
        query.append("		   WHERE a.comp_no = x.comp_no																										");
        query.append("			 AND a.equip_id = x.equip_id) 																				AS equipNo			");
        query.append("	   , (SELECT a.description																												");
        query.append("			FROM TAEQUIPMENT a																												");
        query.append("		   WHERE a.comp_no = x.comp_no																										");
        query.append("		     AND a.equip_id = x.equip_id) 																				AS equipDesc		");
        query.append("	   , (SELECT a.full_desc																												");
        query.append("			FROM TAEQLOC a																													");
        query.append("		   WHERE a.comp_no = x.comp_no																										");
        query.append("			 AND a.eqloc_id = x.eqloc_id) 																				AS eqLocDesc		");
        query.append("	   , x.woreq_status								                                                                    AS woReqStatus	    ");
        query.append("	   , dbo.SFACODE_TO_DESC(x.woreq_status,'WOREQ_STATUS','SYS','','"+user.getLangId()+"')								AS woReqStatusDesc	");
        query.append("	   , x.req_emp_id								                                                                    AS reqEmpId	        ");
        query.append("	   , (SELECT a.emp_name																													");
        query.append("			FROM TAEMP a																													");
        query.append("		   WHERE a.comp_no = x.comp_no																										");
        query.append("		     AND a.emp_id = x.req_emp_id) 																				AS reqBy			");
        query.append("	   , (SELECT a.description																												");
        query.append("			FROM TADEPT a																													");
        query.append("		   WHERE a.comp_no = x.comp_no																										");
        query.append("			 AND a.dept_id = x.req_dept_id) 																			AS reqDept			");
        query.append("     , (SELECT a.emp_name                 																								");
        query.append("          FROM TAEMP a                    																								");
        query.append("         WHERE a.comp_no = x.comp_no     																									");
        query.append("           AND a.emp_id = x.rec_emp_id) 																				AS recBy     		");
        query.append("     , (SELECT a.description              																								");
        query.append("          FROM TADEPT a                   																								");
        query.append("         WHERE a.comp_no = x.comp_no     																									");
        query.append("           AND a.dept_id = x.rec_dept_id) 																			AS recDept   		");
        query.append("     , x.req_priority 																								AS REQPRIORITYID	");
        query.append("     , dbo.SFACODE_TO_DESC(x.req_priority,'REQ_PRIORITY','SYS',x.comp_no,'"+user.getLangId()+"') 						AS REQPRIORITYDESC	");
        query.append("     ,(SELECT a.description              																									");
        query.append("         FROM TAPLANT a                  																									");
        query.append("        WHERE a.comp_no = x.comp_no     																									");
        query.append("          AND a.plant = x.plant) 																						AS plantDesc        ");
        query.append("	   , (SELECT param2																														");
        query.append("	   		FROM tacdsysd																													");
        query.append("	   	   WHERE list_type='WOREQ_TYPE'																										");
        query.append("	   		 AND cdsysd_no = x.woreq_type) 																				AS param			");
        query.append("     , x.woreq_type      																								AS woReqType		");
        query.append("     , (SELECT description																												");
        query.append("     		FROM TACDSYSD																													");
        query.append("     	   WHERE list_type='WOREQ_TYPE'																										");
        query.append("     		 AND cdsysd_no = x.woreq_type)																				AS woReqTypeDesc	");
        query.append("     , x.mo_cd                 																						AS moCd       		");
        query.append("     , (SELECT a.description FROM TAFAILURE a WHERE a.comp_no = x.comp_no AND a.failure_id = x.mo_cd)    				AS moCdDesc 		");
        query.append("     , x.mo_desc               																						AS moDesc     		");
        query.append("	   , x.woreq_channel																								AS REQCHANNELID		");
        query.append("     , dbo.SFACODE_TO_DESC(x.woreq_channel,'WOREQ_CHANNEL','SYS','"+ user.getCompNo() +"','"+ user.getLangId() +"')	AS REQCHANNELDESC	");
        query.append("     , (SELECT a.description             																									");
        query.append("          FROM TAWKCTR a                 																									");
        query.append("         WHERE a.comp_no  = x.comp_no    																									");
        query.append("           AND a.wkctr_ID = x.rec_wkctr_id)																			AS recWkCtrDesc		");
        query.append("	   , (SELECT a.full_desc 																												");
        query.append("		    FROM TAEQASMB a																													");
        query.append("		   WHERE a.comp_no   = x.comp_no																									");
        query.append("		     AND a.eqasmb_id = x.eqasmb_id) 																			AS REQEQASMBDESC	");
        query.append("		, 						");
        query.getDate("x.eqdn_start_date", "EQDNDATE");
        query.append("		, 						");
        query.getTime("x.eqdn_start_time", "EQDNTIME");
        query.append("	FROM TAWOREQ x																															");
        query.append(" WHERE 1 = 1																																");
        query.append(this.getWhere(maWoReqCommonDTO, user));
        query.getOrderByQuery("x.woreq_id","x.woreq_id desc", maWoReqCommonDTO.getOrderBy(), maWoReqCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoReqCommonDTO.getIsLoadMaxCount(), maWoReqCommonDTO.getFirstRow()));
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaWoReqCommonDTO maWoReqCommonDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = user.getCompNo();
        String lang = user.getLangId();
      
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
        if (!"".equals(maWoReqCommonDTO.getWoReqId()))
        {
            query.getAndNumKeyQuery("x.woreq_id", maWoReqCommonDTO.getWoReqId());
            return query.toString();
        }
        query.getAndDateQuery("x.req_date", maWoReqCommonDTO.getFilterReqStartDate(), maWoReqCommonDTO.getFilterReqEndDate());
        query.getSysCdQuery("x.woreq_status", maWoReqCommonDTO.getFilterWoReqStatusId(), maWoReqCommonDTO.getFilterWoReqStatusDesc(), "WOREQ_STATUS", compNo,lang);
        query.getLikeQuery("x.description", maWoReqCommonDTO.getFilterWoReqDesc());
        query.getLikeQuery("x.request", maWoReqCommonDTO.getFilterRequest());
        if(!"".equals(maWoReqCommonDTO.getFilterEquipId())||!"".equals(maWoReqCommonDTO.getFilterEquipDesc())){
        	query.append("AND x.equip_id IN (SELECT a.equip_id  					");
        	query.append("					FROM TAEQUIPMENT a						");
        	query.append("					WHERE a.comp_no = x.comp_no				");
        	query.getStringEqualQuery("a.comp_no", compNo);
        	query.getCodeLikeQuery("a.equip_id", "a.description+a.item_no", 
        			maWoReqCommonDTO.getFilterEquipId(), maWoReqCommonDTO.getFilterEquipDesc());
            query.append("					)										");
        }
        query.getEqLocLevelQuery("x.eqloc_id", maWoReqCommonDTO.getFilterEqLocId(), maWoReqCommonDTO.getFilterEqLocDesc(), compNo);
        query.getCodeLikeQuery("x.req_emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.req_emp_id AND a.comp_no='"+compNo+"')", 
        		maWoReqCommonDTO.getFilterReqEmpId(), maWoReqCommonDTO.getFilterReqEmpDesc());
        query.getDeptLevelQuery("x.req_dept_id", maWoReqCommonDTO.getFilterReqDeptId(), maWoReqCommonDTO.getFilterReqDeptDesc(), compNo);
        query.getCodeLikeQuery("x.rec_emp_id", "(SELECT a.emp_name FROM  TAEMP a WHERE a.emp_id = x.rec_emp_id AND a.comp_no='"+compNo+"')", 
        		maWoReqCommonDTO.getFilterRecEmpId(), maWoReqCommonDTO.getFilterRecEmpDesc());
        query.getDeptLevelQuery("x.rec_dept_id", maWoReqCommonDTO.getFilterRecDeptId(), maWoReqCommonDTO.getFilterRecDeptDesc(), compNo);

        query.append("AND x.woreq_status IN (SELECT cdsysd_no                   ");
        query.append("                       FROM TACDSYSD                      ");
        query.append("                       WHERE param1='C'                   ");
        query.append("                         AND list_type='WOREQ_STATUS')    ");
        
        // 우선순위
        query.getSysCdQuery("x.req_priority", maWoReqCommonDTO.getFilterReqPriorityId(), maWoReqCommonDTO.getFilterReqPriorityDesc(), "REQ_PRIORITY", compNo, lang);
        
        //공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM  TAPLANT a WHERE a.comp_no = '"+compNo+"' AND a.plant = x.plant )", 
        		maWoReqCommonDTO.getFilterPlantId(), maWoReqCommonDTO.getFilterPlantDesc());
        
        // 작업요청유형
        query.getSysCdQuery("x.woreq_type", maWoReqCommonDTO.getFilterWoReqType(), maWoReqCommonDTO.getFilterWoReqTypeDesc(), "WOREQ_TYPE", compNo, lang);
        
        // 고장현상
        query.getCodeLikeQuery("x.mo_cd", "(SELECT a.description FROM TAFAILURE a WHERE a.comp_no = '"+ compNo +"' AND a.failure_id = x.mo_cd)", 
                maWoReqCommonDTO.getFilterMoCd(), maWoReqCommonDTO.getFilterMoCdDesc());
        
        // 현상설명
        query.getLikeQuery("x.mo_desc", maWoReqCommonDTO.getFilterMoDesc());
        
        // 요청#
        query.getLikeQuery("x.woreq_no", maWoReqCommonDTO.getFilterWoReqNo());
        
        // invtlist_id
        if(!"".equals(maWoReqCommonDTO.getInvtlistId()))
        {
        	query.append("AND EXISTS (SELECT 1         								");
        	query.append("				FROM TAWOREQRES a INNER JOIN TAINVTLIST b   ");
        	query.append("                ON a.comp_no = b.comp_no                  ");
        	query.append("               AND a.invtlist_id = b.invtlist_id          ");
        	query.append("             WHERE 1=1                                    ");
        	query.append("               AND a.comp_no= x.comp_no					");
        	query.append("               AND a.woreq_id  = x.woreq_id  				");
        	query.getAndQuery("a.woreqres_method", "INVT");
        	query.getAndQuery("b.invtlist_id", maWoReqCommonDTO.getInvtlistId());
        	query.append("            )												");
        }
        
        // W/O
        if(!"".equals(maWoReqCommonDTO.getWkorId()))
        {
            query.append("AND EXISTS (SELECT 1                                          ");
            query.append("              FROM TAWOREQRES a                               ");
            query.append("             WHERE a.comp_no  = x.comp_no                     ");
            query.append("               AND a.woreq_id = x.woreq_id                    ");
            query.getAndNumKeyQuery("a.wkor_id", maWoReqCommonDTO.getWkorId());
            query.append("           )                                                  ");
        }
        
        // 작업요청발생구분
        query.getSysCdQuery("x.woreq_channel", maWoReqCommonDTO.getFilterReqChannelId(), maWoReqCommonDTO.getFilterReqChannelDesc(), "WOREQ_CHANNEL", compNo, lang);
        
        //처리작업그룹
        query.getWkCtrLevelQuery("x.rec_wkctr_id", maWoReqCommonDTO.getFilterRecWkCtrId(), maWoReqCommonDTO.getFilterRecWkCtrDesc(), compNo);
        
        return query.toString();
    }


    public int deleteList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWOREQ                 ");
        query.append("WHERE woreq_id       = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'     ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }
    
    public int deleteResList(String id, String compNo)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWOREQRES                ");
        query.append("WHERE woreq_id         = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'     ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }

	@Override
	public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, User user) throws Exception {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  ");
        query.append("SELECT									");
        query.append("       COUNT(1)                           ");
        query.append("FROM TAWOREQ x							");
        query.append("WHERE 1 = 1								");
        query.append(this.getWhere(maWoReqCommonDTO,user));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);

	}
    
}