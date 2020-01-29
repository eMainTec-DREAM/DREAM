package dream.req.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.work.dao.ReqWoPlanRsltListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoPlanRsltListDTO;

/**
 * 작업요청-작업계획 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqWoPlanRsltListDAOTarget"
 * @spring.txbn id="reqWoPlanRsltListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWoPlanRsltListDAOOraImpl extends BaseJdbcDaoSupportOra implements ReqWoPlanRsltListDAO
{
    /**
     * grid find
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param maWoReqCommonDTO
     * @return List
     */
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT																				");
        query.append("    	 ''																AS seqNo		");
        query.append("     , ''																AS isDelCheck	");
        query.append("     , x.woreqres_id 													AS woreqresId	");
        query.append("     , x.woreq_id 													AS woreqId		");
        query.append("     , y.wkor_id 														AS wkorId		");
        query.append("     , y.wo_no 														AS woNo			");
        query.append("     , y.description 													AS woDesc		");
        query.append("     , y.wkor_date 													AS woDate		");
        query.append("	   , (SELECT a.description 															");
        query.append("          FROM TAEQUIPMENT a 															");
        query.append("         WHERE a.comp_no  = x.comp_no 												");
        query.append("           AND a.equip_id = z.equip_id) 								AS equipDesc	");
        query.append("	   , (SELECT a.description 															");
        query.append("          FROM TADEPT a 																");
        query.append("         WHERE a.comp_no = y.comp_no 													");
        query.append("           AND a.dept_id = y.dept_id)     							AS deptDesc     ");
        query.append("     , y.wo_type     													AS woType		");
        query.append("     , SFACODETODESC(y.wo_type, 'WO_TYPE', 'SYS', x.comp_no)			AS woTypeDesc	");
        query.append("     , y.pm_type     													AS pmType		");
        query.append("     , SFACODETODESC(y.pm_type, 'PM_TYPE', 'SYS', x.comp_no) 			AS pmTypeDesc	");
        query.append("     , SFACODETODESC(y.wo_status, 'WO_STATUS', 'SYS', x.comp_no)		AS woStatus		");
        query.append("     , y.perform 														AS remark		");
        query.append("     , (SELECT full_desc																");
        query.append("     		FROM TAEQASMB a INNER JOIN TAWOEQUIP b    									");
        query.append("                             ON a.comp_no   = b.comp_no								");
        query.append("                            AND a.eqasmb_id = b.eqasmb_id								");
        query.append("         WHERE a.comp_no = x.comp_no													");
        query.append("           AND b.wkor_id = x.wkor_id) 								AS eqasmbDesc	");
        query.append("	FROM TAWOREQRES x INNER JOIN TAWOPLAN y												");
        query.append("					   	 ON x.comp_no = y.comp_no										");
        query.append("					  	AND x.wkor_id = y.wkor_id										");
        query.append("					  INNER JOIN TAWOEQUIP z											");
        query.append("  					 ON z.comp_no = x.comp_no										");
        query.append("	 					AND z.wkor_id = x.wkor_id										");
        query.append(" WHERE 1 = 1																			");
        query.append(this.getWhere(maWoReqCommonDTO,reqWoPlanRsltListDTO,user));
        query.getOrderByQuery("y.wo_no", reqWoPlanRsltListDTO.getOrderBy(), reqWoPlanRsltListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(reqWoPlanRsltListDTO.getIsLoadMaxCount(), reqWoPlanRsltListDTO.getFirstRow()));
    }
    

    public int deleteList(String id, String compNo)
    {
        QueryBuffer query = new QueryBuffer();
        int rtnValue  = 0;
        
        query.append("DELETE FROM TAWOREQRES               ");
        query.append("WHERE woreqres_id     = "+id+"       ");
        query.append("  AND comp_no  = '"+compNo+"'        ");
        
        rtnValue = this.getJdbcTemplate().update(query.toString());
        
        return rtnValue;

    }
    private String getWhere(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user){
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.woreqres_method", "WOPLAN");
    	
        query.getAndNumKeyQuery("x.woreq_id", maWoReqCommonDTO.getWoReqId());
        
        if (!"".equals(maWoReqCommonDTO.getWkorId()))
        {
            query.getAndQuery("x.wkor_id", maWoReqCommonDTO.getWkorId());
            return query.toString();
        }
        
        return query.toString();
    }


	@Override
	public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoPlanRsltListDTO reqWoPlanRsltListDTO, User user)
			throws Exception {

        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT										");
        query.append("       COUNT(1)     							");
        query.append("FROM TAWOREQRES x INNER JOIN TAWOPLAN y		");
        query.append("ON x.comp_no = y.comp_no						");
        query.append("AND x.wkor_id = y.wkor_id						");
        query.append("WHERE 1=1										");
        query.append(this.getWhere(maWoReqCommonDTO,reqWoPlanRsltListDTO,user));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}
    
}