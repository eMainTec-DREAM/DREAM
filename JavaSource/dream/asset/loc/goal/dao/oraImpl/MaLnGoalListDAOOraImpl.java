package dream.asset.loc.goal.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.loc.goal.dao.MaLnGoalListDAO;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;

/**
 * 목록 dao
 * @author  
 * @version $Id: $
 * @since   1.0
 * @spring.bean id="maLnGoalListDAOTarget"
 * @spring.txbn id="maLnGoalListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLnGoalListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaLnGoalListDAO
{
    /**
     * grid find
     * @author  
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalCommonDTO
     * @return List
     */
    public List findList(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser)
    { 
        QueryBuffer query = new QueryBuffer();
       
        query.append("SELECT                                                            ");
        query.append("       '' seqNo,                                                  ");
        query.append("       '' isDelCheck,                                             ");
        query.append("       SFAPLANTTODESC(comp_no, plant) plant,                      ");
        query.append("       SUBSTR(yyyymm, 0, 4)||'-'||SUBSTR(yyyymm, 5, 2) yyyymm,    ");
        query.append("       (SELECT x.full_desc                                        ");
        query.append("        FROM   TAEQLOC x                                          ");
        query.append("        WHERE  x.eqloc_id = a.eqloc_id) eqlocId,                  ");
        query.append("       mtlnpoint,                                                           ");
        query.append("       SFACODE_TO_DESC(mtlnpoint,'MTLNPOINT','SYS','','"+loginUser.getLangId()+"') mtpointDesc,             ");
        query.append("       pvalue,                                                    ");
        query.append("       remark,                                                    ");
        query.append("       mtLnPoint_id mtLnPointId                                   ");
        query.append("FROM   TAMTLNPOINT a                                               ");
        query.append("WHERE  1=1                                                        ");
        query.append(this.getWhere(maLnGoalCommonDTO, loginUser));
    	query.append("ORDER  BY comp_no, plant, yyyymm, eqloc_id, mtlnpoint            		");
        
        return getJdbcTemplate().queryForList(query.toString());
    } 
    
    /**
     * Filter 조건
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalCommonDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser)
    {        
        QueryBuffer query = new QueryBuffer();
        if (!"".equals(maLnGoalCommonDTO.getMtLnPointId()))
        {
            query.getAndQuery("mtLnPoint_id", maLnGoalCommonDTO.getMtLnPointId());
            return query.toString();
        }
        
        query.getCodeLikeQuery("plant", "SFAPLANTTODESC(comp_no, plant)", maLnGoalCommonDTO.getPlant(), maLnGoalCommonDTO.getPlantDesc());
        
        //위치
        query.getEqLocLevelQuery("eqloc_id", maLnGoalCommonDTO.getEqlocId(), maLnGoalCommonDTO.getEqlocIdDesc(), loginUser.getCompNo());
        
        
        query.getAndDateQuery("yyyymm", maLnGoalCommonDTO.getStartYyyymm(), maLnGoalCommonDTO.getEndYyyymm());
        query.getAndQuery("mtlnpoint", maLnGoalCommonDTO.getGoalItem());
        return query.toString();
    }

    /**
     * 삭제
     * @author  ssong
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param partId
     * @return
     */
    public int deleteHeader(String key, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("DELETE TAMTLNPOINT                                              ");
        query.append("WHERE  comp_no   = ?                                           ");  
        query.append("    and mtLnPoint_id  = ?                                       ");  
        
        Object[] objects = new Object[] {  
        		loginUser.getCompNo(),
                key
                };
        
        return this.getJdbcTemplate().update(query.toString(), objects);
    }
}