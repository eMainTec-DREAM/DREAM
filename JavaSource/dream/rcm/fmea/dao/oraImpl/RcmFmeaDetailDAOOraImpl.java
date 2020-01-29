package dream.rcm.fmea.dao.oraImpl;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.rcm.fmea.dao.RcmFmeaDetailDAO;
import dream.rcm.fmea.dto.RcmFmeaCommonDTO;
import dream.rcm.fmea.dto.RcmFmeaDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author kim21017
 * @version $Id: RcmFmeaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="rcmFmeaDetailDAOTarget"
 * @spring.txbn id="rcmFmeaDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFmeaDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmFmeaDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFmeaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaCommonDTO
     * @return
     */
    public RcmFmeaDetailDTO findDetail(RcmFmeaCommonDTO rcmFmeaCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                             	");
        query.append("       d.item_no equipNo,             ");
        query.append("       d.description equipDesc,    	");
        query.append("       y.description funcDesc,    	");
        query.append("       z.description failDesc,    	");
        query.append("       e.description asmbDesc,    	");
        query.append("       f.mofail_desc mofailDesc,    	");
        query.append("       f.cafail_desc cafailDesc,    	");
        query.append("       f.effail_desc effailDesc,    	");
        query.append("       x.rcmlist_id rcmlistId,		");
        query.append("       y.rcmfunc_id rcmfuncId,		");
        query.append("       z.rcmffail_id rcmffailId,		");
        query.append("       b.rcmeq_id rcmeqId,			");
        query.append("       b.rcmffeq_id rcmffeqId,		");
        query.append("       c.rcmeqasmb_id rcmeqasmbId,    ");
        query.append("       f.remark remark,	        	");
        query.append("       x.critylist_id critylistId,   	");
        query.append("       f.rcmfmea_id rcmfmeaId        	");
        query.append("       ,(SELECT a.rcmffeq_id FROM TARCMFFEQ a WHERE a.rcmffail_id = z.rcmffail_id AND a.rcmfunc_id = y.rcmfunc_id AND a.rcmeq_id = c.rcmeq_id AND a.rcmeqasmb_id = c.rcmeqasmb_id) rcmffeqId      		");
        query.append("FROM   TARCMLIST x                	");
        query.append("       LEFT OUTER JOIN TARCMFUNC y ON x.rcmlist_id = y.rcmlist_id                        	");
        query.append("       LEFT OUTER JOIN TARCMFFAIL z ON y.rcmfunc_id = z.rcmfunc_id                		");
        query.append("       INNER JOIN TARCMFFEQ b INNER JOIN TARCMEQ k INNER JOIN TAEQUIPMENT d ON k.equip_id = d.equip_id ON b.rcmeq_id = k.rcmeq_id ON z.rcmffail_id = b.rcmffail_id  	");
        query.append("       LEFT OUTER JOIN TARCMEQASMB c  INNER JOIN TAEQASMB e ON e.eqasmb_id = c.eqasmb_id ON  b.rcmeqasmb_id = c.rcmeqasmb_id                     		");
        query.append("       LEFT OUTER JOIN TARCMFMEA f ON z.rcmffail_id = f.rcmffail_id AND b.rcmffeq_id = f.rcmffeq_id and NVL(b.rcmeqasmb_id,0) = NVL(f.rcmeqasmb_id,0)          		");
        query.append("WHERE  1=1         	");
        query.getAndNumKeyQuery("x.rcmlist_id", rcmFmeaCommonDTO.getRcmlistId());
        query.getAndNumKeyQuery("y.rcmfunc_id", rcmFmeaCommonDTO.getRcmfuncId());
        query.getAndNumKeyQuery("z.rcmffail_id", rcmFmeaCommonDTO.getRcmffailId());
        query.getAndNumKeyQuery("b.rcmeq_id", rcmFmeaCommonDTO.getRcmeqId());
        query.getAndNumKeyQuery("c.rcmeqasmb_id", rcmFmeaCommonDTO.getRcmeqasmbId());
        query.getAndNumKeyQuery("f.rcmfmea_id", rcmFmeaCommonDTO.getRcmfmeaId());

    
        RcmFmeaDetailDTO rcmFmeaDetailDTO = 
        		(RcmFmeaDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmFmeaDetailDTO()));
        
        return rcmFmeaDetailDTO;
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFmeaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaDetailDTO
     * @return
     */
    public int insertDetail(RcmFmeaDetailDTO rcmFmeaDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TARCMFMEA								");
    	query.append("	(comp_no,		rcmfmea_id,		rcmlist_id,		");
    	query.append("	 rcmfunc_id,	rcmffail_id,	rcmeq_id,		");
    	query.append("	 rcmeqasmb_id,	mofail_id,	    mofcd,			");
    	query.append("	 mofail_desc,	cafail_id,	    cafcd,			");
    	query.append("	 cafail_desc,	effail_id,		effcd,			");
    	query.append("	 effail_desc,	remark,         rcmffeq_id 		");
    	query.append("	)	VALUES										");
    	query.append("	(?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,				?,				");
    	query.append("	 ?,				?,              ? 				");
    	query.append("	)												");
    	
    	Object[] objects = new Object[] {
    			rcmFmeaDetailDTO.getCompNo(),
    			rcmFmeaDetailDTO.getRcmfmeaId(),
    			rcmFmeaDetailDTO.getRcmlistId(),
    			rcmFmeaDetailDTO.getRcmfuncId(),
    			rcmFmeaDetailDTO.getRcmffailId(),
    			rcmFmeaDetailDTO.getRcmeqId(),
    			rcmFmeaDetailDTO.getRcmeqasmbId(),
    			rcmFmeaDetailDTO.getMofailId(),
    			rcmFmeaDetailDTO.getMofcd(),
    			rcmFmeaDetailDTO.getMofailDesc(),
    			rcmFmeaDetailDTO.getCafailId(),
    			rcmFmeaDetailDTO.getCafcd(),
    			rcmFmeaDetailDTO.getCafailDesc(),
    			rcmFmeaDetailDTO.getEffailId(),
    			rcmFmeaDetailDTO.getEffcd(),
    			rcmFmeaDetailDTO.getEffailDesc(),
    			rcmFmeaDetailDTO.getRemark(),
    			rcmFmeaDetailDTO.getRcmffeqId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFmeaDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFmeaDetailDTO
     * @return
     */
    public int updateDetail(RcmFmeaDetailDTO rcmFmeaDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TARCMFMEA SET			");
    	query.append("	mofail_id				= ?,	");
    	query.append("	mofcd					= ?,	");
    	query.append("	mofail_desc				= ?,	");
    	query.append("	cafail_id				= ?,	");
    	query.append("	cafcd					= ?,	");
    	query.append("	cafail_desc				= ?,	");
    	query.append("	effail_id				= ?,	");
    	query.append("	effcd					= ?,	");
    	query.append("	effail_desc				= ?,	");
    	query.append("	remark					= ?		");
    	query.append("WHERE rcmfmea_id			= ?		");
    	query.append("  AND comp_no 			= ?		");
    	
    	Object[] objects = new Object[] {
    			rcmFmeaDetailDTO.getMofailId(),
    			rcmFmeaDetailDTO.getMofcd(),
    			rcmFmeaDetailDTO.getMofailDesc(),
    			rcmFmeaDetailDTO.getCafailId(),
    			rcmFmeaDetailDTO.getCafcd(),
    			rcmFmeaDetailDTO.getCafailDesc(),
    			rcmFmeaDetailDTO.getEffailId(),
    			rcmFmeaDetailDTO.getEffcd(),
    			rcmFmeaDetailDTO.getEffailDesc(),
    			rcmFmeaDetailDTO.getRemark(),
    			rcmFmeaDetailDTO.getRcmfmeaId(),
    			rcmFmeaDetailDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}