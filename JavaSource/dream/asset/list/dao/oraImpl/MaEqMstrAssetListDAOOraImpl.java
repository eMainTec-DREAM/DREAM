package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrAssetListDAO;
import dream.asset.list.dto.MaEqMstrAssetListDTO;
import dream.asset.list.dto.MaEqMstrCommonDTO;

/**
 * 관련자산 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrAssetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrAssetListDAOTarget"
 * @spring.txbn id="maEqMstrAssetListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrAssetListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrAssetListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrAssetListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @return List
     */
    public List findAssetList(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO, User loginUser )
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT												");
        query.append("       '' 					seqNo,					");
        query.append("       '' isDelCheck,									");
        query.append("       x.equip_id 			equipId,				");
        query.append("       x.eqasset_id 			eqAssetId,				");
        query.append("       y.asset_no 			assetNo,				");
        query.append("       y.description 			assetDesc,				");
        query.append("       TO_CHAR(TO_DATE (y.acq_date,'yyyy-mm-dd'),'yyyy-mm-dd') 	acqDate,	");
        query.append("       TO_CHAR(y.buyer_amt,'FM999,999,999,999') 	buyerAmt,					");
        query.append("       TO_CHAR(y.dep_amt,'FM999,999,999,999') 	depAmt,						");
        query.append("       TO_CHAR(y.res_amt,'FM999,999,999,999') 	resAmt						");
        query.append("FROM   TAEQASSET x, TAASSET y							");
        query.append("WHERE  x.comp_no  = y.comp_no							");
        query.append("  AND  x.asset_id = y.asset_id						");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrAssetListDTO,loginUser));
        query.getOrderByQuery("y.description", maEqMstrAssetListDTO.getOrderBy(), maEqMstrAssetListDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maEqMstrAssetListDTO.getIsLoadMaxCount(), maEqMstrAssetListDTO.getFirstRow()));
    }
    
    private String getWhere(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO, User loginUser )
    {
    	QueryBuffer query = new QueryBuffer();
    	query.getAndQuery("x.equip_id", maEqMstrCommonDTO.getEquipId());
    	query.getAndQuery("x.comp_no", loginUser.getCompNo());
    	if (!"".equals(maEqMstrAssetListDTO.getEqAssetId()))
        {
            query.getAndQuery("x.eqasset_id", maEqMstrAssetListDTO.getEqAssetId());
            return query.toString();
        }
    	
    	return query.toString();
    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqMstrAssetListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deleteAssetList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();
    	
    	query.append("DELETE FROM TAEQASSET						");
    	query.append("WHERE  eqasset_id 	= '"+id+"'			");
    	query.append("  AND  comp_no		= '"+compNo+"'		");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public String findTotalCount(MaEqMstrCommonDTO maEqMstrCommonDTO, MaEqMstrAssetListDTO maEqMstrAssetListDTO,User loginUser) throws Exception {
		QueryBuffer query = new QueryBuffer();
		
		query.append("SELECT                            ");
        query.append("       COUNT(1)                   ");
        query.append("FROM   TAEQASSET x, TAASSET y		");
        query.append("WHERE  x.comp_no  = y.comp_no		");
        query.append("  AND  x.asset_id = y.asset_id	");
        query.append(this.getWhere(maEqMstrCommonDTO,maEqMstrAssetListDTO,loginUser));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}