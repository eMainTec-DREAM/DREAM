package dream.tool.adj.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.tool.adj.dao.MaPttDisPartsDetailDAO;
import dream.tool.adj.dto.MaPttDisCommonDTO;
import dream.tool.adj.dto.MaPttDisPartsDetailDTO;
import dream.tool.adj.dto.MaPttDisPartsListDTO;

/**
 *  »ó¼¼ dao
 * @author  kim21017
 * @version $Id: MaPttDisPartsDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPttDisPartsDetailDAOTarget"
 * @spring.txbn id="maPttDisPartsDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPttDisPartsDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPttDisPartsDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsListDTO
     * @param maPttDisCommonDTO
     * @return
     */
    public MaPttDisPartsDetailDTO findDetail(MaPttDisPartsListDTO maPttDisPartsListDTO, MaPttDisCommonDTO maPttDisCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPttDisCommonDTO.getCompNo();
        
        query.append("SELECT");
        query.append("       x.part_id partId,");
        query.append("       y.part_no partNo,");
        query.append("       y.description||', '||y.pt_size        partDesc,  ");
        query.append("       x.ptdisuseitem_id ptdisuseitemId,");
        query.append("       x.disuse_qty disQty,");
        query.append("       x.remark remark       ");
        query.append("FROM TAPTDISUSEITEM x, TAPARTS y");
        query.append("WHERE x.part_id= y.part_id");
        query.getAndQuery("x.comp_no",compNo);
        query.getAndQuery("x.ptdisuseitem_id",maPttDisPartsListDTO.getPtdisuseitemId());
    
        MaPttDisPartsDetailDTO maPttDisPartsDetailDTO1 = 
        		(MaPttDisPartsDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPttDisPartsDetailDTO()));
        
        return maPttDisPartsDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsDetailDTO
     * @param maPttDisCommonDTO
     * @return
     */
    public int updateDetail(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO, MaPttDisCommonDTO maPttDisCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPTDISUSEITEM SET		      ");
    	query.append("	part_id				= ?,		  ");
    	query.append("  disuse_qty          = ?,          ");
    	query.append("  remark              = ?           ");
    	query.append("WHERE ptdisuseitem_id = ?		      ");
    	
    	Object[] objects = new Object[] {
    	        maPttDisPartsDetailDTO.getPartId(),
    	        maPttDisPartsDetailDTO.getDisQty(),
    	        maPttDisPartsDetailDTO.getRemark(),
    	        maPttDisPartsDetailDTO.getPtdisuseitemId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPttDisPartsDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPttDisPartsDetailDTO
     * @param maPttDisCommonDTO
     * @return
     */
    public int insertDetail(MaPttDisPartsDetailDTO maPttDisPartsDetailDTO, MaPttDisCommonDTO maPttDisCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPTDISUSEITEM (								");
    	query.append("	comp_no,		ptdisuseitem_id,		ptdisuselist_id,");
    	query.append("	part_id,		part_grade ,		    disuse_qty,		");
    	query.append("	remark												    ");
    	query.append("	)	VALUES(						     					");
    	query.append("	?,				?,					?,			     	");
    	query.append("	?,				?,					?,			     	");
    	query.append("	?			     								        ");
    	query.append("	)												     	");
    	
    	Object[] objects = new Object[] {
    			maPttDisCommonDTO.getCompNo(),
    			maPttDisPartsDetailDTO.getPtdisuseitemId(),
    			maPttDisCommonDTO.getPtdisuselistId(),
    			maPttDisPartsDetailDTO.getPartId(),
    			"B",
    			maPttDisPartsDetailDTO.getDisQty(),
    			maPttDisPartsDetailDTO.getRemark()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
}