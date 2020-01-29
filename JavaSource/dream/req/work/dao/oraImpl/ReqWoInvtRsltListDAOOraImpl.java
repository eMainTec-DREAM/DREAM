package dream.req.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.work.dao.ReqWoInvtRsltListDAO;
import dream.req.work.dto.MaWoReqCommonDTO;
import dream.req.work.dto.ReqWoInvtRsltListDTO;

/**
 * 작업요청-투자결과 - 목록 dao
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="reqWoInvtRsltListDAOTarget"
 * @spring.txbn id="reqWoInvtRsltListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class ReqWoInvtRsltListDAOOraImpl extends BaseJdbcDaoSupportOra implements ReqWoInvtRsltListDAO
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
    public List findList(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
            query.append("SELECT										");
            query.append("    ''					seqNo				");
            query.append("  , ''					isDelCheck			");
            query.append("  , x.woreqres_id 		woreqresId			");
            query.append("  , x.woreq_id    		woreqId				");
            query.append("  , y.invtlist_id    		invtlistId			");
            query.append("  , y.invtlist_no 		invtlistNo			");
            query.append("  , y.description 		invtDesc			");
            query.append("  , SFACODETODESC(y.invt_categ, 'INVT_CATEG','SYS',x.comp_no) 	invtCategDesc		");
            query.append("  , SFACODETODESC(y.invt_type, 'INVT_TYPE', 'SYS', x.comp_no) 	invtTypeDesc		");
            query.append("  , (SELECT a.description FROM TADEPT a WHERE a.comp_no = x.comp_no AND a.dept_id = y.dept_id) 	deptDesc		");
            query.append("  , y.plan_sdate 			startDate			");
            query.append("  , y.plan_edate 			endDate				");
            query.append("  , SFACODETODESC(y.invtlist_status, 'INVTLIST_STATUS', 'SYS', x.comp_no) 	invtStatusDesc		");
            query.append("  , y.end_date 			realEndDate			");
            query.append("  , y.remark 				remark				");
            query.append("FROM TAWOREQRES x INNER JOIN TAINVTLIST y		");
            query.append("ON x.comp_no = y.comp_no 						");
            query.append("AND x.invtlist_id = y.invtlist_id				");
            query.append("WHERE 1=1										");
            query.append(this.getWhere(maWoReqCommonDTO,reqWoInvtRsltListDTO,user));
            query.getOrderByQuery("y.invtlist_no", reqWoInvtRsltListDTO.getOrderBy(), reqWoInvtRsltListDTO.getDirection());
            
            return getJdbcTemplate().queryForList(query.toString(reqWoInvtRsltListDTO.getIsLoadMaxCount(), reqWoInvtRsltListDTO.getFirstRow()));
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
    private String getWhere(MaWoReqCommonDTO maWoReqCommonDTO,ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user){
    	QueryBuffer query = new QueryBuffer();
    	
    	query.getStringEqualQuery("x.comp_no", user.getCompNo());
    	query.getAndQuery("x.woreqres_method", "INVT");
    	
        query.getAndNumKeyQuery("x.woreq_id", maWoReqCommonDTO.getWoReqId());
        
        if (!"".equals(maWoReqCommonDTO.getInvtlistId()))
        {
            query.getAndQuery("x.invtlist_id", maWoReqCommonDTO.getInvtlistId());
            return query.toString();
        }
        
        return query.toString();
    }


	@Override
	public String findTotalCount(MaWoReqCommonDTO maWoReqCommonDTO, ReqWoInvtRsltListDTO reqWoInvtRsltListDTO, User user)
			throws Exception {

        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT										");
        query.append("       COUNT(1)     							");
        query.append("FROM TAWOREQRES x INNER JOIN TAINVTLIST y		");
        query.append("ON x.comp_no = y.comp_no 						");
        query.append("AND x.invtlist_id = y.invtlist_id				");
        query.append("WHERE 1=1										");
        query.append(this.getWhere(maWoReqCommonDTO,reqWoInvtRsltListDTO,user));
	
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}
    
}