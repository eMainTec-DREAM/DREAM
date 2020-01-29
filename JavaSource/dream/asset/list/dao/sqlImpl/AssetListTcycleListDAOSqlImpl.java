package dream.asset.list.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.list.dao.AssetListTcycleListDAO;
import dream.asset.list.dto.AssetListTcycleListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 교정주기 목록 dao
 * @author  kim21017
 * @version $Id: AssetListTcycleListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="assetListTcycleListDAOTarget"
 * @spring.txbn id="assetListTcycleListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetListTcycleListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetListTcycleListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: AssetListTcycleListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrCommonDTO
     * @param assetListTcycleListDTO
     * @param loginUser
     * @return List
     */
    public List findTcycleList(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT											");
        query.append("       ''						seqNo,				");
        query.append("       '' 					isDelCheck,			");
        query.append("       y.cycle	 			cycleDesc,			");
        query.append("		dbo.SFACODE_TO_DESC(y.period_type,'PERIOD_TYPE','SYS','','"+loginUser.getLangId()+"') 			periodType,	");
//        query.append("       y.init_wrk_date		initWrkDate,		");
        query.append("       (SELECT a.init_wrk_date FROM TAPMEQUIP a WHERE a.comp_no = y.comp_no AND a.pm_id = y.pm_id AND a.equip_id = x.equip_id AND a.is_deleted = 'N') initWrkDate,		");
        query.append("       y.wo_res_bef			woResBef,			");
        query.append("		 (SELECT description						");
        query.append("		  FROM TAWKCTR								");
        query.append("		  WHERE comp_no = x.comp_no					");
        query.append("		  AND wkctr_id = y.wkctr_id) wkCtrDesc,		");
        query.append("       y.is_active			isActive,			");
        query.append("       y.remark				remark,				");
        query.append("       y.pm_id 				pmId,				");
        query.append("       x.eqpmcycle_id			eqPmCycleId			");
        query.append("FROM   TAEQPMCYCLE x, TAPMLST y					");
        query.append("WHERE x.pm_id = y.pm_id							");
        query.append(this.getWhere(maEqMstrCommonDTO,assetListTcycleListDTO,loginUser));
        query.getOrderByQuery("x.eqpmcycle_id", "x.eqpmcycle_id", assetListTcycleListDTO.getOrderBy(), assetListTcycleListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetListTcycleListDTO.getIsLoadMaxCount(), assetListTcycleListDTO.getFirstRow()));
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: AssetListTcycleListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteTcycleList(String id, String compNo)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	int rtnValue = 0;
    	
    	query.append("DELETE FROM TAPMLST						");
    	query.append("WHERE  pm_id 			= (SELECT pm_id FROM TAEQPMCYCLE WHERE eqpmcycle_id = '"+id+"')	");
    	query.append("  AND  comp_no		= '"+compNo+"'		");
    	
    	rtnValue = getJdbcTemplate().update(query.toString());
    	
    	query = new QuerySqlBuffer();
    	
    	query.append("DELETE FROM TAEQPMCYCLE					");
    	query.append("WHERE  eqpmcycle_id 	= '"+id+"'			");
    	query.append("  AND  comp_no		= '"+compNo+"'		");
    	
    	rtnValue = getJdbcTemplate().update(query.toString());
    	
    	return rtnValue;
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO, User loginUser)
    {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	query.getAndQuery("x.equip_id", maEqMstrCommonDTO.getEquipId());
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("y.is_deleted", "N");
        
    	if (!"".equals(assetListTcycleListDTO.getEqPmCycleId()))
        {
            query.getAndQuery("x.eqpmcycle_id", assetListTcycleListDTO.getEqPmCycleId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListTcycleListDTO assetListTcycleListDTO, User loginUser) {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("FROM   TAEQPMCYCLE x, TAPMLST y					");
        query.append("WHERE x.pm_id = y.pm_id							");
        query.append(this.getWhere(maEqMstrCommonDTO,assetListTcycleListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QuerySqlBuffer.listToString(resultList);
    }   
}