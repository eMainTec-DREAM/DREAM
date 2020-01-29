package dream.asset.categ.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;

import dream.asset.categ.list.dao.LovEqCtgListDAO;
import dream.asset.categ.list.dto.LovEqCtgListDTO;

/**
 * 설비종류검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEqCtgListDAOTarget"
 * @spring.txbn id="lovEqCtgListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqCtgListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEqCtgListDAO
{
    /**
     * 설비종류
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqCtgListDTO
     * @param loginUser
     * @return
     */
    public List findEqCtgList(LovEqCtgListDTO lovEqCtgListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT													");
        query.append("      x.eqctg_id 						AS ID,				");  //TreeGrid 출력시 id 필수 
        query.append("		x.eqctg_id						AS EQCTGID,			");
        query.append("		x.eqctg_no						AS EQCTGNO,			");
        query.append("		''							 	AS SEQNO,			");
        query.append("		x.lvl							AS LVL,				");
        query.append("      x.mng_cd                		AS mngcd,			");
        query.append("		x.full_desc						AS FULLDESC,		");
        query.append("		x.p_eqctg_id 					AS PEQCTGID,		");
        query.append("		MIN(y.lvl) OVER( ORDER BY ISNULL(x.ord_no, '99999999')) AS MINLVL,		");
        query.append("		y.lvl 							AS LEVEL,			");
        query.append("		x.is_lowest_lvl					AS ISLOWESTLVL		");
        query.append("FROM TAEQCtg x											");
        query.append("	  ,(SELECT * FROM dbo.SFAEQCTG_ALL('"+loginUser.getCompNo()+"','0')) y		");
        query.append("WHERE 1=1													");
        query.append("AND 	x.EQCTG_ID = y.EQCTG_ID								");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("x.lvl", lovEqCtgListDTO.getLvl());
        query.getLikeQuery("x.full_desc", lovEqCtgListDTO.getFullDesc());
        query.append("ORDER BY x.eqctg_id										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }

	@Override
	public List findEqCtgAcList(LovEqCtgListDTO lovEqCtgListDTO, User user, Map<String, String> conditionMap) {
		QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("With CTE(id, EQCTG_ID, EQCTG_NO, SEQNO, MNGCD, FULL_DESC, description, p_eqctg_id,lvl, ISLOWESTLVL)      ");
        query.append("As        															");
        query.append("(         															");
        query.append(" SELECT                                                           	");
        query.append("      x.eqctg_id                         	AS ID,                     	");
        query.append("        x.eqctg_id                        AS EQCTG_ID,                ");
        query.append("        x.eqctg_no                        AS EQCTG_NO,                ");
        query.append("        '' 								AS SEQNO,          			");
        query.append("        x.mng_cd                		    AS MNGCD,					");
        query.append("        x.full_desc                     	AS FULL_DESC,              	");
        query.append("        x.description                    	AS DESCRIPTION,            	");
        query.append("        x.p_eqctg_id                     	AS P_EQCTG_ID,       		");
        query.append("        y.LVL                            	AS LVL,              		");
        query.append("        x.is_lowest_lvl                  	AS ISLOWESTLVL             	");
        query.append("FROM TAEQCtg x inner join (SELECT * FROM dbo.SFAEQCTG_ALL('"+user.getCompNo()+"','0')) y           ");
        query.append("on x.EQCTG_ID = y.EQCTG_ID                                          	");
        query.append("WHERE 1=1                                                         	");
        query.getAndQuery("x.comp_no", user.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("x.lvl", lovEqCtgListDTO.getLvl());
        query.getLikeQuery("x.full_desc", lovEqCtgListDTO.getFullDesc());
        query.append(" Union All                                                       		");
        query.append(" SELECT                                                           	");
        query.append("      x.eqctg_id                         	AS ID,                     	");
        query.append("        x.eqctg_id                        AS EQCTG_ID,                ");
        query.append("        x.eqctg_no                        AS EQCTG_NO,                ");
        query.append("        '' 								AS SEQNO,          			");
        query.append("        x.mng_cd                		    AS MNGCD,					");
        query.append("        x.full_desc                      	AS FULL_DESC,              	");
        query.append("        x.description                    	AS DESCRIPTION,            	");
        query.append("        x.p_eqctg_id                     	AS P_EQCTG_ID,           	");
        query.append("        y.LVL                            	AS LVL,          			");
        query.append("        x.is_lowest_lvl                  	AS ISLOWESTLVL             	");
        query.append("FROM TAEQCtg x inner join (SELECT * FROM dbo.SFAEQCTG_ALL('"+user.getCompNo()+"','0')) y           ");
        query.append("on x.EQCTG_ID = y.EQCTG_ID                                      		");
        query.append("inner join CTE z                                    	             	");
        query.append("on x.eqctg_id=z.p_eqctg_id        									");
        query.append(")             									        			");
        query.append("Select distinct id, EQCTG_ID, EQCTG_NO, FULL_DESC, description, p_eqctg_id, MIN(lvl) OVER( ORDER BY lvl) AS minLvl, lvl as LEVEL, ISLOWESTLVL      ");
        query.append("From  CTE                     										");
        query.append("order by full_desc                    								");

		
        return getJdbcTemplate().queryForList(query.toString());
	}
}