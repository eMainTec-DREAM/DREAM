package dream.invt.prc.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.invt.list.dto.InvtDetailDTO;
import dream.invt.prc.dao.InvtPrcTpDetailDAO;
import dream.invt.prc.dto.InvtPrcTpCommonDTO;
import dream.invt.prc.dto.InvtPrcTpDetailDTO;

/**
 * 구매절차 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="invtPrcTpDetailDAOTarget"
 * @spring.txbn id="invtPrcTpDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPrcTpDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtPrcTpDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpCommonDTO
     * @return
     */
    public InvtPrcTpDetailDTO findDetail(InvtPrcTpCommonDTO invtPrcTpCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT		                            ");
        query.append("        invtprctp_no       INVTNO		    ");
        query.append("        ,description       INVTDESC		");
        query.append("        ,is_use            ISUSE		    ");
        query.append("        ,reg_date          REGDATE		");
        query.append("        ,remark            REMARK		    ");
        query.append("        ,invtprctp_id      INVTPRCTPID 	");
        query.append("FROM TAINVTPRCTP		                   ");
        query.append("WHERE 1=1		                           ");    							
        query.append("  AND  comp_no = ? 	                   ");
        query.append("  AND  invtprctp_id = ?                  ");
        
        Object[] objects = new Object[] {
        		invtPrcTpCommonDTO.getCompNo()
        		,invtPrcTpCommonDTO.getInvtPrcTpId()
        };

        InvtPrcTpDetailDTO invtPrcTpDetailDTO = 
        		(InvtPrcTpDetailDTO)getJdbcTemplate().query(query.toString(), objects, new MwareExtractor(new InvtPrcTpDetailDTO()));
        
        return invtPrcTpDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * 
     * 
     * @param invtPrcTpDetailDTO
     * @return
     */
    public int insertDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAINVTPRCTP							");
    	query.append("	(comp_no,		invtprctp_id,	    invtprctp_no,	");
    	query.append("	 description,	reg_date,	is_use,		        ");
    	query.append("	 remark                                  		");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?												");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			invtPrcTpDetailDTO.getCompNo()
    			,invtPrcTpDetailDTO.getInvtPrcTpId()
    			,invtPrcTpDetailDTO.getInvtNo()
    			,invtPrcTpDetailDTO.getInvtDesc()
    			,invtPrcTpDetailDTO.getRegDate()
    			,invtPrcTpDetailDTO.getIsUse()
    			,invtPrcTpDetailDTO.getRemark()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailDTO
     * @return
     */
    public int updateDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAINVTPRCTP SET			");    	   
    	query.append("	description			= ?,	");
    	query.append("	reg_date			= ?,		");
    	query.append("	is_use			    = ?,		");
    	query.append("	remark			    = ?		");
    	query.append("WHERE invtprctp_id    = ?		");
    	query.append("  AND comp_no 		= ?		");
    	
    	Object[] objects = new Object[] {
    			invtPrcTpDetailDTO.getInvtDesc()
    			,invtPrcTpDetailDTO.getRegDate()
    			,invtPrcTpDetailDTO.getIsUse()
    			,invtPrcTpDetailDTO.getRemark()
    			,invtPrcTpDetailDTO.getInvtPrcTpId()
    			,invtPrcTpDetailDTO.getCompNo()
    			
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail confirm
     * @author kim21017
     * @version $Id: InvtPrcTpDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param invtPrcTpDetailDTO
     * @return
     */
    public int confirmDetail(InvtPrcTpDetailDTO invtPrcTpDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("");
    	
    	Object[] objects = new Object[] {
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}