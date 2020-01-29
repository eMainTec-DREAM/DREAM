package dream.consult.comp.plant.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.consult.comp.plant.dao.MaPlantMngListDAO;
import dream.consult.comp.plant.dto.MaPlantMngCommonDTO;

/**
 * 회사설정 - 목록 dao
 * @author  kim21017
 * @version $Id: MaPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maPlantMngListDAOTarget"
 * @spring.txbn id="maPlantMngListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaPlantMngListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaPlantMngListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngCommonDTO
     * @return List
     */
    public List findPlantList(MaPlantMngCommonDTO maPlantMngCommonDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();

        query.append("SELECT                   												");
        query.append("       ''                     					seqNo				");
        query.append("		 ,''                    					isDelCheck			");
        query.append("       ,x.comp_no             					compNo      		");
        query.append("       ,(select aa.description                            			");
        query.append("        from tacomp aa                                    			");
        query.append("        where x.comp_no = aa.comp_no) 			as compDesc         ");
        query.append("       ,x.plant               					plantNo     		");
        query.append("       ,x.plant_id               					plantId     		");
        query.append("       ,x.description         					plantDesc   		");
        query.append("       ,x.is_use              					isUse        		");
        query.append("       ,(SELECT description FROM TAWRKCALLIST 						");
        query.append("         WHERE comp_no = x.comp_no 									");
        query.append("            AND wrkcallist_id = x.wrkcallist_id)	wrkCalListDesc 		");
        query.append("FROM   TAPLANT x        												");
        query.append("WHERE  1=1               												");
        query.append(this.getWhere(maPlantMngCommonDTO));
        query.getOrderByQuery("x.comp_no", maPlantMngCommonDTO.getOrderBy(), maPlantMngCommonDTO.getDirection());
        
        return getJdbcTemplate().queryForList(query.toString(maPlantMngCommonDTO.getIsLoadMaxCount(), maPlantMngCommonDTO.getFirstRow()));
    }

    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaPlantMngListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     *
     * @param maPlantMngCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaPlantMngCommonDTO maPlantMngCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();

        if (!"".equals(maPlantMngCommonDTO.getCompNo()) && !"".equals(maPlantMngCommonDTO.getPlantId()))
        {
        	query.getAndQuery("x.plant_id", maPlantMngCommonDTO.getPlantId());
        	query.getAndQuery("x.comp_no", maPlantMngCommonDTO.getCompNo());
            return query.toString();
        }

        if(!"".equals(maPlantMngCommonDTO.getPlantNo())){
     	   query.getAndQuery("upper(x.plant)", maPlantMngCommonDTO.getPlantNo().toUpperCase());
     	   return query.toString();
        }
        query.getAndQuery("x.comp_no", maPlantMngCommonDTO.getFilterCompNo());
        query.getLikeQuery("x.description", maPlantMngCommonDTO.getFilterPlantNo());
        
        return query.toString();
    }

    /**
     * delete
     * @author kim21017
     * @version $Id: MaPlantMngListDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     *
     * @param id
     * @return
     */
    public int deletePlant(String compNo, String plant, User user)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("DELETE FROM TAPLANT				");
    	query.append("WHERE 1=1                  		");
    	query.append("   and comp_no = '"+compNo+"'		");
    	query.append("   and plant_id   = '"+plant+"'	");

    	return this.getJdbcTemplate().update(query.toString());
    }

	@Override
	public String findTotalCount(MaPlantMngCommonDTO maPlantMngCommonDTO, User user) {
		QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT                   												");
        query.append("       COUNT(1)                     									");
        query.append("FROM   TAPLANT x        												");
        query.append("WHERE  1=1               												");
        query.append(this.getWhere(maPlantMngCommonDTO));

        List resultList=  getJdbcTemplate().queryForList(query.toString());
        return QueryBuffer.listToString(resultList);
	}
}