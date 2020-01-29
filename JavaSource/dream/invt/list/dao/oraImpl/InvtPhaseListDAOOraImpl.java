package dream.invt.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.invt.list.dao.InvtPhaseListDAO;
import dream.invt.list.dto.InvtCommonDTO;


/**
 * ¸ñ·Ï dao
 * @author  kim21017
 * @version $Id: InvtPhaseListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="invtPhaseListDAOTarget"
 * @spring.txbn id="invtPhaseListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPhaseListDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtPhaseListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: InvtPhaseListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @param invtPhaseListDTO
     * @return List
     */
    public List findList(InvtCommonDTO invtCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT 											");
        query.append("       '' seqNo									");
        query.append("       ,'' isDelCheck								");
        query.append("       ,a.ord_no ordNo								");
        query.append("       ,SFACODE_TO_DESC(a.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"') invtProcLtypeDesc	");
        query.append("       ,SFACODE_TO_DESC(a.invt_proc_stype,'INVT_PROC_STYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"') invtProcStypeDesc	");
//        query.append("       ,'' contents	");
        query.append("       ,a.ref_depart refDepart	");
        query.append("       ,a.ref_doc refDoc	");
        query.append("       ,a.invtphase_status      invtphaseStatus    ");
        query.append("       ,SFACODE_TO_DESC(a.invtphase_status,'INVTPHASE_STATUS','SYS','','"+user.getLangId()+"') invtphaseStatusDesc	");
        query.append("       ,a.start_date startDate	");
        query.append("       ,a.end_date endDate	");
        query.append("       ,a.actual_amt actualAmt	");
        query.append("       ,a.remark	");
        query.append("       ,a.invtphase_id invtphaseId	");
        query.append("       ,a.invtlist_id invtlistId	");
        query.append("       ,a.invtprcph_id invtprcphId	");
        query.append("       ,a.invt_doc_no invtDocNo  ");
        query.append("       ,(SELECT COUNT(ab.docdata_id) FROM TAOBJDOC aa INNER JOIN TADOCDATA ab ON aa.doc_id=ab.doc_id WHERE aa.comp_no=a.comp_no AND aa.object_type='INVTPHASE' AND aa.object_id=a.invtphase_id) docCnt     ");
        query.append("       ,invt_proc_ltype invtProcLtype                                                                                         ");
        query.append("FROM   TAINVTPHASE A INNER JOIN TAINVTLIST b ON  A.invtlist_id = b.invtlist_id        ");
        query.append("WHERE  1 = 1 	");
        query.append(this.getWhere(invtCommonDTO, user));
        query.getOrderByQuery("a.ord_no", invtCommonDTO.getOrderBy(), invtCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(invtCommonDTO.getIsLoadMaxCount(), invtCommonDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: InvtPhaseListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteList(String deleteRow, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	String question_id=deleteRow;
    	
    	query.append("DELETE FROM TAINVTPHASE					");
    	query.append("WHERE invtphase_id 	= '"+question_id+"'	");
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(InvtCommonDTO invtCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	if (!"".equals(invtCommonDTO.getInvtphaseId()))
        {
            query.getAndQuery("a.invtphase_id", invtCommonDTO.getInvtphaseId());
            return query.toString();
        }
    	
    	query.getAndNumKeyQuery("a.invtlist_id", invtCommonDTO.getInvtlistId());
    	query.getAndQuery("a.comp_no", user.getCompNo());
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(InvtCommonDTO invtCommonDTO, User user) throws Exception {

		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                    ");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAINVTPHASE a    	");
        query.append("WHERE  1 = 1  			");
        query.append(this.getWhere(invtCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}

	public int insertPhase(InvtCommonDTO invtCommonDTO, User user) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();

		int rtnValue  = 0;
		
		query.append("INSERT INTO TAINVTPHASE (										");
		query.append("	 comp_no		  , invtphase_id	, invtlist_id			");
		query.append(" , invtprcph_id	  , ord_no			, invt_proc_ltype		");
		query.append(" , invt_proc_stype  , ref_depart		, ref_doc				");
		query.append(" , invtphase_status , invt_doc_no 					)		");
		query.append("  SELECT 														");
		query.append("   ? 				  , ?				, ?						");
		query.append(" , invtprcph_id	  , y.ord_no 		, y.invt_proc_ltype		");
		query.append(" , y.invt_proc_stype, y.ref_depart	, y.ref_doc				");
		query.append(" , 'W'			  , y.doc_prefix							");
		query.append("  FROM   TAINVTPRCPH y	                                    ");
		query.append("  WHERE  1=1					                                ");
		query.append("    AND  y.is_use       = ?									");
		query.append("    AND  y.invtprctp_id = ?	                                ");
		query.append("    AND  y.invtprcph_id = ?	                                ");
		query.append("    AND  y.comp_no = ?		                                ");
		
    	Object[] objects = new Object[] {
    			  user.getCompNo()
    			, invtCommonDTO.getInvtphaseId()
    			, invtCommonDTO.getInvtlistId()
    			, "Y"
    			, invtCommonDTO.getInvtprctpId()
    			, invtCommonDTO.getInvtprcphId()
    			, user.getCompNo()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
        
        return rtnValue;
	}

	public String validPhase(InvtCommonDTO invtCommonDTO, User user) throws Exception 
	{
    	QueryBuffer query = new QueryBuffer();

		query.append("SELECT count(*) 			");
    	query.append("FROM TAINVTPHASE			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("comp_no", user.getCompNo());
    	query.getAndQuery("invtlist_id", invtCommonDTO.getInvtlistId());
    	query.getAndQuery("invtprcph_id", invtCommonDTO.getInvtprcphId());

    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));

	}

}