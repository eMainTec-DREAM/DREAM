package dream.work.list.dao.sqlImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultToolDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolDetailDTO;

/**
 * 작업결과-투입공기구 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultToolDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultToolDetailDAOTarget"
 * @spring.txbn id="maWoResultToolDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultToolDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoResultToolDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultToolDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woToolId
     * @param compNo
     * @return
     */
    public MaWoResultToolDetailDTO findDetail(String wkOrId, String woToolId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String compNo = user.getCompNo();

        query.append("SELECT												");
        query.append("       x.wkor_id 						wkOrId,			");
        query.append("       x.wotool_id 					woToolId,		");
        query.append("       x.part_id 						partId,			");
        query.append("       y.part_no     partNo,            				");
        query.append("       CASE WHEN x.part_id IS NULL THEN x.pt_desc ELSE y.description END   partDesc, 	");
        query.append("       CASE WHEN x.part_id IS NULL THEN x.pt_size ELSE y.pt_size END   partSize,   	");
        query.append("       x.wcode_id 					wcodeId,		");
        query.append("       (SELECT wname									");
        query.append("          FROM TAWAREHOUSE							");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND wcode_id = x.wcode_id) wcodeDesc,		");
        query.append("       isnull((SELECT stock_qty								");
        query.append("          FROM TAPTSTOCK								");
        query.append("         WHERE comp_no = x.comp_no					");
        query.append("           AND wcode_id = x.wcode_id					");
        query.append("           AND part_grade = x.part_grade              ");
        query.append("           AND part_id = x.part_id),0) stockQty,		");
        query.append("       x.use_qty 						useQty,			");
        query.append("       x.disusertn_qty 				disUseRtnQty,	");
        query.append("       x.remark 						remark,			");
        query.append("		 x.part_grade					partGradeId,	");
        query.append("		 dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"')	partGradeDesc ");
        query.append("FROM   TAWOTOOLS x  LEFT OUTER JOIN TAPARTS Y         ");
        query.append("  ON x.comp_no = y.comp_no                            ");
        query.append(" AND x.part_id = y.part_id                            ");
        query.append("WHERE	 x.wotool_id 		= '"+woToolId+"'			");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        MaWoResultToolDetailDTO maWoResultToolDetailDTO = 
        		(MaWoResultToolDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoResultToolDetailDTO()));
        
        return maWoResultToolDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultToolDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultToolDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOTOOLS SET				");
    	query.append("	part_id					= ?,	");
    	query.append("	use_qty					= ?,	");
    	query.append("	disusertn_qty			= ?,	");
    	query.append("	wcode_id				= ?,	");
    	query.append("	part_grade				= isnull(?,(select value$ from taconfig where comp_no = ? and name = 'PART_GRADE')),	");
    	query.append("	remark					= ?,	");
    	query.append("	pt_desc					= ?,	");
    	query.append("	pt_size					= ?		");
    	query.append("WHERE wotool_id		= ?			");
    	query.append("  AND comp_no			= ?			");
    	
    	Object[] objects = new Object[] {
    			maWoResultToolDetailDTO.getPartId(),
    			maWoResultToolDetailDTO.getUseQty(),
    			maWoResultToolDetailDTO.getDisUseRtnQty(),
    			maWoResultToolDetailDTO.getWcodeId(),
    			maWoResultToolDetailDTO.getPartGradeId(),
    			user.getCompNo(),
    			maWoResultToolDetailDTO.getRemark(),
    			maWoResultToolDetailDTO.getPartDesc(),
    			maWoResultToolDetailDTO.getPartSize(),
    			maWoResultToolDetailDTO.getWoToolId(),
    			user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultToolDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultToolDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultToolDetailDTO maWoResultToolDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOTOOLS							");
    	query.append("	(comp_no,		wotool_id,					");
    	query.append("	 wkor_id,		part_id,					");
    	query.append("	 wcode_id,		use_qty,					");
    	query.append("	 disusertn_qty, remark,						");
    	query.append("	 part_grade,	pt_desc,		   			");
    	query.append("	 pt_size		                			");
    	query.append("	)	VALUES									");
    	query.append("	(?,				?,							");
    	query.append("	 ?,				?,							");
    	query.append("	 ?,				?,							");
    	query.append("	 ?,				?,							");
    	query.append("	 isnull(?,(select value$ from taconfig where comp_no = ? and name = 'PART_GRADE')),							");
    	query.append("	 ?,             ?							");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultToolDetailDTO.getWoToolId(),
    			maWoResultMstrCommonDTO.getWkOrId(),
    			maWoResultToolDetailDTO.getPartId(),
    			maWoResultToolDetailDTO.getWcodeId(),
    			maWoResultToolDetailDTO.getUseQty(),
    			maWoResultToolDetailDTO.getDisUseRtnQty(),
    			maWoResultToolDetailDTO.getRemark(),
    			maWoResultToolDetailDTO.getPartGradeId(),
    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultToolDetailDTO.getPartDesc(),
    			maWoResultToolDetailDTO.getPartSize()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * 재고확인
     */
    public String getStockQty(MaWoResultToolDetailDTO maWoResultToolDetailDTO, User loginUser){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT stock_qty			");
    	query.append("FROM TAPTSTOCK			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("comp_no", loginUser.getCompNo());
    	query.getAndQuery("wcode_id", maWoResultToolDetailDTO.getWcodeId());
    	query.getAndQuery("part_id", maWoResultToolDetailDTO.getPartId());
    	query.getAndQuery("part_grade", maWoResultToolDetailDTO.getPartGradeId());
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
}