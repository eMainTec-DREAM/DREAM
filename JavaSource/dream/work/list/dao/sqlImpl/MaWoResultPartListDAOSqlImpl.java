package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultPartListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultPartListDTO;

/**
 * 작업결과 투입자재 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultPartListDAOTarget"
 * @spring.txbn id="maWoResultPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultPartListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoResultPartListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultPartListDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPartListDTO maWoResultPartListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT													");
        query.append("       '' 								seqNo			");
        query.append("       ,'' 								isDelCheck		");
        query.append("       ,x.wkor_id 						wkOrId			");
        query.append("       ,x.wopart_id 						woPartId		");
        query.append("       ,(SELECT a.full_desc								");
        query.append("         FROM TAEQASMB a									");
        query.append("         WHERE a.eqasmb_id = x.eqasmb_id)	eqAsmbDesc		");
        query.append("       ,(SELECT description                               ");
        query.append("         FROM TAPLANT                                     ");
        query.append("         WHERE comp_no = x.comp_no                        ");
        query.append("          AND plant = (SELECT plant                       ");
        query.append("                       FROM TAWAREHOUSE                   ");
        query.append("                       WHERE comp_no = x.comp_no          ");
        query.append("                        AND wcode_id = x.wcode_id)        ");
        query.append("        ) 								plantDesc		");
        query.append("       ,(SELECT wname										");
        query.append("         FROM TAWAREHOUSE									");
        query.append("         WHERE comp_no = x.comp_no						");
        query.append("          AND wcode_id = x.wcode_id) 		wcodeDesc		");
        query.append("       ,y.part_no 	partNo								");
        query.append("       ,CASE WHEN x.part_id IS NULL THEN x.pt_desc ELSE y.description END	partDesc	");
        query.append("       ,CASE WHEN x.part_id IS NULL THEN x.pt_size ELSE y.pt_size END 	partSize	");
        query.append("       ,x.use_qty 						useQty			");
        query.append("		 ,dbo.SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+loginUser.getLangId()+"')	partGradeDesc	");
        query.append("       ,x.remark 							remark			");
        query.append("       ,x.part_id 						partId			");
        query.append("       ,x.wcode_id 						wcodeId			");
        query.append("       ,x.eqasmb_id 						eqasmbId		");
        query.append("       ,x.part_grade 						partGrade		");
        query.append("       ,y.model 							model			");
        query.append("FROM   TAWOPARTS x LEFT OUTER JOIN TAPARTS y				");
        query.append("ON x.comp_no = y.comp_no									");
        query.append("AND x.part_id = y.part_id									");
        query.append("WHERE  1=1												");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultPartListDTO,loginUser));
//        query.append("ORDER BY x.part_id									");
//        return getJdbcTemplate().queryForList(query.toString());
        query.getOrderByQuery("x.wopart_id", "x.part_id", maWoResultPartListDTO.getOrderBy(), maWoResultPartListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultPartListDTO.getIsLoadMaxCount(), maWoResultPartListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultPartListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePartList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	String woPartId=id;
    	
    	query.append("DELETE FROM TAWOPARTS							");
    	query.append("WHERE  wopart_id 		= '"+woPartId+"'		");
    	query.append("  AND  comp_no		= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultPartListDTO maWoResultPartListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maWoResultPartListDTO.getWoPartId()))
        {
            query.getAndQuery("x.wopart_id", maWoResultPartListDTO.getWoPartId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	public int updateEmgPart(String woPartId, String compNo) 
	{
		QuerySqlBuffer query = new QuerySqlBuffer();

		query.append("UPDATE TAPTEMGISSLIST SET            			");
		query.append("       ptemg_task_status      = ?,         	");
		query.append("       wkor_id          		= ?     		");
		query.append("WHERE  comp_no      			= ?         	");
		query.append("  AND  wopart_id       		= ?         	");

    	
    	Object[] objects = new Object[] {
    			"W",
    			"",
    			compNo,
    			woPartId
    	};
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
	}

	@Override
	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,
			MaWoResultPartListDTO maWoResultPartListDTO, User loginUser) throws Exception {
		
		QuerySqlBuffer query = new QuerySqlBuffer();
		
		query.append("SELECT					");
        query.append("       COUNT(1)           ");
        query.append("FROM   TAWOPARTS x LEFT OUTER JOIN TAPARTS y									");
        query.append("ON x.comp_no = y.comp_no									");
        query.append("AND x.part_id = y.part_id									");
        query.append("WHERE  1=1				");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultPartListDTO,loginUser));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
        
	}
}