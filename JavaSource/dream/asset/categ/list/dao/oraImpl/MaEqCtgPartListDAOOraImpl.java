package dream.asset.categ.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.categ.list.dao.MaEqCtgPartListDAO;
import dream.asset.categ.list.dto.MaEqCatalogCommonDTO;
import dream.asset.categ.list.dto.MaEqCtgPartListDTO;

/**
 * 설비종류별 부품 목록 dao
 * @author  kim21017
 * @version $Id: MaEqCtgPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqCtgPartListDAOTarget"
 * @spring.txbn id="maEqCtgPartListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqCtgPartListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqCtgPartListDAO
{
    
    protected String eqCtgIdTemp = "";
    
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqCtgPartListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param eqCtgId
     * @param compNo
     * @return List
     */
    public List findPartList(MaEqCatalogCommonDTO maEqCatalogCommonDTO, MaEqCtgPartListDTO maEqCtgPartListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT															");
        query.append("		  ''			 							seqNo			");
        query.append("		, '' 										isDelCheck		");
        query.append("		, x.eqctg_id 								eqCtgId			");
        query.append("		, x.eq_ctg_part_id 							eqCtgPartId 	");
        query.append("		, x.part_id				                    partId			");
        query.append("		, y.part_no				                    partNo			");
        query.append("		, y.description||', '||y.pt_size			partNameSize	");
        query.append("		, y.description				                partDesc		");
        query.append("		, y.pt_size				                    ptSize		    ");
        query.append("		, y.model				                    model		    ");
        query.append("		, (SELECT description										");
        query.append("		   FROM TAEQCTGASMB											");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eq_ctg_asmb_id = x.eq_ctg_asmb_id)	eqCtgAsmbDesc	");
        query.append("		, x.use_qty									useQty			");
//        query.append("      , x.cycle||x.period_type                  	cycle           ");
        query.append("      , (SELECT SFACODE_TO_DESC(x.schedule_type, 'SCHEDULE_TYPE', 'SYS', '"+user.getCompNo()+"','"+user.getLangId()+"') FROM DUAL)    scheduleType		");
        query.append("       , CASE x.schedule_type 									");
        query.append("         WHEN 'T' THEN x.CYCLE||x.period_type 					");
        query.append("         ELSE TO_CHAR(x.USAGE, '999,999,999,999,999') 			");
        query.append("          END                   					CYCLE			");
        query.append("		, x.ord_no 									ordNo			");
        query.append("		, x.is_use 									isUse			");
        query.append("      , x.eq_ctg_part_id 							id      		");
        query.append("      , x.eq_ctg_asmb_id                          eqCtgAsmbId     ");
        query.append("      , x.is_eqtype_part                          isEqTypePart    ");
        query.append("FROM   TAEQCTGPART x LEFT OUTER JOIN TAPARTS y					");
        query.append("ON x.comp_no = y.comp_no											");
        query.append("AND x.part_id = y.part_id											");
        query.append("WHERE  1=1														");
        
        query.getAndNumKeyQuery("x.eqctg_id", maEqCatalogCommonDTO.getEqCtgId());
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        if (!"".equals(maEqCtgPartListDTO.getEqCtgPartId()))
        {
            query.getAndQuery("x.eq_ctg_part_id", maEqCtgPartListDTO.getEqCtgPartId());
        }
        
        query.getOrderByQuery("x.ord_no", maEqCtgPartListDTO.getOrderBy(), maEqCtgPartListDTO.getDirection());

        eqCtgIdTemp = maEqCatalogCommonDTO.getEqCtgId();
        
        return getJdbcTemplate().queryForList(query.toString(maEqCtgPartListDTO.getIsLoadMaxCount(), maEqCtgPartListDTO.getFirstRow()));

    }
    
    /**
     * delete
     * @author kim21017
     * @version $Id: MaEqCtgPartListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param id
     * @param compNo
     * @return
     */
    public int deletePartList(String id, String compNo)
    {
    	QueryBuffer query = new QueryBuffer();

    	String eqCtnPartId=id;
    	
    	query.append("DELETE FROM TAEQCTGPART							");
    	query.append("WHERE  eq_ctg_part_id 	= '"+eqCtnPartId+"'		");
    	query.append("  AND  comp_no		 	= '"+compNo+"'			");
    	
    	return this.getJdbcTemplate().update(query.toString());
    }
    
    private String getWhere(MaEqCtgPartListDTO maEqCtgPartListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.getAndNumKeyQuery("x.eqctg_id", eqCtgIdTemp);
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
        
        return query.toString();
    }
    
    public String findTotalCount(
            MaEqCtgPartListDTO maEqCtgPartListDTO, User user) {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                            ");
        query.append("       COUNT(1)                                   ");
        query.append("FROM   TAEQCTGPART x LEFT OUTER JOIN TAPARTS y    ");
        query.append("ON x.comp_no = y.comp_no                          ");
        query.append("AND x.part_id = y.part_id                         ");
        query.append("WHERE  1=1                                        ");
        query.append(this.getWhere(maEqCtgPartListDTO,user));
        
        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
    }   
}