package dream.work.pm.list.dao.sqlImpl;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.MaPmMstrPartDetailDAO;
import dream.work.pm.list.dto.MaPmMstrCommonDTO;
import dream.work.pm.list.dto.MaPmMstrPartDetailDTO;

/**
 * 사용자재 상세 dao
 * @author  jung7126
 * @version $Id: MaPmMstrPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 jung7126 Exp $
 * @since   1.0
 * @spring.bean id="maPmMstrPartDetailDAOTarget"
 * @spring.txbn id="maPmMstrPartDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmMstrPartDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmMstrPartDetailDAO
{
    /**
     * detail find
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param pmId
     * @param pmPartId
     * @param compNo
     * @return
     */
    public MaPmMstrPartDetailDTO findDetail(String pmId, String pmPartId, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT												");
        query.append("       x.pm_id 						pmId,			");
        query.append("       x.pm_part_id 					pmPartId,		");
        query.append("       x.part_id 						partId,			");
        query.append("       y.part_no                      partNo,         ");
        query.append("       y.description                  partDesc,       ");
        query.append("       y.pt_size                      ptSize,         ");
        query.append("       y.model                        model,          ");
        query.append("       x.use_qty 						useQty			");
        query.append("FROM   TAPMPART x LEFT OUTER JOIN TAPARTS y           ");
        query.append("ON x.comp_no = y.comp_no                              ");
        query.append("AND x.part_id = y.part_id                             ");
        query.append("WHERE	 x.pm_part_id 		= '"+pmPartId+"'			");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        MaPmMstrPartDetailDTO maPmMstrPartDetailDTO = 
        		(MaPmMstrPartDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaPmMstrPartDetailDTO()));
        
        return maPmMstrPartDetailDTO;
    }
    /**
     * detail update
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPartDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int updateDetail(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAPMPART SET				");
    	query.append("	part_id					= ?,	");
    	query.append("	use_qty					= ? 	");
    	query.append("WHERE pm_part_id		    = ?	    ");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			maPmMstrPartDetailDTO.getPartId(),
    			maPmMstrPartDetailDTO.getUseQty().replaceAll(",", ""),
    			maPmMstrPartDetailDTO.getPmPartId(),
    			maPmMstrMstrCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author jung7126
     * @version $Id: MaPmMstrPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 jung7126 Exp $
     * @since   1.0
     * 
     * @param maPmMstrPartDetailDTO
     * @param maPmMstrMstrCommonDTO
     * @return
     */
    public int insertDetail(MaPmMstrPartDetailDTO maPmMstrPartDetailDTO, MaPmMstrCommonDTO maPmMstrMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAPMPART					");
    	query.append("	(comp_no,		pm_part_id,			");
    	query.append("	 pm_id,		    part_id,			");
    	query.append("	 use_qty			                ");
    	query.append("	)VALUES							    ");
    	query.append("	(?,				?,					");
    	query.append("	 ?,				?,					");
    	query.append("	 ?									");
    	query.append("	)									");
    	
    	Object[] objects = new Object[] {
    	        maPmMstrMstrCommonDTO.getCompNo(),
    	        maPmMstrPartDetailDTO.getPmPartId(),
    	        maPmMstrMstrCommonDTO.getPmId(),
    	        maPmMstrPartDetailDTO.getPartId(),
    	        maPmMstrPartDetailDTO.getUseQty().replaceAll(",", "")
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}