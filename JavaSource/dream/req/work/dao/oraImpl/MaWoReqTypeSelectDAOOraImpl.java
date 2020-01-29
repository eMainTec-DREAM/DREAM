package dream.req.work.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.req.work.dao.MaWoReqTypeSelectDAO;
import dream.req.work.dto.MaWoReqTypeSelectDTO;

/**
 * 작업요청유형 선택팝업
 * @author  js.lee
 * @version $Id:$
 * @since   1.0
 *
 * @spring.bean id="maWoReqTypeSelectDAOTarget"
 * @spring.txbn id="maWoReqTypeSelectDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoReqTypeSelectDAOOraImpl extends BaseJdbcDaoSupportOra implements MaWoReqTypeSelectDAO
{
    /**
     * @author  js.lee
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findList(User loginUser, MaWoReqTypeSelectDTO maWoReqTypeSelectDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT '' seqNo,						");
        query.append("       cdsysd_no woReqType,			");
        query.append("       nvl((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+loginUser.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       woReqTypeDesc,										");
        query.append("       param1		param1,				");
        query.append("       param2     param2              ");
        query.append("FROM TACDSYSD x						");
        query.append("WHERE 1=1								");
        query.getStringEqualQuery("list_type", "WOREQ_TYPE");
        query.getStringEqualQuery("is_use", "Y");
        query.append("ORDER BY ord_no						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}