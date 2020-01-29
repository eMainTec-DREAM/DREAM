package dream.mgr.plant.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.mgr.plant.dao.MgrPlantMngListDAO;
import dream.mgr.plant.dto.MgrPlantMngCommonDTO;

/**
 * 공장설정 - 목록 dao
 * @author  euna0207
 * @version $Id: MgrPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
 * @since   1.0
 * @spring.bean id="mgrPlantMngListDAOTarget"
 * @spring.txbn id="mgrPlantMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MgrPlantMngListDAOSqlImpl extends BaseJdbcDaoSupportSql implements MgrPlantMngListDAO
{
    /**
     * grid find
     * @author  euna0207
     * @version $Id: MgrPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngCommonDTO
     * @return List
     */
    public List findPlantList(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        query.append("SELECT                   												");
        query.append("       ''                     					seqNo				");
        query.append("		 ,''                    					isDelCheck			");
        query.append("       ,x.plant               					plantNo     		");
        query.append("       ,x.plant_id               					plantId     		");
        query.append("       ,x.description         					plantDesc   		");
        query.append("       ,x.is_use              					isUse        		");
        query.append("FROM   TAPLANT x        												");
        query.append("WHERE  1=1               												");
        query.append(this.getWhere(mgrPlantMngCommonDTO, user));
        query.getOrderByQuery("x.plant_id","x.plant, x.comp_no", mgrPlantMngCommonDTO.getOrderBy(), mgrPlantMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(mgrPlantMngCommonDTO.getIsLoadMaxCount(), mgrPlantMngCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  euna0207
     * @version $Id: MgrPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 euna0207 Exp $
     * @since   1.0
     *
     * @param mgrPlantMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("x.comp_no", user.getCompNo());
        
        if(!"".equals(mgrPlantMngCommonDTO.getPlantId())){
     	   query.getAndQuery("x.plant_id", mgrPlantMngCommonDTO.getPlantId());
     	   return query.toString();
        }
        
        //공장명
        query.getLikeQuery("x.description", mgrPlantMngCommonDTO.getFilterPlantDesc());
        
        //공장코드 valid
        if(!"".equals(mgrPlantMngCommonDTO.getPlantIdValid()))
        	query.getLikeQuery("x.plant_id", "-"+mgrPlantMngCommonDTO.getPlantIdValid());
        
        //공장코드 valid
        query.getLikeQuery("x.plant", mgrPlantMngCommonDTO.getPlantNoValid());
         
        return query.toString();
    }

    /**
     * delete
     * @author euna0207
     * @version $Id: MgrPlantMngListDAO.java,v 1.0 20155/12/02 08:25:47 euna0207 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deletePlant(String plant, User user)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	String compNo = user.getCompNo();
    	
    	query.append("DELETE FROM TAPLANT				");
    	query.append("WHERE 1=1                  		");
    	query.append("   and comp_no = '"+compNo+"'		");
    	query.append("   and plant_id   = '"+plant+"'	");

    	return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public String findTotalCount(MgrPlantMngCommonDTO mgrPlantMngCommonDTO, User user) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                ");
        query.append("       COUNT(1)                       ");
        query.append("FROM   TAPLANT x        				");
        query.append("WHERE  1=1               				");
        query.append(this.getWhere(mgrPlantMngCommonDTO, user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
	}
}