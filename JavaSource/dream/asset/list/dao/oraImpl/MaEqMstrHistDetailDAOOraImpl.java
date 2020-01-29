package dream.asset.list.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.asset.list.dao.MaEqMstrHistDetailDAO;
import dream.asset.list.dto.MaEqMstrHistDetailDTO;

/**
 * 설비변경이력 - 상세 dao
 * 
 * @author kim21017
 * @version $Id: MaEqMstrHistDetailDAO.java,v 1.0 2015/12/02 08:25:47 kim21017 Exp $
 * @since 1.0
 * @spring.bean id="maEqMstrHistDetailDAOTarget"
 * @spring.txbn id="maEqMstrHistDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaEqMstrHistDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaEqMstrHistDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaEqMstrHistDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param equipId
     * @param compNo
     * @return
     */
    public MaEqMstrHistDetailDTO findDetail(String eqalthist_id, User user)
    {
        QueryBuffer query = new QueryBuffer();
        query.append("SELECT															");
        query.append("		x.equip_id									equipId,		");
        query.append("      x.eqalthist_id   					    eqalthistId, 		");
        query.append("		x.item_no									itemNo,			");
        query.append("		x.description								equipDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQLOC												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqloc_id = x.eqloc_id)				eqLocDesc,		");
        query.append("		(SELECT full_desc											");
        query.append("		   FROM TAEQCTG												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND eqctg_id = x.eqctg_id)				eqCtgDesc,		");
        query.append("		SFACODE_TO_DESC(x.eq_status,'EQ_STATUS','SYS','','"+user.getLangId()+"') eqStatusDesc,	");
        query.append("		SFACODE_TO_DESC(y.plf_type,'PLF_TYPE','SYS','','"+user.getLangId()+"')	plfTypeDesc,	");
        query.append("		 y.util_capa								utilCapa,		");
        query.append("		 y.buy_amt									buyAmt,			");
        query.append("		 x.is_law_eq								isLawEq,		");
        query.append("		(SELECT description											");
        query.append("		   FROM TADEPT												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND dept_id = x.dept_id)				deptDesc,		");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.main_mng_id)				mainMngName,	");
        query.append("		(SELECT emp_name											");
        query.append("		   FROM TAEMP												");
        query.append("		  WHERE comp_no = x.comp_no									");
        query.append("		    AND emp_id = x.sub_mng_id)				subMngName,		");
        query.append("		 x.remark									remark,			");
        query.append("		 y.setup_date								setupDate		");
        query.append("FROM   TAEQALTHIST x, TAEQUIPMENT y								");
        query.append("WHERE  x.comp_no = y.comp_no										");
        query.append("  AND  x.equip_id = y.equip_id									");
        query.getAndNumKeyQuery("x.eqalthist_id", eqalthist_id);
        query.getStringEqualQuery("x.comp_no", user.getCompNo());
    
        MaEqMstrHistDetailDTO maEqMstrHistDetailDTO = 
        		(MaEqMstrHistDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new MaEqMstrHistDetailDTO()));
        
        return maEqMstrHistDetailDTO;
    }
}