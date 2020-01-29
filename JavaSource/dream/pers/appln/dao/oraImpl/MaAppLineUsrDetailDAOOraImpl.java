package dream.pers.appln.dao.oraImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.pers.appln.dao.MaAppLineUsrDetailDAO;
import dream.pers.appln.dto.MaAppLineCommonDTO;
import dream.pers.appln.dto.MaAppLineUsrDetailDTO;
import dream.pers.appln.dto.MaAppLineUsrListDTO;

/**
 *  »ó¼¼ dao
 * @author  kim21017
 * @version $Id: MaAppLineUsrDetailDAO.java,v 1.0 2015/12/04 08:10:27 kim21017 Exp $
 * @since   1.0
 * @spring.bean id="maAppLineUsrDetailDAOTarget"
 * @spring.txbn id="maAppLineUsrDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class MaAppLineUsrDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements MaAppLineUsrDetailDAO
{
    /**
     * detail find
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrListDTO
     * @param maAppLineCommonDTO
     * @return
     */
    public MaAppLineUsrDetailDTO findDetail(MaAppLineUsrListDTO maAppLineUsrListDTO, MaAppLineCommonDTO maAppLineCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        String compNo = maAppLineCommonDTO.getCompNo();
        
        query.append("SELECT                                            ");
        query.append("        '' seqNo                                  ");
        query.append("        ,'' isDelCheck                            ");
        query.append("        ,x.appr_seq apprSeq                       ");
        query.append("        ,x.appr_by apprBy                         ");
        query.append("        ,y.emp_name apprByName                    ");
        query.append("        ,y.grade                                  ");
        query.append("        ,(SELECT aa.description                        ");
        query.append("          FROM   TADEPT aa                             ");
        query.append("          WHERE  aa.comp_no = y.comp_No                ");
        query.append("              and  aa.dept_id = y.dept_id) deptName    ");
        query.append("        ,x.apprlineusr_id apprlineusrId           ");
        query.append("        ,x.apprline_id apprlineId                 ");
        query.append("        ,x.apprusr_type apprUsrTypeId             ");
        query.append("        ,SFACODE_TO_DESC(x.apprusr_type,'APPRUSR_TYPE','SYS','','"+maAppLineCommonDTO.getUserLang()+"') apprUsrTypeDesc           ");
        query.append("FROM TAAPPRLINEUSR x, TAEMP y                     ");
        query.append("WHERE x.appr_by = y.emp_id                        ");
        query.append("    and  x.comp_no = y.comp_no                    ");
        query.append("    and  x.comp_no = ?                            ");
        query.append("    and  x.apprlineusr_id = ?                     ");
        
        Object[] objects = new Object[] {
        		maAppLineCommonDTO.getCompNo()
    	        ,maAppLineUsrListDTO.getApprlineusrId()
    	};
    
        MaAppLineUsrDetailDTO maAppLineUsrDetailDTO1 = 
        		(MaAppLineUsrDetailDTO)getJdbcTemplate().query(query.toString(),objects, new MwareExtractor(new MaAppLineUsrDetailDTO()));
        
        return maAppLineUsrDetailDTO1;
    }
    /**
     * detail update
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailDTO
     * @param maAppLineCommonDTO
     * @return
     */
    public int updateDetail(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAAPPRLINEUSR SET				  ");
    	query.append("	appr_seq				= ? 		  ");
    	query.append("  ,appr_by                 = ?           ");
    	query.append("  ,apprusr_type            = ?           ");
    	query.append("WHERE apprlineusr_id 		= ?		      ");
    	
    	Object[] objects = new Object[] {
    	        maAppLineUsrDetailDTO.getApprSeq()
    	        ,maAppLineUsrDetailDTO.getApprBy()
    	        ,maAppLineUsrDetailDTO.getApprUsrTypeId()
    	        ,maAppLineUsrDetailDTO.getApprlineusrId()
    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail insert
     * @author kim21017
     * @version $Id: MaAppLineUsrDetailDAO.java,v 1.0 20155/12/02 08:25:47 kim21017 Exp $
     * @since   1.0
     * 
     * @param maAppLineUsrDetailDTO
     * @param maAppLineCommonDTO
     * @return
     */
    public int insertDetail(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
    	int rtnValue  = 0;
    	
    	query.append("INSERT INTO TAAPPRLINEUSR (							 ");
    	query.append("	        comp_no						                 ");
    	query.append("         ,apprlineusr_id                               ");
    	query.append("         ,apprline_id                                  ");
    	query.append("	       ,appr_seq	                           		 ");
    	query.append("         ,appr_by                                      ");
    	query.append("         ,apprusr_type                                 ");
    	query.append(")VALUES(						     	                 ");
    	query.append("	       ?                                             ");
    	query.append("        ,?                                             ");
    	query.append("        ,?                                             ");
    	query.append("        ,NVL( ?,(select MAX(appr_seq) from TAAPPRLINEUSR where apprline_id=?)+1 ) ");
    	query.append("        ,?                                             ");
    	query.append("        ,?                                             ");
    	query.append("	)												     ");
    	
    	Object[] objects = new Object[] {
    			maAppLineCommonDTO.getCompNo()
    			,maAppLineUsrDetailDTO.getApprlineusrId()
    			,maAppLineCommonDTO.getApprlineId()
    			,maAppLineUsrDetailDTO.getApprSeq()
    			,maAppLineCommonDTO.getApprlineId()
    			,maAppLineUsrDetailDTO.getApprBy()
    			,maAppLineUsrDetailDTO.getApprUsrTypeId()
    	};
    	
    	rtnValue =  getJdbcTemplate().update(query.toString(), objects);
    	
    	return rtnValue;
    }
    
    public String checkAppSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO)
    {
    	QueryBuffer query = new QueryBuffer();
        
		query.append("SELECT count(1)				");
		query.append("  FROM TAAPPRLINEUSR			");
		query.append("WHERE apprlineusr_id!='"+maAppLineUsrDetailDTO.getApprlineusrId()+"'	");
		query.getAndQuery("comp_no", maAppLineCommonDTO.getCompNo());
		query.getAndQuery("apprline_id", maAppLineCommonDTO.getApprlineId());
		query.getAndQuery("appr_seq", maAppLineUsrDetailDTO.getApprSeq());
		
		List resultList = getJdbcTemplate().queryForList(query.toString());

        return QueryBuffer.listToString(resultList);
    }
    
    public String appSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO)
    {
        QueryBuffer query = new QueryBuffer();
        
        query.append("SELECT               ");
        query.append("            MAX(appr_seq)+1           ");
        query.append("FROM TAAPPRLINEUSR          ");
        query.append("WHERE apprline_id='"+maAppLineCommonDTO.getApprlineId()+"'          ");

        
        List resultList = getJdbcTemplate().queryForList(query.toString());

        return QueryBuffer.listToString(resultList);
    }
}