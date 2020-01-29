package dream.mgr.cal.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.mgr.cal.dao.MgrCalCompWkrcalDowsetListDAO;
import dream.mgr.cal.dto.MgrCalCompWkrcalCommonDTO;
import dream.mgr.cal.dto.MgrCalCompWkrcalDowsetListDTO;

/**
 * �޹����� ����  - ��� dao
 * @author  euna0207
 * @version $Id: MgrCalCompWkrcalDowsetListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrCalCompWkrcalDowsetListDAOTarget"
 * @spring.txbn id="mgrCalCompWkrcalDowsetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrCalCompWkrcalDowsetListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrCalCompWkrcalDowsetListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrCalCompWkrcalDowsetListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrCalCompWkrcalCommonDTO
     * @return List
     */
    public List findDowsetList(MgrCalCompWkrcalCommonDTO mgrCalCompWkrcalCommonDTO, MgrCalCompWkrcalDowsetListDTO mgrCalCompWkrcalDowsetListDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("SELECT                   						");
        query.append("       ''                     seqNo,			");
        query.append("		 ''                    	isDelCheck,		");
        query.append("       dbo.SFACODE_TO_DESC(x.dow,'DOW','SYS','','"+mgrCalCompWkrcalCommonDTO.getUserLang()+"')	dow,	");
        query.append("       x.is_off				isOff,			");
        query.append("       x.wrkcaldowset_id  	wrkcaldowsetId	");
        query.append("FROM   TAWRKCALDOWSET x        				");
        query.append("WHERE  1=1               						");
        query.getLikeQuery("x.wrkcallist_id", mgrCalCompWkrcalCommonDTO.getWrkcalListId());
        query.getAndQuery("x.wrkcaldowset_id", mgrCalCompWkrcalDowsetListDTO.getWrkcalDowsetId());
        query.append("ORDER by x.dow						 		");

        return getJdbcTemplate().queryForList(query.toString());
    }

    /**
     * delete
     * @author euna0207
     * @version $Id: MgrCalCompWkrcalDowsetListDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deleteWrkcal(String id)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();

    	query.append("DELETE FROM TAWRKCALDOWSET			");
    	query.append(" WHERE 1=1                  			");
    	query.append("	 AND wrkcaldowset_id  = '"+id+"'	");

    	return this.getJdbcTemplate().update(query.toString());
    }
}