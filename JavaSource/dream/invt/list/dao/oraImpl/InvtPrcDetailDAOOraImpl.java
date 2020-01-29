package dream.invt.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.invt.list.dao.InvtPrcDetailDAO;
import dream.invt.list.dto.InvtCommonDTO;
import dream.invt.list.dto.InvtPrcDetailDTO;
import dream.pers.appreq.dto.AppReqDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: InvtPrcDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="invtPrcDetailDAOTarget"
 * @spring.txbn id="invtPrcDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPrcDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtPrcDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPrcDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtCommonDTO
     * @return
     */
    public InvtPrcDetailDTO findDetail(InvtCommonDTO invtCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("       '' seqNo											");
        query.append("       ,'' isDelCheck										");
        query.append("       ,invtlist_no invtlistNo							");
        query.append("       ,x.description										");
        query.append("       ,invt_categ invtCateg								");
        query.append("       ,SFACODE_TO_DESC(invt_categ,'INVT_CATEG','SYS','','"+user.getLangId()+"') invtCategDesc	");
        query.append("       ,(SELECT full_desc                                 ");
        query.append("         FROM   TAEQCTG                                   ");
        query.append("         WHERE  comp_no = x.comp_no                       ");
        query.append("           AND  eqctg_id = x.eqctg_id) eqCtgDesc			");
        query.append("       ,(SELECT full_desc                                 ");
        query.append("          FROM  TAEQLOC                                   ");
        query.append("          WHERE comp_no = x.comp_no                       ");
        query.append("            AND eqloc_id = x.eqloc_id) eqLocDesc			");
        query.append("       ,x.eqloc_id eqlocId								");
        query.append("       ,x.eqctg_id eqctgId								");
        query.append("       ,x.equip_id equipId								");
        query.append("       ,y.description equipDesc							");
        query.append("       ,x.dept_id deptId									");
        query.append("       ,(SELECT a.description								");
        query.append("         FROM   TADEPT a									");
        query.append("         WHERE  a.dept_id = x.dept_id) deptDesc			");
        query.append("       ,emp_id empId										");
        query.append("       ,(SELECT a.emp_name								");
        query.append("         FROM   TAEMP a									");
        query.append("         WHERE  a.emp_id = x.emp_id) empDesc				");
        query.append("       ,plan_amt planAmt									");
        query.append("       ,plan_sdate planSdate								");
        query.append("       ,plan_edate planEdate								");
        query.append("       ,SFACODE_TO_DESC(invtlist_status,'INVTLIST_STATUS','SYS','','"+user.getLangId()+"') invtStatusDesc	");
        query.append("       ,SFACODE_TO_DESC(z.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+user.getCompNo()+"','ko') invtProcLtypeDesc    		");
        query.append("       ,SFACODE_TO_DESC(z.invt_proc_stype,'INVT_PROC_STYPE','USR','"+user.getCompNo()+"','ko') invtProcStypeDesc    	");
        query.append("       ,invt_proc_ltype invtProcLtype						");
        query.append("       ,invt_proc_stype invtProcStype						");
        query.append("       ,z.ref_depart refDepart    						");
        query.append("       ,z.ref_doc refDoc    								");
        query.append("       ,SFACODE_TO_DESC(z.invtphase_status,'INVTPHASE_STATUS','SYS','','ko') invtphaseStatusDesc    	");
        query.append("       ,z.start_date startDate    						");
        query.append("       ,z.invtphase_status invtphaseStatus    			");
        query.append("       ,z.end_date endDate    							");
        query.append("       ,z.actual_amt invtAmt    							");
        query.append("       ,z.remark    										");
        query.append("       ,z.invtphase_id invtphaseId    					");
        query.append("       ,x.invtlist_id invtlistId    						");
        query.append("       ,z.invtprcph_id invtprcphId                        ");
        query.append("       ,z.invt_doc_no invtDocNo                           ");
        query.append("       ,x.invtprctp_id invtprctpId                        ");
        query.append("FROM   TAINVTLIST x LEFT OUTER JOIN TAEQUIPMENT y              ");
        query.append("                  ON x.equip_id = y.equip_id and x.comp_no = y.comp_no				");
        query.append("           INNER JOIN TAINVTPHASE z						");
        query.append("           ON x.invtlist_id = z.invtlist_id and x.comp_no = z.comp_no               ");
        query.append("WHERE  1=1                                                ");
        query.append("  AND  z.invtphase_id = ?                                   ");
        query.append("  AND  z.comp_no = ?                                   ");

        Object[] objects = new Object[] {
                invtCommonDTO.getInvtphaseId()
                ,user.getCompNo()
        };
        
        InvtPrcDetailDTO invtPrcDetailDTO = 
        		(InvtPrcDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new InvtPrcDetailDTO()));
        
        return invtPrcDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPrcDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcDetailDTO
     * @return
     */
    public int insertDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAINVTLIST                                			");
    	query.append("    (comp_no,        	invtlist_id,        invtlist_no,        	");
    	query.append("     description,    	invtlist_status,    invtprctp_id,        	");
    	query.append("     eqloc_id,    	eqctg_id,        	invt_categ,            	");
    	query.append("     equip_id,    	dept_id,        	emp_id,            		");
    	query.append("     plan_amt,    	plan_sdate,        	plan_edate,            	");
    	query.append("     end_date,    	remark         								");
    	query.append("    )    VALUES                                        			");
    	query.append("    (?,                ?,                ?,                		");
    	query.append("     ?,                ?,                ?,                		");
    	query.append("     ?,                ?,                ?,                		");
    	query.append("     ?,                ?,                ?,                		");
    	query.append("     ?,                ?,                ?,                		");
    	query.append("     ?,                ?                 							");
    	query.append("    )                                                				");
    	
    	Object[] objects = new Object[] {
    			invtPrcDetailDTO.getCompNo(),
    			invtPrcDetailDTO.getInvtlistId(),
    			invtPrcDetailDTO.getInvtlistNo(),
    			invtPrcDetailDTO.getDescription(),
    			invtPrcDetailDTO.getInvtlistStatus(),
    			invtPrcDetailDTO.getInvtprctpId(),
    			invtPrcDetailDTO.getEqlocId(),
    			invtPrcDetailDTO.getEqctgId(),
    			invtPrcDetailDTO.getInvtCateg(),
    			invtPrcDetailDTO.getEquipId(),
    			invtPrcDetailDTO.getDeptId(),
    			invtPrcDetailDTO.getEmpId(),
    			invtPrcDetailDTO.getPlanAmt(),
    			invtPrcDetailDTO.getPlanSdate(),
    			invtPrcDetailDTO.getPlanEdate(),
    			invtPrcDetailDTO.getEndDate(),
    			invtPrcDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPrcDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcDetailDTO
     * @return
     */
    public int updateDetail(InvtPrcDetailDTO invtPrcDetailDTO, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAINVTPHASE SET                    ");
    	query.append("       invtphase_status   = ?,        	");
    	query.append("       start_date        	= ?,        	");
    	query.append("       end_date           = ?,        	");
    	query.append("       actual_amt         = ?,        	");
    	query.append("       invt_doc_no        = ?,        	");
    	query.append("       remark             = ?				");
    	query.append("WHERE  invtphase_id       = ?            	");
    	query.append("  AND  comp_no            = ?    			");


    	
    	Object[] objects = new Object[] {
    			invtPrcDetailDTO.getInvtphaseStatus(),
    			invtPrcDetailDTO.getStartDate(),
    			invtPrcDetailDTO.getEndDate(),
    			invtPrcDetailDTO.getInvtAmt(),
    			invtPrcDetailDTO.getRemark(),
                invtPrcDetailDTO.getInvtDocNo(),
    			invtPrcDetailDTO.getInvtphaseId(),
    			user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }

	@Override
	public void insertPhase(InvtPrcDetailDTO invtPrcDetailDTO, User user) {
		QueryBuffer query = new QueryBuffer();

		query.append("  INSERT INTO TAINVTPHASE (comp_no, 		invtphase_id, 	invtlist_id,			");
		query.append("                      invtprcph_id, 		ord_no, 		invt_proc_ltype,		");
		query.append("                      invt_proc_stype,	ref_depart, 	ref_doc,				");
		query.append("                      invtphase_status, 	start_date, 	end_date,				");
		query.append("                      actual_amt, 		remark,             invt_doc_no )                              	");
		query.append("  SELECT ?, 					?, 							?,						");
		query.append("         invtprcph_id, 		y.ord_no, 					y.invt_proc_ltype,		");
		query.append("         y.invt_proc_stype, 	y.ref_depart, 				y.ref_doc,				");
		query.append("         ?,                   ?,                    		?,						");
		query.append("         ?,                   ?,                           ?             ");
		query.append("  FROM   TAINVTPRCPH y	");
		query.append("  WHERE  y.is_use = 'Y'	");
		query.append("    AND  invtprcph_id = ?	");


    	
    	Object[] objects = new Object[] {
    			user.getCompNo(),
    			invtPrcDetailDTO.getInvtphaseId(),
    			invtPrcDetailDTO.getInvtlistId(),
    			invtPrcDetailDTO.getInvtphaseStatus(),
    			invtPrcDetailDTO.getStartDate(),
    			invtPrcDetailDTO.getEndDate(),
    			invtPrcDetailDTO.getInvtAmt(),
    			invtPrcDetailDTO.getRemark(),
    			invtPrcDetailDTO.getInvtDocNo(),
    			invtPrcDetailDTO.getInvtprcphId()        
    	};
    	
    	getJdbcTemplate().update(query.toString(), objects);
	}

	public String checkPrc(InvtCommonDTO invtCommonDTO,InvtPrcDetailDTO invtPrcDetailDTO, User user){
		QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 				");
    	query.append("FROM TAINVTPHASE				");
    	query.append("WHERE 1=1						");
    	query.getAndQuery("invtlist_id", invtPrcDetailDTO.getInvtlistId());
    	query.append("AND invtphase_id != "+invtPrcDetailDTO.getInvtphaseId()+" ");
    	query.getAndQuery("invtprcph_id", invtPrcDetailDTO.getInvtprcphId());
    	query.getAndQuery("comp_no", user.getCompNo());
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
		
	}
	@Override
	public void deletePhase(InvtPrcDetailDTO invtPrcDetailDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAINVTPHASE                                              ");
        query.append("WHERE  comp_no  		= ?                                       ");
        query.append("  AND  invtlist_id  	= ?                                       ");      
        
        Object[] objects = new Object[] {   
        		user.getCompNo(),
        		invtPrcDetailDTO.getInvtlistId()
                };
        
        this.getJdbcTemplate().update(query.toString(), objects);
	}

	public int setStatus(AppReqDetailDTO appReqDetailDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("UPDATE TAINVTPHASE SET                    ");
        query.append("       invtphase_status   = ?             ");
        query.append("WHERE  invtphase_id       = ?             ");
        query.append("  AND  comp_no    = ?                          ");      
        
        Object[] objects = new Object[] {
                appReqDetailDTO.getParentStatus(),
                appReqDetailDTO.getObjectId(),
                user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
	
}