package dream.asset.list.dao.oraImpl;

import java.util.List;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrHistListDAO;
import dream.asset.list.dto.MaEqMstrHistListDTO;

/**
 * 설비변경이력 - 목록 dao
 * @author  kim21017
 * @version $Id: MaEqMstrHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maEqMstrHistListDAOTarget"
 * @spring.txbn id="maEqMstrHistListDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrHistListDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrHistListDAO
{
    /**
     * grid find
     * @author  kim21017
     * @version $Id: MaEqMstrHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrHistListDTO
     * @return List
     */
    public List findEqMstrHistList(MaEqMstrHistListDTO maEqMstrHistListDTO, User user)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maEqMstrHistListDTO.getCompNo();
        
        query.append("SELECT															");
        query.append("		1											seqNo,			");
        query.append("		x.equip_id									equipId,		");
        query.append("      x.eqalthist_id   					    eqalthistId, 		");
        query.append("		x.item_no									itemNo,			");
        query.append("		x.description								equipDesc,		");
        query.getDate("SUBSTR(x.upd_date,0,8)", "changeDate");
        query.append(",																	");
        query.append("		(SELECT user_name											");
        query.append("		   FROM TAUSER												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND user_id = x.upd_by)					changeByName,	");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQLOC												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqloc_id = x.eqloc_id)				eqLocDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQCTG												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqctg_id = x.eqctg_id)				eqCtgDesc,		");
        query.append("		(SELECT SFACODE_TO_DESC(a.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')		");
        query.append("		   FROM TAEQUIPMENT a										");
        query.append("		  WHERE a.comp_no = x.comp_no								");
        query.append("		    AND a.equip_id = x.equip_id)			plfTypeDesc,	");
        query.append("		 x.is_law_eq								isLawEq,		");
        query.append("		(SELECT description											");
        query.append("		   FROM TADEPT												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND dept_id = x.dept_id)				deptDesc,		");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.main_mng_id)				mainMngDesc,	");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.sub_mng_id)				subMngDesc		");
        query.append("FROM   TAEQALTHIST x												");
        query.append("WHERE  x.comp_no = '"+compNo+"'									");
        query.append(this.getWhere(maEqMstrHistListDTO));
        query.append("ORDER by x.upd_date desc											");
        
        return getJdbcTemplate().queryForList(query.toString());
    }
    
    /**
     * Filter 조건
     * @author  kim21017
     * @version $Id: MaEqMstrHistListDAO.java,v 1.0 2015/12/02 09:14:12 kim21017 Exp $
     * @since   1.0
     * 
     * @param maEqMstrHistListDTO
     * @return
     * @throws Exception
     */
    private String getWhere(MaEqMstrHistListDTO maEqMstrHistListDTO)
    {        
        QueryBuffer query = new QueryBuffer();
        String compNo = maEqMstrHistListDTO.getCompNo();
        query.getAndQuery("comp_no", compNo);
        query.getAndQuery("equip_id", maEqMstrHistListDTO.getEquipId());
        query.getAndDateQuery("upd_date", maEqMstrHistListDTO.getFilterChangeFromDate(), maEqMstrHistListDTO.getFilterChangeToDate());
        return query.toString();
    }
}