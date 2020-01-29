package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultPartDetailDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartDetailDTO;

/**
 * 작업결과-투입자재 상세 dao
 * @author  kim21017
 * @version $Id: MaWoResultPartDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultPartDetailDAOTarget"
 * @spring.txbn id="maWoResultPartDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultPartDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoResultPartDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaWoResultPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param wkOrId
     * @param woPartId
     * @param compNo
     * @return
     */
    public MaWoResultPartDetailDTO findDetail(String wkOrId, String woPartId, User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	String compNo = user.getCompNo();

        query.append("SELECT												");
        query.append("       x.wkor_id 						wkOrId,			");
        query.append("       x.wopart_id 					woPartId,		");
        query.append("       x.part_id 						partId,			");
        query.append("       y.part_no     partNo,            				");
        query.append("       CASE WHEN x.part_id IS NULL THEN x.pt_desc ELSE y.description END   partDesc,   	");
        query.append("       CASE WHEN x.part_id IS NULL THEN x.pt_size ELSE y.pt_size END   partSize,   	");
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
        query.append("       x.disusertn_qty 				disUseRtnQty,	");
        query.append("       x.remark 						remark,			");
        query.append("		x.part_grade					partGrade,		");
        query.append("		dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+user.getLangId()+"')	partGradeDesc,");
        query.append("      (SELECT a.full_desc FROM TAEQASMB a WHERE a.eqasmb_id = x.eqasmb_id) eqAsmbDesc,       ");
        query.append("       x.ptisslist_id                 ptisslistId    ");
        query.append("       ,y.model                 		model    ");
        query.append("FROM   TAWOPARTS x  LEFT OUTER JOIN TAPARTS Y ON x.comp_no = y.comp_no AND x.part_id = y.part_id    ");
        query.append("WHERE	 x.wopart_id 		= '"+woPartId+"'			");
        query.append("  AND  x.comp_no			= '"+compNo+"'				");
    
        MaWoResultPartDetailDTO maWoResultPartDetailDTO = 
        		(MaWoResultPartDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaWoResultPartDetailDTO()));
        
        return maWoResultPartDetailDTO;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaWoResultPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPartDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int updateDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,User user)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("UPDATE TAWOPARTS SET				");
    	query.append("		use_qty				= ?		");
    	query.append("	  , remark				= ?		");
    	query.append("WHERE wopart_id			= ?		");
    	query.append("  AND comp_no				= ?		");
    	
    	Object[] objects = new Object[] {
    			maWoResultPartDetailDTO.getUseQty(),
    			maWoResultPartDetailDTO.getRemark(),
    			maWoResultPartDetailDTO.getWoPartId(),
    			user.getCompNo()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaWoResultPartDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultPartDetailDTO
     * @param maWoResultMstrCommonDTO
     * @return
     */
    public int insertDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("INSERT INTO TAWOPARTS							");
    	query.append("	(comp_no,		wopart_id,					");
    	query.append("	 wkor_id,		part_id,					");
    	query.append("	 wcode_id,		use_qty,					");
    	query.append("	 unit_price,	tot_price,					");
        query.append("   remark,        part_grade,                 ");
        query.append("   eqasmb_id,		pt_desc,		   			");
    	query.append("	 pt_size,		disusertn_qty     			");
    	query.append("	)	VALUES									");
    	query.append("	(?,				?,							");
    	query.append("	 ?,				?,							");
    	query.append("	 ?,				?,							");
    	query.append("	CASE WHEN (SELECT ISNULL(SUM(unit_price),0)	");
    	query.append("				FROM TAPTSTOCK					");
    	query.append("				WHERE comp_no 	= ?				");
    	query.append("				AND   wcode_id 	= ?				");
    	query.append("				AND   part_id 	= ?				");
    	query.append("				AND   part_grade= ?				");
    	query.append("				) = 0							");
    	query.append("		THEN (SELECT ISNULL(SUM(last_price),0)	");
    	query.append("				FROM TAPARTS 					");
    	query.append("				WHERE comp_no = ?				");
    	query.append("				AND   part_id = ?				");
    	query.append("			)									");
    	query.append("		ELSE (SELECT ISNULL(SUM(unit_price),0)	");
    	query.append("				FROM TAPTSTOCK					");
    	query.append("				WHERE comp_no 	= ?				");
    	query.append("				AND   wcode_id 	= ?				");
    	query.append("				AND   part_id 	= ?				");
    	query.append("				AND   part_grade= ?				");
    	query.append("			)									");
    	query.append("		END,										");
    	query.append("	CASE WHEN (SELECT ISNULL(SUM(unit_price),0)	");
    	query.append("				FROM TAPTSTOCK					");
    	query.append("				WHERE comp_no 	= ?				");
    	query.append("				AND   wcode_id 	= ?				");
    	query.append("				AND   part_id 	= ?				");
    	query.append("				AND   part_grade= ?				");
    	query.append("				) = 0							");
    	query.append("		THEN (SELECT ISNULL(SUM(last_price),0)	");
    	query.append("				FROM TAPARTS 					");
    	query.append("				WHERE comp_no = ?				");
    	query.append("				AND   part_id = ?				");
    	query.append("			)									");
    	query.append("		ELSE (SELECT ISNULL(SUM(unit_price),0)	");
    	query.append("				FROM TAPTSTOCK					");
    	query.append("				WHERE comp_no 	= ?				");
    	query.append("				AND   wcode_id 	= ?				");
    	query.append("				AND   part_id 	= ?				");
    	query.append("				AND   part_grade= ?				");
    	query.append("			)									");
    	query.append("		END*ISNULL(?,0),						");
    	query.append("	 ?,											");
        query.append("   ISNULL(?,(select value$ from taconfig where comp_no = ? and name = 'PART_GRADE')),                            ");
        query.append("  ?, ?, ?,?                                   ");
    	query.append("	)											");
    	
    	Object[] objects = new Object[] {
    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultPartDetailDTO.getWoPartId(),
    			maWoResultMstrCommonDTO.getWkOrId(),
    			maWoResultPartDetailDTO.getPartId(),
    			maWoResultPartDetailDTO.getWcodeId(),
    			maWoResultPartDetailDTO.getUseQty(),

    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultPartDetailDTO.getWcodeId(),
    			maWoResultPartDetailDTO.getPartId(),
    			maWoResultPartDetailDTO.getPartGrade(),

    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultPartDetailDTO.getPartId(),

    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultPartDetailDTO.getWcodeId(),
    			maWoResultPartDetailDTO.getPartId(),
    			maWoResultPartDetailDTO.getPartGrade(),
    			
    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultPartDetailDTO.getWcodeId(),
    			maWoResultPartDetailDTO.getPartId(),
    			maWoResultPartDetailDTO.getPartGrade(),

    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultPartDetailDTO.getPartId(),

    			maWoResultMstrCommonDTO.getCompNo(),
    			maWoResultPartDetailDTO.getWcodeId(),
    			maWoResultPartDetailDTO.getPartId(),
    			maWoResultPartDetailDTO.getPartGrade(),
    			
    			maWoResultPartDetailDTO.getUseQty(),
    			maWoResultPartDetailDTO.getRemark(),
    			maWoResultPartDetailDTO.getPartGrade(),
                maWoResultMstrCommonDTO.getCompNo(),
                maWoResultPartDetailDTO.getEqAsmbId(),
    			maWoResultPartDetailDTO.getPartDesc(),
    			maWoResultPartDetailDTO.getPartSize(),
    			maWoResultPartDetailDTO.getDisUseRtnQty()
    	};
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    /**
     * 재고확인
     */
    public String getStockQty(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser){
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT stock_qty			");
    	query.append("FROM TAPTSTOCK			");
    	query.append("WHERE 1=1					");
    	query.getAndQuery("comp_no", loginUser.getCompNo());
    	query.getAndQuery("wcode_id", maWoResultPartDetailDTO.getWcodeId());
    	query.getAndQuery("part_id", maWoResultPartDetailDTO.getPartId());
    	
    	return QuerySqlBuffer.listToString(getJdbcTemplate().queryForList(query.toString()));
    }
    @Override
    public int insertIssPartDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAWOPARTS ( comp_no, wopart_id, wkor_id, wcode_id, part_id, part_grade, use_qty, remark, unit_price, tot_price, eqasmb_id )       ");
        query.append("SELECT comp_no                                            ");
        query.append("       ,?                                                 ");
        query.append("       ,?                                                 ");
        query.append("       ,isnull(to_wcode_id,wcode_id)  as wcode_id         ");
        query.append("       ,part_id                                           ");
        query.append("       ,part_grade                                        ");
        query.append("       ,issue_qty                                         ");
        query.append("       ,'Emg. Issued S/Part'                              ");
        query.append("       ,(SELECT unit_price                                ");
        query.append("         FROM   TAPTSTOCK a                               ");
        query.append("         WHERE  a.comp_no =  x.comp_no                    ");
        query.append("           AND  a.wcode_id = x.wcode_id                   ");
        query.append("           AND  a.part_id = x.part_id                     ");
        query.append("           AND  a.part_grade = x.part_grade)              ");
        query.append("       ,(SELECT unit_price                                ");
        query.append("         FROM   TAPTSTOCK a                               ");
        query.append("         WHERE  a.comp_no =  x.comp_no                    ");
        query.append("           AND  a.wcode_id = x.wcode_id                   ");
        query.append("           AND  a.part_id = x.part_id                     ");
        query.append("           AND  a.part_grade = x.part_grade) * issue_qty  ");
        query.append("       ,eqasmb_id                                         ");
        query.append("FROM   TAPTEMGISSLIST x                                   ");
        query.append("WHERE  ptemgisslist_id = ?                                ");
        query.append("  AND  comp_no = ?                                        ");
        query.append("  AND  ptemg_task_status = ?                              ");

        
        Object[] objects = new Object[] {
                maWoResultPartDetailDTO.getWoPartId(),
                maWoResultMstrCommonDTO.getWkOrId(),
                maWoResultPartDetailDTO.getPtEmgIssListId(),
                maWoResultMstrCommonDTO.getCompNo(),
                "W"
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    @Override
    public int updateEmgPart(MaWoResultPartDetailDTO maWoResultPartDetailDTO, MaWoResultMstrCommonDTO maWoResultMstrCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("UPDATE TAPTEMGISSLIST SET                     ");
        query.append("       ptemg_task_status      = ?,            ");
        query.append("       wopart_id              = ?,            ");
        query.append("       wkor_id                = ?             ");
        query.append("WHERE  comp_no                = ?             ");
        query.append("  AND  ptemgisslist_id        = ?             ");

        
        Object[] objects = new Object[] {
                "C",
                maWoResultPartDetailDTO.getWoPartId(),
                maWoResultMstrCommonDTO.getWkOrId(),
                maWoResultMstrCommonDTO.getCompNo(),
                maWoResultPartDetailDTO.getPtEmgIssListId()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
    @Override
    public List getIssQty(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                        	");
        query.append("  a.issue_qty     			AS issueQty         ");
        query.append("  ,ISNULL(sum(b.use_qty),0)	AS useQty           ");
        query.append("FROM TAPTISSLIST a LEFT OUTER JOIN TAWOPARTS b	");
        query.append("ON a.comp_no = b.comp_no                      	");
        query.append(" AND a.ptisslist_id = b.ptisslist_id           	");
        query.append(" AND b.wopart_id   != ?                        	");
        query.append("WHERE a.comp_no    = ?                        	");
        query.append(" AND a.ptisslist_id = ?                        	");
        query.append("GROUP BY a.issue_qty                          	");
        
        Object[] objects = new Object[] {
        		maWoResultPartDetailDTO.getWoPartId()
                ,loginUser.getCompNo()
                ,maWoResultPartDetailDTO.getPtisslistId()
        };
        
        return getJdbcTemplate().queryForList(query.toString(), getObject(objects));
    }
    @Override
    public int insertPtIssDetail(MaWoResultPartDetailDTO maWoResultPartDetailDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("INSERT INTO TAWOPARTS ( comp_no, wopart_id, wkor_id, wcode_id, part_id, part_grade, use_qty, unit_price, tot_price, ptisslist_id )       ");
        query.append("SELECT comp_no                                            ");
        query.append("       ,?                                                 ");
        query.append("       ,?                                                 ");
        query.append("       ,wcode_id                                          ");
        query.append("       ,part_id                                           ");
        query.append("       ,part_grade                                        ");
        query.append("       ,issue_qty                                         ");
        query.append("       ,unit_price                                        ");
        query.append("       ,tot_price                                         ");
        query.append("       ,ptisslist_id                                      ");
        query.append("FROM   TAPTISSLIST x                                      ");
        query.append("WHERE  ptisslist_id = ?                                   ");
        query.append("  AND  comp_no = ?                                        ");

        
        Object[] objects = new Object[] {
                maWoResultPartDetailDTO.getWoPartId(),
                maWoResultPartDetailDTO.getWkOrId(),
                maWoResultPartDetailDTO.getPtisslistId(),
                user.getCompNo()
        };
        
        return getJdbcTemplate().update(query.toString(), getObject(objects));
    }
}