package dream.work.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.work.list.dao.MaWoResultSelectDAO;
import dream.work.list.dto.MaWoResultSelectDTO;

/**
 * �۾� ����, ���� �����˾�
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="maWoResultSelectDAOTarget"
 * @spring.txbn id="maWoResultSelectDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaWoResultSelectDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaWoResultSelectDAO
{
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findWoTypeList(User loginUser, MaWoResultSelectDTO maWoResultSelectDTO)
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
        query.append("       param2     param2,             ");
        query.append("       remark     remark              ");
        query.append("FROM TACDSYSD x						");
        query.append("WHERE list_type='WO_TYPE'				");
        query.append("  AND is_use='Y'						");
        query.append("  AND cdsysd_no NOT IN('PMI', 'PMP', 'PMU', 'PMC') ");  //����, ������ �����Ѵ�.
        query.getAndQuery("param2", maWoResultSelectDTO.getParam2());
        query.append("ORDER BY ISNULL(ord_no, '99999999')						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param loginUser
     * @return
     */
    public List findTypeList(User loginUser, MaWoResultSelectDTO maWoResultSelectDTO)
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
        query.append("       param2     param2,             ");
        query.append("       remark     remark              ");
        query.append("FROM TACDSYSD x						");
        query.append("WHERE list_type='"+maWoResultSelectDTO.getSelectedPmType()+"'				");
        query.append("  AND is_use='Y'						");
        //PM�� ��� ����(INS)�� ������������.
        if("PM".equals(maWoResultSelectDTO.getParam2())){
            query.append("  AND 'INS' != cdsysd_no       	"); //������ �����Ѵ�.
        }
        query.append("ORDER BY ISNULL(ord_no, '99999999')						");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}