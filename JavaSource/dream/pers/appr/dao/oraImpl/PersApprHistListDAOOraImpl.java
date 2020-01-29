package dream.pers.appr.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.pers.appr.dao.PersApprHistListDAO;
import dream.pers.appr.dto.PersApprHistCommonDTO;

/**
 * 결재이력 - List DAO implements
 * @author sy.yang
 * @version $Id:$
 * @since 1.0
 * 
 * @spring.bean id="persApprHistListDAOTarget"
 * @spring.txbn id="persApprHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class PersApprHistListDAOOraImpl extends BaseJdbcDaoSupportOra implements PersApprHistListDAO
{
	public List findApprHistList(PersApprHistCommonDTO persApprHistCommonDTO, User user) throws Exception
    { 
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT		");
        query.append("    ''                                                                                AS seqNo			");
        query.append("    , ''                                                                              AS isDelCheck		");
        query.append("    , y.OBJECT_ID                                                                   	AS objectId			");
        query.append("    , y.REQ_DATE                                                                      AS reqDate			");
        query.append("    , y.TITLE                                                                         AS title			");
        query.append("    , y.appr_type   																    AS apprType			");
        query.append("    , (SELECT emp_name FROM taemp WHERE emp_id = y.REQ_BY)                			AS reqBy			");
        query.append("    , SFACODE_TO_DESC(y.appr_type,'APPR_TYPE','SYS','','"+user.getLangId()+"' )       AS apprTypeDesc		");
        query.append("    , SFACODE_TO_DESC(y.appr_status,'APPR_STATUS','SYS','','"+user.getLangId()+"')	AS apprStatusDesc	");
        query.append("    , SFACODE_TO_DESC(x.APPRUSR_STATUS,'APPRUSR_STATUS','SYS','','"+user.getLangId()+"')    AS apprusrStatusDesc           ");
        query.append("FROM TAAPPRUSR x INNER JOIN TAAPPRLIST y																	");
        query.append("ON x.APPRLIST_ID = y.APPRLIST_ID																			");
        query.append(" AND x.COMP_NO = y.COMP_NO																				");
        query.append("WHERE  1=1																								");
        query.append(this.getWhere(persApprHistCommonDTO, user));
    	query.getOrderByQuery("y.apprlist_id","y.req_date DESC", persApprHistCommonDTO.getOrderBy(), persApprHistCommonDTO.getDirection());        
        
        return getJdbcTemplate().queryForList(query.toString(persApprHistCommonDTO.getIsLoadMaxCount(), persApprHistCommonDTO.getFirstRow()));
        } 

	private String getWhere(PersApprHistCommonDTO persApprHistCommonDTO, User user)
    {        
        QueryBuffer query = new QueryBuffer();
        
        // 결재처리진행상태 : 결재완료
        query.getAndQuery("x.apprusr_action", "C");        
        // 결재 상태 : [P:결재진행 C:결재완료 D:결재반려]  
        query.append(" AND y.appr_status IN ('P', 'C', 'D','R','P')		");		// P:결재진행 C:결재완료 D:결재반려
        query.append(" AND x.APPRUSR_STATUS IN ('C', 'D', 'R')		");		// C:결재완료 D:결재반려
        // 회사
        query.getStringEqualQuery("x.comp_no", user.getCompNo());        
        // 결재자
//        query.getAndQuery("x.appr_by", user.getEmpId());
        query.getCodeLikeQuery("x.appr_by","(SELECT emp_name FROM taemp WHERE emp_id = x.appr_by)", persApprHistCommonDTO.getApprBy(), persApprHistCommonDTO.getApprByName());
        
        query.getCodeLikeQuery("y.REQ_BY","(SELECT emp_name FROM taemp WHERE emp_id = y.REQ_BY)", persApprHistCommonDTO.getReqBy(), persApprHistCommonDTO.getReqByName());
        
        
        // 요청일자
        query.getAndDateQuery("y.req_date", persApprHistCommonDTO.getFilterStartDate(), persApprHistCommonDTO.getFilterEndDate());
        
       /* if(!"".equals(persApprHistCommonDTO.getPgGuideId())){
        	query.getAndQuery("x.pgguide_id", persApprHistCommonDTO.getPgGuideId());
        	return query.toString();
        }*/
   
        // 결재구분
        query.getSysCdQuery("y.appr_type", persApprHistCommonDTO.getFilterApprTypeId(), persApprHistCommonDTO.getFilterApprTypeDesc(), "APPR_TYPE", user.getCompNo(),user.getLangId());
        
        // 진행상태
        query.getSysCdQuery("y.appr_status", persApprHistCommonDTO.getFilterApprusrStatusId(), persApprHistCommonDTO.getFilterApprusrStatusDesc(), "APPRUSR_STATUS", user.getCompNo(),user.getLangId());

        
    	return query.toString();
    }

    public int deletePgmGuideList(String id, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();

        query.append("DELETE FROM TXPGGUIDE			");
        query.append("WHERE  comp_no 		= ?		");
        query.append("  AND  pgguide_id  	= ?		");
        
        Object[] objects = new Object[] {   
                user.getCompNo()
                ,id
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(PersApprHistCommonDTO persApprHistCommonDTO, User user) throws Exception
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                     	");
        query.append("       COUNT(1)                               ");
        query.append("FROM TAAPPRUSR x INNER JOIN TAAPPRLIST y		");
        query.append("ON x.APPRLIST_ID = y.APPRLIST_ID				");
        query.append(" AND x.COMP_NO = y.COMP_NO					");
        query.append("WHERE  1=1									");
    	query.append(this.getWhere(persApprHistCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}