package dream.pers.appln.dao.sqlImpl;

import java.util.List;

import common.spring.BaseJdbcDaoSupportSql;
import common.spring.MwareExtractor;
import common.util.QuerySqlBuffer;
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
public class MaAppLineUsrDetailDAOSqlImpl extends BaseJdbcDaoSupportSql implements MaAppLineUsrDetailDAO
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
        QuerySqlBuffer query = new QuerySqlBuffer();
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
        query.append("        ,dbo.SFACODE_TO_DESC(x.apprusr_type,'APPRUSR_TYPE','SYS','','"+maAppLineCommonDTO.getUserLang()+"') apprUsrTypeDesc           ");
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
    	QuerySqlBuffer query = new QuerySqlBuffer();

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
    	
    	return getJdbcTemplate().update(query.toString(), getObject(objects));
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
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
    	query.append("        ,isnull( ?,(select MAX(appr_seq) from TAAPPRLINEUSR where apprline_id=?)+1 ) ");
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
    	QuerySqlBuffer query = new QuerySqlBuffer();
        
		query.append("SELECT count(1)				");
		query.append("  FROM TAAPPRLINEUSR			");
		query.append("WHERE apprlineusr_id!='"+maAppLineUsrDetailDTO.getApprlineusrId()+"'	");
		query.getAndQuery("comp_no", maAppLineCommonDTO.getCompNo());
		query.getAndQuery("apprline_id", maAppLineCommonDTO.getApprlineId());
		query.getAndQuery("appr_seq", maAppLineUsrDetailDTO.getApprSeq());
		
		List resultList = getJdbcTemplate().queryForList(query.toString());

        return QuerySqlBuffer.listToString(resultList);
    }
    
    public String appSeq(MaAppLineUsrDetailDTO maAppLineUsrDetailDTO, MaAppLineCommonDTO maAppLineCommonDTO)
    {
        QuerySqlBuffer query = new QuerySqlBuffer();
        
        query.append("SELECT               ");
        query.append("            MAX(appr_seq)+1           ");
        query.append("FROM TAAPPRLINEUSR          ");
        query.append("WHERE apprline_id='"+maAppLineCommonDTO.getApprlineId()+"'          ");

        
        List resultList = getJdbcTemplate().queryForList(query.toString());

        return QuerySqlBuffer.listToString(resultList);
    }
}