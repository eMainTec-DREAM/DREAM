package dream.rcm.funceq.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.rcm.funceq.dao.RcmFuncEqItemListDAO;
import dream.rcm.funceq.dto.RcmFuncEqCommonDTO;
import dream.rcm.funceq.dto.RcmFuncEqItemListDTO;

/**
 * 답변 목록 dao
 * @author  kim21017
 * @version $Id: RcmFuncEqItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="rcmFuncEqItemListDAOTarget"
 * @spring.txbn id="rcmFuncEqItemListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class RcmFuncEqItemListDAOOraImpl extends BaseJdbcDaoSupportOra implements RcmFuncEqItemListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: RcmFuncEqItemListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param rcmFuncEqCommonDTO
     * @param rcmFuncEqItemListDTO
     * @return List
     */
    public List findItemList(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = rcmFuncEqCommonDTO.getCompNo();
        
        query.append("SELECT         									");
        query.append("       '' seqNo,                                  ");
        query.append("       '' isDelCheck,         			        ");
        query.append("       y.item_no itemNo,				");
        query.append("       y.description itemDesc,		");
        query.append("       (SELECT z.eqasmb_id FROM TARCMEQASMB z WHERE x.rcmeqasmb_id=z.rcmeqasmb_id AND x.comp_no=z.comp_no) asmbNo,		");
        query.append("       (SELECT z.full_desc FROM TAEQASMB z WHERE z.eqasmb_id = (SELECT z1.eqasmb_id FROM TARCMEQASMB z1 WHERE x.rcmeqasmb_id=z1.rcmeqasmb_id) AND x.comp_no=z.comp_no)asmbDesc,		");
        query.append("       x.is_possible isPossible,					");
        query.append("       x.remark remark,							");
        query.append("       x.rcmffeq_id rcmFfeqId                		");
        query.append("FROM TARCMFFEQ x, TAEQUIPMENT y					");
        query.append("WHERE (SELECT z.equip_id FROM TARCMEQ z  WHERE z.rcmeq_id=x.rcmeq_id) = y.equip_id 	");
        query.append("AND x.comp_no = '"+compNo+"'						");
        query.append(this.getWhere(rcmFuncEqCommonDTO,rcmFuncEqItemListDTO));
        query.getOrderByQuery("x.rcmffeq_id","y.item_no", rcmFuncEqCommonDTO.getOrderBy(), rcmFuncEqCommonDTO.getDirection());
        return getJdbcTemplate().queryForList(query.toString(rcmFuncEqCommonDTO.getIsLoadMaxCount(), rcmFuncEqCommonDTO.getFirstRow()));
   
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: RcmFuncEqItemListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param deleteRow
     * @param deleteRowsExt 
     * @return
     */
    public int deleteItemList(String deleteRow)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String rcmffeq_id=deleteRow;
    	
    	query.append("DELETE FROM TARCMFFEQ						");
    	query.append("WHERE rcmffeq_id 	= '"+rcmffeq_id+"'	");
    	
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.rcmffail_id", rcmFuncEqCommonDTO.getRcmFfailId());
    	if (!"".equals(rcmFuncEqItemListDTO.getRcmFfEqId()))
        {
            query.getAndQuery("x.rcmffeq_id", rcmFuncEqItemListDTO.getRcmFfEqId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String getEquip(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO,
			String multiEquip, String multiAsmb) {
		 QueryBuffer query = new QueryBuffer();

		 query.append("  SELECT x.rcmeq_id                   		");
		 query.append("                                    FROM TARCMEQ x                                		");
		 query.append("                                WHERE 1 = 1                                     		");
		 query.append("                                AND x.rcmlist_id = (SELECT y.rcmlist_id FROM TARCMFFAIL y WHERE y.rcmffail_id=?)                                    		");
		 query.append("                                    AND x.equip_id = ?     		");


	        Object[] objects = new Object[] {
	        		rcmFuncEqCommonDTO.getRcmFfailId()
	        		,multiEquip
	    	};

	        
	        return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString(), objects));
	}

	@Override
	public String[] getRcmAsmb(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO,
			String multiEquip, String multiAsmb) {
		 QueryBuffer query = new QueryBuffer();

		 query.append("SELECT                                                          		");
		 query.append("       x.rcmeqasmb_id                     rcmEqAsmbId     		");
		 query.append("FROM TARCMEQASMB x                   		");
		 query.append("WHERE 1 =    1                                                		");
		 query.append("AND x.comp_no = ?                    		");
		 query.append("  AND  x.rcmeq_id = ?		");
		 query.append("  AND  x.eqasmb_id = ?		");



	        Object[] objects = new Object[] {
	        		rcmFuncEqCommonDTO.getCompNo(),
	        		multiEquip,
	        		multiAsmb
	    	};
	        
	        List resultList = getJdbcTemplate().queryForList(query.toString(),objects);
	        
	        return QueryBuffer.singleRowToStringArray(resultList);
	}
	
	@Override
	public int insertRcmEq(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO,
			String rcmEqId, String equipId) {
		QueryBuffer query = new QueryBuffer();
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
    			,rcmEqId
    			,rcmFuncEqCommonDTO.getRcmFfailId()
    			,equipId
    			,equipId
    			,equipId
    			,""
    			
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
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
    public int insertRcmAsmb(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO, String rcmEqAsmbId, String rcmEqId, String eqAsmbId)
    {
    	QueryBuffer query = new QueryBuffer();
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
    			,rcmEqId
    			,rcmFuncEqCommonDTO.getRcmFfailId()    			    		
    			,eqAsmbId
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
	@Override
	public int insertDetail(RcmFuncEqItemListDTO rcmFuncEqItemListDTO, RcmFuncEqCommonDTO rcmFuncEqCommonDTO,
			String rcmEqId, String asmbId) {
		QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TARCMFFEQ (								");
    	query.append("	comp_no,		rcmffeq_id,		    rcmffail_id,	");
    	query.append("	rcmfunc_id ,	rcmlist_id,		    rcmeq_id,		");
    	query.append("	rcmeqasmb_id,   is_possible,        remark		    ");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				SQARCMFFEQ_ID.NEXTVAL,					?,				");
    	query.append("	(SELECT rcmfunc_id FROM TARCMFFAIL WHERE rcmffail_id=?), (SELECT rcmlist_id FROM TARCMFFAIL WHERE rcmffail_id=?),					?,				");
    	query.append("	?,				?,					?				");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			rcmFuncEqCommonDTO.getCompNo()
    			,rcmFuncEqCommonDTO.getRcmFfailId()
    			,rcmFuncEqCommonDTO.getRcmFfailId()
    			,rcmFuncEqCommonDTO.getRcmFfailId()
    			,rcmEqId
    			,asmbId
    			,"N"
    			,""
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
	}

	@Override
	public String findTotalCount(RcmFuncEqCommonDTO rcmFuncEqCommonDTO, RcmFuncEqItemListDTO rcmFuncEqItemListDTO,
			User user) {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT					");
    	query.append("		count(1)			");
        query.append("FROM TARCMFFEQ x, TAEQUIPMENT y					");
        query.append("WHERE (SELECT z.equip_id FROM TARCMEQ z  WHERE z.rcmeq_id=x.rcmeq_id) = y.equip_id 	");
        query.append("AND x.comp_no = '"+user.getCompNo()+"'						");
        query.append(this.getWhere(rcmFuncEqCommonDTO,rcmFuncEqItemListDTO));

    	List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
}