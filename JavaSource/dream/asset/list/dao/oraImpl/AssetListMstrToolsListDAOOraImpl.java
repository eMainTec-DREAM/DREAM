package dream.asset.list.dao.oraImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.AssetListMstrToolsListDAO;
import dream.asset.list.dto.AssetListMstrToolsListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 구성자재 목록 dao
 * @author  kim21017
 * @version $Id: AssetListMstrToolsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="assetListMstrToolsListDAOTarget"
 * @spring.txbn id="assetListMstrToolsListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class AssetListMstrToolsListDAOOraImpl extends BaseJdbcDaoSupportOra implements AssetListMstrToolsListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: AssetListMstrToolsListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param maEqMstrCommonDTO
     * @param assetListMstrToolsListDTO
     * @param loginUser
     * @return List
     */
    public List findPartList(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("select                                                                              ");
        query.append("    ''                                                                    seqNo  	  ");
        query.append("    ,a.equip_id 															equipId	  ");
        query.append("    ,a.p_equip_id                                							pEquipId  ");
        query.append("    ,a.item_no                                                            toolNo    ");
        query.append("    ,a.description                                                        toolName  ");
        query.append("    ,SFACODE_TO_DESC(b.guage_type, 'GUAGE_TYPE','USR',b.comp_no, '"+loginUser.getLangId()+"') as gaugeType ");
        query.append("    ,a.maker                                                              maker     ");
        query.append("    ,a.model_no                                                           model     ");
        query.append("    ,(select aa.last_cplt_date from tapmequip aa where a.comp_no = aa.comp_no and a.equip_id = b.equip_id and rownum = 1) as LASTCPLTDATE ");
        query.append("    ,(select aa.next_plan_date from tapmequip aa where a.comp_no = aa.comp_no and a.equip_id = b.equip_id and rownum = 1) as NEXTPLANDATE ");
        query.append("from taequipment a inner join taeqtool b on a.comp_no =b.comp_no and a.equip_id = b.equip_id ");
        query.append("where 1=1                                                                                   ");
        query.append(this.getWhere(maEqMstrCommonDTO,assetListMstrToolsListDTO,loginUser));
        query.getOrderByQuery("a.item_no", assetListMstrToolsListDTO.getOrderBy(), assetListMstrToolsListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(assetListMstrToolsListDTO.getIsLoadMaxCount(), assetListMstrToolsListDTO.getFirstRow()));
    }


    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("a.p_equip_id", maEqMstrCommonDTO.getEquipId());
    	query.getAndQuery("a.comp_no", loginUser.getCompNo());
    	query.getAndQuery("a.is_last_version", "Y");
    	query.getAndQuery("a.is_deleted", "N");
    	
    	if (!"".equals(assetListMstrToolsListDTO.getEquipId()))
        {
            query.getAndQuery("a.equip_id", assetListMstrToolsListDTO.getEquipId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, AssetListMstrToolsListDTO assetListMstrToolsListDTO, User loginUser) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                    ");
        query.append("       COUNT(1)                                           ");
        query.append("from taequipment a inner join taeqtool b on a.comp_no =b.comp_no and a.equip_id = b.equip_id ");
        query.append("where 1=1                                                                                   ");
        query.append(this.getWhere(maEqMstrCommonDTO,assetListMstrToolsListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    } 
    
	public int[] deleteList(final List<AssetListMstrToolsListDTO> list, final User user) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("UPDATE TAEQUIPMENT set		");
		query.append("		p_equip_id = null 		");
    	query.append(" WHERE comp_no		= ?		");
    	query.append("   AND equip_id		= ?		");
    	
    	return getJdbcTemplate().batchUpdate(query.toString(), new BatchPreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				AssetListMstrToolsListDTO assetListMstrToolsListDTO = list.get(i);
				
				Object[] objects = new Object[] {
					     user.getCompNo()
                       , assetListMstrToolsListDTO.getEquipId()                                                                     
                };
				
				for(int j=0;j<objects.length;j++){
                    ps.setObject(j+1, objects[j]);
                }
			}
    		
			@Override
			public int getBatchSize() {
				return list.size();
			}
    	});
	}

}