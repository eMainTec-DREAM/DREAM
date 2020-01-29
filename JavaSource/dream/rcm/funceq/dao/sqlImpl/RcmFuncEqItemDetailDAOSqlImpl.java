package dream.rcm.funceq.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.rcm.funceq.dao.RcmFuncEqItemDetailDAO;
import dream.rcm.funceq.dto.RcmFuncEqItemDetailDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;

/**
 * 답변 상세 dao
 * @author  kim21017
 * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFuncEqItemDetailDAOTarget"
 * @spring.txbn id="rcmFuncEqItemDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFuncEqItemDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements RcmFuncEqItemDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemListDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public RcmFuncEqItemDetailDTO findDetail(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        String compNo = rcmFuncEqCommonDTO.getCompNo();
        
        query.append("SELECT		");
        query.append("    x.rcmeq_id equipId,		");
        query.append("    y.equip_id taEquipId,		");
        query.append("    y.description equipDesc,		");
        query.append("    rcmeqasmb_id asmbId,		");
        query.append("    (SELECT z.full_desc FROM TAEQASMB z WHERE z.eqasmb_id = (SELECT z1.eqasmb_id FROM TARCMEQASMB z1 WHERE x.rcmeqasmb_id=z1.rcmeqasmb_id) AND x.comp_no=z.comp_no)asmbDesc,  		");
        query.append("    x.is_possible isPossible, 		");
        query.append("    x.remark remark,		");
        query.append("    x.rcmffeq_id rcmFfEqId		");
        query.append("FROM TARCMFFEQ x, TAEQUIPMENT y		");
        query.append("WHERE (SELECT z.equip_id FROM TARCMEQ z  WHERE z.rcmeq_id=x.rcmeq_id) = y.equip_id   		");
        query.append("AND x.comp_no = '"+compNo+"'						");
        query.getAndQuery("x.rcmffeq_id",rcmFuncEqItemListDTO.getRcmFfEqId());
    
        RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO1 = 
        		(RcmFuncEqItemDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new RcmFuncEqItemDetailDTO()));
        
        return rcmFuncEqItemDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int updateDetail(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TARCMFFEQ SET				");
    	query.append("	rcmeq_id				= ?		");
    	query.append("	,rcmeqasmb_id			= ?		");
    	query.append("	,is_possible			= ?		");
    	query.append("	,remark				    = ?		");
    	query.append("WHERE rcmffeq_id 		    = ?		");
    	query.append("  AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqItemDetailDTO.getEquipId()
    			,rcmFuncEqItemDetailDTO.getAsmbId()
    			,rcmFuncEqItemDetailDTO.getIsPossible()
    			,rcmFuncEqItemDetailDTO.getRemark()
    			,rcmFuncEqItemDetailDTO.getRcmFfEqId()
    			,rcmFuncEqCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int insertDetail(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMFFEQ (								");
    	query.append("	comp_no,		rcmffeq_id,		    rcmffail_id,	");
    	query.append("	rcmfunc_id ,	rcmlist_id,		    rcmeq_id,		");
    	query.append("	rcmeqasmb_id,   is_possible,        remark		    ");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	(SELECT rcmfunc_id FROM TARCMFFAIL WHERE rcmffail_id=?), (SELECT rcmlist_id FROM TARCMFFAIL WHERE rcmffail_id=?),					?,				");
    	query.append("	?,				?,					?				");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqCommonDTO.getCompNo()
    			,rcmFuncEqItemDetailDTO.getRcmFfEqId()
    			,rcmFuncEqCommonDTO.getRcmFfailId()
    			,rcmFuncEqCommonDTO.getRcmFfailId()
    			,rcmFuncEqCommonDTO.getRcmFfailId()
    			,rcmFuncEqItemDetailDTO.getRcmEqId()
    			,rcmFuncEqItemDetailDTO.getAsmbId()
    			,rcmFuncEqItemDetailDTO.getIsPossible()
    			,rcmFuncEqItemDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
	@Override
	public String[] getRcmEq(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO) {
		 QuerySqlBuffer query = new QuerySqlBuffer();

/*		 query.append("  SELECT x.rcmeq_id rcmEqId 		");
		 query.append("  FROM TARCMFFEQ x 		");
		 query.append("  WHERE x.rcmeq_id = (  SELECT x.rcmeq_id                   		");
		 query.append("                                    FROM TARCMEQ x                                		");
		 query.append("                                WHERE 1 = 1                                     		");
		 query.append("                                AND x.rcmlist_id = (SELECT y.rcmlist_id FROM TARCMFFAIL y WHERE y.rcmffail_id=?)                                    		");
		 query.append("                                    AND x.equip_id = ?     )		");
		 */
		 
		 query.append("  SELECT x.rcmeq_id                   		");
		 query.append("                                    FROM TARCMEQ x                                		");
		 query.append("                                WHERE 1 = 1                                     		");
		 query.append("                                AND x.rcmlist_id = (SELECT y.rcmlist_id FROM TARCMFFAIL y WHERE y.rcmffail_id=?)                                    		");
		 query.append("                                    AND x.equip_id = ?     		");
		 
