package dream.fail.library.dao.oraImpl;

import common.bean.User;
import common.spring.BaseJdbcDaoSupportOra;
import common.spring.MwareExtractor;
import common.util.QueryBuffer;
import dream.fail.library.dao.FailLibraryDetailDAO;
import dream.fail.library.dto.FailLibraryDetailDTO;

/**
 * 고장library - 상세 dao
 * 
 * @author 
 * @version $Id:  $
 * @since 1.0
 * @spring.bean id="failLibraryDetailDAOTarget"
 * @spring.txbn id="failLibraryDetailDAO"
 * @spring.property name="dataSource" ref="dataSource"
 */
public class FailLibraryDetailDAOOraImpl extends BaseJdbcDaoSupportOra implements FailLibraryDetailDAO
{
    /**
     * detail find
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param compNo
     * @param failureId
     * @return
     */
    public FailLibraryDetailDTO findDetail(User user, String failureId)
    {
        QueryBuffer query = new QueryBuffer();
        String lang = user.getLangId();
        query.append("SELECT                                                            ");
        query.append("       ''                                       seqNo,           	");
        query.append("       ''                                       isDelCheck,      	");
        query.append("       x.comp_no                                compNo,          	");
        query.append("       x.failsetlist_id                         failsetlistId,	");
        query.append("       x.eqctg_id                               eqctgId,	");
        query.append("       x.eqctg_no                               eqctgNo,        	");
        query.append("       (SELECT a.description FROM TAEQCTG a   WHERE a.eqctg_id = x.eqctg_id AND a.comp_no = x.comp_no) eqctgDesc,            	");
        query.append("       x.failure_pt_id failurePtId,	");
        query.append("       x.failure_pt_no failurePtNo,                            	");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='PT' AND a.failure_id = x.failure_pt_id) failurePtDesc,    	");
        query.append("       x.failure_mo_id failureMoId,	");
        query.append("       x.failure_mo_no failureMoNo,                            	");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='MO' AND a.failure_id = x.failure_mo_id) failureMoDesc,    	");
        query.append("       x.failure_ca_id failureCaId,	");
        query.append("       x.failure_ca_no failureCaNo,                            	");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='CA' AND a.failure_id = x.failure_ca_id) failureCaDesc,    	");
        query.append("       x.failure_re_id failureReId,	");
        query.append("       x.failure_re_no failureReNo,                            	");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='RE' AND a.failure_id = x.failure_re_id) failureReDesc,    	");
        query.append("       x.failure_pm_id failurePmId,	");
        query.append("       x.failure_pm_no failurePmNo,                            	");
        query.append("       (SELECT a.description FROM TAFAILURE a WHERE a.fail_type ='PM' AND a.failure_id = x.failure_pm_id) failurePmDesc,    	");
        query.append("       is_use isUse,												");
        query.append("       check_yn checkYn                                           ");
        query.append("FROM   tafailsetlist x                                           	");
        query.append("WHERE  x.comp_no    = '"+user.getCompNo()+"'              		");
        query.append("  AND  x.failsetlist_id = '"+failureId+"'	                    	");
    
        FailLibraryDetailDTO failLibraryDetailDTO = 
        		(FailLibraryDetailDTO)getJdbcTemplate().query(query.toString(), new MwareExtractor(new FailLibraryDetailDTO()));
        
        return failLibraryDetailDTO;
    }
    
    /**
     * detail insert
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     */
    public int insertDetail(FailLibraryDetailDTO failLibraryDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("INSERT INTO TAFAILSETLIST x(                                    					");
    	query.append("   comp_no,      		failsetlist_id,  	eqctg_id,   		eqctg_no,    		");
    	query.append("   failure_pt_id,    	failure_pt_no,      failure_mo_id,    	failure_mo_no,  	");
    	query.append("   failure_ca_id,     failure_ca_no,   	failure_re_id,     	failure_re_no,		");
    	query.append("   failure_pm_id,     failure_pm_no,      is_use,     		cre_date,			");
    	query.append("   check_yn                       												");
    	query.append(")VALUES(                                                    						");
    	query.append("     ?,               ?,           		?,            		?,              	");
    	query.append("     ?,               ?,            		?,           		?,               	");
    	query.append("     ?,               ?,            		?,           		?,              	");
    	query.append("     ?,               ?,            		?,           		to_char(sysdate,'yyyymmdd'),              	");
    	query.append("     ?                      														");
    	query.append(")                                                            						");

    	
    	Object[] objects = new Object[] {
    			failLibraryDetailDTO.getCompNo(),
    			failLibraryDetailDTO.getFailsetlistId(),
    			failLibraryDetailDTO.getEqctgId(),
    			failLibraryDetailDTO.getEqctgNo(),
    			failLibraryDetailDTO.getFailurePtId(),
    			failLibraryDetailDTO.getFailurePtNo(),
    			failLibraryDetailDTO.getFailureMoId(),
    			failLibraryDetailDTO.getFailureMoNo(),
    			failLibraryDetailDTO.getFailureCaId(),
    			failLibraryDetailDTO.getFailureCaNo(),
    			failLibraryDetailDTO.getFailureReId(),
    			failLibraryDetailDTO.getFailureReNo(),
    			failLibraryDetailDTO.getFailurePmId(),
    			failLibraryDetailDTO.getFailurePmNo(),
    			failLibraryDetailDTO.getIsUse(),
    			failLibraryDetailDTO.getCheckYn()
    	};
    	return getJdbcTemplate().update(query.toString(), objects);
    }
    
    /**
     * detail update
     * @author 
     * @version $Id: $
     * @since   1.0
     * 
     * @param failLibraryDetailDTO
     * @return
     */
    public int updateDetail(FailLibraryDetailDTO  failLibraryDetailDTO)
    {
    	QueryBuffer query = new QueryBuffer();

    	query.append("UPDATE TAFAILSETLIST x SET            	");
    	query.append("       eqctg_id    			= ?,   	");
    	query.append("       eqctg_no      			= ?,   	");
    	query.append("       failure_pt_id         	= ?,   	");
    	query.append("       failure_pt_no         	= ?,   	");
    	query.append("       failure_mo_id         	= ?,   	");
    	query.append("       failure_mo_no       	= ?,   	");
    	query.append("       failure_ca_id         	= ?,	");
    	query.append("       failure_ca_no        	= ?,	");
    	query.append("       failure_re_id        	= ?,	");
    	query.append("       failure_re_no        	= ?,	");
    	query.append("       failure_pm_id        	= ?,	");
    	query.append("       failure_pm_no        	= ?,	");
    	query.append("       is_use       			= ?,	");
    	query.append("       check_yn     			= ?     ");
    	query.append("WHERE  comp_no        		= ?     ");
    	query.append("  AND  failsetlist_id     	= ?     ");

    	
    	Object[] objects = new Object[] {
    			failLibraryDetailDTO.getEqctgId(),
    			failLibraryDetailDTO.getEqctgNo(),
    			failLibraryDetailDTO.getFailurePtId(),
    			failLibraryDetailDTO.getFailurePtNo(),
    			failLibraryDetailDTO.getFailureMoId(),
    			failLibraryDetailDTO.getFailureMoNo(),
    			failLibraryDetailDTO.getFailureCaId(),
    			failLibraryDetailDTO.getFailureCaNo(),
    			failLibraryDetailDTO.getFailureReId(),
    			failLibraryDetailDTO.getFailureReNo(),
    			failLibraryDetailDTO.getFailurePmId(),
    			failLibraryDetailDTO.getFailurePmNo(),
    			failLibraryDetailDTO.getIsUse(),
    			failLibraryDetailDTO.getCheckYn(),
    			failLibraryDetailDTO.getCompNo(),
    			failLibraryDetailDTO.getFailsetlistId()

    	};
    	
    	return getJdbcTemplate().update(query.toString(), objects);
    }
}