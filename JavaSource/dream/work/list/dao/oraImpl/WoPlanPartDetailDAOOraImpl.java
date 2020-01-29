package dream.work.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.work.list.dao.WoPlanPartDetailDAO;
import dream.work.list.dto.WoPlanCommonDTO;
import dream.work.list.dto.WoPlanPartDetailDTO;

/**
 * 작업계획목록 - 투입부품 상세 dao
 * @author  youngjoo38
 * @version $Id$
 * @since   1.0
 * @spring.bean id="woPlanPartDetailDAOTarget"
 * @spring.txbn id="woPlanPartDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class WoPlanPartDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements WoPlanPartDetailDAO
{
    /**
     * detail find
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPartId
     * @param compNo
     * @return
     */
    public WoPlanPartDetailDTO findDetail(String wkOrId, String woPartId, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String compNo = user.getCompNo();

        query.append("SELECT												");
        query.append("       x.wkor_id 						wkOrId,			");
        query.append("       x.wopartplan_id 				woPartId,		");
        query.append("       x.part_id 						partId,			");
        query.append("       y.part_no                      partNo,         ");
        query.append("       y.description||', '||y.pt_size partDesc,   	");
        query.append("       x.wcode_id 					wcodeId,		");
        query.append("       (SELECT wname									");
        query.append("          FROM TAWAREHOUSE							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND wcode_id = x.wcode_id) wcodeDesc,		");
        query.append("       (SELECT stock_qty								");
        query.append("          FROM TAPTSTOCK								");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND wcode_id = x.wcode_id					");
        query.append("           AND part_grade = x.part_grade              ");
        query.append("           AND part_id = x.part_id)	stockQty,		");
        query.append("       x.use_qty 						useQty,			");
        query.append("       x.remark 						remark,			");
        query.append("		 x.part_grade					partGrade,		");
        query.append("       y.is_serial_part isSerialPart,        			");
        query.append("		 SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"')	partGradeDesc");
        query.append("FROM   TAWOPLANPARTS x  INNER JOIN TAPARTS Y ON x.comp_no = y.comp_no AND x.part_id = y.part_id    ");
        query.append("WHERE	 x.wopartplan_id 		= '"+woPartId+"'			");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        WoPlanPartDetailDTO woPlanPartDetailDTO = 
        		(WoPlanPartDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new WoPlanPartDetailDTO()));
        
        return woPlanPartDetailDTO;
    }
    /**
     * detail update
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanPartDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int updateDetail(WoPlanPartDetailDTO woPlanPartDetailDTO, WoPlanCommonDTO woPlanCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAWOPLANPARTS SET			");
    	query.append("	part_id					= ?,	");
    	query.append("	use_qty					= ?,	");
    	query.append("	wcode_id				= ?,	");
    	query.append("	part_grade				= nvl(?,(select value$ from taconfig where comp_no = ? and name = 'PART_GRADE')),	");
    	query.append("	remark					= ?	    ");
    	query.append("WHERE wopartplan_id		= ?		");
    	query.append("  AND comp_no			    = ?		");
    	
    	Object[] objects = new Object[] {
    			woPlanPartDetailDTO.getPartId(),
    			woPlanPartDetailDTO.getUseQty(),
    			woPlanPartDetailDTO.getWcodeId(),
    			woPlanPartDetailDTO.getPartGrade(),
    			woPlanCommonDTO.getCompNo(),
    			woPlanPartDetailDTO.getRemark(),
    			woPlanPartDetailDTO.getWoPartId(),
    			woPlanCommonDTO.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param woPlanPartDetailDTO
     * @param woPlanCommonDTO
     * @return
     */
    public int insertDetail(WoPlanPartDetailDTO woPlanPartDetailDTO, WoPlanCommonDTO woPlanCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAWOPLANPARTS						");
    	query.append("	(comp_no,		wopartplan_id,				");
    	query.append("	 wkor_id,		part_id,					");
    	query.append("	 wcode_id,		use_qty,					");
    	query.append("	 remark,		part_grade					");
    	query.append("	)	VALUES									");
    	query.append("	(?,				?,							");
    	query.append("	 ?,				?,							");
    	query.append("	 ?,				?,							");
    	query.append("	 ?,											");
    	query.append("	 nvl(?,(select value$ from taconfig where comp_no = ? and name = 'PART_GRADE'))							");
    	query.append("	)											");

    	Object[] objects = new Object[] {
    			woPlanCommonDTO.getCompNo(),
    			woPlanPartDetailDTO.getWoPartId(),
    			woPlanCommonDTO.getWkOrId(),
    			woPlanPartDetailDTO.getPartId(),
    			woPlanPartDetailDTO.getWcodeId(),
    			woPlanPartDetailDTO.getUseQty(),
    			woPlanPartDetailDTO.getRemark(),
    			woPlanPartDetailDTO.getPartGrade(),
    			woPlanCommonDTO.getCompNo()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    /**
     * 재고확인
     */
    public String getStockQty(WoPlanPartDetailDTO woPlanPartDetailDTO, User loginUser){
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("SELECT stock_qty			");
    	query.append("FROM TAPTSTOCK			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("comp_no", loginUser.getCompNo());
    	query.getAndQuery("wcode_id", woPlanPartDetailDTO.getWcodeId());
    	query.getAndQuery("part_id", woPlanPartDetailDTO.getPartId());
    	
    	return QueryBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}