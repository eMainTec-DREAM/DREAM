package dream.asset.loc.list.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
import dream.asset.loc.list.dao.LovEqLocListDAO;
import dream.asset.loc.list.dto.LovEqLocListDTO;

/**
 * 위치분류검색 팝업
 * @author  ssong
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="lovEqLocListDAOTarget"
 * @spring.txbn id="lovEqLocListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class LovEqLocListDAOSqlImpl extends BaseJdbcDaoSupportSql implements LovEqLocListDAO
{
    /**
     * 위치분류
     * @author  ssong
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqLocListDTO
     * @return
     */
    public List findEqLocList(LovEqLocListDTO lovEqLocListDTO, User loginUser)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  				");
        query.append("SELECT a.*, MIN(a.level) OVER() AS MINLVL FROM (        							");
        query.append("SELECT												");
        query.append("		x.eqloc_id AS ID,								");//TreeGrid 출력시 id 필수 
        query.append("		x.eqloc_id               AS EQLOCID,			");
        query.append("		x.eqloc_no               AS EQLOCNO,			");
        query.append("		''						 AS SEQNO,				");
        query.append("		dbo.SFACODE_TO_DESC(x.eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+loginUser.getLangId()+"')	EQLOCLVLTYPE,	");
        query.append("        x.ord_no                    								AS ORDNO,   	");
        query.append("		x.full_desc              AS FULLDESC,			");
        query.append("		x.p_eqloc_id PEQLOCID,							");
        query.append("		y.lvl AS LEVEL,									");
        query.append("		x.is_lowest_lvl ISLOWESTLVL						");
        query.append("FROM TAEQLOC x										");
        query.append("	  ,(SELECT * FROM dbo.SFAEQLOC_ALL('100','0')) y	");
        query.append("WHERE 1=1												");
        query.append("AND 	x.EQLOC_ID = y.EQLOC_ID							");
        query.getAndQuery("x.comp_no", loginUser.getCompNo());
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("x.full_desc", lovEqLocListDTO.getFullDesc());
        query.getSysCdQuery("x.eqloc_lvl_type", lovEqLocListDTO.getEqLocLvlType(), lovEqLocListDTO.getEqLocLvlTypeDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        query.append(" ) a        																		");
        query.append(" ORDER BY a.level asc, ISNULL(a.ORDNO, '99999999')        										");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    /**
     * 위치분류 AC
     * @author  kim21017
     * @version $Id:$
     * @since   1.0
     * 
     * @param lovEqLocListDTO
     * @return
     */
    public List findEqLocAcList(LovEqLocListDTO lovEqLocListDTO, User loginUser, Map<String, String> columnMap,
			Map<String, String> conditionMap) {
    	QuerySqlBuffer query = new QuerySqlBuffer();
    	String compNo = conditionMap.containsKey("comp_no")?conditionMap.get("comp_no"):loginUser.getCompNo();
        
    	query.append("SET TRANSACTION ISOLATION LEVEL READ UNCOMMITTED;                  				");
        query.append("SELECT a.*, MIN(a.level) OVER() AS MINLVL FROM (        							");
        query.append("SELECT                                                                	");
        query.append("        x.eqloc_id AS ID,                                                 ");
        query.append("        x.eqloc_id AS EQLOC_ID,                                           ");
        query.append("        x.eqloc_no  AS EQLOCNO,                                           ");
        query.append("        ROW_NUMBER() OVER (ORDER by y.lvl, x.ord_no)         AS SEQNO,    ");
        query.append("        dbo.SFACODE_TO_DESC(x.eqloc_lvl_type,'EQLOC_LVL_TYPE','SYS','','"+loginUser.getLangId()+"')    EQLOCLVLTYPE,       ");
        query.append("        x.ord_no                    								AS ORDNO,   	");
        query.append("      x.description              AS DESCRIPTION,                                    ");
        query.append("        x.full_desc AS FULL_DESC,                                      	");
        query.append("        x.p_eqloc_id PEQLOCID,                                            ");
    	query.append("        y.lvl as LEVEL,                                            		");
        query.append("        x.is_lowest_lvl    AS ISLOWESTLVL                                	");
        query.append("FROM    TAEQLOC x                                                     	");
        query.append("	  	,(SELECT * FROM dbo.SFAEQLOC_ALL('"+compNo+"','0')) y						");
        query.append("WHERE 1=1																	");
        query.append("AND 	x.EQLOC_ID = y.EQLOC_ID												");
        query.getAndQuery("x.comp_no", compNo);
        query.getAndQuery("x.eqloc_lvl_type", conditionMap);
        query.getAndQuery("x.is_lowest_lvl", conditionMap);
        query.getAndQuery("x.is_use", "Y");
        query.getLikeQuery("x.full_desc", lovEqLocListDTO.getFullDesc());
        query.getSysCdQuery("x.eqloc_lvl_type", lovEqLocListDTO.getEqLocLvlType(), lovEqLocListDTO.getEqLocLvlTypeDesc(), "EQLOC_LVL_TYPE", loginUser.getCompNo(),loginUser.getLangId());
        // 공장코드
        query.getCodeLikeQuery("x.plant", "(SELECT a.description FROM TAPLANT a WHERE a.comp_no = x.comp_no AND a.plant = x.plant )", lovEqLocListDTO.getPlant(), lovEqLocListDTO.getPlantDesc());
        query.append(" ) a        																		");
        query.append(" ORDER BY a.level asc, ISNULL(a.ORDNO, '99999999')        										");
        

        return getJdbcTemplate().queryForList(query.toString());
    }
}