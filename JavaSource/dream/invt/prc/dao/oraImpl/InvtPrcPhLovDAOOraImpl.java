package dream.invt.prc.dao.oraImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.util.QueryBuffer;
import dream.invt.prc.dao.InvtPrcPhLovDAO;
import dream.invt.prc.dto.InvtPrcPhLovDTO;

/**
 * 구매절차 소분류 팝업
 * @author  ghlee
 * @version $Id:$
 * @since   1.0
 * @spring.bean id="invtPrcPhLovDAOTarget"
 * @spring.txbn id="invtPrcPhLovDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class InvtPrcPhLovDAOOraImpl extends BaseJdbcDaoSupportOra implements InvtPrcPhLovDAO
{

    @Override
    public List findTaskMapAcList(InvtPrcPhLovDTO invtPrcPhLovDTO, User loginUser,  Map<String, String> conditionMap)
    {
        QueryBuffer query = new QueryBuffer();
        
        String lang = loginUser.getLangId();

        query.append("SELECT                                                                                                   ");
        query.append("        SFACODE_TO_DESC(y.invt_proc_stype,'INVT_PROC_STYPE','USR','"+loginUser.getCompNo()+"','"+lang+"')  	invtProcStypeDesc  ");
        query.append("        ,SFACODE_TO_DESC(y.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+loginUser.getCompNo()+"','"+lang+"')  	invtProcLtypeDesc  ");
        query.append("        ,y.invt_proc_stype 															invtProcStype      ");
        query.append("        ,y.invt_proc_ltype                                              				invtProcLtype      ");
        query.append("        ,y.invtprcph_id                                                   		    invtprcphId        ");
        query.append("        ,y.invtprctp_id 																invtprctpId 	   ");
        query.append("        ,y.ref_depart 																refDepart		   ");
        query.append("        ,y.ref_doc 																	refDoc			   ");
        query.append("        ,y.doc_prefix 																docPrefix 		   ");
        query.append(" FROM  TAINVTPRCTP x INNER JOIN TAINVTPRCPH y                                                            ");
        query.append("       ON x.invtprctp_id = y.invtprctp_id                                                                ");
        query.append("       AND x.comp_no = y.comp_no                                                                         ");
        query.append("WHERE  1=1                                                                                               ");
        query.getLikeQuery("SFACODE_TO_DESC(y.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+loginUser.getCompNo()+"','"+lang+"')||SFACODE_TO_DESC(y.invt_proc_stype,'INVT_PROC_STYPE','USR','','"+lang+"')", invtPrcPhLovDTO.getDescription());
        query.getAndQuery("y.comp_no", conditionMap);
        query.getAndQuery("y.invtprctp_id", conditionMap);
        query.getAndQuery("y.is_use", conditionMap);
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}