package dream.asset.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.AssetListITSWListDAO;
import dream.asset.list.dto.AssetListITSWListDTO;

/**
 * List DAO implements
 * @author youngjoo38
 * @version $Id$
 * @since 1.0
 * 
 * @spring.bean id="assetListITSWListDAOTarget"
 * @spring.txbn id="assetListITSWListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetListITSWListDAOSqlImpl  extends BaseJdbcDaoSupportSql implements AssetListITSWListDAO
{

    /**
     * grid find
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param assetListITSWListDTO
     * @return List
     */
    public List findList(AssetListITSWListDTO assetListITSWListDTO, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                                    		");
		query.append("       ''                 seqNo               ");
		query.append("     , ''                 isDelCheck          ");
		query.append("     , x.eqitinstsw_id	eqItInstSwId	    ");
		query.append("     , x.sw_category      swCategoryId	    ");
		query.append("     ,(SELECT a.description FROM TACDUSRD a	");
		query.append("       WHERE 1=1								");
		query.append("       AND a.comp_no = '"+user.getCompNo()+"'	");
		query.append("       AND a.cdusrm_id = ( SELECT b.cdusrm_id FROM TACDUSRM b WHERE b.dir_type = 'SW_CATEGORY')		");
		query.append("		 AND a.cdusrd_no = x.sw_category		");
		query.append("        				  ) swCategory			");
		query.append("     , x.sw_name          swName		        ");
		query.append("     , x.sw_ver           swVer		        ");
		query.append("     , x.install_date     installDate	        ");
		query.append("     , x.remark           remark		        ");
		query.append("FROM TAEQITINSTSW x					        ");
		query.append("WHERE  1=1                                    ");
    	query.append(this.getWhere(assetListITSWListDTO, user));
        query.getOrderByQuery("x.eqitinstsw_id", "x.sw_name", assetListITSWListDTO.getOrderBy(), assetListITSWListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetListITSWListDTO.getIsLoadMaxCount(), assetListITSWListDTO.getFirstRow()));

    }

    private String getWhere(AssetListITSWListDTO assetListITSWListDTO, User user)
    {        
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.equip_id", assetListITSWListDTO.getItEqId());
        
        if (!"".equals(assetListITSWListDTO.getEqItInstSwId()))
        {
        	query.getAndQuery("x.eqitinstsw_id", assetListITSWListDTO.getEqItInstSwId());
        }

        return query.toString();
    }
    
    /**
     * Filter Á¶°Ç
     * @author  youngjoo38
     * @version $Id$
     * @since   1.0
     * 
     * @param assetListITSWListDTO
     * @return
     * @throws Exception
     */
    public int deleteList(String id, User user) throws Exception
    {
        QuerySqlBuffer query = new QuerySqlBuffer();

        query.append("DELETE FROM TAEQITINSTSW   ");
        query.append("WHERE eqitinstsw_id = ?    ");
        query.append("  AND comp_no = ?          ");
        
        Object[] objects = new Object[] {   
                 id
                ,user.getCompNo()
        };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
    public String findTotalCount(AssetListITSWListDTO assetListITSWListDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAEQITINSTSW x                                     ");
        query.append("WHERE  1=1                                                ");
        query.append(this.getWhere(assetListITSWListDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }    
}
