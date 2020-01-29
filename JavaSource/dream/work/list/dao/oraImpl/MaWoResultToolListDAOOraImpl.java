package dream.work.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.list.dao.MaWoResultToolListDAO;
import dream.work.list.dto.MaWoResultMstrCommonDTO;
import dream.work.list.dto.MaWoResultToolListDTO;

/**
 * 작업결과 투입공기구 목록 dao
 * @author  kim21017
 * @version $Id: MaWoResultToolListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maWoResultToolListDAOTarget"
 * @spring.txbn id="maWoResultToolListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultToolListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoResultToolListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaWoResultToolListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maWoResultMstrCommonDTO
     * @param maWoResultToolListDTO
     * @param loginUser
     * @return List
     */
    public List findToolList(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT													");
        query.append("       '' 								isDelCheck		");
        query.append("       ,'' 								seqNo			");
        query.append("       ,x.wkor_id 						wkOrId			");
        query.append("       ,x.wotool_id 						woToolId		");
        query.append("       ,(SELECT wname										");
        query.append("         FROM TAWAREHOUSE									");
        query.append("         WHERE comp_no = x.comp_no						");
        query.append("          AND wcode_id = x.wcode_id) 		wcodeDesc		");
        query.append("       ,y.part_no 						partNo			");
        query.append("       ,CASE WHEN x.part_id IS NULL THEN x.pt_desc ELSE y.description END	partDesc	");
        query.append("       ,CASE WHEN x.part_id IS NULL THEN x.pt_size ELSE y.pt_size END		partSize	");
        query.append("       ,x.use_qty 						useQty			");
        query.append("       ,x.disusertn_qty 					disUseRtnQty	");
        query.append("		 ,SFACODE_TO_DESC(x.part_grade,'PART_GRADE','SYS','','"+loginUser.getLangId()+"')	partGradeDesc	");
        query.append("       ,x.remark 							remark			");
        query.append("       ,x.part_id 						partId			");
        query.append("       ,x.wcode_id 						wcodeId			");
        query.append("       ,x.part_grade 						partGradeId		");
        query.append("FROM   TAWOTOOLS x LEFT OUTER JOIN TAPARTS y				");
        query.append("ON x.comp_no = y.comp_no									");
        query.append("AND x.part_id = y.part_id									");
        query.append("WHERE  1=1												");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultToolListDTO,loginUser));
        
        query.getOrderByQuery("x.part_id", maWoResultToolListDTO.getOrderBy(), maWoResultToolListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maWoResultToolListDTO.getIsLoadMaxCount(), maWoResultToolListDTO.getFirstRow()));    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaWoResultToolListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteToolList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAWOTOOLS					");
    	query.append("WHERE  wotool_id 		= '"+id+"'		");
    	query.append("  AND  comp_no		= '"+compNo+"'	");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO, MaWoResultToolListDTO maWoResultToolListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.wkor_id", maWoResultMstrCommonDTO.getWkOrId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maWoResultToolListDTO.getWoToolId()))
        {
            query.getAndQuery("x.wotool_id", maWoResultToolListDTO.getWoToolId());
            return query.toString();
        }
    	
    	return query.toString();
    }

	@Override
	public String findTotalCount(MaWoResultMstrCommonDTO maWoResultMstrCommonDTO,
			MaWoResultToolListDTO maWoResultToolListDTO, User loginUser) throws Exception {
	    QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT	COUNT(1)								");
        query.append("FROM   TAWOTOOLS x LEFT OUTER JOIN TAPARTS y		");
        query.append("ON x.comp_no = y.comp_no							");
        query.append("AND x.part_id = y.part_id							");
        query.append("WHERE  1=1										");
        query.append(this.getWhere(maWoResultMstrCommonDTO,maWoResultToolListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);

	}
}