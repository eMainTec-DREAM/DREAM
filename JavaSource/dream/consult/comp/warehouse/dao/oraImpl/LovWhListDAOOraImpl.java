package dream.consult.comp.warehouse.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.warehouse.dao.LovWhListDAO;
import dream.consult.comp.warehouse.dto.LovWhListDTO;

/**
 * 사용창고 팝업
 * @author  kim21017
 * @version $Id: LovWhListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="lovWhListDAOTarget"
 * @spring.txbn id="lovWhListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovWhListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovWhListDAO
{
    /**
     * 사용창고 검색
     * @author  kim21017
     * @version $Id: LovWhListDAO.java,v 1.0 2016/01/18 00:16:44 kim21017 Exp $
     * @since   1.0
     * 
     * @param lovWhListDTO
     * @param loginUser
     * @return
     */
    public List findWhList(LovWhListDTO lovWhListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															                                                       ");
        query.append("       wcode_id AS wcodeId,									                                                           ");
        query.append("       wcode AS wcode,									                                                               ");
        query.append("       wname AS wname,									                                                               ");
        query.append("       SFACODE_TO_DESC(wh_type,'WH_TYPE','SYS','','"+loginUser.getLangId()+"') AS whTypeDesc,	");
        query.append("       remark AS remark,									                                                               ");
        query.append("       out_wcode outWcode,                                                                                               ");
        query.append("       out_plant outPlant,                                                                                               ");
        query.append("       wh_type whType,                                                                                                   ");
        query.append("       plant AS plantId,                                                                                                 ");
        query.append("       (SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant=a.plant) plantDesc                           ");
        query.append("FROM TAWAREHOUSE	a									                                                                   ");
        query.append("WHERE 1=1														                                                           ");
        query.getAndQuery("comp_no", loginUser.getCompNo());
        query.getAndQuery("wh_categ", "PART");
        query.getLikeQuery("wcode", lovWhListDTO.getWcode());
        query.getLikeQuery("wname", lovWhListDTO.getWname());
        query.getLikeQuery("plant", lovWhListDTO.getPlant());
        query.getLikeQuery("is_use", lovWhListDTO.getIsUse());
        
        return getJdbcTemplate().queryForList(query.toString());
    }

    @Override
    public List findWhACList(LovWhListDTO lovWhListDTO, User user,
            Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                                                                 ");
        query.append("       wcode_id AS wcode_id,                                                                                           ");
        query.append("       wcode AS wcode,                                                                                                 ");
        query.append("       wname AS wname,                                                                                                 ");
        query.append("       remark AS remark,                                                                                               ");
        query.append("       wh_type wh_type,                                                                                                ");
        query.append("       plant AS plantId,                                                                                               ");
        query.append("       (SELECT description FROM TAPLANT WHERE comp_no = a.comp_no AND plant=a.plant) plantDesc                         ");
        query.append("FROM TAWAREHOUSE   a                                                                                                   ");
        query.append("WHERE 1=1                                                                                                              ");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("wh_categ", conditionMap);
        query.getAndQuery("wh_type", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getAndQuery("plant", conditionMap);
        query.getLikeQuery("wcode", lovWhListDTO.getWcode());
        query.getLikeQuery("wname", lovWhListDTO.getWname());
        query.getCodeLikeQuery("plant", "(SELECT description FROM TAPLANT WHERE comp_no=a.comp_no AND plant=a.plant)", lovWhListDTO.getPlant(), lovWhListDTO.getPlantDesc());
        query.getSysCdQuery("is_use", lovWhListDTO.getIsUse(), lovWhListDTO.getIsUse(), "IS_USE", user.getCompNo(),user.getLangId());
        query.getOrderByQuery("wcode_id", lovWhListDTO.getOrderBy(), lovWhListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(lovWhListDTO.getIsLoadMaxCount(), lovWhListDTO.getFirstRow()));
    }

	@Override
	public String findTotalCount(LovWhListDTO lovWhListDTO, User user, Map<String, String> conditionMap)
	{
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT								");
        query.append("       COUNT(1)                  		");
        query.append("FROM TAWAREHOUSE  a                  	");
        query.append("WHERE 1=1                             ");
        query.getAndQuery("comp_no", conditionMap);
        query.getAndQuery("wh_categ", conditionMap);
        query.getAndQuery("wh_type", conditionMap);
        query.getAndQuery("plant", conditionMap);
        query.getAndQuery("is_use", conditionMap);
        query.getLikeQuery("wcode", lovWhListDTO.getWcode());
        query.getLikeQuery("wname", lovWhListDTO.getWname());
        query.getSysCdQuery("is_use", lovWhListDTO.getIsUse(), lovWhListDTO.getIsUse(), "IS_USE", user.getCompNo(),user.getLangId());
        query.getCodeLikeQuery("plant", "(SELECT description FROM TAPLANT WHERE comp_no=a.comp_no AND plant=a.plant)", lovWhListDTO.getPlant(), lovWhListDTO.getPlantDesc());
        
        List resultList = getJdbcTemplate().queryForList(query.toString());
        
		return QueryBuffer.listToString(resultList);
	}
}