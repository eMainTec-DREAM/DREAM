package dream.part.rec.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.part.rec.dao.MaPtRecSerialDetailDAO;
import dream.part.rec.dto.MaPtRecSerialDetailDTO;
import dream.part.rec.dto.MaPtRecCommonDTO;
import dream.part.rec.dto.MaPtRecSerialListDTO;
import dream.work.list.dto.MaWoResultCraftDetailDTO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;

/**
 * 구매입고 item 상세 dao
 * @author  kim21017
 * @version $Id: MaPtRecSerialDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPtRecSerialDetailDAOTarget"
 * @spring.txbn id="maPtRecSerialDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPtRecSerialDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPtRecSerialDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialListDTO
     * @param maPtRecCommonDTO
     * @return
     */
    public MaPtRecSerialDetailDTO findDetail(MaPtRecSerialListDTO maPtRecSerialListDTO, MaPtRecCommonDTO maPtRecCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maPtRecCommonDTO.getCompNo();
        
        query.append("SELECT												");
        query.append("       serial_no serialNo		");
        query.append("       ,remark remark		");
        query.append("       ,prreclist_serial_id serialId		");
        query.append("FROM TAPTPRRECLIST_SERIAL x										");
        query.append("WHERE x.comp_no = '"+compNo+"'						");
        query.getAndQuery("x.prreclist_id", maPtRecCommonDTO.getPrRecListId());
        query.getAndQuery("x.prreclist_serial_id", maPtRecSerialListDTO.getPrreclistSerialId());
    
        MaPtRecSerialDetailDTO maPtRecSerialDetailDTO1 = 
        		(MaPtRecSerialDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPtRecSerialDetailDTO()));
        
        return maPtRecSerialDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialDetailDTO
     * @param maPtRecCommonDTO
     * @return
     */
    public int updateDetail(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAPTPRRECLIST_SERIAL SET				");
    	query.append("	serial_no					= ?,		");
    	query.append("	remark					= ?		");
    	query.append("WHERE prreclist_id 		= ?		");
    	query.append("  AND prreclist_serial_id			= ? 	");
    	query.append("  AND comp_no				= ? 	");
    	
    	Object[] objects = new Object[] {
    			maPtRecSerialDetailDTO.getSerialNo()
    			,maPtRecSerialDetailDTO.getRemark()
    			,maPtRecCommonDTO.getPrRecListId()
    			,maPtRecSerialDetailDTO.getSerialId()
    			,maPtRecCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaPtRecSerialDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maPtRecSerialDetailDTO
     * @param maPtRecCommonDTO
     * @return
     */
    public int insertDetail(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAPTPRRECLIST_SERIAL (								");
    	query.append("	comp_no,		prreclist_serial_id,	prreclist_id,		");
    	query.append("	part_id,	    serial_no,              remark			");
    	query.append("	)	VALUES				(							");
    	query.append("	?,				?,					?,				");
    	query.append("	?,				?,    ?				");
    	query.append("	)													");
    	
    	Object[] objects = new Object[] {
    			maPtRecCommonDTO.getCompNo()
    			,maPtRecSerialDetailDTO.getSerialId()
    			,maPtRecCommonDTO.getPrRecListId()
    			,maPtRecCommonDTO.getPartId()
    			,maPtRecSerialDetailDTO.getSerialNo()
    			,maPtRecSerialDetailDTO.getRemark()

    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    /**
     * 재고확인
     */
    public String validSerial(MaPtRecSerialDetailDTO maPtRecSerialDetailDTO, MaPtRecCommonDTO maPtRecCommonDTO, User loginUser){
    	QueryBuffer query = new QueryBuffer();
    	query.append("SELECT count(*) 			");
    	query.append("FROM TAPTPRRECLIST_SERIAL			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("part_id", maPtRecSerialDetailDTO.getPartId());
    	query.getAndQuery("serial_no", maPtRecSerialDetailDTO.getSerialNo());
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}
