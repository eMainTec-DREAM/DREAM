package dream.asset.rpt.lcc.loc.dao.sqlImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QueryBuffer;
import common.util.QuerySqlBuffer;
import dream.asset.rpt.lcc.loc.dao.AssetRptLccLocListDAO;
import dream.asset.rpt.lcc.loc.dto.AssetRptLccLocCommonDTO;

/**
 * 고장TOP(위치) 목록 dao
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="assetRptLccLocListDAOTarget"
 * @spring.txbn id="assetRptLccLocListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetRptLccLocListDAOSqlImpl extends BaseJdbcDaoSupportSql implements AssetRptLccLocListDAO
{
    /**
     * grid find
     * @author  ghlee
     * @version $Id:$
     * @since   1.0
     * 
     * @param assetRptLccLocCommonDTO
     * @param loginUser
     * @return List
     */
    public List findList(AssetRptLccLocCommonDTO assetRptLccLocCommonDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        
        query.append("SELECT 										");
        query.append("    ''     		  SEQNO  				    ");
        query.append("    ,''     		  ISDELCHECK     			");
        query.append("    ,x.eqloc_id     eqLocId                   ");
        query.append("    ,x.full_desc	  eqLocDesc                 ");
        query.append("            ,(SELECT COUNT(1) 		        ");
        query.append("             FROM TAEQHISTORY a 		        ");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' 		");
        query.append("             WHERE eqloc_id IN (													");
        query.append("                                   SELECT eqloc_id                                		");
        query.append("                                    FROM (select * from dbo.SFAEQLOC_CALL('"+loginUser.getCompNo()+"', x.eqloc_id, '')) a where a.comp_no = x.comp_no		");
        query.append("                                          )			");											
        query.append(this.getWhere(assetRptLccLocCommonDTO,loginUser));
        query.append("            ) bdTimeFreq															");
        query.append("            ,(SELECT isnull(SUM(a.work_time),0) 										");
        query.append("             FROM TAEQHISTORY a 													");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' 		");
        query.append("             WHERE eqloc_id IN (													");
        query.append("                                   SELECT eqloc_id                                		");
        query.append("                                    FROM (select * from dbo.SFAEQLOC_CALL('"+loginUser.getCompNo()+"', x.eqloc_id, '')) a where a.comp_no = x.comp_no		");
        query.append("                                          )			");				
        query.append(this.getWhere(assetRptLccLocCommonDTO,loginUser));
        query.append("            ) woTimeMin								");
        query.append("            ,(SELECT isnull(SUM(a.tot_amt),0) 			");
        query.append("             FROM TAEQHISTORY a 						");
        query.append("             INNER JOIN TAEQUIPMENT b ON a.item_no = b.item_no AND b.is_deleted ='N' 	");
        query.append("             WHERE eqloc_id IN (													");
        query.append("                                   SELECT eqloc_id                                		");
        query.append("                                    FROM (select * from dbo.SFAEQLOC_CALL('"+loginUser.getCompNo()+"', x.eqloc_id, '')) a where a.comp_no = x.comp_no		");
        query.append("                                          )			");														
        query.append(this.getWhere(assetRptLccLocCommonDTO,loginUser));
        query.append("            ) maintAmt            													");
        query.append("FROM TAEQLOC x 		");
        query.append("WHERE 1=1 			");
        query.append("AND x.is_use ='Y'		");
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", assetRptLccLocCommonDTO.getFilterPlantId(), assetRptLccLocCommonDTO.getFilterPlantDesc());
        query.getOrderByQuery("x.eqloc_id", "x.full_desc DESC", assetRptLccLocCommonDTO.getOrderBy(), assetRptLccLocCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetRptLccLocCommonDTO.getIsLoadMaxCount(), assetRptLccLocCommonDTO.getFirstRow()));
    }
    
    public String getWhere(AssetRptLccLocCommonDTO assetRptLccLocCommonDTO,User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.getAndQuery("a.comp_no", loginUser.getCompNo());
        query.getAndQuery("b.is_last_version", "Y");
        
        query.append("AND a.wo_type='BM'        ");    
        
        query.append("AND a.wkor_date IS NOT NULL      ");
        
        String fromDate = assetRptLccLocCommonDTO.getFilterStartDate()+"01";
        String toDate   = assetRptLccLocCommonDTO.getFilterEndDate()+"31";
        
        query.getAndDateQuery("a.wkor_date", fromDate, toDate);
        
        query.getEqLocLevelQuery("b.eqloc_id", assetRptLccLocCommonDTO.getFilterEqLocId(), assetRptLccLocCommonDTO.getFilterEqLocDesc(), loginUser.getCompNo());
        //query.getSysCdQuery("b.eqloc_lvl_type", assetRptLccLocCommonDTO.getFilterEqLocLevel(), assetRptLccLocCommonDTO.getFilterEqLocLevelDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        
        //공장코드
        query.getCodeLikeQuery("a.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", 
                assetRptLccLocCommonDTO.getFilterPlantId(), assetRptLccLocCommonDTO.getFilterPlantDesc());
        
        return query.toString();
    }

    @Override
    public String findTotalCount(AssetRptLccLocCommonDTO assetRptLccLocCommonDTO, User user)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT 								");
        query.append("      count(1)          													");
        query.append("FROM TAEQLOC x 		");
        query.append("WHERE 1=1 			");
        query.append("AND x.is_use ='Y'		");
        query.getCodeLikeQuery("x.plant", "(SELECT description FROM  TAPLANT WHERE comp_no = a.comp_no AND plant = a.plant )", assetRptLccLocCommonDTO.getFilterPlantId(), assetRptLccLocCommonDTO.getFilterPlantDesc());
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }
    
}