package dream.invt.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.invt.list.dao.InvtPhaseDetailDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPhaseDetailDTO;


/**
 * 상세 dao
 * @author  kim21017
 * @version $Id: InvtPhaseDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="invtPhaseDetailDAOTarget"
 * @spring.txbn id="invtPhaseDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPhaseDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtPhaseDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPhaseDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseListDTO
     * @param invtCommonDTO
     * @return
     */
    public InvtPhaseDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                             		");
        query.append("       ord_no ordNo										");
        query.append("       ,invt_proc_ltype invtProcLtype						");
        query.append("       ,invt_proc_stype invtProcStype                     ");
        query.append("       ,SFACODE_TO_DESC(invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"') invtProcLtypeDesc    	");
        query.append("       ,SFACODE_TO_DESC(invt_proc_stype,'INVT_PROC_STYPE','USR','"+user.getCompNo()+"','"+user.getLangId()+"') invtProcStypeDesc    	");
        query.append("       ,ref_depart refDepart    							");
        query.append("       ,ref_doc refDoc    								");
        query.append("       ,invtphase_status invtphaseStatus					");
        query.append("       ,SFACODE_TO_DESC(invtphase_status,'INVTPHASE_STATUS','SYS','','ko') invtphaseStatusDesc    	");
        query.append("       ,start_date startDate    							");
        query.append("       ,end_date endDate    								");
        query.append("       ,actual_amt actualAmt    							");
        query.append("       ,remark    										");
        query.append("       ,invtphase_id invtphaseId    						");
        query.append("       ,invtlist_id invtlistId    						");
        query.append("       ,invtprcph_id invtprcphId    						");
        query.append("       ,invt_doc_no invtDocNo                          ");
        query.append("FROM   TAINVTPHASE     									");
        query.append("WHERE  1 = 1     											");
        query.append("  AND  invtphase_id 	= ?									");
        query.append("  AND  comp_no 	= ?										");
        
        Object[] objects = new Object[] {
        		invtCommonDTO.getInvtphaseId()
        		,user.getCompNo()
        };
    
        InvtPhaseDetailDTO invtPhaseDetailDTO1 = 
        		(InvtPhaseDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new InvtPhaseDetailDTO()));
        
        return invtPhaseDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPhaseDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseDetailDTO
     * @param invtCommonDTO
     * @return
     */
    public int updateDetail(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAINVTPHASE SET             	");
    	query.append("      invtphase_status    = ?        	");
    	query.append("      ,start_date         = ?        	");
    	query.append("      ,end_date         	= ?        	");
    	query.append("      ,actual_amt         = ?        	");
    	query.append("      ,ref_doc            = ?        	");
    	query.append("      ,invt_doc_no        = ?        	");
    	query.append("      ,ref_depart         = ?        	");
    	query.append("      ,remark       		= ?        	");
    	query.append("WHERE invtphase_id        = ?     	");
    	query.append("  AND comp_no             = ?     	");
    	
    	Object[] objects = new Object[] {
    			invtPhaseDetailDTO.getInvtphaseStatus()
    			,invtPhaseDetailDTO.getStartDate()
    			,invtPhaseDetailDTO.getEndDate()
    			,invtPhaseDetailDTO.getActualAmt()
    			,invtPhaseDetailDTO.getRefDoc()
    			,invtPhaseDetailDTO.getInvtDocNo()
    			,invtPhaseDetailDTO.getRefDepart()
    			,invtPhaseDetailDTO.getRemark()
    			,invtPhaseDetailDTO.getInvtphaseId()
    			,user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPhaseDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPhaseDetailDTO
     * @param invtCommonDTO
     * @return
     */
    public int insertDetail(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMCRITY (                                		");
    	query.append("    comp_no,        rcmcrity_id,      rcmfmea_id,        		");
    	query.append("    rcmlist_id,     col_name,        	row_name,            	");
    	query.append("    crityvalue,     critycolor,      	is_critical,			");
    	query.append("    remark,         crity_lvl,                 				");
    	query.append("    crityvalue_id                        						");
    	query.append("    )    VALUES                (                            	");
    	query.append("    ?,              ?,                ?,                		");
    	query.append("    ?,              ?,                ?,     					");
    	query.append("    ?,              ?,                ?,            			");
    	query.append("    ?,              ?,                           				");
    	query.append("    ?                   										");
    	query.append("    )                                          				");

    	
    	Object[] objects = new Object[] {
//    			invtCommonDTO.getCompNo(),
//    			invtPhaseDetailDTO.getRcmcrityId(),
//    			invtCommonDTO.getRcmfmeaId(),
//    			invtCommonDTO.getRcmlistId(),
//    			invtPhaseDetailDTO.getColName(),
//    			invtPhaseDetailDTO.getRowName(),
//    			invtPhaseDetailDTO.getCrityvalue(),
//    			invtPhaseDetailDTO.getCritycolor(),
//    			invtPhaseDetailDTO.getIsCritical(),
//    			invtPhaseDetailDTO.getRemark(),
//    			invtPhaseDetailDTO.getCrityLvl(),
////    			invtPhaseDetailDTO.getRowOrdNo(),
////    			invtPhaseDetailDTO.getColOrdNo(),
//    			invtPhaseDetailDTO.getCrityvalueId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }

	@Override
	public int findVal(InvtPhaseDetailDTO invtPhaseDetailDTO, InvtCommonDTO invtCommonDTO) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT count(1)									");
        query.append("FROM   TARCMCRITY x								");
        query.append("WHERE  x.crityvalue_id IN (						");
        query.append("                           SELECT crityvalue_id	");
        query.append(" 							 FROM  TACRITYVALUE		");
        query.append("							 WHERE critycol_id = ?)	");
        query.append("AND   x.rcmfmea_id = ?							");
        query.append("AND   x.rcmlist_id = ? 							");

        
        Object[] objects = new Object[] {
//        		invtPhaseDetailDTO.getCritycolId()
//        		,invtCommonDTO.getRcmfmeaId()
//    			,invtCommonDTO.getRcmlistId()
        };
   
        return (int)getJdbcTemplate().queryForObject(query.toString(),objects, Integer.class);

	}

	public String copyDetail(String oldInvtId, String newInvtId, String oldKeyId, String newKeyId, User user) throws Exception 
	{
		QueryBuffer query = new QueryBuffer();
		
		query.append("INSERT INTO TAINVTPHASE										");
		query.append("(   comp_no       	, invtphase_id      , invtlist_id		");
		query.append("  , invtprcph_id   	, ord_no            , invt_porc_ltype	");
		query.append("  , invt_proc_stype 	, ref_depart      	, ref_doc			");
		query.append("  , invtphase_status 	, start_Date     	, end_Date			");
		query.append("  , actual_amt        , REMARK         	, invt_doc_no		");
		query.append("  , wkor_id                                                )	");
		query.append("SELECT 														");
		query.append("  comp_no       												");
		
		if(!"".equals(newKeyId))
    	{	// Unit 복사인 경우
    		query.append("   , '"+newKeyId+"'   									");
    	}
    	else
    	{	// 전체 복사인 경우
    		query.append("   , sqainvtphase_id.NEXTVAL   							");
    	}
		
		query.append("  , ?															");
		query.append("  , invtprcph_id   	, ord_no          , invt_porc_ltype		");
		query.append("  , invt_proc_stype 	, ref_depart      , ref_dic				");
		query.append("  , ?          		, start_Date      , end_Date			");
		query.append("  , actual_amt        , REMARK          , invt_doc_no			");
		query.append("  , wkor_id													");
		query.append("FROM TAINVTPHASE												");
		query.append("WHERE comp_no 	= ?											");
		query.append("AND invtlist_id 	= ?											");

		// Unit 복사인 경우 Key 값을 넣어 하나만 복사한다.
    	query.getAndQuery("invtphase_id", oldKeyId);
    	
    	Object[] objects = new Object[] {
    			  newInvtId
    			, "W"
    			, user.getCompNo()
    			, oldInvtId
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
    	
		return "0";
	}
}