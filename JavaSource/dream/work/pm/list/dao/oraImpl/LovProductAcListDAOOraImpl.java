package dream.work.pm.list.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.work.pm.list.dao.LovProductAcListDAO;
import dream.work.pm.list.dto.LovProductAcListDTO;

/**
 * 积魂力前 扑诀
 * @author  youngjoo38
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovProductAcListDAOTarget"
 * @spring.txbn id="lovProductAcListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovProductAcListDAOOraImpl extends BaseJdbcDaoSupportOra implements LovProductAcListDAO
{
    /**
     * 八祸
     * @author  youngjoo38
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovProductAcListDTO
     * @param loginUser
     * @return
     */
    public List findProductAcList(LovProductAcListDTO lovProductAcListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("   x.comp_no      compNo          ");
        query.append(" , x.product_id   productId       ");
        query.append(" , x.product_no   productNo       ");
        query.append(" , x.description  productDesc     ");
        query.append(" , x.is_use       isUse           ");
        query.append(" , x.remark       remark          ");
        query.append("FROM TAPRODUCT x                  ");
        query.append("WHERE  1 = 1                      ");
        
        query.append("ORDER by product_no  ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
    }

	@Override
	public List findProductAcAcList(LovProductAcListDTO lovProductAcListDTO, User user, Map<String, String> conditionMap) {
		QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                            ");
        query.append("   x.comp_no      compNo          ");
        query.append(" , x.product_id   productId       ");
        query.append(" , x.product_no   productNo       ");
        query.append(" , x.description  productDesc     ");
        query.append(" , x.is_use       isUse           ");
        query.append(" , x.remark       remark          ");
        query.append("FROM TAPRODUCT x                  ");
        query.append("WHERE  1 = 1                      ");
        
        
        query.getAndQuery("x.comp_no", conditionMap);
        
        // 荤侩咯何
        query.getAndQuery("x.is_use", conditionMap);

        // 积魂力前疙
        query.getLikeQuery("x.description", lovProductAcListDTO.getProductDesc());
        
        // 积魂力前内靛
        query.getLikeQuery("x.product_no", lovProductAcListDTO.getProductNo());
                
        query.append("ORDER by product_no  ");
        
        return getJdbcTemplate().queryForList(query.toString());
        
	}
}