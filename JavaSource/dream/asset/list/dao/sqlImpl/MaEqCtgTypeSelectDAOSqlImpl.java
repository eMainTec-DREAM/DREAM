package dream.asset.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class MaEqCtgTypeSelectDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaEqCtgTypeSelectDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT '' seqNo,						");
        query.append("       cdsysd_no eqCtgType,			");
        query.append("       ISNULL((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+loginUser.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       eqCtgTypeDesc,										");
        query.append("       param1		param1,				");
        query.append("       param2     param2              ");
        query.append("FROM TACDSYSD x						");
        query.append("WHERE 1=1								");
        query.getStringEqualQuery("list_type", "EQCTG_TYPE");
        query.getStringEqualQuery("is_use", "Y");
        query.append("ORDER BY ISNULL(ord_no, '99999999')						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}