/*		 if(!"".equals(rcmFuncEqItemDetailDTO.getAsmbId()))
		 {
			 query.append("AND x.rcmeqasmb_id = (SELECT z.rcmeqasmb_id  FROM TARCMEQASMB z WHERE z.rcmeqasmb_id = '"+rcmFuncEqItemDetailDTO.getAsmbId()+"')      		");
		 }*/


	        Object[] objects = new Object[] {
	        		rcmFuncEqCommonDTO.getRcmFfailId(),
	        		rcmFuncEqItemDetailDTO.getEquipId()
	    	};
	        
	        List resultList = getJdbcTemplate().queryForList(query.toString(),objects);
	        
	        return QuerySqlBuffer.singleRowToStringArray(resultList);
	}
	
	@Override
	public String[] getRcmAsmb(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO) {
		 QuerySqlBuffer query = new QuerySqlBuffer();

		 query.append("SELECT                                                          		");
		 query.append("       x.rcmeqasmb_id                     rcmEqAsmbId     		");
		 query.append("FROM TARCMEQASMB x                   		");
		 query.append("WHERE 1 =    1                                                		");
		 query.append("AND x.comp_no = ?                    		");
		 query.append("  AND  x.rcmeq_id = ?		");
		 query.append("  AND  x.eqasmb_id = ?		");



	        Object[] objects = new Object[] {
	        		rcmFuncEqCommonDTO.getCompNo(),
	        		rcmFuncEqItemDetailDTO.getRcmEqId(),
	        		rcmFuncEqItemDetailDTO.getAsmbId()
	    	};
	        
	        List resultList = getJdbcTemplate().queryForList(query.toString(),objects);
	        
	        return QuerySqlBuffer.singleRowToStringArray(resultList);
	}
	
	/**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int insertRcmEq(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMEQ (								");
    	query.append("	comp_no,		rcmeq_id,		    rcmlist_id,	");
    	query.append("	eqloc_id ,	    eqctg_id,		    equip_id,		");
    	query.append("	remark		    ");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					(SELECT rcmlist_id FROM TARCMFFAIL WHERE rcmffail_id=?),				");
    	query.append("	(SELECT eqloc_id FROM TAEQUIPMENT where equip_id=?),	(SELECT eqctg_id FROM TAEQUIPMENT where equip_id=?),	?,			");
    	query.append("	?			");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqCommonDTO.getCompNo()
    			,rcmFuncEqItemDetailDTO.getRcmEqId()
    			,rcmFuncEqCommonDTO.getRcmFfailId()
    			,rcmFuncEqItemDetailDTO.getEquipId()
    			,rcmFuncEqItemDetailDTO.getEquipId()
    			,rcmFuncEqItemDetailDTO.getEquipId()
    			,""
    			
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
    
	/**
     * detail insert
     * @author kim21017
     * @version $Id: RcmFuncEqItemDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqItemDetailDTO
     * @param rcmFuncEqCommonDTO
     * @return
     */
    public int insertRcmAsmb(RcmFuncEqItemDetailDTO rcmFuncEqItemDetailDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO, String rcmEqAsmbId)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMEQASMB (								");
    	query.append("	comp_no,		rcmeqasmb_id,		    rcmeq_id,	");
    	query.append("	rcmlist_id ,	    eqasmb_id	");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	(SELECT rcmlist_id FROM TARCMFFAIL WHERE rcmffail_id=?),		?	)		");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqCommonDTO.getCompNo()
    			,rcmEqAsmbId
    			,rcmFuncEqItemDetailDTO.getRcmEqId()
    			,rcmFuncEqCommonDTO.getRcmFfailId()    			    		
    			,rcmFuncEqItemDetailDTO.getAsmbId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), getObject(objects));
    	
    	return rtnValue;
    }
}