package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqCtgTypeSelectDAO;
import dream.asset.list.dto.MaEqCtgTypeSelectDTO;

/**
 * 설비유형 선택팝업
 * @author  kim21017
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="maEqCtgTypeSelectDAOTarget"
 * @spring.txbn id="maEqCtgTypeSelectDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCtgTypeSelectDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqCtgTypeSelectDAO
{
    /**
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findList(User loginUser, MaEqCtgTypeSelectDTO maEqCtgTypeSelectDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT '' seqNo,						");
        query.append("       cdsysd_no eqCtgType,			");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+loginUser.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       eqCtgTypeDesc,										");
        query.append("       param1		param1,				");
        query.append("       param2     param2              ");
        query.append("FROM TACDSYSD x						");
        query.append("WHERE 1=1								");
        query.append("  AND cdsysd_no <> 'PT'				");
        query.getStringEqualQuery("list_type", "EQCTG_TYPE");
        query.getStringEqualQuery("is_use", "Y");
        query.append("ORDER BY ord_no						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}