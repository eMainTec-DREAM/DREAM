package dream.asset.loc.goal.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.loc.goal.dao.MaLnGoalDetailDAO;
import dream.asset.loc.goal.dto.MaLnGoalCommonDTO;
import dream.asset.loc.goal.dto.MaLnGoalDetailDTO;

/**
 * »ó¼¼ dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="maLnGoalDetailDAOTarget"
 * @spring.txbn id="maLnGoalDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaLnGoalDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaLnGoalDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param deptNo
     * @return
     */
    public MaLnGoalDetailDTO findDetail(MaLnGoalCommonDTO maLnGoalCommonDTO, User loginUser)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT                                                                    ");
        query.append("       '' seqNo,                                                          ");
        query.append("       '' isDelCheck,                                                     ");
        query.append("       plant,                                                             ");
        query.append("       SFAPLANTTODESC(comp_no, plant) plantDesc,                   ");
        query.append("       yyyymm,                                                            ");
        query.append("       eqloc_id eqlocId,                                                  ");
        query.append("       (SELECT x.full_desc                                                ");
        query.append("        FROM   TAEQLOC x                                                  ");
        query.append("        WHERE  x.eqloc_id = a.eqloc_id) eqlocIdDesc,                      ");
        query.append("       mtlnpoint mtpoint,                                                           ");
        query.append("       SFACODE_TO_DESC(mtlnpoint,'MTLNPOINT','SYS','','"+loginUser.getLangId()+"') mtpointDesc,             ");
        query.append("       pvalue,                                                            ");
        query.append("       REMARK,                                                            ");
        query.append("       mtLnPoint_id mtLnPointId                                           ");
        query.append("FROM   TAMTLNPOINT a                                                      ");
        query.append("WHERE  1=1                                ");
    	query.getAndQuery("mtLnPoint_id", maLnGoalCommonDTO.getMtLnPointId());
    
        MaLnGoalDetailDTO maLnGoalDetailDTO = 
        		(MaLnGoalDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaLnGoalDetailDTO()));
        
        return maLnGoalDetailDTO;
    }
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalDetailDTO
     * @return
     */
    public int insertDetail(MaLnGoalDetailDTO maLnGoalDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAMTLNPOINT(                                             ");
    	query.append("   comp_no,      mtLnPoint_id,  yyyymm,     eqloc_id,   mtlnpoint,     ");
    	query.append("   pvalue,       remark,        plant                                ");
    	query.append(")VALUES(                                                             ");
    	query.append("     ?,               ?,           ?,            ?,    ?,            ");
    	query.append("     ?,               ?,           ?                                 ");
    	query.append(")                                                                    ");
    	
    	Object[] objects = new Object[] {
    	        loginUser.getCompNo(),
    	        maLnGoalDetailDTO.getMtLnPointId(),
    	        maLnGoalDetailDTO.getYyyymm(),
    	        maLnGoalDetailDTO.getEqlocId(),
    	        maLnGoalDetailDTO.getMtpoint(),
    	        maLnGoalDetailDTO.getPvalue(),
    	        maLnGoalDetailDTO.getRemark(),
    	        maLnGoalDetailDTO.getPlant()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param maLnGoalDetailDTO
     * @return
     */
    public int updateDetail(MaLnGoalDetailDTO maLnGoalDetailDTO, User loginUser)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAMTLNPOINT SET	           ");
        query.append("       pvalue          = ?,          ");
        query.append("       remark          = ?,          ");
        query.append("       mtlnpoint       = ?           ");
    	query.append("WHERE  comp_no         = ?	       ");
    	query.append("    and mtLnPoint_id   = ?	       ");
    	
    	Object[] objects = new Object[] {
    			maLnGoalDetailDTO.getPvalue(),
    			maLnGoalDetailDTO.getRemark(),
    			maLnGoalDetailDTO.getMtpoint(),
    			loginUser.getCompNo(),
    			maLnGoalDetailDTO.getMtLnPointId()
    	
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}