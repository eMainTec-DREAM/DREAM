package dream.invt.prc.dao.sqlImpl;

import java.util.List;
import java.util.Map;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportSql;
import common.util.QuerySqlBuffer;
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
public class InvtPrcPhLovDAOSqlImpl extends BaseJdbcDaoSupportSql implements InvtPrcPhLovDAO
{

    @Override
    public List findTaskMapAcList(InvtPrcPhLovDTO invtPrcPhLovDTO, User loginUser,  Map<String, String> conditionMap)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        String lang = loginUser.getLangId();
        
        query.append("SELECT                                                                                                    ");
        query.append("        dbo.SFACODE_TO_DESC(y.invt_proc_stype,'INVT_PROC_STYPE','USR','"+loginUser.getCompNo()+"','"+lang+"')   invtProcStypeDesc  ");
        query.append("        ,dbo.SFACODE_TO_DESC(y.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+loginUser.getCompNo()+"','"+lang+"')  invtProcLtypeDesc  ");
        query.append("        ,y.invt_proc_stype 															 invtProcStype		");
        query.append("        ,y.invt_proc_ltype 												 			 invtProcLtype      ");
        query.append("        ,y.invtprcph_id                                                                invtprcphId        ");
        query.append("        ,y.invtprctp_id                                                     		 	 invtprctpId        ");
        query.append("        ,y.ref_depart                                                         		 refDepart          ");
        query.append("        ,y.ref_doc 																	 refDoc				");
        query.append("        ,y.doc_prefix 																 docPrefix  		");
        query.append(" FROM  TAINVTPRCTP x INNER JOIN TAINVTPRCPH y                                                             ");
        query.append("       ON x.invtprctp_id = y.invtprctp_id                                                                 ");
        query.append("       AND x.comp_no = y.comp_no                                                                          ");
        query.append("WHERE  1=1                                                                                                ");
        query.getLikeQuery("dbo.SFACODE_TO_DESC(y.invt_proc_ltype,'INVT_PROC_LTYPE','USR','"+loginUser.getCompNo()+"','"+lang+"')||dbo.SFACODE_TO_DESC(y.invt_proc_stype,'INVT_PROC_STYPE','USR','"+loginUser.getCompNo()+"','"+lang+"')", invtPrcPhLovDTO.getDescription());
        query.getAndQuery("y.comp_no", conditionMap);
        query.getAndQuery("y.invtprctp_id", conditionMap);
        query.getAndQuery("y.is_use", conditionMap);
        
        return getJdbcTemplate().queryForList(query.toString());
    }
}