package dream.work.pm.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.pm.list.dao.MaPmMstrSelectDAO;
import dream.work.pm.list.dto.MaPmMstrSelectDTO;

/**
 * 작업 형태 선택팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maPmMstrSelectDAOTarget"
 * @spring.txbn id="maPmMstrSelectDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPmMstrSelectDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaPmMstrSelectDAO
{
    /**
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findWoTypeList(User loginUser, MaPmMstrSelectDTO maPmMstrSelectDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT '' woTypeSeqNo,				");
        query.append("       cdsysd_no woType,				");
        query.append("       ISNULL((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+loginUser.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       woTypeDesc,										");
        query.append("       param1		param,				");
        query.append("       param2     param2              ");
        query.append("FROM TACDSYSD x						");
        query.append("WHERE list_type='WO_TYPE'				");
        query.append("  AND is_use='Y'						");
        query.append("  AND param2 ='PM'				    ");  // 작업유형중에서 PM항목만 올린다.
        query.append("ORDER BY ISNULL(ord_no, '99999999')						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    
    public List findPmTypeList(User loginUser, MaPmMstrSelectDTO maPmMstrSelectDTO)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	
    	query.append("SELECT '' pmTypeSeqNo,				");
        query.append("       cdsysd_no pmType,				");
        query.append("       ISNULL((select aa.key_name							");
        query.append("            from talang aa								");
        query.append("            where  lang = '"+loginUser.getLangId()+"'		");
        query.append("            and x.key_type = aa.key_type					");
        query.append("            and x.key_no = aa.key_no), x.description)		");
        query.append("       pmTypeDesc,										");
        query.append("       param1		param,   			");
        query.append("       param2     param2              ");
        query.append("FROM TACDSYSD x						");
        query.append("WHERE list_type='"+maPmMstrSelectDTO.getSelectedPmType()+"'				");
        query.append("  AND is_use='Y'						");
        query.append("ORDER BY ISNULL(ord_no, '99999999')						");
    	
    	return getJdbcTemplate().queryForList(query.toString());
    }
}