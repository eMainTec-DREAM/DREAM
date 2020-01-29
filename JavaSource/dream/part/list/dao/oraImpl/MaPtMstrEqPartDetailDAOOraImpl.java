package dream.part.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.part.list.dao.MaPtMstrEqPartDetailDAO;
import dream.part.list.dto.MaPtMstrCommonDTO;
import dream.part.list.dto.MaPtMstrEqPartDetailDTO;

/**
 * 사용설비 상세 dao
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPtMstrEqPartDetailDAOTarget"
 * @spring.txbn id="maPtMstrEqPartDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtMstrEqPartDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtMstrEqPartDetailDAO
{
    /**
     * detail find
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrCommonDTO
     * @param loginUser
     * @return
     */
    public MaPtMstrEqPartDetailDTO findDetail(MaPtMstrCommonDTO maPtMstrCommonDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT x.comp_no                      compNo,             ");
        query.append("       x.eqpart_id                    eqPartId,           ");
        query.append("       x.equip_id                     equipId,	        ");
        query.append("       x.part_id                      partId,	            ");
        query.append("       y.item_no                      itemNo,             ");
        query.append("       y.description                  equipDesc,          ");
        query.append("       x.eqasmb_id                    eqAsmbId,           ");
        query.append("      (SELECT full_desc FROM TAEQASMB                     ");
        query.append("       WHERE comp_no=x.comp_no                            ");
        query.append("         AND eqasmb_id=x.eqasmb_id)   eqAsmbDesc,         ");
        query.append("      (SELECT full_desc FROM TAEQLOC                      ");
        query.append("       WHERE  comp_no  = y.comp_no                        ");
        query.append("         AND  eqloc_id = y.eqloc_id)  eqLocDesc,          ");
        query.append("      (SELECT full_desc FROM TAEQCTG                      ");
        query.append("       WHERE  comp_no  = y.comp_no                        ");
        query.append("         AND  eqctg_id = y.eqctg_id)  eqCtgDesc           ");
        query.append("FROM   TAEQPART x, TAEQUIPMENT y                          ");
        query.append("WHERE  x.comp_no   = y.comp_no			                ");
        query.append("  AND  x.equip_id  = y.equip_id                           ");
        query.append("  AND  x.comp_no   = '"+loginUser.getCompNo()+"'          ");
        query.append("  AND  x.eqpart_id = '"+maPtMstrCommonDTO.getEqPartId()+"' ");
    
        MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO = 
        		(MaPtMstrEqPartDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtMstrEqPartDetailDTO()));
        
        return maPtMstrEqPartDetailDTO;
    }
    
    /**
     * detail update
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrEqPartDetailDTO
     * @param loginUser
     * @return
     */
    public int updateDetail(MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAEQPART SET		");
    	query.append("	     equip_id   = ? 	");
    	query.append("	     ,eqasmb_id = ? 	");
    	query.append("WHERE  comp_no    = ?		");
    	query.append("  AND  eqpart_id  = ?		");
    	
    	Object[] objects = new Object[] {
    			maPtMstrEqPartDetailDTO.getEquipId(),
    			maPtMstrEqPartDetailDTO.getEqAsmbId(),
    			maPtMstrEqPartDetailDTO.getCompNo(),
    			maPtMstrEqPartDetailDTO.getEqPartId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param maPtMstrEqPartDetailDTO
     * @param loginUser
     * @return
     */
    public int insertDetail(MaPtMstrEqPartDetailDTO maPtMstrEqPartDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAEQPART (				                    ");
    	query.append("	comp_no,   eqpart_id,    part_id,    equip_id,          ");
    	query.append("	eqasmb_id                                               ");
    	query.append(")	VALUES (                                                ");
    	query.append("  ?,	       ?,            ?,          ?,                 ");
    	query.append("  ?                                                       ");
    	query.append(")									                        ");
    	
    	Object[] objects = new Object[] {
    	        maPtMstrEqPartDetailDTO.getCompNo(),
    			maPtMstrEqPartDetailDTO.getEqPartId(),
    			maPtMstrEqPartDetailDTO.getPartId(),
    			maPtMstrEqPartDetailDTO.getEquipId(),
    			maPtMstrEqPartDetailDTO.getEqAsmbId()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}