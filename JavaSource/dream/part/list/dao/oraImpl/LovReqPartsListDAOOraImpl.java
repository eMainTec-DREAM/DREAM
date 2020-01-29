package dream.part.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.part.list.dao.LovReqPartsListDAO;
import dream.part.list.dto.LovReqPartsListDTO;

/**
 * 자재검색 팝업
 * @author  kim21017
 * @version $Id: LovReqPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovReqPartsListDAOTarget"
 * @spring.txbn id="lovReqPartsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovReqPartsListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovReqPartsListDAO
{
    /**
     * 자재 검색
     * @author  kim21017
     * @version $Id: LovReqPartsListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovReqPartsListDTO
     * @param loginUser
     * @return
     */
    public List findPartsList(LovReqPartsListDTO lovReqPartsListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("       part_id partId,											");
        query.append("       part_no partNo,											");
        query.append("       description partDesc,										");
        query.append("       maker maker,												");
        query.append("       model model,												");
        query.append("       plf_type plfType,											");
        query.append("       SFACODE_TO_DESC(plf_type,'PLF_TYPE','SYS','','"+loginUser.getLangId()+"') plfTypeDesc,	");
        query.append("       pt_size ptSize												");
        query.append("FROM TAPARTS														");
        query.append("WHERE 1=1															");
        query.getAndQuery("part_categ", "SPPT");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        if(!"".equals(lovReqPartsListDTO.getDeptId())||!"".equals(lovReqPartsListDTO.getDeptDesc())){
        	query.append("AND part_id IN (SELECT part_id 										");
        	query.append("					FROM TAPTUSEDDEPT									");
        	query.append("					WHERE 1=1											");
        	query.getAndQuery("comp_no", loginUser.getCompNo());
        	query.getDeptLevelQuery("dept_id", lovReqPartsListDTO.getDeptId(),lovReqPartsListDTO.getDeptDesc(), loginUser.getCompNo());
        	query.append("					)													");
        }
        query.getAndQuery("part_no", lovReqPartsListDTO.getPartNo());
        query.getLikeQuery("full_desc", lovReqPartsListDTO.getPartDesc());
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}