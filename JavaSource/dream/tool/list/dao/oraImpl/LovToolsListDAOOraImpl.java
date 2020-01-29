package dream.tool.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.list.dto.LovPartsListDTO;
import dream.tool.list.dao.LovToolsListDAO;
import dream.tool.list.dto.LovToolsListDTO;
import dream.tool.list.form.LovToolsListForm;

/**
 * 자재검색 팝업
 * @author  kim21017
 * @version $Id: LovToolsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovToolsListDAOTarget"
 * @spring.txbn id="lovToolsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovToolsListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovToolsListDAO
{
    /**
     * 자재 검색
     * @author  kim21017
     * @version $Id: LovToolsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovToolsListDTO
     * @param loginUser
     * @return
     */
    public List findPartsList(LovToolsListDTO lovToolsListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("       part_id partId,											");
        query.append("       part_no partNo,											");
        query.append("       description||', '||pt_size partDesc,						");
        query.append("       maker maker,												");
        query.append("       model model,												");
        query.append("       plf_type plfType,											");
        query.append("       SFACODE_TO_DESC(plf_type,'PLF_TYPE','SYS','','"+loginUser.getLangId()+"') plfTypeDesc	");
        query.append("FROM TAPARTS														");
        query.append("WHERE 1=1															");
        query.getAndQuery("part_categ", "TOOL");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        if(!"".equals(lovToolsListDTO.getDeptId())||!"".equals(lovToolsListDTO.getDeptDesc())){
        	query.append("AND part_id IN (SELECT part_id 										");
        	query.append("					FROM TAPTUSEDDEPT									");
        	query.append("					WHERE 1=1											");
        	query.getAndQuery("comp_no", loginUser.getCompNo());
        	query.getDeptLevelQuery("dept_id", lovToolsListDTO.getDeptId(),lovToolsListDTO.getDeptDesc(), loginUser.getCompNo());
        	query.append("					)													");
        }
        query.getLikeQuery("part_no", lovToolsListDTO.getPartNo());
        query.getLikeQuery("full_desc", lovToolsListDTO.getPartDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public List findToolAcList(LovToolsListDTO lovToolsListDTO, User loginUser, Map<String, String> conditionMap) {
		QueryBuffer query = new QueryBuffer();

    	query.append("SELECT															");
        query.append("       x.part_id 			AS part_id								");
        query.append("       ,x.part_no 		AS part_no								");
        query.append("       ,x.description 	AS description							");
        query.append("       ,x.pt_size			AS pt_size								");
        query.append("       ,x.maker 			AS maker								");
        query.append("       ,x.model 			AS model								");
        query.append("FROM TAPARTS x													");
        query.append("WHERE 1=1															");
        query.append(this.getWhere(lovToolsListDTO, loginUser, conditionMap));

        query.getOrderByQuery("x.part_no", lovToolsListDTO.getOrderBy(), lovToolsListDTO.getDirection());

        return getJdbcTemplate().queryForList(query.toString(lovToolsListDTO.getIsLoadMaxCount(), lovToolsListDTO.getFirstRow()));

	}

	@Override
	public String findTotalCount(LovToolsListDTO lovToolsListDTO, User loginUser, Map<String, String> conditionMap)
			throws Exception {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                        ");
        query.append("       COUNT(1)                                               ");
        query.append("FROM   TAPARTS x                                              ");
        query.append("WHERE  1=1                                                    ");
        query.append(this.getWhere(lovToolsListDTO, loginUser, conditionMap));
        query.append("ORDER BY 1                                                    ");
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
	
	private String getWhere(LovToolsListDTO lovToolsListDTO, User loginUser, Map<String, String> conditionMap)
    {        
        QueryBuffer query = new QueryBuffer();
        
        query.getAndQuery("part_categ", "TOOL");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("is_deleted", "N");
        query.getLikeQuery("part_no", lovToolsListDTO.getPartNo());
        query.getLikeQuery("description", lovToolsListDTO.getPartDesc());
        
        return query.toString();
    }
}